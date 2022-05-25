<template>
  <div>
    <div style="margin-bottom: 30px">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入学号" suffix-icon="el-icon-search" v-model="stuId"></el-input>
      <el-input style="width: 200px" placeholder="请输入姓名" suffix-icon="el-icon-message" class="ml-5" v-model="name"></el-input>
      <el-input style="width: 200px" placeholder="请输入学院" suffix-icon="el-icon-position" class="ml-5" v-model="dname"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-5" type="warning" @click="reset">清空</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="stuadd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
<!--      <el-popconfirm-->
<!--          class="ml-5"-->
<!--          confirm-button-text='确定'-->
<!--          cancel-button-text='我再想想'-->
<!--          icon="el-icon-info"-->
<!--          icon-color="red"-->
<!--          title="您确定批量删除这些数据吗？"-->
<!--          @confirm="delBatch"-->
<!--      >-->
<!--        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>-->
<!--      </el-popconfirm>-->
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'" @selection-change="handleSelectionChange">
<!--      <el-table-column-->
<!--          type="selection"-->
<!--          width="55">-->
<!--      </el-table-column>-->
      <el-table-column prop="stuId" label="学号" width="140">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="120">
      </el-table-column>
      <el-table-column prop="dname" label="院系" width="120">
      </el-table-column>
      <el-table-column prop="username" label="账号" width="120">
      </el-table-column>
      <el-table-column prop="passwd" label="密码">
      </el-table-column>
      <el-table-column label="操作"  width="240" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="Edit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class = "ml-5"
              confirm-button-text='确认删除'
              cancel-button-text='取消'
              icon="el-icon-info"
              icon-color="red"
              title="注意！删除该学生会同时删去该学生的所有选课记录！"
              @confirm="del(scope.row.stuId)"
          >
            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 4, 6, 8, 10]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>


    <el-dialog title="新增/修改学生信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="form" size="small ">
        <el-form-item label="学号" :label-width="formLabelWidth" v-if="this.add == true">
          <el-input v-model="form.stuId" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="姓名" :label-width="formLabelWidth">
          <el-input v-model="form.name" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="性别" :label-width="formLabelWidth">
          <el-select v-model="form.sex" placeholder="请选择性别">
            <el-option label="男" value="男"></el-option>
            <el-option label="女" value="女"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="学院" :label-width="formLabelWidth">
          <el-select v-model="form.depId" placeholder="请选择学院">
            <el-option
                v-for="item in depoptions"
                :key="item.depId"
                :label="item.dname"
                :value="item.depId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="账号" :label-width="formLabelWidth">
          <el-input v-model="form.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" :label-width="formLabelWidth">
          <el-input v-model="form.passwd" autocomplete="off"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="stu_save" v-if="add === true">确定添加</el-button>
        <el-button type="primary" @click="stu_update" v-if="add === false">确定更新</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Student",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      stuId: "",
      name: "",
      sex: "",
      depId: "",
      dname: "",
      username: "",
      passwd: "",
      multipleSelection: [],
      msg: "hello",
      dialogFormVisible: false,
      form: {},
      add: true,
      updstuId:"",
      updusername:"",
      formLabelWidth: "80px",
      depoptions:[],
      delmsg: ""
    }
  },
  created() {
    this.load()
    this.loaddep()
  },
  methods:{
    load(){
      this.request.get("http://localhost:9090/student/page",{
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          stuId: this.stuId,
          name: this.name,
          depId: this.depId,
          dname: this.dname,
        }
      }).then(res =>{
        console.log(res)

        this.tableData = res.records
        this.total = res.total
      })
    },
    loaddep(){
      this.request.get("http://localhost:9090/department").then(res =>{
        console.log(res)
        this.depoptions = res
      })
    },
    reset(){ //重置搜索框
      this.stuId = ""
      this.name = ""
      this.depId = ""
      this.dname = ""
      this.load()
    },
    Edit(row){
      this.updstuId = row.stuId
      this.updusername = row.username
      this.add = false
      this.form = Object.assign({},row)  //该方法相比下一行，会使得修改数据的时候不会立刻刷新页面
      //this.form = row
      this.dialogFormVisible = true
    },
    del(stuId){
      //删除学生的信息需要先删除选课表中该学生未登录成绩的项
      this.request.delete("http://localhost:9090/electclass/delstu/" + stuId).then(res =>{
        if (res.code == 200){
          this.msg = res.msg
          this.request.delete("http://localhost:9090/student/" + stuId).then(res => {
            if (res) {
              this.$message.success("删除成功," + this.msg)
              this.load()
            } else {
              this.$message.error("删除失败")
            }
          })
        }
        else {
          this.$message.error("删除失败")
        }
      })






    },

    handleSelectionChange(val){
      this.multipleSelection = val
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.stuId)  // [{}, {}, {}] => [1,2,3]
      this.request.post("http://localhost:9090/student/del/batch", ids).then(res => {
        if (res) {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },

    //学生信息添加
    stuadd(){
      this.add = true
      this.dialogFormVisible = true
      this.form = {}
    },
    stu_save(){
      this.request.post("http://localhost:9090/student", this.form).then(res => {
        if (res.code == 200) {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else if(res.code == 602) {
          this.$message.error("保存失败:" + res.msg)
        } else if(res.code == 603) {
          this.$message.error("保存失败:" + res.msg)
        }
      })
    },
    stu_update(){
      this.request.post("http://localhost:9090/student/update/" + this.updstuId + "/" + this.updusername, this.form).then(res => {
        if (res.code == 200) {
          this.$message.success("更新成功")
          this.dialogFormVisible = false
          this.load()
        } else if(res.code == 602) {
          this.$message.error("保存失败:" + res.msg)
        } else if(res.code == 603) {
          this.$message.error("保存失败:" + res.msg)
        }
      })
    },


    handleSizeChange(pageSize){
      this.pageSize = pageSize
      this.load()
    },
    handleCurrentChange(pageNum){
      this.pageNum = pageNum
      this.load()
    },
  }
}
</script>

<style>
.headerBg {
    background: #eee!important;
  }
</style>