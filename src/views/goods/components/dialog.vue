<template>
    <el-dialog :model-value="dialogVisible" title="添加货物" width="40%" :@close="handleClose" :show-close="false">
        <el-form ref="fromRef" :model="form" label-width="70px" :rules="rules">
            <el-form-item label="名称" prop="name">
                <el-input v-model="form.name" />
            </el-form-item>
            <el-form-item label="单价" prop="price">
                <el-input v-model="form.price" />
            </el-form-item>
            <el-form-item label="数量" prop="count">
                <el-input v-model="form.count" />
            </el-form-item>
            <el-form-item label="总价" prop="value">
                <el-input v-model="form.value" />
            </el-form-item>
            <el-form-item label="大类别" prop="groupUid">
                <el-select v-model="form.groupUid" placeholder="选择类型" style="width: 115px" @change="handleGroupChange">
                    <el-option v-for="option in options.groupUid.value" :label="option.name" :value="option.uid"
                        :key="option.uid" />
                </el-select>
            </el-form-item>
            <el-form-item label="小类别" prop="classUid">
                <el-select v-model="form.classUid" placeholder="选择类型" style="width: 115px">
                    <el-option v-for="option in options.classUid.value" :label="option.name" :value="option.uid"
                        :key="option.uid" />
                </el-select>
            </el-form-item>
            <el-form-item label="单位" prop="unitUid">
                <el-select v-model="form.unitUid" placeholder="选择单位" style="width: 115px">
                    <el-option v-for="option in options.unitUid.value" :label="option.name" :value="option.uid"
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
<script setup>
import { insertInboundRecords } from '../../../api/warehouse/inGoods'
import { getGoods, insertGoods } from '../../../api/Goods/index'
import { ElMessage } from 'element-plus'
import { ref, defineEmits, onMounted } from 'vue';
import { selectGroup } from '../../../api/group/biggroup'
import { selectClass } from '../../../api/group/smallgroup'
import { getUnit } from '../../../api/unit/index'

const rules = ref({
    name: [
        { required: true, message: '请输入货物名称', trigger: 'blur' },
        { min: 1, max: 10, message: '名称长度为1-10', trigger: 'blur' },
    ],
    price: [
        { required: true, message: '请输入单价', trigger: 'blur' },
        { min: 1, max: 13, message: '请输入正确的单价', trigger: 'blur' },
    ],
    value: [
        { required: true, message: '请输入总价', trigger: 'blur' },
        { min: 1, max: 13, message: '请输入正确的总价', trigger: 'blur' },
    ],
    count: [
        { required: true, message: '请输入数量', trigger: 'blur' },
        { min: 1, max: 13, message: '请输入正确的数量', trigger: 'blur' },
    ],

})

const formattedTime = ref('');

onMounted(() => {
    getCurrentTime();
});

const getCurrentTime = () => {
    const now = new Date();
    const year = now.getFullYear();
    const month = padNumber(now.getMonth() + 1);
    const day = padNumber(now.getDate());
    const hours = padNumber(now.getHours());
    const minutes = padNumber(now.getMinutes());
    const seconds = padNumber(now.getSeconds());

    formattedTime.value = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    setTimeout(getCurrentTime, 1000); // 更新时间，每秒执行一次
};
const padNumber = (number) => {
    return number.toString().padStart(2, '0');
};

const emits = defineEmits(['update:modelValue', 'initGetClass'])
const handleClose = () => {
    emits('update:modelValue', false)
}

const fromRef = ref(null)
const props = defineProps({
    uid: {
        type: String,
        default: "默认值"
    }
})

const form = ref({
    warehouseUid: props.uid,
    name: '',
    price: '',
    count: '',
    value: '',
    groupUid: '',
})


const options = {
    groupUid: ref([]),
    classUid: ref([]),
    unitUid: ref([]),
};

const data = ref({
    page: 1,
    count: 5
})

const data1 = ref({
    groupId: ''
})

const handleGroupChange = () => {

    data1.value.groupId = form.value.groupUid;
    console.log(data1.value)
    //         // 根据选择的大类型获取小类型选项
    const small = async () => {
        const classOption = await selectClass(data1.value);
        const classOptions = classOption.data;
        console.log(classOption)
        options.classUid.value = classOptions.map(item => ({ name: item.name, uid: item.uid }));
    }
    small()
    console.log('选择的大类型:', form.value);

};

onMounted(async () => {
    try {
        // 获取大类别选项
        const groupOption = await selectGroup(data.value);

        const groupOptions = groupOption.data;
        options.groupUid.value = groupOptions.map(item => ({ name: item.name, uid: item.uid }));
        // allName.GroupName.value = groupOptions.map(item => item.name)


        //获取单位选项
        const unitOption = await getUnit(data.value);
        const unitOptions = unitOption.data;
        options.unitUid.value = unitOptions.map(item => ({ name: item.name, uid: item.uid }));
        // allName.UnitName.value = unitOptions.map(item => item.name)

    }
    catch (error) {
        console.error('Failed to fetch options from backend:', error);
    }

});

const handleConfirm = async () => {
    ElMessageBox.confirm(
        '确定要创建货物',
        '警告',
        {
            confirmButtonText: '创建',
            cancelButtonText: '放弃',
            type: 'warning',
        })
        .then(async () => {
            const res = await insertGoods(form.value);
            console.log(res.data)
            ElMessage({
                type: 'success',
                message: '创建成功',
            })
            ElMessageBox.confirm(
                '是否要同步入库记录？',
                '提示',
                {
                    confirmButtonText: '是',
                    cancelButtonText: '否',
                    type: 'info',
                }
            )
                .then(async () => {
                    console.log(res);
                    emits('initGetClass');
                    handleClose();
                    console.log(formattedTime.value)
                    const form1 = ref({ ...form })
                    form1._value.time = formattedTime.value,
                    form1._value.goodsName = form._value.name
                    form1._value.goodsUid = props.uid
                    console.log(form1._value.time)

                    // 用户选择是，执行insertInboundRecords
                    await insertInboundRecords(form1._value);
                    ElMessage({
                        type: 'success',
                        message: '创建成功并同步入库记录',
                    });
                })
                .catch(() => {
                    ElMessage({
                        type: 'info',
                        message: '同步失败',
                    });
                });
        })
        .catch(() => {
            ElMessage({
                type: 'info',
                message: '创建失败',
            });
        });
};



</script>
<style scoped></style>