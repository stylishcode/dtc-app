import Vue from 'vue'
import App from './App.vue'
import BootstrapVue from 'bootstrap-vue'

// import 'spectre.css/dist/spectre.min.css'
// import 'spectre.css/dist/spectre-icons.min.css'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.config.productionTip = false
Vue.use(BootstrapVue)

new Vue({
  render: h => h(App),
}).$mount('#app')
