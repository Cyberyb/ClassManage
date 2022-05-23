import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import './assets/gloable.css'
import request from "@/utils/request";
import * as echarts from "echarts"

Vue.use(ElementUI);
Vue.config.productionTip = false
Vue.prototype.request = request
//Vue.prototype.echarts = echarts

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
