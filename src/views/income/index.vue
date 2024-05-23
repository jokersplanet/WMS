<template>
    <div class="demo-datetime-picker">
    <div class="block">
      <el-date-picker
        v-model="form.startTime"
        type="datetime"
        placeholder="输入日期"
        format="YYYY/MM/DD hh:mm:ss:sss"  
        value-format="YYYY-MM-DD hh:mm:ss:sss"
        :clearable="false"
      />
      -
      <el-date-picker
        v-model="form.endTime"
        type="datetime"
        placeholder="输入日期"
        format="YYYY/MM/DD hh:mm:ss:sss"  
        value-format="YYYY-MM-DD hh:mm:ss:sss"
        :clearable="false"
      />
      <el-button :icon="Search" circle @click="searchTime" style="margin-left: 15px;margin-top: 5px;"/>
    </div>
  </div>
<div>
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

  <DialogEdit :uid="AccountUid"  v-model="dialogEdit" v-if="dialogEdit" @initGetClass="initGetClass"/>
</div>
</template>

<script setup>
import { ref } from 'vue'
import { getIncome, deleteIncome } from '../../api/money/income'
import DialogEdit from './components/dialogeEdit.vue'
import { useI18n } from 'vue-i18n'
import {Search} from '@element-plus/icons-vue'
import { getIncomeByTime } from '../../api/money/income'




const i18n = useI18n()
const AccountUid = ref(null)

const dialogEdit = ref(false)
const searchTime = async()=>{
  const res = await getIncomeByTime(form.value)
  console.log(res)
  tableData.value = res.data.records
}
let total = ref(0); // 总数据量，用于计算分页

const handleCurrentChange = (page) => {
  queryFrom.value.page = 1
  queryFrom.value.page = page
  initGetClass();
};
const queryFrom = ref({
  page: 1,
  count: 10
})
const tableData = ref([])
const form = ref({
  page:queryFrom.page,
  count:queryFrom.count,
  page: 1,
  count: 100
})


const initGetClass = async () => {
  const res = await getIncome(queryFrom.value)
  console.log(res)
  tableData.value = res.data.records;
  console.log(tableData.value)
  total = res.data.total
}

initGetClass()


const options = [
  {
    label: 'uid',
    prop: 'uid'
  },
  {
    label: 'time',
    prop: 'time'
  },
  {
    label: 'value',
    prop: 'value'
  },
  // {
  //   label: 'username',
  //   prop: 'username'
  // },
  {
    label: 'address',
    prop: 'address'
  },
]
//编辑
const Edit = (row) => {
  dialogEdit.value =true
  console.log(row.uid)
  AccountUid.value = row.uid
}


// 删除
const deleteUid = (row) => {
  console.log(row.uid)
  const data = {
    uid: row.uid
  }
  ElMessageBox.confirm(
    i18n.t('dialog.Records'),
    '警告',
    {
      confirmButtonText: '删除',
      cancelButtonText: '放弃',
      type: 'warning',
    }
  )
    .then(async () => {
      const res = await deleteIncome(data)
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
<style scoped>

 .demo-datetime-picker {
    display: none;
  }
.demo-datetime-picker {
  display: flex;
  width: 100%;
  padding: 0;
  flex-wrap: wrap;
}
.demo-datetime-picker .block {
  padding: 30px 0;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  flex: 1;
}
.demo-datetime-picker .block:last-child {
  border-right: none;
}
.demo-datetime-picker .demonstration {
  display: block;
  color: var(--el-text-color-secondary);
  font-size: 14px;
  margin-bottom: 20px;
}</style>