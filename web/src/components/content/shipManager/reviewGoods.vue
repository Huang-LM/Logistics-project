<template>
  <div class="reviewGoods">
    <!-- 搜索 -->
    <el-form :model="queryInfo" ref="queryInfo">
      <el-form-item>
        <el-input
          v-model="queryInfo.id"
          style="width: 80px"
          auto-complete="new-password"
          placeholder="id"
          clearable
          class="search-input"
          @clear="getGoodsList"
        ></el-input>
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
      <el-table-column label="物流状态" min-width="120px">
        <template v-slot="props">
          <el-button
            type="info"
            v-if="props.row.verify === 0"
            size="mini"
            @click="changeVerify(props.row)"
            >待审核</el-button
          >
          <el-button
            type="success"
            size="mini"
            @click="changeVerify(props.row)"
            v-else-if="props.row.verify === 1"
            >审核通过</el-button
          >
          <el-button
            type="danger"
            size="mini"
            @click="changeVerify(props.row)"
            v-else-if="props.row.verify === 2"
            >未通过审核</el-button
          >
        </template>
        <!-- <template v-slot="scope">
          <el-select
            v-model="scope.row.verify"
            placeholder="请选择"
            style="width: 120px"
            @change="updateUserRole(scope.row.id, scope.row.verify)"
          >
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </template> -->
      </el-table-column>
      <!-- <el-table-column label="寄件地址" prop="mailing_address">
      </el-table-column>
      <el-table-column label="收件地址" prop="shipping_address">
      </el-table-column> -->
      <el-table-column
        label="预计到达时间"
        sortable
        prop="shipping_time"
        min-width="170"
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
      <el-table-column label="操作" min-width="180" fixed="right">
        <template slot-scope="scope">
          <!-- <el-button
            type="primary"
            plain
            v-waves
            size="mini"
            @click="handleEdit(scope.$index, scope.row)"
            >审核</el-button
          > -->
          <el-button
            type="primary"
            plain
            v-waves
            size="mini"
            @click="handleEdit(scope.$index, scope.row)"
            >安排运输</el-button
          >
          <el-button
            type="danger"
            v-waves
            size="mini"
            @click="handleDelete(scope.$index, scope.row)"
            >删除</el-button
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

    <!-- 更新用户对话框 -->
    <el-dialog
      title="运输工具编号"
      class="update-dialog"
      :visible.sync="dialogFormVisible2"
      :append-to-body="true"
    >
      <el-form
        :model="updateLogisticsForm"
        ref="updateLogisticsForm"
        label-width="120px"
      >
        <el-form-item label="运输方式" prop="logistics_way">{{
          updateLogisticsForm.logistics_way
        }}</el-form-item>
        <el-form-item label="物流工具编号" prop="logistics_way_number">
          <el-select
            v-model="updateLogisticsForm.logistics_way_number"
            placeholder="请选择"
          >
            <el-option
              v-for="item in options"
              :key="item.id"
              :label="item.name"
              :value="item.name"
            >
            </el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button
          class="btn-reset"
          plain
          @click="resetForm('updateLogisticsForm')"
          v-waves
          >取消</el-button
        >
        <el-button
          type="primary"
          @click="updateForm('updateLogisticsForm')"
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
  name: "reviewGoods",
  directives: { waves },
  created() {
    this.getGoodsList();
    // this.getWays();
    // this.getVerifyOptions();
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
      total: 0,
      logisticsData: [],
      updateLogisticsForm: {
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
      },
      formLabelWidth: "150px",
      dialogFormVisible2: false,
      // shipping_address_options: [],
      // 之后获取rowWay以后去找子id去
      options: [
        {
          value: "0",
          label: "CA2137"
        },
        {
          value: "1",
          label: "HG8563"
        },
        {
          value: "2",
          label: "UI2314"
        }
      ],
      select_verify: ""
    };
  },

  methods: {
    getGoodsList() {
      service
        .post("/logisticsInfo/listLogistics", this.queryInfo)
        .then(res => {
          // console.log(res);
          const resData = res.data;
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
      service.post("/logisticsInfo/select", this.queryInfo).then(res => {
        if (res.status === 200) this.logisticsData = res.data;
      });
    },
    // 修改审核值
    changeVerify(row) {
      if (row.verify === 0) {
        this.successVerify(row.id);
      } else if (row.verify === 1) {
        this.refuseVerify(row.id);
      } else if (row.verify === 2) {
        this.successVerify(row.id);
      }
    },
    // 同意寄件
    successVerify(id) {
      service
        .post("/logisticsInfo/agree", { id: id })
        .then(res => {
          // console.log(res.data);

          if (res.data.code === 1) {
            this.$message.success(res.data.message);
            this.getGoodsList();
          } else {
            this.$message.error(res.data.message);
          }
        })
        .catch(err => {
          this.$message.error(err.data.message);
          this.getGoodsList();
        });
    },
    // 拒绝寄件
    refuseVerify(id) {
      service
        .post("/logisticsInfo/refuse", { id: id })
        .then(res => {
          if (res.data.code === 1) {
            this.$message.success(res.data.message);
            this.getGoodsList();
          } else {
            this.$message.error(res.data.message);
          }
        })
        .catch(err => {
          this.$message.error(err.data.message);
          this.getGoodsList();
        });
    },
    getWays(way) {
      service
        .post("/dictionaries/findSonByName?name=" + way)
        .then(res => {
          // console.log(res);
          this.options = res.data;
        })
        .catch(err => {
          console.log(err);
        });
    },
    // updateLogistics(row) {
    //   this.updateLogisticsForm =
    //   service.post("");
    // },
    //跳转至编辑界面
    handleEdit(index, row) {
      this.updateLogisticsForm = row;
      this.getWays(row.logistics_way);
      this.dialogFormVisible2 = true;
    },
    //删除物流信息
    handleDelete(index, row) {
      this.$confirm("此操作将永久删除该条记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          this.delId.id = row.id;
          service
            .post("/user/delete", this.delId)
            .then(res => {
              if (res.data.code === 1) {
                this.$message({
                  type: "success",
                  message: "该信息已删除!"
                });
                this.getUserList();
              } else {
                this.$message({
                  type: "info",
                  message: "删除失败"
                });
              }
            })
            .catch(err => {
              console.log(err);
              this.$message({
                type: "info",
                message: "删除失败"
              });
            });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除"
          });
        });
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
    // 更新用户
    updateForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          //console.log(this.addUserForm);
          service
            .post("/logisticsInfo/update", this.updateLogisticsForm)
            .then(res => {
              // console.log(res);
              if (res.data.code === 1) {
                this.$message({
                  message: "更新成功",
                  type: "success"
                });

                this.dialogFormVisible2 = false;
                this.$refs[formName].resetFields();
                this.getGoodsList();
              } else {
                this.$message({
                  message: "更新失败",
                  type: "warning"
                });
              }
            })
            .catch(err => {
              console.log(err);
              this.$message.error("更新失败，请重试！");
            });
        } else {
          this.$message.error("更新失败，请重试！");
          return false;
        }
      });
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
