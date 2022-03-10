<template>
  <div class="dictionaryManager">
    <el-table :data="dicList" style="width: 100%;" stripe>
      <!-- <el-table-column prop="id" label="ID" sortable width="70">
      </el-table-column> -->
      <el-table-column prop="name" label="名称" width="120"> </el-table-column>
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
      <el-table-column label="操作" min-width="280" fixed="right">
        <template slot-scope="scope">
          <el-button
            type="primary"
            plain
            v-waves
            size="mini"
            @click="handleEdit(scope.$index, scope.row)"
            >管理子类</el-button
          >
          <el-button
            type="info"
            plain
            v-waves
            size="mini"
            @click="handleAdd(scope.$index, scope.row)"
            >新增子类</el-button
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

    <!-- 管理子类 -->
    <el-drawer :visible.sync="drawer" size="70%" :append-to-body="true">
      <div>
        <!-- 外层 -->
        <el-table :data="dicSonList" style="width: 100%;" stripe>
          <el-table-column prop="id" label="ID" sortable width="70">
          </el-table-column>
          <el-table-column prop="name" label="名称" width="100">
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
          <el-table-column label="操作" min-width="280" fixed="right">
            <template slot-scope="scope">
              <el-button
                type="primary"
                plain
                v-waves
                size="mini"
                @click="handleEditTwo(scope.$index, scope.row)"
                >管理子类</el-button
              >
              <el-button
                type="info"
                plain
                v-waves
                size="mini"
                @click="handleAdd(scope.$index, scope.row)"
                >新增子类</el-button
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

        <!-- 内层 -->
        <el-drawer
          :append-to-body="true"
          :before-close="handleClose"
          :visible.sync="innerDrawer"
          size="55%"
        >
          <el-table :data="dicGrandSonList" style="width: 100%;" stripe>
            <el-table-column prop="id" label="ID" sortable width="70">
            </el-table-column>
            <el-table-column prop="name" label="名称" width="100">
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
            <el-table-column label="操作" min-width="280" fixed="right">
              <template slot-scope="scope">
                <el-button
                  type="primary"
                  plain
                  v-waves
                  size="mini"
                  @click="handleEditTwo(scope.$index, scope.row)"
                  >管理子类</el-button
                >
                <el-button
                  type="info"
                  plain
                  v-waves
                  size="mini"
                  @click="handleAdd(scope.$index, scope.row)"
                  >新增子类</el-button
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
        </el-drawer>
      </div>
    </el-drawer>

    <!-- 新增字典 -->
    <el-dialog
      title="新增字典"
      :visible.sync="dialogFormVisible"
      :append-to-body="true"
    >
      <el-form
        :model="addDicForm"
        :rules="rules"
        ref="addDicForm"
        :label-position="labelPosition"
        label-width="80px"
      >
        <el-form-item label="名称" prop="name">
          <el-input
            v-model="addDicForm.name"
            auto-complete="new-password"
            placeholder="请输入名称"
          ></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button plain @click="resetForm('addDicForm')" v-waves
          >取消</el-button
        >
        <el-button type="primary" @click="submitForm('addDicForm')" v-waves
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
  name: "dictionaryManager",
  directives: { waves },
  created() {
    this.getDicList();
    // this.getRoleOptions();
  },
  data() {
    return {
      labelPosition: "right",
      dicList: [],
      drawer: false,
      innerDrawer: false,
      dialogFormVisible: false,
      pid: "",
      deleteId: {
        id: ""
      },
      dicGrandSonList: [],
      formLabelWidth: "150px",
      dicSonList: [],
      addDicForm: {
        name: "",
        parentId: "",
        createTime: ""
      },
      rules: {
        name: [{ required: true, message: "请输入名称", trigger: "blur" }]
      },
      options: []
    };
  },
  methods: {
    //获取用户列表
    getDicList() {
      service
        .post("/dictionaries/find", { parentId: 0 })
        .then(res => {
          // console.log(res);
          this.dicList = res.data.data;
        })
        .catch(err => {
          console.log(err);
          this.$message.error("网络异常");
        });
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

      this.addDicForm.createTime = currentdate;
    },
    //跳转至编辑用户
    handleEdit(index, row) {
      this.addDicForm.parentId = row.id;
      this.pid = row.id;
      service
        .post("/dictionaries/find", { parentId: row.id })
        .then(res => {
          this.dicSonList = res.data.data;
        })
        .catch(err => {
          console.log(err);
        });
      // this.updateUserForm = row;
      this.drawer = true;
    },
    handleEditTwo(index, row) {
      service
        .post("/dictionaries/find", { parentId: row.id })
        .then(res => {
          this.dicGrandSonList = res.data.data;
        })
        .catch(err => {
          console.log(err);
        });
      // this.updateUserForm = row;
      this.pid = row.id;
      this.innerDrawer = true;
    },
    handleClose(done) {
      this.$confirm("确定关闭吗？")
        .then(() => {
          done();
        })
        .catch(() => {});
    },
    refrashGrandTable() {
      service
        .post("/dictionaries/find", { parentId: 0 })
        .then(res => {
          this.dicList = res.data.data;
        })
        .catch(err => {
          console.log(err);
        });
    },
    refrashTable() {
      service
        .post("/dictionaries/find", { parentId: 0 })
        .then(res => {
          this.dicList = res.data.data;
        })
        .catch(err => {
          console.log(err);
        });
    },
    //删除字典数据
    handleDelete(index, row) {
      this.$confirm("此操作将永久删除该字典数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      })
        .then(() => {
          // this.deleteId.id = row.id;
          service
            .post("/dictionaries/delete", { id: row.id })
            .then(res => {
              if (res.data.code === 1) {
                this.$message({
                  type: "success",
                  message: "该信息已删除!"
                });
                this.innerDrawer = false;
                this.drawer = false;
                this.refrashGrandTable();
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
    handleAdd(index, row) {
      this.dialogFormVisible = true;
      this.addDicForm.parentId = row.id;
    },
    // 新增
    submitForm(formName) {
      this.getTime();
      service
        .post("/dictionaries/add", this.addDicForm)
        .then(res => {
          // console.log(res);
          if (res.data.code === 1) {
            this.$message({
              message: "添加成功",
              type: "success"
            });
            this.dialogFormVisible = false;
            this.$refs[formName].resetFields();
            // this.$router.replace({
            //   path: "/dictionaryManager"
            // });
          } else if (res.data.code === 0) {
            this.$message({
              message: "添加失败，请重试！",
              type: "warning"
            });
          } else {
            // console.log(res);
            this.$message.error("添加失败,请重试！");
          }
        })
        .catch(err => {
          console.log(err);
          this.$message.error("网络异常");
        });
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
