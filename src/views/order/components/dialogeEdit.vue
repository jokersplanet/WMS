<template>
    <el-dialog v-model="dialogVisible" title="订单状态信息 " width="30%" :before-close="handleClose" :show-close="false">
        <el-form ref="fromRef" :model="form" label-width="70px" :rules="rules">
            <el-form-item label="名称" prop="name">
                <el-select v-model="tableData.name" placeholder="选择状态" style="width: 115px" @change="handleGroupChange">
                    <el-option v-for="option in options1.status.value" :label="option.name" :value="option.name"
                        :key="option.uid" />
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
<script setup >
import { ref, defineEmits,onMounted } from 'vue'
import { updateOrder } from '../../../api/order/index'
import { getStatus } from '../../../api/status/index'
import { ElMessage } from 'element-plus'
import { useI18n } from 'vue-i18n'

const i18n = useI18n()

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

const props = defineProps({
    uidId: {
        type: String,
        default: "默认值"
    }
})




const tableData = ref({
    uid: props.uidId,
    name: ''
})


const handleConfirm = async () => {

    ElMessageBox.confirm(
        '确定要修改状态',
        '警告',
        {
            confirmButtonText: '修改',
            cancelButtonText: '放弃',
            type: 'warning',
        }
    )
        .then(async () => {

            const res = await updateOrder(tableData.value)
            console.log(res)
            emits('initGetUnit')
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