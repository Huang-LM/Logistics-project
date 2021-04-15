<template>
  <div class="goodsStatistics">
    <div id="statistics"></div>
  </div>
</template>

<script>
export default {
  name: "goodsStatistics",
  data() {
    return {
      statisticsData: {
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b}: {c} ({d}%)"
        },
        series: [
          {
            name: "海上物流平台",
            type: "pie",
            radius: [0, "45%"],
            label: {
              normal: {
                position: "inner"
              }
            },
            //扇形区域内显示文字
            data: [
              { value: 18, name: "第一季度" },
              { value: 30, name: "第二季度" },
              { value: 42, name: "第三季度" },
              { value: 25, name: "第四季度" }
            ],
            color: ["#10EFE0", "#EF42A4", "#FF8F03", "#67C23A"]
          },
          {
            name: "海上运输平台",
            type: "pie",
            radius: ["45%", "90%"],
            labelLine: {
              normal: {
                show: true
              }
            },
            // 外扇形显示的文字
            data: [
              { value: 60, name: "一月" },
              { value: 60, name: "二月" },
              { value: 60, name: "三月" },
              { value: 100, name: "四月" },
              { value: 100, name: "五月" },
              { value: 100, name: "六月" },
              { value: 220, name: "七月" },
              { value: 100, name: "八月" },
              { value: 100, name: "九月" },
              { value: 50, name: "十月" },
              { value: 100, name: "十一月" },
              { value: 100, name: "十二月" }
            ],
            label: {
              show: true,
              formatter: "{b}: {c} ({d}%)"
            },
            color: [
              "#51CEC6",
              "#6AE2D9",
              "#96F5EF",
              "#EF87C2",
              "#F79DD0",
              "#FFC8E7",
              "#FFB703",
              "#FFCD50",
              "#FFE296",
              "#CAE8BC",
              "#86ED57",
              "#9EEE79",
              "#86ED57"
            ]
          }
        ]
      }
    };
  },

  methods: {
    drawLine() {
      // 基于准备好的dom，初始化echarts实例
      let myChart = this.$echarts.init(document.getElementById("statistics"));
      // 绘制图表
      myChart.setOption(this.statisticsData);
    },
    getStatistics() {}
  },
  mounted() {
    this.drawLine();
  }
};
</script>
<style scoped>
#statistics {
  width: 80vw;
  height: 60vh;
  margin-top: 5%;
}
</style>
