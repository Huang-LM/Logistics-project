<template>
  <div class="shipApplication">
    <div style="height: 45vh;" class="apply-step">
      <el-steps direction="vertical" :active="active">
        <el-step title="寄件人"></el-step>
        <el-step title="收件人"></el-step>
        <el-step title="货物"></el-step>
      </el-steps>
    </div>
    <el-form
      :model="shipSubmitForm"
      :rules="rules"
      ref="shipSubmitForm"
      label-width="100px"
      :inline="true"
    >
      <el-divider content-position="left">寄件人信息</el-divider>
      <el-form-item label="用户名" prop="mailing_name">
        <el-input
          v-model="shipSubmitForm.mailing_name"
          auto-complete="new-password"
          placeholder="请输入寄件人用户名"
        >
        </el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="mailing_phone">
        <el-input
          v-model="shipSubmitForm.mailing_phone"
          placeholder="请输入寄件人手机号"
        ></el-input>
      </el-form-item>
      <el-form-item label="地址" prop="mailing_address">
        <el-input
          v-model="shipSubmitForm.mailing_address"
          placeholder="请输入寄件人地址"
          style="width: 50vw"
          @change="setp1(shipSubmitForm.mailing_address)"
        >
        </el-input>
      </el-form-item>
      <el-divider content-position="left">收件人信息</el-divider>
      <el-form-item label="用户名" prop="shipping_name">
        <el-input
          v-model="shipSubmitForm.shipping_name"
          auto-complete="new-password"
          placeholder="请输入收件人用户名"
        >
        </el-input>
      </el-form-item>
      <el-form-item label="手机号" prop="shipping_phone">
        <el-input
          v-model="shipSubmitForm.shipping_phone"
          placeholder="请输入收件人手机号"
        ></el-input>
      </el-form-item>
      <el-form-item label="地址" prop="shipping_address">
        <el-input
          v-model="shipSubmitForm.shipping_address"
          placeholder="请输入收件人地址"
          style="width: 50vw"
          @change="setp1(shipSubmitForm.shipping_address)"
        ></el-input>
      </el-form-item>
      <el-divider content-position="left">货物信息</el-divider>
      <el-form-item label="货物类型" prop="mailing_type">
        <el-select
          v-model="shipSubmitForm.mailing_type"
          placeholder="请选择"
          style="width: 120px"
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
      <el-form-item label="运输方式" prop="logistics_way">
        <el-select
          v-model="shipSubmitForm.logistics_way"
          placeholder="请选择"
          style="width: 120px"
        >
          <el-option
            v-for="item in ways"
            :key="item.id"
            :label="item.name"
            :value="item.name"
          >
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="备注" prop="note">
        <el-input
          type="textarea"
          :rows="3"
          style="width: 50vw"
          placeholder="请输入内容"
          v-model="shipSubmitForm.note"
        >
        </el-input>
      </el-form-item>
      <el-form-item class="form-btn">
        <el-button plain @click="resetForm('shipSubmitForm')" v-waves
          >重置</el-button
        >
        <el-button type="primary" @click="submitForm('shipSubmitForm')" v-waves
          >确认</el-button
        >
      </el-form-item>
    </el-form>

    <!-- 成功对话框 -->
    <el-dialog
      title="提交申请成功"
      :visible.sync="dialogFormVisible"
      :append-to-body="true"
    >
      请耐心等待审核，可在查询页查看进度。
      <div slot="footer" class="dialog-footer">
        <el-button class="btn-reset" plain @click="toInquiry()" v-waves
          >跳转查询页面</el-button
        >
        <el-button type="primary" @click="resetForm('shipSubmitForm')" v-waves
          >继续寄件</el-button
        >
      </div>
    </el-dialog>
  </div>
</template>

<script>
import service from "@/utils/request";
import waves from "@/directive/waves";

export default {
  name: "shipApplication",
  directives: { waves },
  created() {
    this.getOption();
    this.getWay();
  },
  data() {
    return {
      labelPosition: "top",
      dialogFormVisible: false,
      active: 1,
      logistics_number: "",
      shipSubmitForm: {
        mailing_name: "",
        mailing_phone: "",
        mailing_address: "",
        shipping_name: "",
        shipping_phone: "",
        shipping_address: "",
        mailing_type: "",
        logistics_way: "",
        note: "",
        createTime: "",
        user_id: ""
      },
      rules: {
        mailing_name: [
          { required: true, message: "请输入姓名", trigger: "blur" }
        ],
        shipping_name: [
          { required: true, message: "请输入姓名", trigger: "blur" }
        ],
        mailing_phone: [
          { required: true, message: "请输入手机号", trigger: "blur" }
        ],
        shipping_phone: [
          { required: true, message: "请输入手机号", trigger: "blur" }
        ],
        shipping_address: [
          { required: true, message: "请输入地址", trigger: "blur" }
        ],
        mailing_address: [
          { required: true, message: "请输入地址", trigger: "blur" }
        ],
        mailing_type: [
          { required: true, message: "请输入选择货物类型", trigger: "blur" }
        ],
        logistics_way: [
          { required: true, message: "请输入选择运输方式", trigger: "blur" }
        ]
      },
      options: [
        {
          value: "1",
          label: "电子产品"
        },
        {
          value: "2",
          label: "日用品"
        },
        {
          value: "3",
          label: "贵重物品"
        }
      ],
      ways: [
        {
          value: "1",
          label: "空运"
        },
        {
          value: "2",
          label: "海运"
        },
        {
          value: "3",
          label: "火箭运"
        },
        {
          value: "4",
          label: "太空运"
        }
      ]
    };
  },

  methods: {
    // 申请请求
    submitForm(formName) {
      this.getTime();
      this.shipSubmitForm.user_id = this.$store.state.account.userID;
      this.$refs[formName].validate(valid => {
        if (valid) {
          service
            .post("/logisticsInfo/send", this.shipSubmitForm)
            .then(res => {
              // console.log(res);
              if (res.data.code === 1) {
                this.$message({
                  message: "申请已提交",
                  type: "success"
                });
                // this.logistics_number = "";
                this.dialogFormVisible = true;
                this.$refs[formName].resetFields();
              } else if (res.data.code === 0) {
                this.$message({
                  message: "系统出错",
                  type: "warning"
                });
              } else {
                // console.log(res);
                this.$message.error("申请失败,请重试！");
              }
            })
            .catch(err => {
              console.log(err);
              this.$message.error("申请失败，请重试！");
            });
        } else {
          this.$message.error("信息未填完！");
          return false;
        }
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

      this.shipSubmitForm.createTime = currentdate;
    },
    getOption() {
      service
        .post("/dictionaries/findSonByName?name=货物类型")
        .then(res => {
          // console.log(res);
          this.options = res.data;
        })
        .catch(err => {
          console.log(err);
        });
    },
    getWay() {
      service
        .post("/dictionaries/findSonByName?name=运输方式")
        .then(res => {
          // console.log(res);
          this.ways = res.data;
        })
        .catch(err => {
          console.log(err);
        });
    },
    toInquiry() {
      this.$router.push("/shipInquiry");
      this.resetForm("shipSubmitForm");
    },
    setp1(value) {
      if (value) {
        this.active++;
      }
    },
    resetForm(formName) {
      this.dialogFormVisible = false;
      this.$refs[formName].resetFields();
    }
  }
};
</script>
<style scoped>
.shipApplication {
  width: 60vw;
  margin-left: 10vw;
}
.apply-step {
  float: left;
  margin-left: -15%;
}
.form-btn {
  float: right;
}
</style>
