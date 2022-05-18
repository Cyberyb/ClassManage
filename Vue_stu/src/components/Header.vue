<template>
  <div style="font-size: 12px;line-height: 60px; display: flex">
    <div style="flex: 1; font-size: 20px">
      <span :class="collapseBtnClass" style="cursor: pointer" @click="collapse"></span>
    </div>
    <el-dropdown style="width: 150px; cursor: pointer">
      <span>您好！{{myname}}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      <el-dropdown-menu slot="dropdown" style="width: 100px; text-align:center">
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <router-link to="/login" style="text-decoration: none">退出</router-link>
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
export default {
  name: "Header",
  props:{
    collapseBtnClass: String,
    collapse: Function,
  },
  data(){
    return{
      user:localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{},
      radio:localStorage.getItem("radio")?JSON.parse(localStorage.getItem("radio")):{},
      myname: "",
    }
  },
  created() {
    this.myname = this.radio == "1"? this.user.tname : this.user.name
  }
}
</script>

<style scoped>

</style>