<template>
  <div>
    <div style="margin-bottom: 30px">
      <el-breadcrumb separator="/">
        <el-breadcrumb-item :to="{ path: '/home' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>学院管理</el-breadcrumb-item>
      </el-breadcrumb>
    </div>

    <div style="margin: 10px 0">
      <el-input style="width: 200px" placeholder="请输入学院号" suffix-icon="el-icon-search" v-model="depId"></el-input>
      <el-input style="width: 200px" placeholder="请输入学院名称" suffix-icon="el-icon-message" class="ml-5" v-model="dname"></el-input>
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
      <el-table-column prop="depId" label="学院号" width="140">
      </el-table-column>
      <el-table-column prop="dname" label="学院名称" width="120">
      </el-table-column>
      <el-table-column prop="adr" label="地址" width="200">
      </el-table-column>
      <el-table-column prop="tel" label="联系电话">
      </el-table-column>
      <el-table-column label="操作"  width="240" align="center">
        <template slot-scope="scope">
          <el-button type="success" @click="Edit(scope.row)">编辑 <i class="el-icon-edit"></i></el-button>
<!--          <el-popconfirm-->
<!--              class = "ml-5"-->
<!--              confirm-button-text='好的'-->
<!--              cancel-button-text='不用了'-->
<!--              icon="el-icon-info"-->
<!--              icon-color="red"-->
<!--              title="您确定删除吗？"-->
<!--              @confirm="del(scope.row.depId)"-->
<!--          >-->
<!--            <el-button type="danger" slot="reference">删除 <i class="el-icon-remove-outline"></i></el-button>-->
<!--          </el-popconfirm>-->
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
        <el-form-item label="学院号" :label-width="formLabelWidth">
          <el-input v-model="form.depId" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="学院名" :label-width="formLabelWidth">
          <el-input v-model="form.dname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="地址" :label-width="formLabelWidth">
            <el-input v-model="form.adr" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="联系方式" :label-width="formLabelWidth">
          <el-input v-model="form.tel" autocomplete="off"></el-input>
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
      depId: "",
      adr: "",
      dname: "",
      tel: "",
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
      this.request.get("http://localhost:9090/department/page",{
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          depId: this.depId,
          dname: this.dname,
        }
      }).then(res =>{
        console.log(res)

        this.tableData = res.records
        this.total = res.total
      })
    },
    reset(){ //重置搜索框
      this.depId = ""
      this.dname = ""
      this.depId = ""
      this.load()
    },
    Edit(row){
      this.form = Object.assign({},row)  //该方法相比下一行，会使得修改数据的时候不会立刻刷新页面
      //this.form = row
      this.dialogFormVisible = true
    },
    del(depId){
      this.request.delete("http://localhost:9090/department/" + depId).then(res => {
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
      let ids = this.multipleSelection.map(v => v.depId)  // [{}, {}, {}] => [1,2,3]
      this.request.post("http://localhost:9090/department/del/batch", ids).then(res => {
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
      this.request.post("http://localhost:9090/department", this.form).then(res => {
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