<template>
  <div class="weatherSimple">
    <div id="he-plugin-simple"></div>
  </div>
</template>

<script>
export default {
  name: "weatherSimple",
  data() {
    return {};
  },

  methods: {},

  created() {
    window.WIDGET = {
      // eslint-disable-next-line prettier/prettier
        "CONFIG": {
        // eslint-disable-next-line prettier/prettier
          "modules": "01234",
        // eslint-disable-next-line prettier/prettier
          "background": "1",
        // eslint-disable-next-line prettier/prettier
          "tmpColor": "FFFFFF",
        // eslint-disable-next-line prettier/prettier
          "tmpSize": "16",
        // eslint-disable-next-line prettier/prettier
          "cityColor": "FFFFFF",
        // eslint-disable-next-line prettier/prettier
          "citySize": "16",
        // eslint-disable-next-line prettier/prettier
          "aqiColor": "FFFFFF",
        // eslint-disable-next-line prettier/prettier
          "aqiSize": "16",
        // eslint-disable-next-line prettier/prettier
          "weatherIconSize": "24",
        // eslint-disable-next-line prettier/prettier
          "alertIconSize": "18",
        // eslint-disable-next-line prettier/prettier
          "padding": "10px 10px 10px 10px",
        // eslint-disable-next-line prettier/prettier
          "shadow": "0",
        // eslint-disable-next-line prettier/prettier
          "borderRadius": "10",
        // eslint-disable-next-line prettier/prettier
          "language": "auto",
        // eslint-disable-next-line prettier/prettier
          "fixed": "false",
        // eslint-disable-next-line prettier/prettier
          "vertical": "top",
        // eslint-disable-next-line prettier/prettier
          "horizontal": "left",
        // eslint-disable-next-line prettier/prettier
          "key": "fd3a209966124a85a383385740d008f0"
      }
    };
    let script1 = document.createElement("script");
    script1.type = "text/javascript";
    script1.src =
      "https://widget.qweather.net/simple/static/js/he-simple-common.js?v=2.0";
    document.getElementsByTagName("head")[0].appendChild(script1);
  }
};
</script>
<style scoped></style>
