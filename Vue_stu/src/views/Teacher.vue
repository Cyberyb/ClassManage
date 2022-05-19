<template>
  <div>
    <div style="margin-bottom: 30px">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>教师管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入教师号" suffix-icon="el-icon-search" v-model="teaId"></el-input>
      <el-input style="width: 200px" placeholder="请输入教师姓名" suffix-icon="el-icon-message" class="ml-5" v-model="tname"></el-input>
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
      <el-table-column prop="teaId" label="教师号" width="140">
      </el-table-column>
      <el-table-column prop="tname" label="教师名" width="120">
      </el-table-column>
      <el-table-column prop="dname" label="所在院系" width="120">
      </el-table-column>
      <el-table-column prop="username" label="账号名" width="120">
      </el-table-column>
      <el-table-column prop="role" label="职能">
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
              @confirm="del(scope.row.teaId)"
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


    <el-dialog title="新增教师信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="form" size="small ">
        <el-form-item label="教师号" :label-width="formLabelWidth">
          <el-input v-model="form.teaId" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="教师名" :label-width="formLabelWidth">
          <el-input v-model="form.tname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="职能" :label-width="formLabelWidth">
          <el-select v-model="form.role" placeholder="职能">
            <el-option label="教师" value="教师"></el-option>
            <el-option label="管理员" value="管理员"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="所在学院" :label-width="formLabelWidth">
          <el-select v-model="form.depId" placeholder="请选择学院">
            <el-option label="计算机学院" value="1"></el-option>
            <el-option label="理学院" value="2"></el-option>
            <el-option label="通信学院" value="3"></el-option>
            <el-option label="机电学院" value="4"></el-option>
            <el-option label="文学院" value="5"></el-option>
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
  name: "Course",
  data() {
    return {
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      teaId: "",
      username:"",
      passwd: "",
      role: "",
      tname: "",
      depId: "",
      dname:"",
      multipleSelection: [],
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
      this.request.get("http://localhost:9090/teacher/page",{
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          teaId: this.teaId,
          tname: this.tname,
        }
      }).then(res =>{
        console.log(res)

        this.tableData = res.records
        this.total = res.total
      })
    },
    reset(){ //重置搜索框
      this.teaId = ""
      this.tname = ""
      this.load()
    },
    Edit(row){
      this.form = Object.assign({},row)  //该方法相比下一行，会使得修改数据的时候不会立刻刷新页面
      //this.form = row
      this.dialogFormVisible = true
    },
    del(teaId){
      this.request.delete("http://localhost:9090/teacher/" + teaId).then(res => {
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
      let ids = this.multipleSelection.map(v => v.teaId)  // [{}, {}, {}] => [1,2,3]
      this.request.post("http://localhost:9090/teacher/del/batch", ids).then(res => {
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
      this.request.post("http://localhost:9090/teacher", this.form).then(res => {
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