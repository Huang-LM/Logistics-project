<template>
  <div class="roleUser">
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
          @clear="getUserList"
        ></el-input>
        <el-input
          v-model="queryInfo.username"
          style="width: 180px"
          auto-complete="new-password"
          placeholder="请输入用户名"
          clearable
          class="search-input"
          @clear="getUserList"
        ></el-input>
        <el-input
          v-model="queryInfo.phone"
          style="width: 200px"
          placeholder="请输入手机号"
          clearable
          class="search-input"
          @clear="getUserList"
        ></el-input>
        <el-select
          v-model="queryInfo.role"
          placeholder="请选择"
          style="width: 120px"
          clearable
          @clear="getUserList"
        >
          <el-option
            v-for="item in options"
            :key="item.id"
            :label="item.name"
            :value="item.id"
          >
          </el-option>
        </el-select>
        <el-button
          type="primary"
          v-waves
          @click="this.selectUserList"
          icon="el-icon-search"
          class="search-btn"
        >
          查询
        </el-button>
      </el-form-item>
    </el-form>
    <!-- 搜索结果 -->
    <el-table :data="userList" style="width: 100%;" stripe>
      <el-table-column prop="id" label="ID" sortable width="70">
      </el-table-column>
      <el-table-column prop="username" label="用户名" width="100">
      </el-table-column>
      <el-table-column prop="phone" label="手机号" width="130">
      </el-table-column>
      <el-table-column prop="role" label="角色" width="180">
        <template v-slot="scope">
          <el-select
            v-model="scope.row.role"
            placeholder="请选择"
            style="width: 120px"
            @change="updateUserRole(scope.row.id, scope.row.role)"
          >
            <el-option
              v-for="item in options"
              :key="item.id"
              :label="item.name"
              :value="item.name"
            >
            </el-option>
          </el-select>
        </template>
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
  </div>
</template>

<script>
import service from "@/utils/request";
import waves from "@/directive/waves";
import bus from "@/utils/eventBus.js";
// import Waves from "@/components/common/directives/waves";

export default {
  name: "roleUser",
  directives: { waves },
  created() {
    this.getUserList();
    this.getRoleOptions();
  },
  data() {
    return {
      labelPosition: "right",
      userList: [],
      queryInfo: {
        // 查询参数
        id: "",
        username: "",
        phone: "",
        role: "",
        gender: "",
        // 当前页数
        page: 0,
        // 当前显示多少行
        pageSize: 5
      },
      total: 0,
      delId: {
        id: 0
      },
      dialogFormVisible: false,
      dialogFormVisible2: false,
      formLabelWidth: "150px",
      value1: true,
      userId: "",
      options: [
        {
          value: "1",
          label: "管理员"
        },
        {
          value: "2",
          label: "货主"
        },
        {
          value: "3",
          label: "物流管理员"
        },
        {
          value: "4",
          label: "信息服务员"
        }
      ],
      test: {
        id: "",
        verify: ""
      }
    };
  },
  methods: {
    //获取用户列表
    getUserList() {
      service
        .post("/user/list", this.queryInfo)
        .then(res => {
          // console.log(res);
          const resData = res.data;
          // console.log(resData.records);
          this.userList = resData.list;
          this.total = resData.total;
        })
        .catch(err => {
          console.log(err);
          this.$message.error("网络异常");
        });
    },
    // 获取角色菜单
    getRoleOptions() {
      service
        .get("/role/listRole")
        .then(res => {
          // console.log(1);
          this.options = res.data.data;
        })
        .catch(err => {
          console.log(err);
          this.$message.error("网络异常");
        });
    },
    // 查找用户
    selectUserList() {
      // console.log(this.queryInfo);
      service
        .post("/user/select", this.queryInfo)
        .then(res => {
          // console.log(res);
          const resData = res.data;
          // console.log(resData.records);
          this.userList = resData.list;
          this.total = resData.total;
        })
        .catch(err => {
          console.log(err);
          this.$message.error("网络异常");
        });
    },
    //更新用户状态
    updateUserRole(user_id, new_role) {
      // console.log(user_id, new_role_id);

      service
        .post("role/updateRole", { user_id: user_id, new_role: new_role })
        .then(res => {
          // console.log("res");

          if (res.data.code === 1) {
            this.$alert(res.data.message + "已修改为：" + new_role, {
              confirmButtonText: "确定"
            });
            this.userId = this.$store.state.account.userID;
            // this.$message.success(res.data.message);
            this.getUserList();
          } else {
            this.$alert(res.data.message, {
              confirmButtonText: "确定"
            });
            // this.$message.error(res.data.message);
          }
        })
        .catch(err => {
          console.log(err);
          this.$message.error("网络错误！");
        });
    },
    // 换页
    handleCurrentChange(val) {
      this.queryInfo.page = val - 1;
      this.getUserList();
    },
    handleSizeChange(val) {
      this.queryInfo.pageSize = val;
      this.getUserList();
    },
    resetForm(formName) {
      this.dialogFormVisible = false;
      this.$refs[formName].resetFields();
    }
  },
  watch: {
    userId() {
      bus.$emit("getSide");
      this.$router.push("/home");
    }
  }
};
</script>

<style scoped>
.pagination {
  margin-top: 40px;
  text-align: center;
}
.search-btn {
  margin-left: 10px;
}
</style>
