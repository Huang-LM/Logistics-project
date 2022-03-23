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
      <div v-for="o in 4" :key="o" class="text item">
        <el-link icon="el-icon-arrow-right">{{ "公告 " + o }}</el-link>
        <el-divider></el-divider>
      </div>
    </el-card>
  </div>
</template>
<script>
// import service from "@/utils/request";
// import {WIDGET} from "@/assets/js/he-sim"
export default {
  name: "announceShow",
  created() {},

  data() {
    return {
      imgList: [
        { id: 0, idView: require("../../../../public/static/13.jpg") },
        { id: 1, idView: require("../../../../public/static/4ky.jpg") },
        {
          id: 2,
          idView: require("../../../../public/static/new_logo_banner.jpg")
        }
      ]
    };
  },
  methods: {}
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
