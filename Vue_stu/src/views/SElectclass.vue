<template>
  <div>
    <div style="margin-bottom: 30px">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/s' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>课表管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入课程号" suffix-icon="el-icon-search" v-model="couId"></el-input>
      <el-input style="width: 200px" placeholder="请输入课程名" suffix-icon="el-icon-message" class="ml-5" v-model="cname"></el-input>
      <el-input style="width: 200px" placeholder="请输入教师号" suffix-icon="el-icon-search" class="ml-5" v-model="teaId"></el-input>
      <el-input style="width: 200px" placeholder="请输入教师名" suffix-icon="el-icon-message" class="ml-5" v-model="tname"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-5" type="warning" @click="reset">清空</el-button>
<!--      <el-select class = "selectxq" v-model="xq" placeholder="请选择学期" clearable>-->
<!--        <el-option label="秋季学期" value="秋季学期"></el-option>-->
<!--        <el-option label="冬季学期" value="冬季学期"></el-option>-->
<!--        <el-option label="春季学期" value="春季学期"></el-option>-->
<!--        <el-option label="夏季学期" value="夏季学期"></el-option>-->
<!--      </el-select>-->
      <el-radio-group class = "selectxq" v-model="xq" @change="load">
        <el-radio-button label="秋" value="秋季学期" ></el-radio-button>
        <el-radio-button label="冬" value="冬季学期"></el-radio-button>
        <el-radio-button label="春" value="春季学期"></el-radio-button>
        <el-radio-button label="夏" value="夏季学期"></el-radio-button>
      </el-radio-group>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'" @selection-change="handleSelectionChange">
      <el-table-column prop="couId" label="课程号" width="120">
      </el-table-column>
      <el-table-column prop="cname" label="课程名" width="120">
      </el-table-column>
      <el-table-column prop="teaId" label="教师号" width="140">
      </el-table-column>
      <el-table-column prop="tname" label="教师名" width="120">
      </el-table-column>
      <el-table-column prop="xq" label="开课学期" width="120">
      </el-table-column>
      <el-table-column prop="time" label="开课时间" width="120">
      </el-table-column>
      <el-table-column prop="cj" label="成绩">
      </el-table-column>
      <el-table-column label="操作"  width="240" align="center">
        <template slot-scope="scope">
          <el-button type="warning" v-if="scope.row.cj == null" @click="delClass(scope.row)">退课 <i class="el-icon-edit"></i></el-button>
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


    <el-dialog title="新增课程信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="form" size="small ">
        <el-form-item label="学号" :label-width="formLabelWidth">
          <el-input v-model="form.couId" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="课程号" :label-width="formLabelWidth">
          <el-input v-model="form.couId" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="教师号" :label-width="formLabelWidth">
          <el-input v-model="form.teaId" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="开课学期" :label-width="formLabelWidth">
          <el-select v-model="form.xq" placeholder="职能">
            <el-option label="秋季学期" value="秋季学期"></el-option>
            <el-option label="冬季学期" value="冬季学期"></el-option>
            <el-option label="春季学期" value="春季学期"></el-option>
            <el-option label="夏季学期" value="夏季学期"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开课时间" :label-width="formLabelWidth">
          <el-select v-model="form.time" placeholder="请选择学院">
            <el-option label="1-2" value="1-2"></el-option>
            <el-option label="3-4" value="3-4"></el-option>
            <el-option label="5-6" value="5-6"></el-option>
            <el-option label="7-8" value="7-8"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="成绩" :label-width="formLabelWidth">
          <el-input v-model="form.cj" autocomplete="off"></el-input>
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
      stuId: "",
      teaId: "",
      couId: "",
      tname:"",
      cname:"",
      xq:"秋",  //课程学期
      time:"",  //课程时间
      cj: "",  //成绩
      multipleSelection: [],
      dialogFormVisible: false,
      form: {},
      formLabelWidth: "80px",
      user:localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{},
      radio:localStorage.getItem("radio")?JSON.parse(localStorage.getItem("radio")):{},
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      this.request.get("http://localhost:9090/electclass/page",{
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          stuId: this.user.stuId,
          couId: this.couId,
          cname:this.cname,
          teaId: this.teaId,
          tname:this.tname,
          xq:this.xq,
        }
      }).then(res =>{
        console.log(res)
        console.log(this.user)
        this.tableData = res.records
        this.total = res.total
      })
    },
    reset(){ //重置搜索框
      this.stuId = ""
      this.teaId = ""
      this.couId = ""
      this.cname = ""
      this.tname = ""
      this.load()
    },
    Edit(row){
      this.form = Object.assign({},row)  //该方法相比下一行，会使得修改数据的时候不会立刻刷新页面
      //this.form = row
      this.dialogFormVisible = true
    },
    del(teaId){
      this.request.delete("http://localhost:9090/electclass/" + teaId).then(res => {
        if (res) {
          this.$message.success("删除成功")
          this.load()
        } else {
          this.$message.error("删除失败")
        }
      })
    },

    delClass(row){
      console.log("退课信息：")
      console.log(row.couId,row.teaId)
      this.request.delete('http://localhost:9090/electclass/delcourse/' + this.user.stuId + "/" + row.couId + "/" + row.teaId).then(res =>{
        if(!res){
          console.log(res)
          this.$message.success("退课成功")
          this.load()
        }else{
          this.$message.error("退课失败")
        }
      })
    },

    handleSelectionChange(val){
      this.multipleSelection = val
    },
    delBatch() {
      let ids = this.multipleSelection.map(v => v.teaId)  // [{}, {}, {}] => [1,2,3]
      this.request.post("http://localhost:9090/electclass/del/batch", ids).then(res => {
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
      this.request.post("http://localhost:9090/electclass", this.form).then(res => {
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

.selectxq{
  margin-left: 300px;
}
</style>