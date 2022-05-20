<template>
  <div>
    <div style="margin-bottom: 30px">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/s' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>选课查询</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入课程号" suffix-icon="el-icon-search" v-model="couId"></el-input>
      <el-input style="width: 200px" placeholder="请输入教师号" suffix-icon="el-icon-message" class="ml-5" v-model="teaId"></el-input>
      <el-select v-model="xq" placeholder="请选择学期" clearable class="ml-5">
        <el-option label="秋季学期" value="秋季学期"></el-option>
        <el-option label="冬季学期" value="冬季学期"></el-option>
        <el-option label="春季学期" value="春季学期"></el-option>
        <el-option label="夏季学期" value="夏季学期"></el-option>
      </el-select>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-5" type="warning" @click="reset">清空</el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'" @selection-change="handleSelectionChange">
<!--      <el-table-column-->
<!--          type="selection"-->
<!--          width="55">-->
<!--      </el-table-column>-->
      <el-table-column prop="couId" label="课程号" width="140">
      </el-table-column>
      <el-table-column prop="cname" label="课程名称" width="140">
      </el-table-column>
      <el-table-column prop="teaId" label="教师号" width="120">
      </el-table-column>
      <el-table-column prop="tname" label="教师名称" width="120">
      </el-table-column>
      <el-table-column prop="xq" label="开课学期" width="120">
      </el-table-column>
      <el-table-column prop="time" label="开课时间" width="120">
      </el-table-column>
      <el-table-column prop="stunum" label="课程容量" width="120">
      </el-table-column>
      <el-table-column prop="uplim" label="容量上限">
      </el-table-column>
      <el-table-column label="操作"  width="240" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="selectClass(scope.row)">选课 <i class="el-icon-edit"></i></el-button>
        </template>
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageNum"
          :page-sizes="[2, 4, 6, 8,10]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>

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
      pageSize: 8,
      teaId: "",
      couId: "",
      cname:"",
      tname:"",
      xq:"",  //课程学期
      time:"",  //课程时间
      stunum:"", //选课人数
      uplim:"",  //人数上限
      multipleSelection: [],
      dialogFormVisible: false,
      form: {},
      formLabelWidth: "80px",

      //浏览器上存储的登录用户的信息
      user:localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{},
      radio:localStorage.getItem("radio")?JSON.parse(localStorage.getItem("radio")):{},
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      this.request.get("http://localhost:9090/openclass/page",{
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          couId: this.couId,
          teaId: this.teaId,
          xq:this.xq
        }
      }).then(res =>{
        console.log(res)

        this.tableData = res.records
        this.total = res.total
      })
    },
    reset(){ //重置搜索框
      this.teaId = ""
      this.couId = ""
      this.xq = ""
      this.load()
    },
    // Edit(row){
    //   this.form = Object.assign({},row)  //该方法相比下一行，会使得修改数据的时候不会立刻刷新页面
    //   //this.form = row
    //   this.dialogFormVisible = true
    // },
    selectClass(row){
      console.log("选课信息：")
      console.log(row.couId,row.teaId)
      this.request.post('http://localhost:9090/electclass/selectcourse/' + this.user.stuId + "/" +
          row.couId + "/" +
          row.teaId + "/" +
          row.xq + "/" +
          row.time).then(res =>{
        if(res.code == 200){
          this.$message.success("选课成功")
        }else if(res.code == 600){
          this.$message.error("时间冲突选课失败")
        }
      })
      this.load()
    },
    handleSelectionChange(val){
      this.multipleSelection = val
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