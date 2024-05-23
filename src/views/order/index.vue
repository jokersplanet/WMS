<template>
  <div>
    <div class="status">
    <h1>根据订单查询：</h1>
    <el-select class="selectInput" v-model="queryFrom.status" placeholder="查询选项 " style="width: 115px">
      <el-option v-for="option in options1.status.value" :label="option.name" :value="option.uid" :key="option.uid" />
    </el-select>
    <el-button  class="select" type="primary" @click="selectOrder">搜索</el-button>
  </div>
    <el-button  type="primary" @click="handelDialogValue" class="add">添加</el-button>


    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column :prop="item.prop" :label="$t(`table.${item.label}`)" v-for="(item, index) in options" :key="index">
        <template #default="{ row }" v-if="item.prop === 'name'">
          {{ row.name }}
          <el-button size="small" @click="Edit(row)">编辑</el-button>
        </template>
        <template #default="{ row }" v-if="item.prop === 'address'">
          <el-button size="small" type="danger" @click="deleteUid(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <Dialog  :destroy-on-close="false" v-model="dialogVisible" v-if="dialogVisible" :visible="dialogVisible" @initGetUnit="initGetUnit" @closeDialog="handelDialogClose"/>
    <DialogEdit :uidId="AccountUid" v-model="dialogEdit" v-if="dialogEdit" @initGetUnit="initGetUnit" />

  </div>
</template>
<script setup>
import { ref,onMounted } from 'vue'
import { getOrder, deleteOrder } from '../../api/order/index'
import { getStatus } from '../../api/status/index'
import Dialog from './components/dialog.vue'
import DialogEdit from './components/dialogeEdit.vue'

import { useI18n } from 'vue-i18n'

const i18n = useI18n()
const AccountUid = ref(null)
let total = ref(0); // 总数据量，用于计算分页


const options1 = {
    status: ref([]),
};

const queryFrom = ref({
  page: 1,
  count: 3
})
onMounted(async () => {
    try {
        // 获取状态
        const groupOption = await getStatus(queryFrom.value);
        console.log(groupOption)
        const groupOptions = groupOption.data;
        console.log(groupOptions)
        options1.status.value = groupOptions.map(item => ({ name: item.name, uid: item.uid }));

    }
    catch (error) {
        console.error('Failed to fetch options from backend:', error);
    }

});

const tableData = ref([])

//搜索
const  selectOrder = () => {
  console.log(queryFrom.value)
  initGetUnit()
  
}
//添加
const dialogVisible = ref(false)
const handelDialogValue = () => {
  dialogVisible.value = true
}
const handelDialogClose = () => {
  dialogVisible.value = false;
  dialogEdit.value = false;
}
//修改
const dialogEdit = ref(false)

const Edit = (row) => {
  dialogEdit.value = true
  console.log(row.uid)
  AccountUid.value = row.uid
}

//查询
const initGetUnit = async () => {
  const res = await getOrder(queryFrom.value)
  console.log(res)
  tableData.value = res.data.records;
  console.log(tableData.value)
  total = res.data.length
  console.log(total)

}

initGetUnit()
//展示
const options = [
  // {
  //     label: 'uid',
  //     prop: 'uid'
  // },
  {
    label: 'goodsName',
    prop: 'goodsName'
  },
  {
    label: 'count',
    prop: 'count'
  },

  {
    label: 'price',
    prop: 'price'
  },
  {
    label: 'value',
    prop: 'value'
  },
  {
    label: 'statusName',
    prop: 'statusName'
  },
  {
    label: 'address',
    prop: 'address'
  },
]
//删除
const deleteUid = (row) => {
  console.log(row.uid)
  const data = {
    uid: row.uid
  }
  ElMessageBox.confirm(
    i18n.t('dialog.order'),
    '警告',
    {
      confirmButtonText: '删除',
      cancelButtonText: '放弃',
      type: 'warning',
    }
  )
    .then(async () => {
      const res = await deleteOrder(data)
      console.log(res)
      ElMessage({
        type: 'success',
        message: '删除成功',
      })

      initGetUnit()
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
.status{
  display: flex;
}
.select{
  margin:15px
}
.selectInput{
  margin:15px
}
.add{
  margin :15px
}
</style> 