<template>
  <div>

    <el-row :gutter="100">
      <el-col :span="5"><el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item><a href="/">器械管理</a></el-breadcrumb-item>
      </el-breadcrumb>
      </el-col>
      <el-col :span="1" ><el-button type="primary" @click="addopen = true">新增</el-button></el-col>
      <el-col :span="5" :push="5"> <el-input placeholder="请输入账号" v-model="res"></el-input></el-col>
      <el-col :span="3" :push="4"><el-button type="primary"  v-on:click="toUpdateEquipment()">搜索</el-button></el-col>

      <el-col :span="3" :push="3"><el-button plain @click="reload()">重置</el-button></el-col>
    </el-row>

    <el-divider></el-divider>
    <el-row>
      <el-table
        :data="list"
        style="width: 100%">
        <el-table-column
          prop="equipmentId"
          label="编号"
          width="180">
        </el-table-column>
        <el-table-column
          prop="equipmentName"
          label="名称"
          width="180">
        </el-table-column>
        <el-table-column
          prop="equipmentLocation"
          label="位置">
        </el-table-column>
        <el-table-column
          prop="equipmentStatus"
          label="状态">
        </el-table-column>
        <el-table-column
          prop="equipmentMessage"
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

        <el-form-item label="名称" prop="equipmentName">
          <el-input v-model="result[0].equipmentName" placeholder="请输入间隔区间" />
        </el-form-item>
        <el-form-item label="位置" prop="equipmentLocation">
          <el-input v-model="result[0].equipmentLocation" placeholder="请输入普通患者间隔时间" />
        </el-form-item>
        <el-form-item label="状态" prop="equipmentStatus">
          <el-input v-model="result[0].equipmentStatus" placeholder="请输入普通患者间隔时间" />
        </el-form-item>
        <el-form-item label="信息" prop="equipmentMessage">
          <el-input v-model="result[0].equipmentMessage" placeholder="请输入普通患者间隔时间" />
        </el-form-item>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>


    <!--equipmentId	1
    equipmentName	"哑铃1"
    equipmentLocation	"1号房间"
    equipmentStatus	"正常"
    equipmentMessage	""
    -->

    <el-dialog :title="title" :visible.sync="addopen" width="500px" append-to-body>
      <el-form ref="form" :model="addResult"  label-width="80px">

        <el-form-item label="名称" prop="equipmentName">
          <el-input v-model="addResult.equipmentName" placeholder="请输入姓名" />
        </el-form-item>
        <el-form-item label="位置" prop="equipmentLocation">
          <el-input v-model="addResult.equipmentLocation" placeholder="请输入年龄" />
        </el-form-item>
        <el-form-item label="状态" prop="equipmentStatus">
          <el-input v-model="addResult.equipmentStatus" placeholder="请输入身高" />
        </el-form-item>
        <el-form-item label="信息" prop="equipmentMessage">
          <el-input v-model="addResult.equipmentMessage" placeholder="请输入体重" />
        </el-form-item>


      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="addSubmitForm">确 定</el-button>
        <el-button @click="addCancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>

</template>
<!--使用 axios 与后端接口进行通信。
init 方法在组件挂载时调用，用于初始化器械列表。
toUpdateEquipment 方法根据用户输入进行搜索。
reload 方法用于重置搜索条件并重新加载列表。
handleDelete 和 handleUpdate 方法用于处理器械信息的删除和更新。
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
    }
  },
  mounted() {
    this.init()
  },
  methods:{
    init(){
      axios.get("http://localhost:8888/equipment/selEquipment").then(res=>{
        this.list = res.data
      })
    },
    toUpdateEquipment(){
      axios.get("http://localhost:8888/equipment/toUpdateEquipment/"+this.res).then(res=>{
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
      const param = row.equipmentId
      console.log(param)
      this.$confirm('是否确认删除会员编号为"' + param + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        axios.delete("http://localhost:8888/equipment/delEquipment/"+param).then(res=>{
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
      axios.get("http://localhost:8888/equipment/toUpdateEquipment/"+row.equipmentId).then(res=>{
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
      axios.put("http://localhost:8888/equipment/updateEquipment/",this.result[0]).then(res=>{
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
      axios.post("http://localhost:8888/equipment/addEquipment/",this.addResult).then(res=>{
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
