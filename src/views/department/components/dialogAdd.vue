<template>
    <el-dialog :model-value="dialogVisible" title="创建" width="40%" :@close="handleClose" :show-close="false" >
        <el-form ref="fromRef" :model="form" label-width="70px" :rules="rules">
            <el-form-item label="部门用户" prop="name" >
                <el-input v-model="form.name" value="1"/>
            </el-form-item>
            <el-form-item label="备注" prop="notes">
                <el-input v-model="form.notes" />
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
<script setup>
import { defineEmits, ref } from 'vue'
import { insertDepartment } from '../../../api/department/insert'
import { ElMessage } from 'element-plus'



const emits = defineEmits(['update:modelValue','initGetDepartmentList'])
const handleClose = () => {
    emits('update:modelValue', false)
}
const fromRef = ref(null)
const form = ref({
        name:'',
        notes:'',
    })
const handleConfirm = async () => {
    ElMessageBox.confirm(
        '确定要创建部门',
        '警告',
        {
            confirmButtonText: '创建',
            cancelButtonText: '放弃',
            type: 'warning',
        }
    )
        .then(async () => {
            
            const res = await insertDepartment(form.value)
            console.log(res)
            emits('initGetDepartmentList')
            handleClose()
            ElMessage({
                type: 'success',
                message: '创建成功',
            })
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '创建失败',
            })
        })
}


const rules = ref({
    username: [
        { required: true, message: 'Please input Activity name', trigger: 'blur' },
        { min: 3, max: 5, message: 'Length should be 3 to 5', trigger: 'blur' },
    ],
    password: [
        { required: true, message: 'Please input Activity name', trigger: 'blur' },
        { min: 3, max: 5, message: 'Length should be 3 to 5', trigger: 'blur' },
    ],
    email: [
        { required: true, message: 'Please input Activity name', trigger: 'blur' },
        {
          type: 'email',
          message: '输入正确邮箱',
          trigger: ['blur', 'change'],
        }
    ],
    mobile: [
        { required: true, message: 'Please input Activity name', trigger: 'blur' },
        { min: 3, max: 5, message: 'Length should be 3 to 5', trigger: 'blur' },
    ],

})
</script>
<style scoped></style>