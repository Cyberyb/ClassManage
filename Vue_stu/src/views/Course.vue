<template>
  <div>
    <div style="margin-bottom: 30px">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>课程管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入课程号" suffix-icon="el-icon-search" v-model="couId"></el-input>
      <el-input style="width: 200px" placeholder="请输入课程名" suffix-icon="el-icon-message" class="ml-5" v-model="cname"></el-input>
      <el-input style="width: 200px" placeholder="请输入学院名" suffix-icon="el-icon-message" class="ml-5" v-model="dname"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-5" type="warning" @click="reset">清空</el-button>
    </div>

    <div style="margin: 10px 0">
      <el-button type="primary" @click="couadd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
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
      <el-table-column prop="couId" label="课程号" width="140">
      </el-table-column>
      <el-table-column prop="cname" label="课程名" width="120">
      </el-table-column>
      <el-table-column prop="credit" label="学分" width="120">
      </el-table-column>
      <el-table-column prop="dname" label="院系">
      </el-table-column>
      <el-table-column label="操作"  width="350" align="center">
        <template slot-scope="scope">
          <el-button type="primary" @click="opencourse(scope.row)">开课 <i class="el-icon-circle-plus-outline"></i></el-button>
          <el-button type="success" @click="editcourse(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
          <el-popconfirm
              class = "ml-5"
              confirm-button-text='好的'
              cancel-button-text='不用了'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.couId)"
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

<!--添加新课程的对话框-->
    <el-dialog title="新增课程信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="form" size="small ">
        <el-form-item label="课号" :label-width="formLabelWidth">
          <el-input v-model="form.couId" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="课程名" :label-width="formLabelWidth">
          <el-input v-model="form.cname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="学分" :label-width="formLabelWidth">
            <el-input v-model="form.credit" autocomplete="off"></el-input>
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

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="cou_save">确 定</el-button>
      </div>
    </el-dialog>

<!--开课的对话框-->
    <el-dialog title="新增开课信息" :visible.sync="dialogForm2Visible" width="30%">
      <el-form :model="form" size="small ">
        <el-form-item label="教授教师" :label-width="formLabelWidth">
          <el-select v-model="formopen.teaId" placeholder="请选择教师">
            <el-option
                v-for="item in teaoptions"
                v-if="item.role != '管理员'"
                :key="item.teaId"
                :label="item.tname"
                :value="item.teaId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开课学期" :label-width="formLabelWidth">
          <el-select v-model="formopen.xq" placeholder="请选择学期">
            <el-option label="秋季学期" value="秋季学期"></el-option>
            <el-option label="冬季学期" value="冬季学期"></el-option>
            <el-option label="春季学期" value="春季学期"></el-option>
            <el-option label="夏季学期" value="夏季学期"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="开课日期" :label-width="formLabelWidth">
          <el-select v-model="formopen.ftime" placeholder="日期">
            <el-option label="星期一" value="一"></el-option>
            <el-option label="星期二" value="二"></el-option>
            <el-option label="星期三" value="三"></el-option>
            <el-option label="星期四" value="四"></el-option>
            <el-option label="星期五" value="五"></el-option>
          </el-select>
          <el-select v-model="formopen.ltime" placeholder="时间">
            <el-option label="1-2" value="1-2"></el-option>
            <el-option label="3-4" value="3-4"></el-option>
            <el-option label="5-6" value="5-6"></el-option>
            <el-option label="7-8" value="7-8"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="课程容量" label-width="formLabelWidth">
          <el-input v-model="formopen.uplim" autocomplete="off"></el-input>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogForm2Visible = false">取 消</el-button>
        <el-button type="primary" @click="opencourse_save">确 定</el-button>
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
      couId: "",
      credit: "",
      cname: "",
      depId: "",
      dname:"",
      multipleSelection: [],
      dialogFormVisible: false,
      dialogForm2Visible: false,
      form: {
      },
      formopen:{
      },
      formLabelWidth: "80px",
      depoptions:[],
      teaoptions:[],
    }
  },
  created() {
    this.load()
    this.loaddep()
  },
  methods:{
    load(){
      this.request.get("http://localhost:9090/course/page",{
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          couId: this.couId,
          cname: this.cname,
          dname: this.dname
        }
      }).then(res =>{
        console.log(res)
        console.log(res.records)
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

    //根据学院号返回对应的Teacher list
    loadtea(depId){
      this.request.get("http://localhost:9090/teacher/opencourse/" + depId).then(res =>{
        console.log(res)
        this.teaoptions = res
      })
    },
    reset(){ //重置搜索框
      this.couId = ""
      this.cname = ""
      this.dname = ""
      this.load()
    },

    //修改课程
    editcourse(row){
      this.form = Object.assign({},row)  //该方法相比下一行，会使得修改数据的时候不会立刻刷新页面
      //this.form = row
      this.dialogFormVisible = true
    },

    //开课
    opencourse(row){
      this.loadtea(row.depId)
      this.formopen = Object.assign({},row)  //该方法相比下一行，会使得修改数据的时候不会立刻刷新页面
      console.log(this.formopen)
      this.dialogForm2Visible = true
    },

    del(couId){
      this.request.delete("http://localhost:9090/course/" + couId).then(res => {
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
      let ids = this.multipleSelection.map(v => v.couId)  // [{}, {}, {}] => [1,2,3]
      this.request.post("http://localhost:9090/course/del/batch", ids).then(res => {
        if (res) {
          this.$message.success("批量删除成功")
          this.load()
        } else {
          this.$message.error("批量删除失败")
        }
      })
    },

    //课程信息添加
    couadd(){
      this.dialogFormVisible = true
      this.form = {}
    },

    //课程信息插入||跟新
    cou_save(){
      this.request.post("http://localhost:9090/course", this.form).then(res => {
        if (res) {
          this.$message.success("保存成功")
          this.dialogFormVisible = false
          this.load()
        } else {
          this.$message.error("保存失败")
        }
      })
    },
    opencourse_save(){
      console.log(this.formopen)
      this.formopen.time = this.formopen.ftime + this.formopen.ltime
      console.log(this.formopen.time)
      this.request.post("http://localhost:9090/openclass/" +
          this.formopen.couId + "/" +
          this.formopen.teaId + "/" +
          this.formopen.xq + "/" +
          this.formopen.time + "/" +
          this.formopen.uplim + "/").then(res => {
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