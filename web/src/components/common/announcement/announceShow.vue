<template>
  <div class="announceShow">
    <!-- 轮播图 -->
    <el-carousel indicator-position="outside">
      <el-carousel-item v-for="item in imgList" :key="item.id">
        <img :src="item.idView" class="image" />
      </el-carousel-item>
    </el-carousel>
    <!-- 公告页面 -->
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>公告</span>
        <el-button style="float: right; padding: 3px 0" type="text"
          >查看更多</el-button
        >
      </div>
      <div v-for="o in announceList" :key="o.id" class="text item">
        <el-link
          icon="el-icon-arrow-right"
          :download="o.announcement_title"
          target="_blank"
          :href="'http://localhost:8000/' + o.announcement_body"
          :underline="false"
          >{{ o.announcement_title }}</el-link
        >
        <el-divider></el-divider>
      </div>
    </el-card>
  </div>
</template>
<script>
import service from "@/utils/request";
// import {WIDGET} from "@/assets/js/he-sim"
export default {
  name: "announceShow",
  created() {
    this.getAnnounceList();
  },

  data() {
    return {
      imgList: [
        { id: 0, idView: require("../../../../public/static/13.jpg") },
        { id: 1, idView: require("../../../../public/static/4ky.jpg") },
        {
          id: 2,
          idView: require("../../../../public/static/new_logo_banner.jpg")
        }
      ],
      announceList: []
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
    }
  }
};
</script>

<style scoped>
.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}

.text {
  font-size: 16px;
}

.item {
  margin-bottom: -10px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}
</style>
