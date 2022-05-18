<template>
  <div>
    <div class="wrapper">
      <div style="margin: 200px auto; background-color: #fff; width: 350px; height: 350px; padding: 20px; border-radius: 10px">
        <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>登 录</b></div>
        <el-form :model="user" :rules="rules" ref="userForm">
          <el-form-item prop="username">
            <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.username"></el-input>
          </el-form-item>
          <el-form-item prop="passwd">
            <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-passwd v-model="user.passwd"></el-input>
          </el-form-item>
          <el-radio v-model="radio" label="1">教师/管理员</el-radio>
          <el-radio v-model="radio" label="2">学生</el-radio>
          <el-form-item style="margin: 10px 0; text-align: right">
            <el-button type="primary" size="small"  autocomplete="off" @click="login">登录</el-button>
            <el-button type="warning" size="small"  autocomplete="off">注册</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      user: {},
      radio: "1",
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        passwd: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
      }
    }
  },
  methods: {
    login() {
      this.$refs['userForm'].validate((valid) => {
        if (valid) {  // 表单校验合法
          let path =""
          if (this.radio ==  "1"){
            path = "http://localhost:9090/teacher/login"
          }
          else if(this.radio == "2"){
            path = "http://localhost:9090/student/login"
          }
          this.request.post(path, this.user).then(res => {
            if(!res) {
              this.$message.error("用户名或密码错误")
            } else {
              console.log(res)
              this.$message.success("登录成功")
              localStorage.setItem("user",JSON.stringify(res))
              localStorage.setItem("radio",this.radio)
              if(this.radio ==  "1"){
                this.$router.push("/")
              }
              else if(this.radio == "2"){
                this.$router.push("/s")
              }

            }
          })
        } else {
          return false;
        }
      });
    }
  }
}
</script>

<style>
.wrapper {
    height: 100vh;
    background-image: linear-gradient(to bottom right, #FC466B , #3F5EFB);
    overflow: hidden;
  }
</style>