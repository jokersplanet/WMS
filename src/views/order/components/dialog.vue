<template>
    <el-dialog :model-value="dialogVisible" title="添加订单" width="40%" :@close="handleClose" :show-close="false">
        <el-form ref="fromRef" :model="form" label-width="70px" :rules="rules">
            <el-form-item label="货物名称" prop="goodsName">
                <el-input v-model="form.goodsName" value="1" />
            </el-form-item>
            <el-form-item label="数量" prop="count">
                <el-input v-model="form.count" value="1" />
            </el-form-item>
            <el-form-item label="单价" prop="price">
                <el-input v-model="form.price" value="1" />
            </el-form-item>
            <el-form-item label="总价" prop="value">
                <el-input v-model="form.value" value="1" />
            </el-form-item>
            <el-form-item label="状态" prop="status">
                <el-select v-model="form.status" placeholder="选择状态" style="width: 115px" @change="handleGroupChange">
                    <el-option v-for="option in options1.status.value" :label="option.name" :value="option.uid" :key="option" />
                </el-select>
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
import { defineEmits, ref,onMounted } from 'vue'
import { insertOrder } from '../../../api/order/index'
import { getStatus } from '../../../api/status';
import { ElMessage, colProps } from 'element-plus'
//获取状态
const options1 = {
    status: ref([]),
};
const data = ref({
    page: 1,
    count: 5
})
onMounted(async () => {
    try {
        // 获取状态
        const groupOption = await getStatus(data.value);
        console.log(groupOption)
        const groupOptions = groupOption.data;
        console.log(groupOptions)
        options1.status.value = groupOptions.map(item => ({ name: item.name, uid: item.uid }));

    }
    catch (error) {
        console.error('Failed to fetch options from backend:', error);
    }

});
const emits = defineEmits(['update:modelValue', 'initGetUnit'])
const handleClose = () => {
    emits('update:modelValue', false)
}
const fromRef = ref(null)
const form = ref({
    goodsName: ''
})
const handleConfirm = async () => {
    ElMessageBox.confirm(
        '确定要创建订单信息',
        '警告',
        {
            confirmButtonText: '创建',
            cancelButtonText: '放弃',
            type: 'warning',
        }
    )
        .then(async () => {

            const res = await insertOrder(form.value)
            console.log(res)
            emits('initGetUnit')
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