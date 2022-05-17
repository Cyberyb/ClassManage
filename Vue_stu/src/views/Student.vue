<template>
  <div>
    <div style="margin-bottom: 30px">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>用户管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入学号" suffix-icon="el-icon-search" v-model="stuId"></el-input>
      <el-input style="width: 200px" placeholder="请输入姓名" suffix-icon="el-icon-message" class="ml-5" v-model="name"></el-input>
      <el-input style="width: 200px" placeholder="请输入学院" suffix-icon="el-icon-position" class="ml-5" v-model="depId"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-5" type="warning" @click="reset">清空</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="stuadd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
      <el-popconfirm
          class="ml-5"
          confirm-button-text='确定'
          cancel-button-text='我再想想'
          icon="el-icon-info"
          icon-color="red"
          title="您确定批量删除这些数据吗？"
          @confirm="delBatch"
      >
        <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
      </el-popconfirm>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'" @selection-change="handleSelectionChange">
      <el-table-column
          type="selection"
          width="55">
      </el-table-column>
      <el-table-column prop="stuId" label="学号" width="140">
      </el-table-column>
      <el-table-column prop="name" label="姓名" width="120">
      </el-table-column>
      <el-table-column prop="sex" label="性别" width="120">
      </el-table-column>
      <el-table-column prop="depId" label="院系" width="120">
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
              confirm-button-text='好的'
              cancel-button-text='不用了'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
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
          :page-sizes="[2, 4, 6, 8]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>


    <el-dialog title="新增学生信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="form" size="small ">
        <el-form-item label="学号" :label-width="formLabelWidth">
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
          <el-select v-model="form.dId" placeholder="请选择学院">
            <el-option label="计算机学院" value="计算机学院"></el-option>
            <el-option label="通信学院" value="通信学院"></el-option>
            <el-option label="文学院" value="文学院"></el-option>
            <el-option label="理学院" value="理学院"></el-option>
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
        <el-button type="primary" @click="stu_save">确 定</el-button>
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
      username: "",
      passwd: "",
      multipleSelection: [],
      msg: "hello",
      dialogFormVisible: false,
      form: {},
      formLabelWidth: "80px"
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      this.request.get("http://localhost:9090/student/page",{
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          stuId: this.stuId,
          name: this.name,
          depId: this.depId
        }
      }).then(res =>{
        console.log(res)

        this.tableData = res.records
        this.total = res.total
      })
    },
    reset(){ //重置搜索框
      this.stuId = ""
      this.name = ""
      this.depId = ""
      this.load()
    },
    Edit(row){
      this.form = Object.assign({},row)  //该方法相比下一行，会使得修改数据的时候不会立刻刷新页面
      //this.form = row
      this.dialogFormVisible = true
    },
    del(stuId){
      this.request.delete("http://localhost:9090/student/" + stuId).then(res => {
        if (res) {
          this.$message.success("删除成功")
          this.load()
        } else {
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
      this.dialogFormVisible = true
      this.form = {}
    },
    stu_save(){
      this.request.post("http://localhost:9090/student", this.form).then(res => {
        if (res) {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
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