<template>
  <div>
    <el-row :gutter="12">
      <el-col v-for="(cards, index) in displayedCards" :span="8" :key="index" style="width: 100%;">
        <router-link :to="{
          path: '/DepartmentUser',
          query: { DepartmentUid: cards.uid }
        }" class="custom-link">
          <el-card shadow="hover" class="card" @mouseenter="showButton(index)" @mouseleave="hideButton(index)"
            @click="selectUser">
            {{ cards.name }}
            <div>
              <el-button type="danger" :icon="Delete" circle @click.stop.prevent="DepDelete(cards)" v-show="cards.showButton" />
              <el-button type="warning" :icon="Edit" circle @click.stop.prevent="update(cards)" v-show="cards.showButton"/>
            </div>
          </el-card>
        </router-link>
      </el-col>
    </el-row>
  </div>
  <div class="footer">
    <el-button type="primary" @click="add">增加</el-button>
  </div>
  <dialogAdd :uidId="AccountUid" v-model="dialogVisible" v-if="dialogVisible"
    @initGetDepartmentList="initGetDepartmentList" />
  <dialogUpdate :uid="AccountUid" v-model="dialogUpdateVisible" v-if="dialogUpdateVisible"
    @initGetDepartmentList="initGetDepartmentList" />
</template>

<script setup>
import { ref, computed } from 'vue';
import { selectDepartment } from '../../api/department/select';
import { Delete, Edit } from '@element-plus/icons-vue'
import { deleteDepartment } from '../../api/department/delete'
import dialogAdd from './components/dialogAdd.vue'
import dialogUpdate from './components/dialogUpdate.vue'
import { useI18n } from 'vue-i18n'

const i18n = useI18n()
const AccountUid = ref(null)


// 当前页码
const currentPage = ref(1);
const cards = ref([]);
const dialogVisible = ref(false)
const dialogUpdateVisible = ref(false)

// 处理分页变化事件
function handlePageChange(newPage) {
  currentPage.value = newPage;
  initGetDepartmentList(); // 页面变化时重新获取部门列表数据
}

const queryFrom = ref({
  page: currentPage,
  count: 100
});

const initGetDepartmentList = async () => {
  const res = await selectDepartment(queryFrom.value);
  cards.value = res.data.records; // 将部门列表数据赋值给cards
  console.log(cards.value);
};

initGetDepartmentList();

// 页面加载时获取部门列表数据

// 根据当前页码和每页显示数量计算显示的卡片
const displayedCards = computed(() => {
  return cards.value;
});


// 显示按钮
function showButton(index) {
  cards.value[index].showButton = true;
}

// 隐藏按钮
function hideButton(index) {
  cards.value[index].showButton = false;
}

//增加
const add = () => {
  dialogVisible.value = true
}

//更新
const update = (row) => {
  dialogUpdateVisible.value = true
  console.log(cards.id)
  AccountUid.value = row.uid

}

//删除


const DepDelete = (row) => {
  console.log(row.uid)
  const data = {
    uid: row.uid
  }
  ElMessageBox.confirm(
    i18n.t('dialog.deleteDepartment'),
    '警告',
    {
      confirmButtonText: '删除',
      cancelButtonText: '放弃',
      type: 'warning',
    }
  )
    .then(async () => {
      const res = await deleteDepartment(data)
      console.log(res)
      ElMessage({
        type: 'success',
        message: '删除成功',
      })

      initGetDepartmentList()
    })
    .catch(() => {
      ElMessage({
        type: 'info',
        message: '删除失败',
      })
    })
}

//搜索部门用户

const selectUser = () => {

}
</script>

<style lang="scss" scoped>
.custom-link {
  text-decoration: none;
  /* 移除下划线 */
  /* 其他样式属性 */
}

.card {
  height: 200px;
  margin: 15px;
  cursor: pointer;
}

.footer {
  position: fixed;
  bottom: 80px;
  left: 110px;
  width: 100%;
  display: flex;
  justify-content: center;
  padding: 20px 0;
}

// .el-button{
//   // margin-left: 200px;
// }
.el-card {
  // width: 100%;
  display: flex;
  justify-content: space-between;
  font-size: 25px;
}

// .content{
//   // display: flex;
// }
span {
  font-size: 20px;
}

.el-input {
  padding-top: 20px;
  padding-bottom: 20px;
}

// .content{
//   position: fixed;
//   top: 0px;
// }</style>
