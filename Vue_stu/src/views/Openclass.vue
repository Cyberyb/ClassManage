<template>
  <div>
    <div style="margin-bottom: 30px">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>开课表管理</el-breadcrumb-item>
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

    <div style="margin: 10px 0">
<!--      <el-button type="primary" @click="stuadd">新增 <i class="el-icon-circle-plus-outline"></i></el-button>-->
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
      <el-table-column prop="stunum" label="已选人数" width="120">
      </el-table-column>
      <el-table-column prop="uplim" label="容量上限">
      </el-table-column>
      <el-table-column label="操作"  width="240" align="center">
        <template slot-scope="scope">
<!--          <el-button type="success" @click="Edit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>-->
          <el-popconfirm
              class = "ml-5"
              confirm-button-text='好的'
              cancel-button-text='不用了'
              icon="el-icon-info"
              icon-color="red"
              title="您确定删除吗？"
              @confirm="del(scope.row.couId,scope.row.teaId)"
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
          :page-sizes="[2, 4, 6, 8,10]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>


    <el-dialog title="新增课程信息" :visible.sync="dialogFormVisible" width="30%">
      <el-form :model="form" size="small ">
        <el-form-item label="教师号" :label-width="formLabelWidth">
          <el-input v-model="form.teaId" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="课程号" :label-width="formLabelWidth">
          <el-input v-model="form.couId" autocomplete="off"></el-input>
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
      formLabelWidth: "80px"
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
          // cname:this.cname,
          // tname:this.tname,
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
    Edit(row){
      this.form = Object.assign({},row)  //该方法相比下一行，会使得修改数据的时候不会立刻刷新页面
      //this.form = row
      this.dialogFormVisible = true
    },
    del(couId,teaId){
      this.request.delete("http://localhost:9090/openclass/" + couId + "/" + teaId).then(res => {
        if (res == true) {
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
      this.request.post("http://localhost:9090/openclass/del/batch", ids).then(res => {
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
      this.request.post("http://localhost:9090/openclass", this.form).then(res => {
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