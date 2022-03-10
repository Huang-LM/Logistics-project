<template>
  <div class="reviewGoods">
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
          v-model="queryInfo.mailing_phone"
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
              <span v-if="props.row.shipping_time === 'Invalid date'"
                >暂未生成</span
              >
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
          <!-- <el-button
            type="info"
            v-if="props.row.verify === 0"
            size="mini"
            @click="changeVerify(props.row)"
            >待审核</el-button
          >-->
          <el-dropdown v-if="props.row.verify === 0" @command="handleCommand">
            <el-button
              size="mini"
              split-button
              type="info"
              @click="handleClick(props.row.id)"
            >
              未审核<i class="el-icon-arrow-down el-icon--right"></i>
            </el-button>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item command="1">审核通过</el-dropdown-item>
              <el-dropdown-item command="2">未通过审核</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
          <el-button
            type="success"
            size="mini"
            v-else-if="props.row.verify === 1"
            >审核通过</el-button
          >
          <el-button
            type="danger"
            size="mini"
            v-else-if="props.row.verify === 2"
            >未通过审核</el-button
          >
        </template>
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
          ><span v-if="props.row.shipping_time === 'Invalid date'">未知</span>
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
        // id: "",
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
      optionsVerify: [
        {
          value: "0",
          label: "未审核"
        },
        {
          value: "1",
          label: "审核通过"
        },
        {
          value: "2",
          label: "未通过审核"
        }
      ],
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
      select_verify: "",
      verifyId: null,
      delId: {
        id: 0
      }
    };
  },

  methods: {
    getGoodsList() {
      service
        .post("/logisticsInfo/listLogistics", this.queryInfo)
        .then(res => {
          // console.log(res.data);
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
      service.post("/logisticsInfo/listLogistics", this.queryInfo).then(res => {
        const resData = res.data.data;
        // console.log(resData.records);
        this.logisticsData = resData.list;
        this.total = resData.total;
      });
    },
    // 修改审核值
    handleCommand(command) {
      if (command == 1) {
        this.updateVerify(1);
      } else if (command == 2) {
        this.updateVerify(2);
      }
    },
    handleClick(id) {
      this.verifyId = id;
    },
    // 同意寄件
    updateVerify(verify) {
      service
        .post("/logisticsInfo/updateLogistics", { id: this.verifyId, verify })
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
        .post("/dictionaries/find", { name: way })
        .then(res => {
          // console.log(res);
          this.options = res.data.data;
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
            .post("/logisticsInfo/delete", this.delId)
            .then(res => {
              if (res.data.code === 1) {
                this.$message({
                  type: "success",
                  message: "该信息已删除!"
                });
                this.getGoodsList();
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
            .post("/logisticsInfo/updateLogistics", this.updateLogisticsForm)
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
