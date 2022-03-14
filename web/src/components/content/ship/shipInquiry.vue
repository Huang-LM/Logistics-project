<template>
  <div class="shipInquiry">
    <!-- 搜索 -->
    <el-form :model="queryInfo" ref="queryInfo">
      <el-form-item>
        <el-input
          v-model="queryInfo.logistics_number"
          style="width: 250px"
          auto-complete="new-password"
          placeholder="请输入物流编号"
          @clear="resData"
          clearable
        ></el-input>
        <el-button
          type="primary"
          v-waves
          @click="selectLogistics"
          icon="el-icon-search"
          class="search-btn"
        >
          查询物流信息
        </el-button>
        <el-button v-waves @click="resData" icon="el-icon-refresh">
          重置
        </el-button>
      </el-form-item>
    </el-form>

    <!-- 展示 -->
    <el-table :data="logisticsData" style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="table-expand">
            <el-form-item label="物流状态">
              <el-tag type="info" v-if="props.row.verify === 0">待审核</el-tag>
              <el-tag type="success" v-else-if="props.row.verify === 1"
                >审核通过</el-tag
              >
              <el-tag type="danger" v-else-if="props.row.verify === 2"
                >未通过审核</el-tag
              >
            </el-form-item>
            <el-form-item label="物流编号">
              <span>{{ props.row.logistics_number }}</span>
            </el-form-item>
            <el-form-item label="运输方式">
              <span>{{ props.row.logistics_way }}</span>
            </el-form-item>
            <el-form-item label="运输工具编号">
              <span v-if="props.row.logistics_way_number === null"
                >暂未安排</span
              >
              <span v-else>{{ props.row.logistics_way_number }}</span>
            </el-form-item>
            <el-form-item label="预计到达时间">
              <span v-if="props.row.shipping_time === 'Invalid date'"
                >暂未生成</span
              >
              <span v-else>{{ props.row.shipping_time }}</span>
            </el-form-item>
            <el-form-item label="货代公司信息">
              <span
                >货代公司名称：{{ props.row.mailing_name }} ， 手机号：
                {{ props.row.mailing_phone }} ， 地址：
                {{ props.row.mailing_address }}</span
              >
            </el-form-item>
            <el-form-item label="收件人信息">
              <span
                >姓名：{{ props.row.shipping_name }} ， 手机号：
                {{ props.row.shipping_phone }} ， 地址：
                {{ props.row.shipping_address }}</span
              >
            </el-form-item>
            <el-form-item label="备注">
              <span>{{ props.row.note }}</span>
            </el-form-item>
            <el-form-item label="订单创建时间">
              <span>{{ props.row.createTime }}</span>
            </el-form-item>
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="物流编号" prop="logistics_number">
      </el-table-column>
      <el-table-column label="物流状态">
        <template v-slot="props">
          <el-tag type="info" v-if="props.row.verify === 0">待审核</el-tag>
          <el-tag type="success" v-else-if="props.row.verify === 1"
            >审核通过</el-tag
          >
          <el-tag type="danger" v-else-if="props.row.verify === 2"
            >未通过审核</el-tag
          >
        </template>
      </el-table-column>
      <el-table-column label="预计到达时间" sortable prop="shipping_time">
        <template v-slot="props"
          ><span v-if="props.row.shipping_time === 'Invalid date'">未知</span>
          <span v-else>{{ props.row.shipping_time }}</span></template
        >
      </el-table-column>
      <el-table-column label="操作" min-width="80" fixed="right">
        <template slot-scope="scope">
          <el-button
            type="primary"
            plain
            v-waves
            size="mini"
            @click="handleEdit(scope.$index, scope.row)"
            >查看当前状况</el-button
          >
        </template>
      </el-table-column>
    </el-table>

    <!-- 状态 -->
    <el-dialog
      :visible.sync="dialogFormVisible"
      :append-to-body="true"
      :close="dialogClose"
      width="40%"
    >
      <el-timeline v-if="state">
        <el-timeline-item
          v-for="(state, index) in state"
          :key="index"
          :timestamp="state.createTime"
        >
          <el-card>
            <div slot="header">
              <span>{{ state.state }}</span>
            </div>
            <br />
            <p>{{ state.state_info }}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
      <div class="noState" v-else>还没有消息</div>
    </el-dialog>
  </div>
</template>

<script>
import service from "@/utils/request";
import waves from "@/directive/waves";

export default {
  name: "shipInquiry",
  directives: { waves },
  created() {
    this.getLogistics();
  },

  data() {
    return {
      queryInfo: { logistics_number: "" },
      user_id: "",
      dialogFormVisible: false,
      reverse: true,
      logisticsData: [
        {
          id: "",
          verify: "",
          logistics_number: "",
          logistics_way: "",
          logistics_way_number: "",
          mailing_type: "",
          mailing_name: "",
          mailing_phone: "",
          mailing_address: "",
          shipping_name: "",
          shipping_phone: "",
          shipping_address: "",
          note: "",
          shipping_time: "",
          createTime: "",
          updateTime: ""
        }
      ],
      state: []
    };
  },

  methods: {
    // 查询物流信息
    getLogistics() {
      this.user_id = this.$store.state.account.userID;
      service
        .post("/logisticsInfo/getLogistics", {
          user_id: this.user_id
        })
        .then(res => {
          // console.log(res.data.data);
          this.logisticsData = res.data.data;
        })
        .catch(err => {
          console.log(err);
          this.$message.error("网络错误！");
        });
    },
    selectLogistics() {
      service
        .post("/logisticsInfo/getLogistics", {
          user_id: null,
          logistics_number: this.queryInfo.logistics_number
        })
        .then(res => {
          // console.log(res.data.data);
          this.logisticsData = [];

          if (res.data.data.length !== 0) {
            this.logisticsData.push(res.data.data);
          }
        })
        .catch(err => {
          console.log(err);
          this.$message.error("网络错误！");
        });
    },

    // 刷新物流信息
    resData() {
      this.queryInfo.logistics_number = "";
      this.getLogistics();
    },
    //跳转至查看页面
    handleEdit(index, row) {
      service.post("/logisticsInfo/state", { id: row.id }).then(res => {
        this.state = res.data.data;
        // console.log(res.data);
      });
      this.dialogFormVisible = true;
    },
    // 对话框关闭
    dialogClose() {
      this.state = [];
      this.dialogFormVisible = false;
    }
  }
};
</script>
<style scoped>
.search-btn {
  margin-left: 10px;
}
.table-expand {
  font-size: 0;
}
.table-expand label {
  width: 90px;
  color: #99a9bf;
}
.table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
</style>
