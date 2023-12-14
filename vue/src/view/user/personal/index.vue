<template>
<div>
  <el-row>
    <el-breadcrumb-item :to="{ path: '/vipMain' }">首页</el-breadcrumb-item>
    <el-breadcrumb-item><a href="/">课程预约</a></el-breadcrumb-item>
  </el-row>

  <el-divider></el-divider>
  <el-row>
    <el-table
      :data="list"
      style="width: 100%">
      <el-table-column
        prop="classId"
        label="编号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="className"
        label="课程名称"
        width="180">
      </el-table-column>
      <el-table-column
        prop="classBegin"
        label="时间">
      </el-table-column>
      <el-table-column
        prop="classTime"
        label="课程时长">
      </el-table-column>
      <el-table-column
        prop="coach"
        label="教练">
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="success" icon="el-icon-edit"   @click="add(scope.row)" plain>报名</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-row>





  <el-dialog title="确认报名信息" :visible.sync="open" width="500px" append-to-body>
    <el-form ref="form" :model="list"  label-width="80px">

      <el-form-item label="姓名" prop="memberName">
        <el-input v-model="addResult.memberName" placeholder="请输入姓名" />
      </el-form-item>
      <el-form-item label="年龄" prop="memberAge">
        <el-input v-model="addResult.memberAge" placeholder="请输入年龄" />
      </el-form-item>
      <el-form-item label="身高" prop="memberHeight">
        <el-input v-model="addResult.memberHeight" placeholder="请输入身高" />
      </el-form-item>
      <el-form-item label="体重" prop="memberWeight">
        <el-input v-model="addResult.memberWeight" placeholder="请输入体重" />
      </el-form-item>
      <el-form-item label="联系方式" prop="memberPhone">
        <el-input v-model="addResult.memberPhone" placeholder="请输入联系方式" />
      </el-form-item>
      <el-form-item label="课程" prop="cardClass">
        <el-input v-model="addResult.cardClass" placeholder="请输入课程" />
      </el-form-item>
      <el-form-item label="剩余课程" prop="memberPhone">
        <el-input v-model="addResult.cardNextClass" placeholder="请输入剩余课程" />
      </el-form-item>
      <el-form-item label="性别" prop="memberGender">
        <el-radio-group v-model="addResult.memberGender">
          <el-radio :label="option" :key="option" v-for="option in adjustOptions">
            {{ option }}
          </el-radio>
        </el-radio-group>
      </el-form-item>

    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button type="primary" @click="addSubmitForm">确 定</el-button>
      <el-button @click="addCancel">取 消</el-button>
    </div>
  </el-dialog>
</div>
<!--使用 axios 发送 HTTP 请求，与后端接口进行通信。
组件加载时，通过 mounted 生命周期钩子调用 init 方法来获取课程列表。
init 方法用于初始化课程列表。
add 方法用于处理课程报名操作。
课程报名功能
当用户点击报名按钮 (<el-button type="success" @click="add(scope.row)">) 时，触发 add 方法。
add 方法首先获取用户信息，然后根据选中的课程和用户信息发送 POST 请求进行报名。
报名成功后，显示消息提示，并重新加载课程列表。-->
</template>

<script>
import axios from "axios";

export default {
  name: "personal",
  data(){
    return{
      list:[],
      res:'',
      open: false,
      result:[],
      title: "",
      addopen: false,
      addResult:{},
      member:[]
    }
  },
  mounted() {
    this.init()
  },
  methods:{
    init(){
      axios.get("http://localhost:8888/user/applyClass").then(res=>{
        this.list = res.data
      })
    },


  add(row){
    var paramName = this.$route.query.paramName;
    axios.get("http://localhost:8888/member/toUpdateMember/"+paramName).then(res=>{
      this.member = res.data
      var data = {memberAccount:paramName , memberName:this.member[0].memberName}
      axios.post("http://localhost:8888/user/applyClass/"+row.classId,data).then(res=>{
        this.$message('报名成功!');
        this.init()
      })
    })


  },
}
}

</script>

<style scoped>

</style>
