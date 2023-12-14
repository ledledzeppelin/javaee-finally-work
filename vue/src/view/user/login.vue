<template>
  <div>
    <el-form ref="loginForm" :model="form" :rules="rules" label-width="80px" class="login-box">
      <h3 class="login-title">欢迎登录客户管理系统</h3>
      <el-form-item label="账号" prop="username">
        <el-input type="text" placeholder="请输入账号" v-model="form.memberAccount"/>
      </el-form-item>
      <el-form-item label="密码" prop="pwd">
        <el-input type="password" placeholder="请输入密码" v-model="form.memberPassword"/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" v-on:click="onSubmit">登录</el-button>
        <el-button type="primary" v-on:click="toAdmin">管理员登录</el-button>
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
  <!--使用 axios 发送 HTTP 请求进行登录验证。
表单数据通过 v-model 绑定到 form 对象，其中包括 memberAccount（会员账号）和 memberPassword（会员密码）。
登录操作
onSubmit 方法在用户点击登录按钮时调用，发送 POST 请求到后端验证用户。
成功登录后，使用 Vue Router 导航到 /vipMain 路径，并传递用户信息作为查询参数。-->
</template>

<script>

import axios from "axios";
export default {
  name: "vipLogin",
  data(){
    return {
      form: {
        memberAccount: '202100788',
        memberPassword: '123456'
      },
    }
  },
  methods:{
    onSubmit(){
      console.log(this.form);
      axios.post("http://localhost:8888/userLogin",this.form).then(res=>{
        console.log(res)

          this.$router.push({
            path: "/vipMain",
            query: {user:this.form}
          });

      })
    },
    toAdmin(){
      this.$router.push({
        path: "/",
      });
    }
  }
}

</script>

<style scoped>
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
