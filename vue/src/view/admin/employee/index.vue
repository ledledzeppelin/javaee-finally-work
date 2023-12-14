<template>
  <div>

    <el-row :gutter="100">
      <el-col :span="5"><el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item><a href="/">员工管理</a></el-breadcrumb-item>
      </el-breadcrumb>
      </el-col>
      <el-col :span="1" ><el-button type="primary" @click="addopen = true">新增</el-button></el-col>
      <el-col :span="5" :push="5"> <el-input placeholder="请输入账号" v-model="res"></el-input></el-col>
      <el-col :span="3" :push="4"><el-button type="primary"  v-on:click="toUpdateEmployee()">搜索</el-button></el-col>

      <el-col :span="3" :push="3"><el-button plain @click="reload()">重置</el-button></el-col>
    </el-row>

    <el-divider></el-divider>
    <el-row>
      <el-table
        :data="list"
        style="width: 100%">
        <el-table-column
          prop="employeeAccount"
          label="账号"
          width="180">
        </el-table-column>
        <el-table-column
          prop="employeeName"
          label="姓名"
          width="180">
        </el-table-column>
        <el-table-column
          prop="employeeGender"
          label="性别">
        </el-table-column>
        <el-table-column
          prop="employeeAge"
          label="年龄">
        </el-table-column>
        <el-table-column
          prop="entryTime"
          label="时间">
        </el-table-column>
        <el-table-column
          prop="staff"
          label="职务">
        </el-table-column>
        <el-table-column
          prop="employeeMessage"
          label="信息">
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
            >修改</el-button>
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


    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="this.result[0]"  label-width="80px">

        <el-form-item label="姓名" prop="employeeName">
          <el-input v-model="result[0].employeeName"  />
        </el-form-item>
        <el-form-item label="年龄" prop="employeeAge">
          <el-input v-model="result[0].employeeAge" />
        </el-form-item>
        <el-form-item label="职务" prop="staff">
          <el-input v-model="result[0].staff" />
        </el-form-item>
        <el-form-item label="信息" prop="employeeMessage">
          <el-input v-model="result[0].employeeMessage"  />
        </el-form-item>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


    <!--
    employeeAccount	101034208
    employeeName	"保洁2"
    employeeGender	"女"
    employeeAge	48
    entryTime	"2010-08-01"
    staff	"保洁员"
    employeeMessage	""
    -->

    <el-dialog :title="title" :visible.sync="addopen" width="500px" append-to-body>
      <el-form ref="form" :model="addResult"  label-width="80px">

        <el-form-item label="姓名" prop="employeeName">
          <el-input v-model="addResult.employeeName" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="年龄" prop="employeeAge">
          <el-input v-model="addResult.employeeAge" placeholder="请输入年龄" />
        </el-form-item>
        <el-form-item label="职务" prop="staff">
          <el-input v-model="addResult.staff" placeholder="请输入身高" />
        </el-form-item>
        <el-form-item label="信息" prop="employeeMessage">
          <el-input v-model="addResult.employeeMessage" placeholder="请输入体重" />
        </el-form-item>
        <el-form-item label="性别" prop="employeeGender">
          <el-radio-group v-model="addResult.employeeGender">
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
<!--使用 axios 来进行 HTTP 请求，与后端接口交互。
init 方法在组件加载时调用，用于获取并显示员工列表。
toUpdateEmployee 方法用于根据用户输入的账号搜索特定员工信息。
reload 方法用于重置搜索并重新加载员工列表。
handleDelete 和 handleUpdate 方法用于处理员工信息的删除和修改。
cancel, submitForm, addCancel, addSubmitForm 方法用于处理弹出窗口的关闭和表单提交。-->
<script>
import axios from "axios";

export default {
  name: "employee",
  data(){
    return{
      list:[],
      res:'',
      open: false,
      result:[{}],
      title: "",
      addopen: false,
      addResult:{},
      adjustOptions:['男','女']
    }
  },
  mounted() {
    this.init()
  },

  methods:{
    init(){
      axios.get("http://localhost:8888/employee/selEmployee").then(res=>{
        this.list = res.data
      })
    },
    toUpdateEmployee(){
      axios.get("http://localhost:8888/employee/toUpdateEmployee/"+this.res).then(res=>{
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
      const param = row.employeeAccount
      console.log(param)
      this.$confirm('是否确认删除会员编号为"' + param + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        axios.delete("http://localhost:8888/employee/delEmployee/"+param).then(res=>{
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
      axios.get("http://localhost:8888/employee/toUpdateEmployee/"+row.employeeAccount).then(res=>{
        this.result = res.data
        this.open = true
        console.log(this.result[0])
      })
    },
    cancel(){
      this.open = false
    },
    submitForm(){
      this.open = false
      axios.put("http://localhost:8888/employee/updateEmployee/",this.result[0]).then(res=>{
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
