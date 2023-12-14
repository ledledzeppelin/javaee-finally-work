<template>
  <div>
  <el-descriptions title="用户信息">
    <el-descriptions-item label="用户名">{{this.list[0].memberAccount}}</el-descriptions-item>
    <el-descriptions-item label="姓名">{{this.list[0].memberName}}</el-descriptions-item>
    <el-descriptions-item label="年龄">{{this.list[0].memberAge}}</el-descriptions-item>
    <el-descriptions-item label="联系方式">{{this.list[0].memberPhone}}</el-descriptions-item>
    <el-descriptions-item label="身高">{{this.list[0].memberHeight}}</el-descriptions-item>
    <el-descriptions-item label="体重">{{this.list[0].memberWeight}}</el-descriptions-item>
    <el-descriptions-item label="办卡时间">{{this.list[0].cardTime}}</el-descriptions-item>
    <el-descriptions-item label="办卡时间">{{this.list[0].cardNextClass}}</el-descriptions-item>


    <el-descriptions-item label="备注">
      <el-tag size="small">学校</el-tag>
    </el-descriptions-item>

  </el-descriptions>

  <el-button plain @click="open = true">编辑个人信息</el-button>



    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="this.result[0]"  label-width="80px">

        <el-form-item label="用户名" prop="memberAccount">
          <el-input v-model="result[0].memberAccount" disabled />
        </el-form-item>
        <el-form-item label="姓名" prop="memberName">
          <el-input v-model="result[0].memberName" />
        </el-form-item>
        <el-form-item label="年龄" prop="memberAge">
          <el-input v-model="result[0].memberAge" />
        </el-form-item>
        <el-form-item label="联系方式" prop="memberPhone">
          <el-input v-model="result[0].memberPhone"  />
        </el-form-item>
        <el-form-item label="身高" prop="memberHeight">
          <el-input v-model="result[0].memberHeight"  />
        </el-form-item>
        <el-form-item label="体重" prop="memberWeight">
          <el-input v-model="result[0].memberWeight"  />
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel()">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<!--memberAccount	202009867
memberPassword	"123456"
memberName	"张三"
memberGender	"女"
memberAge	24
memberHeight	182
memberWeight	60
memberPhone	13515548482
cardTime	"2022-06-05"
cardClass	40
cardNextClass	40-->

<!--使用 axios 进行 HTTP 请求，与后端接口进行通信。
组件加载时，通过 mounted 生命周期钩子调用 init 和 toUpdateEmployee 方法，获取用户信息并填充到列表和编辑表单中。
init 方法用于初始化用户信息展示。
toUpdateEmployee 方法用于初始化用户信息编辑表单。
cancel 方法用于关闭编辑弹出窗口。
submitForm 方法用于提交编辑后的用户信息，并重新加载数据。-->
<script>

import axios from "axios";

export default {
  name: "apply",
  data(){
    return{
      list:[],
      res:'',
      open: false,
      result:[],
      title: "",
      addopen: false,
      addResult:{},
      paramName:''

    }
  },
  mounted() {
    var paramName = this.$route.query.paramName;
    this.init(paramName)
    this.toUpdateEmployee(paramName)


  },
  methods:{
    init(name){
      console.log(this.paramName);
      axios.get("http://localhost:8888/member/toUpdateMember/"+name).then(res=>{
        this.list = res.data
        this.paramName = name
      })
    },
    toUpdateEmployee(name){
      axios.get("http://localhost:8888/member/toUpdateMember/"+ name).then(res=>{
          this.result = res.data


      }).catch(error=>{
        if (error.response.status == '404'){
          this.$message('查无此人');
        }
      })
    },
    cancel(){
      this.open = false
    },
    submitForm(){
      this.open = false
      axios.put("http://localhost:8888/member/updateMember/",this.result[0]).then(res=>{
        var paramName = this.$route.query.paramName;
        this.init(paramName)
        this.toUpdateEmployee(paramName)
        this.$message('更新成功!');
      })
    },

  }
}
</script>

<style scoped>

</style>
