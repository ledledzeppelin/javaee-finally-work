<template>
  <el-container style="height: 200%; border: 10px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <h5>用户</h5>
        <el-menu
          default-active="2"
          class="el-menu-vertical-demo"
          @open="handleOpen"
          @close="handleClose"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">

          <router-link :to="{ path: '/apply', query: { paramName: user.memberAccount } }">
            <el-menu-item index="1" >
              <i class="el-icon-menu"></i>
              <span slot="title">个人信息</span>
            </el-menu-item>
          </router-link>
          <router-link :to="{ path: '/myCourse', query: { paramName: user.memberAccount } }">
            <el-menu-item index="2" >
              <i class="el-icon-document"></i>
              <span slot="title">我的课程</span>
            </el-menu-item>
          </router-link>
          <router-link :to="{ path: '/personal', query: { paramName: user.memberAccount } }">
            <el-menu-item index="3" >
              <i class="el-icon-document"></i>
              <span slot="title">课程预约</span>
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
        <span>{{user.memberAccount}}</span>
      </el-header>

      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "vipMain",
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
}
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
