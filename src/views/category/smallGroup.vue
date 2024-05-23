<template  #default="{ row }">
  <div class="bottom">
    <el-button type="primary" @click="handelDialogValue">增加</el-button>
    <el-button type="danger" @click="deleteGroup(row)">删除所有小类</el-button>
  </div>
  <el-card>
    <el-table :data="tableData" stripe style="width: 100%">
      <el-table-column :prop="item.prop" :label="$t(`table.${item.label}`)" v-for="(item, index) in options" :key="index">
        <template #default="{ row }" v-if="item.prop === 'address'">
          <el-button size="small" @click="Edit(row)">编辑</el-button>
          <el-button size="small" type="danger" @click="deleteUid(row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>
  <Dialog v-model="dialogVisible" v-if="dialogVisible" @initGetClass="initGetClass" />

  <DialogEdit :uid="AccountUid" v-model="dialogEdit" v-if="dialogEdit" @initGetClass="initGetClass"/>


</template>
  
<script  setup>
import { ref } from 'vue'
import { deleteClass, deleteAllClass, selectClass } from '../../api/group/smallgroup'
import Dialog from './components/dialog.vue'
import DialogEdit from './components/dialogeEdit.vue'

import { useRoute } from 'vue-router';
import { useI18n } from 'vue-i18n'


const i18n = useI18n()

const AccountUid = ref(null)

const dialogEdit = ref(false)

const options = [
    {
        label:'groupUid',
        prop:'uid'
    },
    {
        label:'name',
        prop:'name'
    },
    {
        label:'address',
        prop:'address'
    },
]
// const uid = ref(null)
const route = useRoute();
const groupId = route.query.groupId;

// const queryFrom = ref({
//   groupId: uid
// })
const tableData = ref([])

//查询小类
const initGetClass = async () => {
  const res = await selectClass({ groupId })
  console.log(res)
  tableData.value = res.data;
  console.log(tableData.value)
  console.log(groupId)

}

initGetClass()
// console.log(select.value)

//编辑
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

// 删除
const deleteUid = (row) => {
  console.log(row.uid)
  const data = {
    uid: row.uid
  }
  ElMessageBox.confirm(
    i18n.t('dialog.delete'),
    '警告',
    {
      confirmButtonText: '删除',
      cancelButtonText: '放弃',
      type: 'warning',
    }
  )
    .then(async () => {
      const res = await deleteClass(data)
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
// //删除大类下的所有小类
const deleteGroup = () => {
  ElMessageBox.confirm(
    i18n.t('dialog.delete'),
    '警告',
    {
      confirmButtonText: '删除',
      cancelButtonText: '放弃',
      type: 'warning',
    }
  )
    .then(async () => {
      const res = await deleteAllClass({ groupId })
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

<style>
.bottom {
  display: flex;
  justify-content: center;
  padding-bottom: 20px;
}
</style>
  