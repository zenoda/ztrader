<script setup>
import {logout} from "@/lib/auth/AuthClient.js";
import {getUserProfile} from "@/lib/admin/UserClient.js";
import router from "@/router/index.js";
import {onMounted, ref} from "vue";

async function onLogoutButtonClick() {
  await logout()
  await router.push({name: 'login'})
}

async function onUserProfileButtonClick() {
  await router.push({name: 'profile'})
}

const userProfile = ref({username: null, nickname: null, avatarUrl: null})
onMounted(async () => {
  userProfile.value = await getUserProfile()
})
</script>

<template>
  <div class="z-user-info">
    <el-popover placement="top" :width="255" :show-arrow="false" trigger="click">
      <template #reference>
        <div class="user-info">
          <el-avatar :src="userProfile.avatarUrl"/>
          <div class="user-label">
            <div class="user-name">{{ userProfile.nickname }}</div>
            <div class="user-email">{{ userProfile.email }}</div>
          </div>
        </div>
      </template>
      <div class="z-user-info__controller">
        <div class="controller-btn" @click="onUserProfileButtonClick">
          <img src="../assets/profile.svg">
          <span>个人资料</span>
        </div>
        <div class="controller-btn" @click="onLogoutButtonClick">
          <img src="../assets/exit.svg">
          <span>退出</span>
        </div>
      </div>
    </el-popover>
  </div>
</template>

<style scoped lang="scss">
.z-user-info {
  display: flex;
  align-items: center;

  .user-info {
    cursor: pointer;
    flex: 1;
    display: flex;
    align-items: center;
    column-gap: 10px;

    .user-label {
      line-height: 1.5;

      .user-name {
        font-size: 16px;
        font-weight: bold;
      }
    }
  }
}

.z-user-info__controller {
  .controller-btn {
    padding: 8px;
    cursor: pointer;
    border-radius: var(--el-border-radius-base);
    display: flex;
    column-gap: 10px;
    align-items: center;
    line-height: 1;

    img {
      width: 20px;
      height: 20px;
    }

    &:hover {
      background-color: var(--el-fill-color-dark);
    }
  }
}
</style>