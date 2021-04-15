<template>
  <div class="checkUser">
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
            :key="item.value"
            :label="item.label"
            :value="item.label"
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
      <el-table-column prop="role" label="角色" width="150"> </el-table-column>
      <el-table-column label="审核" width="180">
        <template v-slot="scope">
          <el-switch
            v-model="scope.row.verify"
            active-text="通过"
            inactive-text="未通过"
            @change="examineUser(scope.row.id, scope.row.verify)"
          >
          </el-switch>
        </template>
      </el-table-column>
      <el-table-column
        prop="createTime"
        label="创建时间"
        sortabl
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
// import Waves from "@/components/common/directives/waves";

export default {
  name: "checkUser",
  directives: { waves },
  created() {
    this.getUserList();
  },
  data() {
    let validatePass = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请输入密码"));
      } else {
        if (this.addUserForm.check_password !== "") {
          this.$refs.addUserForm.validateField("check_password");
        }
        callback();
      }
    };

    let validatePass2 = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("请再次输入密码"));
      } else if (value !== this.addUserForm.password) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
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
      rules: {
        username: [
          { required: true, message: "请输入用户姓名", trigger: "blur" }
        ],
        password: [
          { required: true, validator: validatePass, trigger: "blur" }
        ],
        check_password: [
          { required: true, validator: validatePass2, trigger: "blur" }
        ],
        phone: [{ required: true, message: "请输入手机号", trigger: "blur" }]
      },
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
    // 查找用户
    selectUserList() {
      console.log(this.queryInfo);
      service
        .post("/user/select", this.queryInfo)
        .then(res => {
          console.log(res);
          const resData = res.data;
          // console.log(resData.records);
          this.userList = resData;
          this.total = resData.length;
        })
        .catch(err => {
          console.log(err);
          this.$message.error("网络异常");
        });
    },
    //时间格式化
    // dateFormat(row) {
    //   let t = new Date(row.createTime);
    //   let year = t.getFullYear(),
    //     month = ("0" + (t.getMonth() + 1)).slice(-2),
    //     date = ("0" + t.getDate()).slice(-2),
    //     hour = ("0" + t.getHours()).slice(-2),
    //     minute = ("0" + t.getMinutes()).slice(-2),
    //     second = ("0" + t.getSeconds()).slice(-2);
    //   // 拼接
    //   let result =
    //     year +
    //     "-" +
    //     month +
    //     "-" +
    //     date +
    //     " " +
    //     hour +
    //     ":" +
    //     minute +
    //     ":" +
    //     second;
    //   // 返回
    //   return result;
    // },
    //更新用户状态
    examineUser(id, verify) {
      let _verify = verify ? 0 : 1;
      this.test.id = id;
      this.test.verify = _verify;
      // console.log(this.test);

      service
        // .post("user/examine", this.$qs.stringify({ id: id, verity: _verity }))
        .post("user/examine", this.test)
        .then(res => {
          if (res.data.code === 1) {
            this.$message.success(res.data.message);
            this.getUserList();
          } else {
            this.$message.error(res.data.message);
          }
        })
        .catch(err => {
          this.$message.error(err.data.message);
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
