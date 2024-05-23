<template>
  <div>
    <el-row :gutter="12">
      <el-col v-for="(cards, index) in displayedCards" :span="8" :key="index" style="width: 100%;">
        <router-link :to="{
          path: '/WarehouseGoods',
          query: { warehouseUid: cards.uid }
        }" class="custom-link">
          <el-card shadow="hover" class="card" @mouseenter="showButton(index)" @mouseleave="hideButton(index)"
            @click="selectUser">
            <div class="center">
              <div class="name">
                {{ cards.name }}
              </div>

              <div class="value">
                备注: {{ cards.notes }}
                <br>
                负责人: {{ cards.head }}
                <br>
                地点：{{ cards.address }}

              </div>
            </div>

            <div class="button">
              <el-button type="danger" :icon="Delete" circle @click.stop.prevent="DepDelete(cards)"
                v-show="cards.showButton" />
              <el-button type="warning" :icon="Edit" circle @click.stop.prevent="update(cards)"
                v-show="cards.showButton"></el-button>
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
import { Delete, Edit } from '@element-plus/icons-vue'
import { selectWarehouse } from '../../api/warehouse/select'
import { deleteWarehouse } from '../../api/warehouse/delete'
import dialogAdd from './components/dialogAdd.vue'
import dialogUpdate from './components/dialogUpdate.vue'
import { useI18n } from 'vue-i18n'

const i18n = useI18n()
// 当前页码
const currentPage = ref(1);
const cards = ref([]);
const dialogVisible = ref(false)
const dialogUpdateVisible = ref(false)
const AccountUid = ref(null)


const queryFrom = ref({
  page: currentPage,
  count: 6
});

const initGetDepartmentList = async () => {
  const res = await selectWarehouse(queryFrom.value);
  cards.value = res.data.records; // 将部门列表数据赋值给cards
  console.log(cards.value);
};

initGetDepartmentList();


const add = () => {
  dialogVisible.value = true
}



// 处理分页变化事件
function handlePageChange(newPage) {
  currentPage.value = newPage;
  initGetDepartmentList(); // 页面变化时重新获取部门列表数据
}

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

//修改

const update = (row) => {
  dialogUpdateVisible.value = true
  console.log(row.uid)
  AccountUid.value = row.uid
}

//删除
const DepDelete = (row) => {
  console.log(row.id)
  const data = {
    id: row.id
  }
  ElMessageBox.confirm(
    i18n.t('dialog.deleteWarehouse'),
    '警告',
    {
      confirmButtonText: '删除',
      cancelButtonText: '放弃',
      type: 'warning',
    }
  )
    .then(async () => {
      const res = await deleteWarehouse(data)
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




</script>

<style lang="scss" scoped>
.button {
  padding-top: 20px;
}

.custom-link {
  text-decoration: none;
  /* 移除下划线 */
  /* 其他样式属性 */
}

.card {
  height: 250px;
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
//   margin-left: 200px;
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


.value {
  display: flex;
  justify-items: center;
  padding-top: 60px;
  padding-left:20px;
  font-size: 24px;
}

.name {
  display: flex;
  justify-content: center;
  padding-left: 10px;
  padding-top: 10px;
}
.center{
  display: flex;
  justify-items: center;
}

</style>
