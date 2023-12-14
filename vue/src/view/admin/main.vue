<template>
<div id="app">
  <el-container style="height: 100%; border: 10px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <h5>管理员</h5>
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">

          <router-link to="/vip">
            <el-menu-item index="1" >
              <i class="el-icon-menu"></i>
              <span slot="title">会员管理</span>
            </el-menu-item>
          </router-link>
          <router-link to="/employee">
          <el-menu-item index="2" >
            <i class="el-icon-document"></i>
            <span slot="title">员工管理</span>
          </el-menu-item>
          </router-link>
          <router-link to="/equipment">
          <el-menu-item index="3">
            <i class="el-icon-setting"></i>
            <span slot="title">器材管理</span>
          </el-menu-item>
          </router-link>
          <router-link to="/course">
            <el-menu-item index="4">
              <i class="el-icon-setting"></i>
              <span slot="title">课程管理</span>
            </el-menu-item>
          </router-link>
        </el-menu>
    </el-aside>


    <el-container>
      <el-header style="text-align: right; font-size: 12px">
        <el-dropdown>
          <i class="el-icon-setting" style="margin-right: 15px"></i>
          <el-dropdown-menu slot="dropdown">
            <el-dropdown-item>
              <el-link @click="fnLogout">注销</el-link>


            </el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
        <span>{{user.adminAccount}}</span>
      </el-header>

      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</div>
</template>

<script>
import axios from "axios";
export default {
  name: "main",
  data() {
    return {
      user : [] =this.$route.query.user,
      list:[]
    }
  },
  methods:{
    fnLogout(){
      this.$router.push({
        path: "/",
      });
    }
  }
};
</script>

<style >
html, body {
  height: 100%;
  margin: 0;
  padding: 0;
  overflow: hidden;
}

#app {
  height: 100%;
}
.full-height {
  height: 100%;
}
.el-container {
  height: 100%;
}
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}
</style>
