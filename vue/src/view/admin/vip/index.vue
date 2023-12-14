<template>
  <div>

    <el-row :gutter="100">
      <el-col :span="5"><el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/main' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item><a href="/">会员管理</a></el-breadcrumb-item>
      </el-breadcrumb>
      </el-col>
      <el-col :span="1" ><el-button type="primary" @click="addopen = true">新增</el-button></el-col>
      <el-col :span="5" :push="5"> <el-input placeholder="请输入账号" v-model="res"></el-input></el-col>
      <el-col :span="3" :push="4"><el-button type="primary"  v-on:click="selByCard()">搜索</el-button></el-col>

      <el-col :span="3" :push="3"><el-button plain @click="reload()">重置</el-button></el-col>
    </el-row>

    <el-divider></el-divider>
    <el-row>
      <el-table
        :data="list"
        style="width: 100%">
        <el-table-column
          prop="memberAccount"
          label="会员账号"
          width="180">
        </el-table-column>
        <el-table-column
          prop="memberName"
          label="姓名"
          width="180">
        </el-table-column>
        <el-table-column
          prop="memberGender"
          label="性别">
        </el-table-column>
        <el-table-column
          prop="memberAge"
          label="年龄">
        </el-table-column><el-table-column
        prop="memberHeight"
        label="身高">
      </el-table-column><el-table-column
        prop="memberWeight"
        label="体重">
      </el-table-column><el-table-column
        prop="memberPhone"
        label="联系方式">
      </el-table-column><el-table-column
        prop="cardClass"
        label="购买课时">
      </el-table-column><el-table-column
        prop="cardNextClass"
        label="剩余课时">
      </el-table-column><el-table-column
        prop="cardTime"
        label="办理时间">
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

        <el-form-item label="姓名" prop="memberName">
          <el-input v-model="result[0].memberName" placeholder="请输入间隔区间" />
        </el-form-item>
        <el-form-item label="年龄" prop="memberAge">
          <el-input v-model="result[0].memberAge" placeholder="请输入普通患者间隔时间" />
        </el-form-item>
        <el-form-item label="身高" prop="memberHeight">
          <el-input v-model="result[0].memberHeight" placeholder="请输入普通患者间隔时间" />
        </el-form-item>
        <el-form-item label="体重" prop="memberWeight">
          <el-input v-model="result[0].memberWeight" placeholder="请输入普通患者间隔时间" />
        </el-form-item>
        <el-form-item label="联系方式" prop="memberPhone">
          <el-input v-model="result[0].memberPhone" placeholder="请输入普通患者间隔时间" />
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>




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
<!--使用 axios 进行 HTTP 请求，与后端接口进行通信。
init 方法在组件挂载时调用，用于初始化会员列表。
selByCard 方法根据用户输入的账号搜索特定会员信息。
reload 方法用于重置搜索条件并重新加载会员列表。
handleDelete 和 handleUpdate 方法用于处理会员信息的删除和更新。
cancel, submitForm, addCancel, addSubmitForm 方法用于处理弹出窗口的关闭和表单提交。-->
<script>
import axios from "axios";
export default {
  name: "vip",
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
        axios.get("http://localhost:8888/member/selMember").then(res=>{
          this.list = res.data
        })
      },
    selByCard(){
      axios.get("http://localhost:8888/member/selByCard/"+this.res).then(res=>{
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
        const param = row.memberAccount
      console.log(param)
      this.$confirm('是否确认删除会员编号为"' + param + '"的数据项?', "警告", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning"
      }).then(function() {
        axios.delete("http://localhost:8888/member/delMember/"+param).then(res=>{
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
      axios.get("http://localhost:8888/member/selByCard/"+row.memberAccount).then(res=>{
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
      axios.put("http://localhost:8888/member/updateMember/",this.result[0]).then(res=>{
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
      axios.post("http://localhost:8888/member/addMember/",this.addResult).then(res=>{
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
