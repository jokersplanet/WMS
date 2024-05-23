<template>
  <!-- <el-button type="primary" @click="handelDialogValue" class="add">添加记录</el-button> -->

  <el-table :data="tableData" stripe style="width: 100%">
    <el-table-column :prop="item.prop" :label="$t(`table.${item.label}`)" v-for="(item, index) in options" :key="index">
      <template #default="{ row }" v-if="item.prop === 'address'">
        <el-button size="small" @click="Edit(row)">编辑</el-button>
        <el-button size="small" type="danger" @click="deleteUid(row)">删除</el-button>
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
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
    />
    
  <Dialog v-model="dialogVisible" v-if="dialogVisible" @initGetClass="initGetClass" />
  <DialogEdit :uid="AccountUid" v-model="dialogEdit" v-if="dialogEdit" @initGetClass="initGetClass" />
</template>

<script setup>
import { ref } from 'vue'
import { getOutboundRecords, deleteOutboundRecords } from '../../api/warehouse/outgoods'
import { useI18n } from 'vue-i18n'
import Dialog from './components/dialog.vue'
import DialogEdit from './components/dialogeEdit.vue'


const i18n = useI18n()
const AccountUid = ref(null)

let total = ref(0); // 总数据量，用于计算分页

const handleCurrentChange = (page) => {
  queryFrom.value.page = 1
  queryFrom.value.page = page
  initGetClass();
};


//修改
const dialogEdit = ref(false)

const Edit = (row) => {
  dialogEdit.value = true
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
  count: 10
})
const tableData = ref([])


const initGetClass = async () => {
  const res = await getOutboundRecords(queryFrom.value)
  console.log(res)
  tableData.value = res.data.records;
  console.log(tableData.value)

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
  {
    label: 'count',
    prop: 'count'
  },
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
    i18n.t('dialog.Records'),
    '警告',
    {
      confirmButtonText: '删除',
      cancelButtonText: '放弃',
      type: 'warning',
    }
  )
    .then(async () => {
      const res = await deleteOutboundRecords(data)
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
