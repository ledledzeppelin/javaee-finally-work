<template>
<div>
  <el-row>
  <el-breadcrumb-item :to="{ path: '/vipMain' }">首页</el-breadcrumb-item>
  <el-breadcrumb-item><a href="/">我的课程</a></el-breadcrumb-item>
  </el-row>

  <el-divider></el-divider>
  <el-row>
    <el-table
      :data="list"
      style="width: 100%">
      <el-table-column
        prop="classOrderId"
        label="编号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="className"
        label="课程名"
        width="180">
      </el-table-column>
      <el-table-column
        prop="classBegin"
        label="课程时间">
      </el-table-column>
      <el-table-column
        prop="coach"
        label="教练">
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="danger" plain
          @click="handleDelete(scope.row)"
          icon="el-icon-delete"
          >退课</el-button>

        </template>
      </el-table-column>







    </el-table>
  </el-row>

</div>
<!--使用 axios 进行 HTTP 请求，与后端接口进行通信。
组件加载时，通过 mounted 生命周期钩子调用 init 方法，获取用户课程信息并填充到列表中。
init 方法用于初始化用户课程列表。
handleDelete 方法用于处理用户退课操作，发送 DELETE 请求到后端。-->
</template>

<script>
import axios from "axios";

export default {
  name: "myCourse",
  data(){
    return{
      list:[],
      res:'',
      open: false,
      result:[],
      title: "",
      addopen: false,
      addResult:{},
      paramName:'',
     
    }
  },
  mounted() {
    var paramName = this.$route.query.paramName;
    this.init(paramName)



  },
  methods:{
    init(name){
      var data = {memberAccount:name}
  
      console.log(this.paramName);
      axios.post("http://localhost:8888/user/userClass",data).then(res=>{
        this.list = res.data

      })
    },
    handleDelete(row){

      axios.delete("http://localhost:8888/user/delUserClass/"+row.classOrderId).then(res=>{
        this.$message('退课成功!');
        var paramName = this.$route.query.paramName;
        this.init(paramName)
      })
    }



  }
}
</script>

<style scoped>

</style>
