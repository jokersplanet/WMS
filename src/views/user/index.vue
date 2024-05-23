<template>
  <div>
    <el-card>
      <el-row :gutter="20" class="header">
        <el-col :span="7">
          <el-input v-model="input3" placeholder="请输入关键词" class="input-with-select">
            <template #append>
              <el-select v-model="select" placeholder="查询选项 " style="width: 115px">
                <el-option label="账号" value="1" />
                <el-option label="电话号" value="2"  />
                <el-option label="编号" value="3"  />
              </el-select>
            </template>
          </el-input>
        </el-col>
        <el-button type="primary" :icon="Search" @click="initGetUser">搜索</el-button>
        <el-button type="primary" @click="handelDialogValue">添加</el-button>
      </el-row>

      <el-table :data="tableData" stripe style="width: 100%">
        <el-table-column  :prop="item.prop" :label="$t(`table.${item.label}`)" v-for="(item, index) in options"
          :key="index">

          <template #default="{ row }" v-if="item.prop === 'address'">
            <el-button type="info" size="small" @click="Edit(row)">编辑</el-button>
            <el-button type="danger" size="small" @click="deleteUid(row)">删除</el-button>
          </template>

          <template #default="{ row }" v-if="item.prop === 'department'">
             {{ getDepartmentName[row.value] }} 
          </template>

          <template #default="{ row }" v-if="item.prop === 'uid'">
              <a @click="SearchCountValue(row)" style=" cursor: pointer;">{{ row.uid }}</a>
              
          </template>
        </el-table-column>
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

    </el-card>
    <Dialog v-model="dialogVisible" v-if="dialogVisible" @initGetUsersList="initGetUsersList" />
    <DialogAccount :uidId="AccountUid" v-model="dialogAccount" v-if="dialogAccount" />
    <DialogEdit :uidId="AccountUid"  v-model="dialogEdit" v-if="dialogEdit" @initGetUsersList="initGetUsersList"/>
  </div>
</template>
  
<script setup>
import { ref  } from 'vue'
import { Search } from '@element-plus/icons-vue'
import { getUser, deleteAccount } from '../../api/user'
import Dialog from './components/dialog.vue'
import DialogAccount from './components/dialogUid.vue'
import DialogEdit from './components/dialogeEdit.vue'
import { ElMessage } from 'element-plus'
import { options } from './options'
import { searchTelephone, searchUid, searchNumber } from '../../api/search/fuzzy'
import { useI18n } from 'vue-i18n'
import { selectDepartment } from '../../api/department/select'

var getDepartmentName = ref({})

const i18n = useI18n()
let total = ref(0)
const pageSize = 10
const dialogVisible = ref(false)
const dialogAccount = ref(false)
const dialogEdit = ref(false)


const handleCurrentChange = (page) => {
  queryFrom.value.page = 1
  queryFrom.value.page = page
  initGetUsersList();
};


const handelDialogValue = () => {
  dialogVisible.value = true
}
const AccountUid = ref(null)
//查看全部信息
const SearchCountValue = (row) => {
  dialogAccount.value =true
  console.log(row.uid)
  AccountUid.value = row.uid

}

//编辑
const Edit = (row) => {
  dialogEdit.value =true
  console.log(row.uid)
  AccountUid.value = row.uid
}

//展示用户数据
const queryFrom = ref({
  query: '',
  page: 1,
  count: 10
})
const tableData = ref([])

const queryFrom1 = ref({
  page:1,
  count:10
})

const initGetUsersList = async () => {
  const res = await getUser(queryFrom.value)
  console.log(res)
  tableData.value = res.data.records;
  console.log(tableData.value)
  total = res.data.total
  const res1 = await selectDepartment(queryFrom1.value)
  getDepartmentName = res1.data.records
  console.log(getDepartmentName)
}
initGetUsersList()
// console.log(select.value)

//搜索的下拉选择
const initGetUser = async () => {
  let res = null
  if (select.value === '1') { // 选择了 uid
    console.log(select.value)
    const params = {
      uid: input3.value,
      page: 1,
      count: pageSize
    }
    res = await searchUid(params)
    tableData.value = res.data.records;
    
    console.log(tableData)

  } else if (select.value === '2') { // 选择了电话号
    console.log(select.value)
    const params = {
      telephone: input3.value,
      page: 1,
      count: pageSize
    }
    res = await searchTelephone(params)
    console.log(res)
    tableData.value = res.data.records;

  } else if (select.value === '3') { // 选择了编号
    console.log(select.value)
    const params = {
      number: input3.value,
      page: 1,
      count: pageSize
    }
    res = await searchNumber(params)
    tableData.value = res.data.records;

    console.log(res)
  }
  // tableData.value = res.data.data
  console.log(tableData.value)
}

const input3 = ref('')
const select = ref('')

//删除
const deleteUid = (row) => {
  console.log(row.uid)
  const data = {
    uid: row.uid
  }
  ElMessageBox.confirm(
    i18n.t('dialog.deleteTitle'),
    '警告',
    {
      confirmButtonText: '删除',
      cancelButtonText: '放弃',
      type: 'warning',
    }
  )
    .then(async () => {
      const res = await deleteAccount(data)
      console.log(res)
      ElMessage({
        type: 'success',
        message: '删除成功',
      })

      initGetUsersList()
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '删除失败',
      })
    })
}


</script>

<style lang="scss" scoped>
.header {
  padding-bottom: 16px;
  box-sizing: border-box;
}

::v-deep .el-input__suffix {
  align-items: center;
}

.input-with-select .el-input-group__prepend {
  background-color: var(--el-fill-color-blank);
}
</style>
