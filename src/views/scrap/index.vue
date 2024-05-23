<template>
  <div>
    <el-row :gutter="20" class="header">
      <!-- <el-col :span="7">
        <el-input v-model="input3" placeholder="请输入关键词" class="input-with-select">
          <template #append>
            <el-select v-model="select" placeholder="查询选项 " style="width: 115px" class=" ">
              <el-option label="账号" value="1" />
              <el-option label="电话号" value="2" />
              <el-option label="编号" value="3" />
            </el-select>
          </template>
        </el-input>
      </el-col> -->
      <!-- <el-button type="primary" :icon="Search" @click="initGetUser">搜索</el-button> -->
      <el-button type="primary" @click="handelDialogValue">添加</el-button>
    </el-row>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column :prop="item.prop" :label="$t(`table.${item.label}`)" v-for="(item, index) in options" :key="index">
        <template #default="{ row }" v-if="item.prop === 'address'">
          <el-button size="small" @click="Edit(row)">编辑</el-button>
          <el-button size="small" type="danger" @click="deleteUid(row)">删除</el-button>
        </template></el-table-column>
    </el-table>
    <el-pagination
      v-model:current-page="queryFrom.page"
      :page-size="queryFrom.count"
      :small="small"
      :disabled="disabled"
      :background="background"
      layout="total, prev, pager, next"
      :total="total"
      @current-change="handleCurrentChange"
    />
    <Dialog v-model="dialogVisible" v-if="dialogVisible" @initGetClass="initGetClass" />
    <DialogEdit :uidId="AccountUid" v-model="dialogEdit" v-if="dialogEdit" @initGetClass="initGetClass" />
  </div>
</template>
  
<script setup>
import { ref } from 'vue'
import { getScrapRecords, deleteScrapRecords } from '../../api/scrap/index'
import Dialog from './components/dialog.vue'
import DialogEdit from './components/dialogeEdit.vue'
import { useI18n } from 'vue-i18n'

const i18n = useI18n()
const AccountUid = ref(null)
let total = ref(0); // 总数据量，用于计算分页

const handleCurrentChange = (page) => {
  queryFrom.value.page = 1
  queryFrom.value.page = page
  initGetUnit();
};
//修改
const dialogEdit = ref(false)

const Edit = (row) => {
  dialogEdit.value =true
  console.log(row.uid)
  AccountUid.value = row.uid
}
//添加
const dialogVisible = ref(false)
const handelDialogValue = () => {
  dialogVisible.value = true
}



const queryFrom = ref({
  page: 1,
  count: 6
})
const tableData = ref([])


const initGetClass = async () => {
  const res = await getScrapRecords(queryFrom.value)
  console.log(res)
  tableData.value = res.data.records;
  console.log(tableData.value)
  total = res.data.total  

}

initGetClass()


const options = [
  {
    label: 'principal',
    prop: 'username'
  },
  {
    label: 'goodsName',
    prop: 'goodsName'
  },

    // {
    //   label: 'uid',
    //   prop: 'uid'
    // },
  {
    label: 'time',
    prop: 'time'
  },
  {
    label: 'value',
    prop: 'value'
  },
  {
    label: 'address',
    prop: 'address'
  },
]

// 删除
const deleteUid = (row) => {
  console.log(row.uid)
  const data = {
    uid: row.uid
  }
  ElMessageBox.confirm(
    i18n.t('dialog.scrapTitle'),
    '警告',
    {
      confirmButtonText: '删除',
      cancelButtonText: '放弃',
      type: 'warning',
    }
  )
    .then(async () => {
      const res = await deleteScrapRecords(data)
      console.log(res)
      ElMessage({
        type: 'success',
        message: '删除成功',
      })

      initGetClass()
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '删除失败',
      })
    })
}
</script>
  