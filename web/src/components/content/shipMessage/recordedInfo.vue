<template>
  <div class="recordedInfo">
    <!-- 搜索 -->
    <el-form :model="queryInfo" ref="queryInfo">
      <el-form-item>
        <!-- <el-input
          v-model="queryInfo.id"
          style="width: 80px"
          auto-complete="new-password"
          placeholder="id"
          clearable
          class="search-input"
          @clear="getGoodsList"
        ></el-input> -->
        <el-input
          v-model="queryInfo.phone"
          style="width: 200px"
          placeholder="请输入寄件人手机号"
          clearable
          class="search-input"
          @clear="getGoodsList"
        ></el-input>
        <el-input
          v-model="queryInfo.logistics_number"
          style="width: 200px"
          placeholder="请输入物流编号"
          clearable
          class="search-input"
          @clear="getGoodsList"
        ></el-input>
        <el-button
          type="primary"
          v-waves
          @click="this.selectGoodsList"
          icon="el-icon-search"
          class="search-btn"
        >
          查询
        </el-button>
      </el-form-item>
    </el-form>
    <!-- 搜索结果 -->
    <el-table :data="logisticsData" style="width: 100%;" stripe>
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
              <span v-if="props.row.shipping_time === null">暂未生成</span>
              <span v-else>{{ props.row.shipping_time }}</span>
            </el-form-item>
            <el-form-item label="寄件人信息">
              <span
                >姓名：{{ props.row.mailing_name }} ， 手机号：
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
      <el-table-column label="ID" prop="id"> </el-table-column>
      <el-table-column
        label="物流编号"
        prop="logistics_number"
        min-width="120px"
      >
      </el-table-column>
      <el-table-column label="物流状态" min-width="100px">
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
      <!-- <el-table-column
        label="寄件地址"
        prop="mailing_address"
        min-width="120px"
      >
      </el-table-column>
      <el-table-column
        label="收件地址"
        prop="shipping_address"
        min-width="120px"
      >
      </el-table-column> -->
      <el-table-column
        label="预计到达时间"
        sortable
        prop="shipping_time"
        min-width="170px"
      >
        <template v-slot="props"
          ><span v-if="props.row.shipping_time === null">未知</span>
          <span v-else>{{ props.row.shipping_time }}</span></template
        >
      </el-table-column>
      <el-table-column
        prop="createTime"
        label="创建时间"
        sortable
        min-width="170"
      >
      </el-table-column>
      <el-table-column
        prop="updateTime"
        label="修改时间"
        sortable
        min-width="170"
      >
      </el-table-column>
      <el-table-column label="操作" min-width="220" fixed="right">
        <template slot-scope="scope">
          <el-button
            type="primary"
            v-waves
            size="mini"
            @click="handleEdit(scope.$index, scope.row)"
            >记录状态</el-button
          >
          <el-button
            type="primary"
            plain
            v-waves
            size="mini"
            @click="handleEditTime(scope.$index, scope.row)"
            >填写时间</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页 -->
    <el-pagination
      layout="sizes, prev, pager, next, total"
      @current-change="handleCurrentChange"
      @size-change="handleSizeChange"
      :current-page="queryInfo.page + 1"
      :page-sizes="[5, 10, 15, 20]"
      :page-size="queryInfo.pageSize"
      :total="total"
      class="pagination"
    >
    </el-pagination>

    <!-- 状态 -->
    <el-dialog
      :visible.sync="dialogFormVisible"
      :append-to-body="true"
      :close="dialogClose"
      width="40%"
    >
      <el-timeline>
        <el-timeline-item
          v-for="(state, index) in state"
          :key="index"
          :timestamp="state.createTime"
        >
          <el-card>
            <div slot="header">
              <span>{{ state.state }}</span>
            </div>
            <p>{{ state.state_info }}</p>
          </el-card>
        </el-timeline-item>
      </el-timeline>
      <el-select v-model="newState.state_id" placeholder="请选择">
        <el-option
          v-for="item in options"
          :key="item.id"
          :label="item.state"
          :value="item.id"
        >
        </el-option>
      </el-select>
      <el-button type="primary" @click="updateState">新增</el-button>
    </el-dialog>

    <!-- 到达时间 -->
    <el-dialog
      title="到达时间"
      :visible.sync="dialogFormVisible2"
      :append-to-body="true"
    >
      <el-form
        :model="updateLogisticsForm"
        ref="updateLogisticsForm"
        label-width="140px"
      >
        <el-form-item label="预计到达时间" prop="shipping_time">
          <el-date-picker
            v-model="updateLogisticsForm.shipping_time"
            type="datetime"
            placeholder="选择日期时间"
            value-format="yyyy-MM-dd HH:mm:ss"
            align="right"
            :picker-options="pickerOptions"
          >
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button plain @click="resetForm('updateLogisticsForm')" v-waves
          >取消</el-button
        >
        <el-button
          type="primary"
          @click="updateTime('updateLogisticsForm')"
          v-waves
          >确认</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import service from "@/utils/request";
