<template>
  <el-menu :default-openeds="['1', '3']" style="min-height: 100%; overflow-x: hidden"
           background-color="rgb(48, 65, 86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
  >
    <div style="height: 60px; line-height: 60px; text-align: center">
      <img src="../assets/logo.png" alt="" style="width: 20px; position: relative; top: 5px; right: 5px">
      <b style="color: white" v-show="logoTextShow">选课系统</b>
    </div>

    <el-menu-item index="/home">
      <template slot="title">
        <i class="el-icon-house"></i>
        <span slot="title">主页</span>
      </template>
    </el-menu-item>

    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span slot="title">功能模块</span>
      </template>
      <el-menu-item index="/student">
        <template slot="title">学生信息管理</template>
      </el-menu-item>
      <el-menu-item index="/course">
        <template slot="title">课程信息管理</template>
      </el-menu-item>
      <el-menu-item index="/department">
        <template slot="title">学院信息管理</template>
      </el-menu-item>
      <el-menu-item index="/teacher">
        <template slot="title">教师信息管理</template>
      </el-menu-item>
      <el-menu-item index="/openclass">
        <template slot="title">开课表信息管理</template>
      </el-menu-item>
      <el-menu-item index="/electclass">
        <template slot="title">选课表信息管理</template>
      </el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props:{
    isCollapse:Boolean,
    logoTextShow:Boolean,
  }
}
</script>

<style scoped>

</style>