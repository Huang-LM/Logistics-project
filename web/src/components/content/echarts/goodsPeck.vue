<template>
  <div class="goodsPeck">
    <div class="block">
      <el-date-picker v-model="selectYear" type="year" placeholder="选择年">
      </el-date-picker>
      <el-button type="primary" @click="this.getPeckData">查询</el-button>
    </div>
    <!-- 绑定元素 -->
    <div id="peck"></div>
  </div>
</template>

<script>
// import service from "@/utils/request";

export default {
  name: "goodsPeck",
  data() {
    return {
      // 想要选择的时间点
      selectYear: "2021",
      // 图表配置与数据
      peckData: {
        title: {
          text: "物流高峰图"
        },
        tooltip: {
          trigger: "axis" //坐标轴触发，主要在柱状图，折线图等会使用类目轴的图表中使用。
        },
        legend: {
          data: ["销量"]
        },
        xAxis: {
          name: "月份",
          type: "category",
          data: [
            "一月",
            "二月",
            "三月",
            "四月",
            "五月",
            "六月",
            "七月",
            "八月",
            "九月",
            "十月",
            "十一月",
            "十二月"
          ],
          boundaryGap: false
        },
        yAxis: {
          name: "订单数/千",
          value: "value",
          scale: true,
          splitLine: {
            show: false //是否显示分隔线。默认数值轴显示，类目轴不显示。
          }
        },
        series: {
          name: "销量",
          type: "line",
          data: [5, 20, 36, 10, 10, 20, 33, 44, 56, 44, 86, 98],
          symbol: "circle", //标记的类型：圆圈
          markPoint: {
            data: [
              {
                type: "min"
              },
              {
                type: "max"
              }
            ]
          },
          markLine: {
            data: [
              {
                type: "average"
              }
            ]
          },
          lineStyle: {
            color: "black"
          }
        }
      }
    };
  },
  // 数据初始化时执行
  created() {
    // this.getPeckData();
  },
  // dom树加载完成时渲染echarts
  mounted() {
    this.drawLine();
  },
  methods: {
    // 绘制图表的方法
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("peck"));
      // 绘制图表
      myChart.setOption(this.peckData);
    },
    // 获取图表数据
    getPeckData() {
      // service
      //   .post("接口", { year: this.selectYear })
      //   .then(res => {
      //     console.log(res);
      //   })
      //   .catch(err => {
      //     console.log(err);
      //   });
      var year = this.selectYear.getFullYear();
      // console.log(this.selectYear.getFullYear());
      if (year === 2020) {
        this.peckData.series.data = [
          56,
          44,
          20,
          5,
          26,
          36,
          10,
          98,
          10,
          20,
          33,
          44
        ];
      } else if (year === 2019) {
        this.peckData.series.data = [
          10,
          40,
          10,
          20,
          33,
          44,
          56,
          44,
          86,
          20,
          20,
          36
        ];
      }

      console.log(this.peckData);

      this.drawLine();
    }
  }
};
</script>
<style scoped>
#peck {
  width: 80vw;
  height: 70vh;
}
</style>