import waves from "@/directive/waves";

export default {
  name: "recordedInfo",
  directives: { waves },
  created() {
    this.getGoodsList();
    this.getStateOptions();
  },
  data() {
    return {
      queryInfo: {
        // 查询参数
        id: "",
        mailing_phone: "",
        logistics_number: "",
        // shipping_address: "",
        // 当前页数
        page: 0,
        // 当前显示多少行
        pageSize: 5
      },
      updateLogisticsForm: {
        id: "",
        shipping_time: ""
      },
      total: 0,
      state: [],
      logisticsData: [],
      formLabelWidth: "150px",
      dialogFormVisible: false,
      dialogFormVisible2: false,
      // 获取state都有啥
      options: [],
      newState: {
        state_id: "",
        logistics_id: ""
      },
      pickerOptions: {
        shortcuts: [
          {
            text: "今天",
            onClick(picker) {
              picker.$emit("pick", new Date());
            }
          },
          {
            text: "明天",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() + 3600 * 1000 * 24);
              picker.$emit("pick", date);
            }
          },
          {
            text: "一周后",
            onClick(picker) {
              const date = new Date();
              date.setTime(date.getTime() + 3600 * 1000 * 24 * 7);
              picker.$emit("pick", date);
            }
          }
        ]
      }
    };
  },

  methods: {
    getGoodsList() {
      service
        .post("/logisticsInfo/listLogistics", this.queryInfo)
        .then(res => {
          // console.log(res);
          const resData = res.data.data;
          // console.log(resData.records);
          this.logisticsData = resData.list;
          this.total = resData.total;
        })
        .catch(err => {
          console.log(err);
          this.$message.error("网络异常");
        });
    },
    selectGoodsList() {
      service
        .post("/logisticsInfo/getLogistics", {
          user_id: null,
          logistics_number: this.queryInfo.logistics_number
        })
        .then(res => {
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
    //跳转至编辑用户
    handleEdit(index, row) {
      this.newState.logistics_id = row.logistics_id;
      service
        .post("/logisticsInfo/state", { id: row.logistics_id })
        .then(res => {
          this.state = res.data;
        });
      this.dialogFormVisible = true;
    },
    handleEditTime(index, row) {
      this.updateLogisticsForm = row;
      this.dialogFormVisible2 = true;
    },
    // 获取当前时间
    getTime() {
      var date = new Date();
      var seperator1 = "-";
      var seperator2 = ":";
      var month = date.getMonth() + 1;
      var strDate = date.getDate();
      if (month >= 1 && month <= 9) {
        month = "0" + month;
      }
      if (strDate >= 0 && strDate <= 9) {
        strDate = "0" + strDate;
      }
      var currentdate =
        date.getFullYear() +
        seperator1 +
        month +
        seperator1 +
        strDate +
        " " +
        date.getHours() +
        seperator2 +
        date.getMinutes() +
        seperator2 +
        date.getSeconds();

      this.newState.create_time = currentdate;
    },
    // 换页
    handleCurrentChange(val) {
      this.queryInfo.page = val - 1;
      this.getGoodsList();
    },
    handleSizeChange(val) {
      this.queryInfo.pageSize = val;
      this.getGoodsList();
    },
    // 获取所有state状态
    getStateOptions() {
      service
        .get("/logisticsInfo/allState")
        .then(res => {
          this.options = res.data.data;
        })
        .catch(err => {
          console.log(err);
        });
    },
    // 预计到达时间
    updateTime() {
      // console.log(this.updateLogisticsForm);
      service
        .post("/logisticsInfo/updateLogistics", this.updateLogisticsForm)
        .then(res => {
          if (res.data.code === 1) {
            this.$message.success(res.data.message);
            this.dialogFormVisible2 = false;
            this.getGoodsList();
          } else {
            this.$message.error(res.data.message);
          }
        })
        .catch(err => {
          console.log(err);
          this.$message.error("网络错误");
        });
    },
    // 新增状态信息
    updateState() {
      this.getTime();
      service
        .post("/logisticsInfo/addState", this.newState)
        .then(res => {
          if (res.data.code === 1) {
            this.$message.success(res.data.message);
            service
              .post("/logisticsInfo/state", {
                id: this.newState.logistics_id
              })
              .then(res => {
                this.state = res.data;
              });
          }
        })
        .catch(err => {
          console.log(err);
          this.$message.error("网络错误");
        });
    },
    // 对话框关闭
    dialogClose() {
      this.state = [];
      this.updateLogisticsForm = {};
      this.dialogFormVisible = false;
      this.dialogFormVisible2 = false;
    },
    resetForm(formName) {
      this.dialogFormVisible2 = false;
      this.$refs[formName].resetFields();
    }
  }
};
</script>
<style scoped>
.pagination {
  margin-top: 40px;
  text-align: center;
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
