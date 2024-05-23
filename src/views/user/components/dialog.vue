<template>
    <el-dialog :model-value="dialogVisible" title="添加账户" width="40%" :show-close="false" :@close="handleClose">
        <el-form ref="fromRef" :model="form" label-width="70px" :rules="rules">
            <el-form-item label="账号" prop="uid">
                <el-input v-model="form.uid" value="1" />
            </el-form-item>
            <el-form-item label="员工编号" prop="number">
                <el-input v-model="form.number" />
            </el-form-item>
            <el-form-item label="密码" prop="password">
                <el-input v-model="form.password" type="password" />
            </el-form-item>
            <el-form-item label="姓名" prop="username">
                <el-input v-model="form.username" />
            </el-form-item>
            <el-form-item label="性别" prop="classUid">
                <el-select v-model="form.gender" placeholder="选择性别" style="width: 115px" @change="handleGroupChange">
                    <el-option v-for="option in options" :label="option" :value="option" :key="option" />
                </el-select>
            </el-form-item>
            <el-form-item label="权限等级" prop="privilege">
                <el-select v-model="form.privilege" placeholder="选择权限" style="width: 115px">
                    <el-option v-for="option in options2" :label="option.label" :value="option.value" :key="option.uid" />
                </el-select>
            </el-form-item>
            <el-form-item label="电话" prop="telephone">
                <el-input v-model="form.telephone" />
            </el-form-item>
            <el-form-item label="部门" prop="department">
                <el-select v-model="form.department" placeholder="选择部门" style="width: 115px">
                    <el-option v-for="option in options1.department.value" :label="option.name" :value="option.uid"
                        :key="option.uid" />
                </el-select>
            </el-form-item>
            <el-form-item label="职务" prop="duties">
                <el-input v-model="form.duties" />
            </el-form-item>
            <el-form-item label="地址" prop="address">
                <el-input v-model="form.address" />
            </el-form-item>
            <!-- <el-form-item label="照片" prop="photo">
                <el-upload class="avatar-uploader" action="http://localhost:8080/register" :show-file-list="false"
                    :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                    <img v-if="imageUrl" :src="imageUrl" class="avatar" />
                    <el-icon v-else class="avatar-uploader-icon">
                        <Plus />
                    </el-icon>
                </el-upload>
            </el-form-item> -->
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
import { defineEmits, ref, onMounted } from 'vue'
import { register } from '../../../api/register'
import { ElMessage, ElUpload } from 'element-plus'
import { selectDepartment } from '../../../api/department/select'
import { Plus } from '@element-plus/icons-vue'



const imageUrl = ref('')

const handleAvatarSuccess = (response, uploadFile) => {
    imageUrl.value = URL.createObjectURL(uploadFile.raw)
}

const beforeAvatarUpload = (rawFile) => {
    if (rawFile.type !== 'image/jpeg') {
        ElMessage.error('Avatar picture must be JPG format!')
        return false
    } else if (rawFile.size / 1024 / 1024 > 2) {
        ElMessage.error('Avatar picture size can not exceed 2MB!')
        return false
    }
    return true
}



const data = ref({
    page: 1,
    count: 5
})
const options1 = {
    department: ref([]),
};

onMounted(async () => {
    try {
        // 获取部门
        const groupOption = await selectDepartment(data.value);

        const groupOptions = groupOption.data.records;
        console.log(groupOptions)
        options1.department.value = groupOptions.map(item => ({ name: item.name, uid: item.uid }));

    }
    catch (error) {
        console.error('Failed to fetch options from backend:', error);
    }

});

const options2 = ref([
    { label: "管理员", value: 1 },
    { label: "人事专员", value: 2 },
    { label: "仓库管理员", value: 3 },
    { label: "普通用户", value: 4 }
])



const emits = defineEmits(['update:modelValue', 'initGetUsersList'])
const handleClose = () => {
    form.value = {
    uid: '',
    number: '',
    password: '',
    username: '',
    gender: '',
    privilege: '',
    telephone: '',
    department: ''
  };
    emits('update:modelValue', false)
}
const fromRef = ref(null)
const form = ref({
    uid: '',
    number: '',
    password: '',
    username: '',
    gender: '',
    privilege: '',
    telephone: '',
    department: ''
})

const options = ref({
    man: '男',
    women: '女'
})


const handleConfirm = async () => {
    ElMessageBox.confirm(
        '确定要创建用户',
        '警告',
        {
            confirmButtonText: '创建',
            cancelButtonText: '放弃',
            type: 'warning',
        }
    )
        .then(async () => {

            const res = await register(form.value)
            console.log(res)
            emits('initGetUsersList')
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
        { required: true, message: '请输入名字', trigger: 'blur' },
        { min: 1, max: 10, message: '长度为1到10', trigger: 'blur' },
    ],
    password: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { min: 8, max: 20, message: '长度为8到20', trigger: 'blur' },
    ],
    email: [
        { required: true, message: '请输入邮箱', trigger: 'blur' },
        {
            type: 'email',
            message: '输入正确邮箱',
            trigger: ['blur', 'change'],
        }
    ],
    telephone: [
        { required: true, message: '请输入电话号', trigger: 'blur' },
        { min: 3, max: 11, message: '请输入正确的电话号', trigger: 'blur' },
    ],

})
</script>
<style scoped>
.avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
}

.avatar-uploader .el-upload:hover {
    border-color: #409EFF;
}

.avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 178px;
    height: 178px;
    line-height: 178px;
    text-align: center;
}

.avatar {
    width: 178px;
    height: 178px;
    display: block;
}
</style>