<template>
  <div>

    <el-row :gutter="100">
      <el-col :span="5"><el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item><a href="/">课程管理</a></el-breadcrumb-item>
      </el-breadcrumb>
      </el-col>
<!--      <el-col :span="1" ><el-button type="primary" @click="addopen = true">新增</el-button></el-col>-->
      <el-col :span="5" :push="5"> <el-input placeholder="请输入账号" v-model="res"></el-input></el-col>
      <el-col :span="3" :push="4"><el-button type="primary"  v-on:click="selClassOrder()">搜索</el-button></el-col>

      <el-col :span="3" :push="3"><el-button plain @click="reload()">重置</el-button></el-col>
    </el-row>

    <el-divider></el-divider>
    <!--显示页面-->
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
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
            >报名信息</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>

<!--classId	1
className	"增肌"
classBegin	"2023年1月1日 15:00"
classTime	"60分钟"
coach	"增肌教练"
-->










<!--
classOrderId	2
classId	2
className	"瑜伽"
coach	"瑜伽教练"
memberName	"王五"
memberAccount	202132539
classBegin	"2023年1月2日 10:20"
有两个 <el-dialog>，一个用于展示具体课程的报名信息，另一个用于添加新的课程信息。
-->
<!--修改-->
    <el-dialog :title="title" :visible.sync="open" width="700px" append-to-body>
      <el-table
        :data="result"
        style="width: 100%">
        <el-table-column
          prop="classOrderId"
          label="订单编号">
        </el-table-column>
        <el-table-column
          prop="classId"
          label="课程编号">
        </el-table-column>
        <el-table-column
          prop="className"
          label="课程名">
        </el-table-column>
        <el-table-column
          prop="coach"
          label="教练">
        </el-table-column>
        <el-table-column
          prop="memberName"
          label="会员名">
        </el-table-column>
        <el-table-column
          prop="memberAccount"
          label="会员卡号">
        </el-table-column>
        <el-table-column
          prop="classBegin"
          label="课程开始时间">
        </el-table-column>

      </el-table>
      <div slot="footer" class="dialog-footer">
<!--        <el-button type="primary" @click="submitForm">确 定</el-button>-->
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>



<!--新增-->
    <el-dialog :title="title" :visible.sync="addopen" width="500px" append-to-body>
      <el-form ref="form" :model="addResult"  label-width="80px">

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

</template>
<!--使用 axios 发送 HTTP 请求到后端接口。
init 方法在组件挂载时调用，用于初始化课程列表。
selClassOrder 方法用于根据用户输入的账号搜索课程。
reload 方法重置搜索栏并重新加载课程列表。
handleDelete 和 handleUpdate 方法处理课程信息的删除和更新。
cancel, submitForm, addCancel, addSubmitForm 方法处理对话框的关闭和表单提交-->
<script>
import axios from "axios";

export default {
  name: "employee",
  data(){
    return{
      list:[],
      res:'',
      open: false,
      result:[],
      title: "",
      addopen: false,
      addResult:{},
    }
  },
  mounted() {
    this.init()
  },
  methods:{
    init(){
      axios.get("http://localhost:8888/class/selClass").then(res=>{
        this.list = res.data
      })
    },
    selClassOrder(){
      axios.get("http://localhost:8888/class/selClassOrder/"+this.res).then(res=>{
        if (res.status == "200"){
          this.list = res.data
        }else {
          this.$message('查无此人');
        }

      }).catch(error=>{
        if (error.response.status == '404'){
          this.$message('查无此人');
        }
      })
    },
    reload(){
      this.init()
      this.res = ''
    },
    handleDelete(row){
      const param = row.classId
      console.log(param)
      this.$confirm('是否确认删除课程编号为"' + param + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        axios.delete("http://localhost:8888/class/delClass/"+param).then(res=>{
          console.log(res);
          if (res.status == '200') {
            this.init()
            this.$message('删除成功');
          }
        }).catch(error => {
          if (error.response.status == '404'){
            this.init()
            this.$message('删除成功');
          }
        })
      })
    },
    handleUpdate(row){
      axios.get("http://localhost:8888/class/selClassOrder/"+row.classId).then(res=>{
        this.result = res.data
        this.open = true

      })
    },
    cancel(){
      this.open = false
    },
    submitForm(){
      this.open = false
      axios.put("http://localhost:8888/classTime/updateEmployee/",this.result[0]).then(res=>{
        this.init()
        this.$message('更新成功!');
      })
    },
    addCancel(){
      this.addopen = false
      this.addResult = []
    },
    addSubmitForm(){
      this.addopen = false
      axios.post("http://localhost:8888/employee/addEmployee/",this.addResult).then(res=>{
        this.addResult = []
        this.init()
        this.$message('添加成功!');
      })
    }
  }
}
</script>

<style scoped>

</style>
