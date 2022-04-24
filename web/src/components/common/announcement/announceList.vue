<template>
  <div class="announceList">
    <el-button
      type="primary"
      v-waves
      @click="dialogFormVisible = true"
      icon="el-icon-edit"
    >
      新增
    </el-button>
    <el-table :data="announceList" style="width: 100%;" stripe>
      <!-- <el-table-column prop="id" label="ID" sortable width="70">
      </el-table-column> -->
      <el-table-column prop="announcement_title" label="公告名称" width="250">
      </el-table-column>
      <el-table-column prop="announcement_body" label="公告文件" width="250">
        <template v-slot="scope">
          <el-link
            :download="downloadname"
            target="_blank"
            :href="aHerf"
            :underline="false"
          >
            <el-button
              size="mini"
              type="primary"
              @click="
                getAnnouncement(
                  scope.row.announcement_title,
                  scope.row.announcement_body
                )
              "
              >下载文件</el-button
            >
          </el-link>
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
      <el-table-column label="操作" min-width="180" fixed="right">
        <template slot-scope="scope">
          <!-- <el-button
            type="primary"
            plain
            v-waves
            size="mini"
            @click="handleEdit(scope.$index, scope.row)"
            >编辑</el-button
          > -->
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

    <!-- 新增公告对话框 -->
    <el-dialog
      title="新增公告"
      class="update-dialog"
      :visible.sync="dialogFormVisible"
      :append-to-body="true"
    >
      <el-form
        :model="addAnnounceForm"
        :rules="rules"
        ref="addAnnounceForm"
        :label-position="labelPosition"
        label-width="80px"
        class="update-Form"
      >
        <el-form-item label="公告名称" prop="announcement_title">
          <el-input
            v-model="addAnnounceForm.announcement_title"
            placeholder="请输入公告名称"
          ></el-input>
        </el-form-item>
        <el-form-item label="公告文件">
          <el-upload drag action="/api/user/upload" :on-success="successUpload">
            <i class="el-icon-upload"></i>
            <div class="el-upload__text">
              将文件拖到此处，或<em>点击上传</em>
            </div>
            <div class="el-upload__tip" slot="tip">
              只能上传一个excl或是word文件，且不超过500kb
            </div>
          </el-upload>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button
          class="btn-reset"
          plain
          @click="resetForm('addAnnounceForm')"
          v-waves
          >取消</el-button
        >
        <el-button type="primary" @click="submitForm('addAnnounceForm')" v-waves
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
  name: "announceList",
  directives: { waves },
  created() {
    this.getAnnounceList();
  },
  data() {
    return {
      labelPosition: "right",
      announceList: [],
      downloadname: "",
      // queryInfo: {
      //   // 查询参数
      //   id: "",
      //   username: "",
      //   phone: "",
      //   role: "",
      //   gender: "",
      //   // 当前页数
      //   page: 0,
      //   // 当前显示多少行
      //   pageSize: 5
      // },
      aHerf: "",
      total: 0,
      dialogFormVisible: false,
      formLabelWidth: "150px",
      addAnnounceForm: {
        announcement_title: "",
        announcement_body: ""
      },
      rules: {
        username: [
          { required: true, message: "请输入用户姓名", trigger: "blur" }
        ]
      }
    };
  },

  methods: {
    //获取公告列表
    getAnnounceList() {
      service
        .get("/user/announceList")
        .then(res => {
          const resData = res.data;
          this.announceList = resData.list;
          // this.total = resData.total;
        })
        .catch(err => {
          console.log(err);
          this.$message.error("网络异常");
        });
    },
    getAnnouncement(name, body) {
      this.downloadname = name;
      this.aHerf = "http://localhost:8000/" + body;
    },
    successUpload(response) {
      console.log(response);
      if (response.code === 1) {
        this.addAnnounceForm.announcement_body = response.data;
      }
    },
    //删除用户信息
    handleDelete(index, row) {
      this.$confirm("此操作将永久删除该公告, 是否继续?", "提示", {
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
                  message: "该用户信息已删除!"
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
    // handleCurrentChange(val) {
    //   this.queryInfo.page = val - 1;
    //   this.getUserList();
    // },
    // handleSizeChange(val) {
    //   this.queryInfo.pageSize = val;
    //   this.getUserList();
    // },
    // 新增用户
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          service
            .post("/user/addAnnounce", this.addAnnounceForm)
            .then(res => {
              if (res.data.code === 1) {
                this.$message({
                  message: "发布成功",
                  type: "success"
                });
                this.dialogFormVisible = false;
                this.$refs[formName].resetFields();
                this.getAnnounceList();
              } else if (res.data.code === 0) {
                this.$message({
                  message: res.data.message,
                  type: "warning"
                });
              } else {
                // console.log(res);
                this.$message.error("添加失败,请重试！");
              }
            })
            .catch(err => {
              console.log(err);
              this.$message.error("添加失败，请重试！");
            });
        } else {
          this.$message.error("添加失败，请重试！");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.dialogFormVisible = false;
      this.$refs[formName].resetFields();
    }
  }
};
</script>
<style scoped></style>
