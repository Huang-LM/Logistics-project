<template>
  <div class="boatTrip">
    <!-- <div id="he-plugin-standard"></div> -->
    <el-row :gutter="10">
      <el-col :span="16">
        <el-card class="weather-card">
          <div slot="header" class="clearfix">
            <span>目的地天气</span>
          </div>
          <weather-standard :city="city"></weather-standard>
        </el-card>
        <el-card class="logistics-card">
          <el-table :data="logisticsData" style="width: 100%;">
            <el-table-column type="expand">
              <template slot-scope="props">
                <el-form
                  label-position="left"
                  inline
                  class="table-expand"
                  style="color: #99a9bf"
                >
                  <el-form-item label="物流状态">
                    <el-tag type="info" v-if="props.row.verify === 0"
                      >待审核</el-tag
                    >
                    <el-tag type="success" v-else-if="props.row.verify === 1"
                      >审核通过</el-tag
                    >
                    <el-tag type="danger" v-else-if="props.row.verify === 2"
                      >未通过审核</el-tag
                    >
                  </el-form-item>
                  <el-form-item label="物流编号">
                    <span>{{ props.row.logistics_number }}</span>
                  </el-form-item>
                  <el-form-item label="运输方式">
                    <span>{{ props.row.logistics_way }}</span>
                  </el-form-item>
                  <el-form-item label="运输工具编号">
                    <span v-if="props.row.logistics_way_number === null"
                      >暂未安排</span
                    >
                    <span v-else>{{ props.row.logistics_way_number }}</span>
                  </el-form-item>
                  <el-form-item label="预计到达时间">
                    <span v-if="props.row.shipping_time === 'Invalid date'"
                      >暂未生成</span
                    >
                    <span v-else>{{ props.row.shipping_time }}</span>
                  </el-form-item>
                  <el-form-item label="货代公司信息">
                    <span
                      >货代公司名称：{{ props.row.mailing_name }} ， 手机号：
                      {{ props.row.mailing_phone }} ， 地址：
                      {{ props.row.mailing_address }}</span
                    >
                  </el-form-item>
                  <el-form-item label="收件人信息">
                    <span
                      >姓名：{{ props.row.shipping_name }} ， 手机号：
                      {{ props.row.shipping_phone }} ， 地址：
                      {{ props.row.shipping_address }}</span
                    >
                  </el-form-item>
                  <el-form-item label="备注">
                    <span>{{ props.row.note }}</span>
                  </el-form-item>
                  <el-form-item label="订单创建时间">
                    <span>{{ props.row.createTime }}</span>
                  </el-form-item>
                </el-form>
              </template>
            </el-table-column>

            <el-table-column label="ID" prop="id"> </el-table-column>
            <el-table-column
              label="物流编号"
              prop="logistics_number"
              min-width="120px"
            >
            </el-table-column>
            <el-table-column label="物流状态" min-width="120px">
              <template v-slot="props">
                <el-dropdown v-if="props.row.verify === 0">
                  <el-button size="mini" split-button type="info">
                    未审核
                  </el-button>
                </el-dropdown>
                <el-button
                  type="success"
                  size="mini"
                  v-else-if="props.row.verify === 1"
                  >审核通过</el-button
                >
                <el-button
                  type="danger"
                  size="mini"
                  v-else-if="props.row.verify === 2"
                  >未通过审核</el-button
                >
              </template>
            </el-table-column>
            <el-table-column
              prop="createTime"
              label="创建时间"
              sortable
              min-width="170"
            >
            </el-table-column>
            <el-table-column
              prop="updateTime"
              label="修改时间"
              sortable
              min-width="170"
            >
            </el-table-column>
            <!-- <el-table-column label="操作" min-width="180" fixed="right">
          <template slot-scope="scope">
            <el-button
              type="primary"
              plain
              v-waves
              size="mini"
              @click="handleEdit(scope.$index, scope.row)"
              >安排运输</el-button
            >
          </template>
        </el-table-column> -->
          </el-table>
          <!-- 分页 -->
          <el-pagination
            layout="sizes, prev, pager, next, total"
            @current-change="handleCurrentChange"
            @size-change="handleSizeChange"
            :current-page="queryInfo.page + 1"
            :page-sizes="[5, 10, 15, 20]"
            :page-size="queryInfo.pageSize"
            :total="total"
            class="pagination"
          >
          </el-pagination>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card class="calendar-card">
          <el-calendar v-model="date" class="calendar"> </el-calendar>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import WeatherStandard from "../../common/weather/weatherStandard.vue";
import service from "@/utils/request";

export default {
  name: "boatTrip",
  components: { WeatherStandard },
  created() {
    this.getGoodsList();
  },

  data() {
    return {
      city: "CN101210101",
      date: new Date(),
      logisticsData: [],
      queryInfo: {
        logistics_way_number: "敦刻尔克号",
        // 当前页数
        page: 0,
        // 当前显示多少行
        pageSize: 5
      },
      total: 0,
      formLabelWidth: "150px"
    };
  },

  methods: {
    getGoodsList() {
      service
        .post("/logisticsInfo/listLogistics", this.queryInfo)
        .then(res => {
          // console.log(res.data);
          const resData = res.data.data;
          // console.log(resData.records);
          this.logisticsData = resData.list;
          this.total = resData.total;
        })
        .catch(err => {
          console.log(err);
          this.$message.error("网络异常");
        });
    },
    // 换页
    handleCurrentChange(val) {
      this.queryInfo.page = val - 1;
      this.getGoodsList();
    },
    handleSizeChange(val) {
      this.queryInfo.pageSize = val;
      this.getGoodsList();
    }
  }
};
</script>
<style scoped>
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}
/* .calendar-card {
  width: 30vw;
  height: 400px;
}
.calendar {
  height: ;
}
.weather-card {
  width: ;
} */
.logistics-card {
  height: 100%;
  margin-top: 20px;
}

.pagination {
  margin-top: 40px;
  text-align: center;
}
.table-expand {
  font-size: 0 !important;
}
.table-expand label {
  width: 90px !important;
  color: #99a9bf !important;
}
.el-form-item__content {
  width: 90px !important;
  color: #99a9bf !important;
}
.table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 100%;
}
</style>
