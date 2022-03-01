module.exports = {
  publicPath: "/",
  outputDir: "dist",
  devServer: {
    open: true,
    host: "localhost",
    port: "10000",
    proxy: {
      "/api": {
        target: "http://localhost:8000", // 要请求的地址
        ws: true,
        changeOrigin: true,
        pathRewrite: {
          "^/api": ""
        }
      }
    }
  }

  // chainWebpack: config => {
  //   // 查看打包文件体积大小
  //   config
  //     .plugin("webpack-bundle-analyzer")
  //     .use(require("webpack-bundle-analyzer").BundleAnalyzerPlugin);
  // }
};
