<template>
  <div class="boatTrip">
    <div id="he-plugin-standard"></div>
  </div>
</template>

<script>
export default {
  name: "boatTrip",

  data() {
    return {};
  },

  methods: {},
  created() {
    window.WIDGET = {
      // eslint-disable-next-line prettier/prettier
      "CONFIG": {
        // eslint-disable-next-line prettier/prettier
        "layout": "1",
        // eslint-disable-next-line prettier/prettier
        "width": 450,
        // eslint-disable-next-line prettier/prettier
        "height": 200,
        // eslint-disable-next-line prettier/prettier
        "background": "1",
        // eslint-disable-next-line prettier/prettier
        "dataColor": "FFFFFF",
        // eslint-disable-next-line prettier/prettier
        "borderRadius": "10",
        // eslint-disable-next-line prettier/prettier
        "key": "e98b9251ff634caba978b9e9902cb0b8"  
      }
    };
    let script = document.createElement("script");
    script.type = "text/javascript";
    script.src =
      "https://widget.qweather.net/standard/static/js/he-standard-common.js?v=2.0";
    document.getElementsByTagName("head")[0].appendChild(script);
  }
};
</script>
<style scoped>
/* .boatTrip {
  height: 500px;
  width: 50vw;
}

#he-plugin-standard {
  height: 450px;
  width: 450px;
} */
</style>
