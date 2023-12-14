<template>
  <div>
    <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
      <h3 class="login-title">欢迎登录管理员管理系统</h3>
      <el-form-item label="账号" prop="username">
        <el-input type="text" placeholder="请输入账号" v-model="form.adminAccount"/>
      </el-form-item>
      <el-form-item label="密码" prop="pwd">
        <el-input type="password" placeholder="请输入密码" v-model="form.adminPassword"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" v-on:click="onSubmit">登录</el-button>
        <el-button type="primary" v-on:click="toVip">客户登录</el-button>
      </el-form-item>

    </el-form>

    <el-dialog
      title="温馨提示"
      :visible.sync="dialogVisible"
      width="30%"
    >
      <!--      :before-close="handleClose"-->

      <span>请输入账号和密码</span>
      <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
      </span>
    </el-dialog>



  </div>
</template>

<script>

import axios from "axios";
export default {
  name: "login",
  data(){
    return {
      form: {
        adminAccount: '1001',
        adminPassword: '123456'
      },
    }
  },
  methods:{
    onSubmit(){
        axios.post("http://localhost:8888/adminLogin",this.form).then(res=>{
          console.log(res)
            if (res.status == '200'){
              this.$router.push({
                path: "/main",
                query: {user: this.form}
              });
            }
        })
      },
    toVip(){
      this.$router.push({
        path: "/vipLogin",
      });
    }
    }
  }

</script>

<style >
.login-box {
  border: 1px solid #DCDFE6;
  width: 350px;
  margin: 180px auto;
  padding: 35px 35px 15px 35px;
  border-radius: 5px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  box-shadow: 0 0 25px #909399;
}

.login-title {
  text-align: center;
  margin: 0 auto 40px auto;
  color: #303133;
}
</style>
