<template>
    <el-dialog v-model="dialogVisible" title="账户信息 " width="30%" :before-close="handleClose" :show-close="false" >
        <el-form ref="fromRef" :model="form" label-width="70px" :rules="rules">
            <el-form-item label="员工编号" prop="number">
                <el-input v-model="tableData.number" />
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="tableData.password" type="password" />
            </el-form-item>
            <el-form-item label="姓名" prop="username">
                <el-input v-model="tableData.username" />
            </el-form-item>
            <el-form-item label="性别" prop="gender">
                <el-input v-model="tableData.gender" />
            </el-form-item>
            <el-form-item label="权限等级" prop="privilege">
                <el-select v-model="tableData.privilege" placeholder="选择权限" style="width: 115px">
                    <el-option v-for="option in options2" :label="option.label" :value="option.value" :key="option.uid" />
                </el-select>
            </el-form-item>
            <el-form-item label="电话" prop="telephone">
                <el-input v-model="tableData.telephone" />
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button @click="handleClose">取消</el-button>
                <el-button type="primary" @click="handleConfirm">
                    确认
                </el-button>
            </span>
        </template>
    </el-dialog>
</template>
<script setup >
import { searchUid } from '../../../api/search/uid'
import { ref,defineEmits } from 'vue'
import { updateUser } from '../../../api/updatauser'
import { ElMessage } from 'element-plus'
import { useI18n } from 'vue-i18n'

const i18n = useI18n()

const emits = defineEmits(['update:modelValue','initGetUsersList'])
const handleClose = () => {
    emits('update:modelValue', false)
}

const props = defineProps({
    uidId: {
        type: String,
        default: "默认值"
    }
})

const options2 = ref([
    { label: "管理员", value: 1 },
    { label: "人事专员", value: 2 },
    { label: "仓库管理员", value: 3 },
    { label: "普通用户", value: 4 }
])
    
const tableData = ref({})
const params = {
    uid: props.uidId
}
const init = async () => {
    const res = await searchUid(params)
    tableData.value = res.data
    console.log(res)
}
init()

const handleConfirm = async () => {
    ElMessageBox.confirm(
        '确定要修改用户',
        '警告',
        {
            confirmButtonText: '修改',
            cancelButtonText: '放弃',
            type: 'warning',
        }
    )
        .then(async () => {
            
            const res = await updateUser(tableData.value)
            console.log(res)
            emits('initGetUsersList')
            handleClose()
            ElMessage({
                type: 'success',
                message: '修改成功',
            })
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '修改失败',
            })
        })

}
console.log(tableData.value)

</script>
<style scoped>
p {
    padding-bottom: 15px;
}
</style>