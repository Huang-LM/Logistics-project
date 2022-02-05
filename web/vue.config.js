module.exports = {
  publicPath: "/",
  outputDir: "dist",
  devServer: {
    open: true,
    host: "localhost",
    port: "10000",
    proxy: {
      "/api": {
        target: "http://localhost:22818", // 要请求的地址
        ws: true,
        changeOrigin: true,
        pathRewrite: {
          "^/api": ""
        }
      }
    }
  }
};
