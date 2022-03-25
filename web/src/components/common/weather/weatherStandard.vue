<template>
  <div class="weatherStandard">
    <div id="he-plugin-standard"></div>
  </div>
</template>

<script>
export default {
  name: "weatherStandard",
  props: ["city"],
  data() {
    return {};
  },

  created() {
    // if (this.city) {
    //   // console.log(this.city);
    //   window.WIDGET = {
    //     // eslint-disable-next-line prettier/prettier
    //     "CONFIG": {
    //       // eslint-disable-next-line prettier/prettier
    //       "layout": "1",
    //       // eslint-disable-next-line prettier/prettier
    //       "width": 450,
    //       // eslint-disable-next-line prettier/prettier
    //       "height": 100,
    //       // eslint-disable-next-line prettier/prettier
    //       "background": "1",
    //       // eslint-disable-next-line prettier/prettier
    //       "dataColor": "FFFFFF",
    //       // eslint-disable-next-line prettier/prettier
    //       "borderRadius": "10",
    //       // eslint-disable-next-line prettier/prettier
    //       "key": "1ec52d33c0c94eddac88ab9f695e3426",
    //       // eslint-disable-next-line prettier/prettier
    //       "city": this.city,
    //     }
    //   };
    // } else {
    window.WIDGET = {
      // eslint-disable-next-line prettier/prettier
        "CONFIG": {
        // eslint-disable-next-line prettier/prettier
          "layout": "1",
        // eslint-disable-next-line prettier/prettier
          "width": 840,
        // eslint-disable-next-line prettier/prettier
          "height": 180,
        // eslint-disable-next-line prettier/prettier
          "background": "1",
        // eslint-disable-next-line prettier/prettier
          "dataColor": "FFFFFF",
        // eslint-disable-next-line prettier/prettier
          "borderRadius": "10",
        // eslint-disable-next-line prettier/prettier
          "key": "1ec52d33c0c94eddac88ab9f695e3426",
        // eslint-disable-next-line prettier/prettier
        "city": this.city
      }
      // };
    };
    let script = document.createElement("script");
    script.type = "text/javascript";
    script.src =
      "https://widget.qweather.net/standard/static/js/he-standard-common.js?v=2.0";
    document.getElementsByTagName("head")[0].appendChild(script);
  }
};
</script>
<style scoped></style>
