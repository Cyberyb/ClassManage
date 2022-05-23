<template>
  <div>
    <div style="margin-bottom: 30px">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/t' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>我的课程</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div style="margin: 10px 0">
        <el-select v-model="form.couId" placeholder="请选择课程" @change="loadPage()">
          <el-option
              v-for="item in Courseoptions"
              :key="item.couId"
              :label="item.cname"
              :value="item.couId">
          </el-option>
        </el-select>
      <el-input style="width: 200px" placeholder="请输入学号" suffix-icon="el-icon-message" class="ml-5" v-model="stuId"></el-input>
      <el-button class="ml-5" type="primary" @click="load">搜索</el-button>
      <el-button class="ml-5" type="warning" @click="reset">清空</el-button>
    </div>

    <div id="cards">
    <el-card class="box-card" style="width: 620px">
    <el-table :data="tableData" border stripe :header-cell-class-name="'headerBg'" @selection-change="handleSelectionChange" style="width: 561px">
      <el-table-column prop="stuId" label="学号" width="120">
      </el-table-column>
      <el-table-column prop="name" label="学生" width="120">
      </el-table-column>
      <el-table-column prop="xq" label="开课学期" width="120">
      </el-table-column>
      <el-table-column prop="time" label="开课时间" width="120">
      </el-table-column>
      <el-table-column prop="cj" label="成绩" width="80">
      </el-table-column>
    </el-table>
    </el-card>
      <el-card class="box-card" style="width: 620px;margin-left: 100px">
        <el-descriptions title="班级信息">
          <el-descriptions-item label="课程号">{{this.form.couId}}</el-descriptions-item>
          <el-descriptions-item label="课程名称">{{this.cardtext.cname}}</el-descriptions-item>
          <el-descriptions-item label="开课学期">{{this.cardtext.xq}}</el-descriptions-item>
          <el-descriptions-item label="开课时间">{{this.cardtext.xq}}</el-descriptions-item>
<!--          <el-descriptions-item label="开课时间">-->
<!--            <el-tag size="small">学校</el-tag>-->
<!--          </el-descriptions-item>-->
          <el-descriptions-item label="选课人数">{{this.classinfo.sumofstu}}</el-descriptions-item>
          <el-descriptions-item label="统分情况">{{this.classinfo.gradenum}}/{{this.classinfo.sumofstu}}</el-descriptions-item>
          <el-descriptions-item label="及格人数">{{this.classinfo.goodnum}}/{{this.classinfo.gradenum}}</el-descriptions-item>
          <el-descriptions-item label="优秀人数">{{this.classinfo.greatnum}}/{{this.classinfo.gradenum}}</el-descriptions-item>
        </el-descriptions>
      </el-card>
    </div>
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

  </div>
</template>

<script>
export default {
  name: "Course",
  data() {
    return {
      tableData: [],
      Courseoptions:[],
      total: 0,
      pageNum: 1,
      pageSize: 10,
      stuId: "",
      name:"",
      teaId: "",
      couId: "",
      cname:"",
      xq:"",  //课程学期
      time:"",  //课程时间
      cj: "",  //成绩
      multipleSelection: [],
      dialogFormVisible: false,
      form: {},
      formLabelWidth: "80px",
      user:localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{},
      radio:localStorage.getItem("radio")?JSON.parse(localStorage.getItem("radio")):{},
      cardtext:{},
      classinfo:{}
    }
  },
  created() {
    this.load()
    this.loadmycourse()
  },
  methods:{
    load(){
      this.request.get("http://localhost:9090/electclass/page",{
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          stuId: this.stuId,
          couId: this.form.couId,
          teaId: this.user.teaId,
        }
      }).then(res =>{
        //console.log(res)
        //console.log(this.user)
        this.tableData = res.records
        this.total = res.total
        //console.log(this.tableData)
      })
    },
    loadmycourse(){
      this.request.get("http://localhost:9090/openclass/" + this.user.teaId).then(res =>{
        this.Courseoptions = res
      })
    },
    loadClassinfo(){
      this.request.get("http://localhost:9090/electclass/classinfo/" + this.user.teaId +"/"+ this.cardtext.couId).then(res =>{
        this.classinfo = res
        console.log(res)
      })
    },
    loadPage(){
      let len = this.Courseoptions.length
      for(let i = 0;i<len;i++){
        if(this.Courseoptions[i].couId == this.form.couId){
          this.cardtext = this.Courseoptions[i]
          break
        }
      }
      setTimeout(()=>{
        this.loadClassinfo()
      },80)
      setTimeout(()=>{
        this.load()
      },80)
    },
    reset(){ //重置搜索框
      this.stuId = ""
      this.form.couId = ""
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

#cards{
  display: flex;
}
</style>