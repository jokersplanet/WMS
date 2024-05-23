<template>
    <div>
        <el-button type="primary" @click="handelDialogValue" class="add">添加</el-button>


        <el-table :data="tableData" stripe style="width: 100%">
            <el-table-column :prop="item.prop" :label="$t(`table.${item.label}`)" v-for="(item, index) in options"
                :key="index">
                <template #default="{ row }" v-if="item.prop === 'address'">
                    <el-button size="small" @click="Edit(row)">编辑</el-button>
                    <el-button size="small" type="danger" @click="deleteUid(row)">删除</el-button>
                </template></el-table-column>
        </el-table>
        <Dialog v-model="dialogVisible" v-if="dialogVisible" @initGetUnit="initGetUnit" />
        <DialogEdit :uidId="AccountUid" v-model="dialogEdit" v-if="dialogEdit" @initGetUnit="initGetUnit" />

    </div>
</template>
<script setup>
import { ref } from 'vue'
import { getStatus, deleteStatusById } from '../../api/status/index'
import Dialog from './components/dialog.vue'
import DialogEdit from './components/dialogeEdit.vue'

// import DialogEdit from './components/dialogeEdit.vue'
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
    count: 3
})
const tableData = ref([])


const initGetUnit = async () => {
    const res = await getStatus(queryFrom.value)
    console.log(res)
    tableData.value = res.data;
    console.log(tableData.value)
    total = res.data.length
    console.log(total)

}

initGetUnit()


const options = [
    {
        label: 'uid',
        prop: 'uid'
    },
    {
        label: 'name',
        prop: 'name'
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
        i18n.t('dialog.status'),
        '警告',
        {
            confirmButtonText: '删除',
            cancelButtonText: '放弃',
            type: 'warning',
        }
    )
        .then(async () => {
            const res = await deleteStatusById(data)
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

<style>
.add{
    margin-bottom: 20px;
}

</style>