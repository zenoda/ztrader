<script setup>
import {onMounted, ref, useTemplateRef} from "vue";
import {getUserProfile, saveUserProfile, changePassword} from "@/lib/admin/UserClient.js"
import {uploadFile} from "@/lib/file/FileClient.js"
import AvatarDialog from "@/views/profile/AvatarDialog.vue";
import ZPassword from "@/components/ZPassword.vue";

function onNavChange(item) {
  console.log(item)
}

const userProfile = ref({})
onMounted(async () => {
  userProfile.value = await getUserProfile()
})
const fileInput = useTemplateRef('fileInput')

function onUploadButtonClick() {
  fileInput.value.click()
}

async function uploadLocalFile(event) {
  const fd = await uploadFile(event.target.files[0], 'profile/avatar')
  userProfile.value.avatarUrl = `/api/files/${fd.path}/${fd.name}`
}

const avatarDialogVisible = ref(false)

function onSelectAvatarButtonClick() {
  avatarDialogVisible.value = true
}

function selectAvatar(url) {
  userProfile.value.avatarUrl = url
}

async function onBaseInfoSaveClick() {
  userProfile.value = await saveUserProfile(userProfile.value)
}

const passwordChangeRequest = ref({oldPassword: null, newPassword: null})
const confirmPassword = ref()

async function onChangePassword() {
  userProfile.value = await changePassword(passwordChangeRequest.value)
}
</script>

<template>
  <el-space class="z-trader-user-profile" direction="vertical" size="large" alignment="normal">
    <el-row>
      <h3>个人资料</h3>
    </el-row>
    <el-row>
      <el-card shadow="never" class="user-profile-card">
        <el-row>
          <h3>基础信息</h3>
        </el-row>
        <el-form class="user-profile-form" label-width="120px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="用户名">
                <el-input v-model="userProfile.username" disabled/>
              </el-form-item>
              <el-form-item label="昵称">
                <el-input v-model="userProfile.nickname"/>
              </el-form-item>
              <el-form-item class="flex-input-item" label="电话号码">
                <el-input v-model="userProfile.phone"/>
                <el-tag v-if="userProfile.phoneVerified" size="large" type="success">已验证</el-tag>
                <el-tag v-else size="large" type="info">未验证</el-tag>
              </el-form-item>
              <el-form-item class="flex-item" label="验证电话号码">
                <el-input placeholder="输入手机上收到的验证码"/>
                <el-button>发送验证码</el-button>
                <el-button type="primary">验证</el-button>
              </el-form-item>
              <el-form-item class="flex-input-item" label="邮箱地址">
                <el-input v-model="userProfile.email"/>
                <el-tag v-if="userProfile.emailVerified" size="large" type="success">已验证</el-tag>
                <el-tag v-else size="large" type="info">未验证</el-tag>
              </el-form-item>
              <el-form-item class="flex-item" label="验证邮箱地址">
                <el-input placeholder="输入邮箱中收到的验证码"/>
                <el-button>发送验证码</el-button>
                <el-button type="primary">验证</el-button>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item class="user-avatar-item">
                <div class="flex direction-vertical items-center">
                  <div class="avatar-wrapper">
                    <el-avatar shape="square" :size="100" :src="userProfile.avatarUrl"/>
                  </div>
                  <el-row class="flex justify-center">
                    <input type="file" style="display: none" ref="fileInput" @change="uploadLocalFile"/>
                    <avatar-dialog v-model="avatarDialogVisible" @change="selectAvatar"/>
                    <el-button type="primary" @click="onUploadButtonClick">上传本地图片</el-button>
                    <el-button type="primary" @click="onSelectAvatarButtonClick">选择内置头像</el-button>
                  </el-row>
                </div>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <el-row class="flex justify-end">
          <el-button>刷新</el-button>
          <el-button type="primary" @click="onBaseInfoSaveClick">保存</el-button>
        </el-row>
        <el-divider/>
        <el-row>
          <h3>修改密码</h3>
        </el-row>
        <el-form class="change-password-form" label-width="120px">
          <el-form-item label="当前密码">
            <z-password v-model="passwordChangeRequest.oldPassword"/>
          </el-form-item>
          <el-form-item label="新密码">
            <z-password v-model="passwordChangeRequest.newPassword"/>
          </el-form-item>
          <el-form-item label="确认新密码">
            <z-password v-model="confirmPassword"/>
          </el-form-item>
        </el-form>
        <el-row class="flex justify-end">
          <el-button type="primary" @click="onChangePassword">保存</el-button>
        </el-row>
      </el-card>
    </el-row>
  </el-space>
</template>

<style lang="scss">
.z-trader-user-profile {
  width: 100%;

  .user-profile-card {
    width: 100%;
    border: var(--z-trader-border-card);
    border-radius: var(--z-trader-border-radius-large);
    background-color: var(--z-trader-bg-color-white);

    .user-profile-form {
      .el-form-item {
        &.flex-input-item {
          .el-form-item__content {
            display: flex;
            column-gap: 10px;

            .el-input {
              width: 0;
              flex: 1;
            }
          }
        }

        &.flex-item {
          .el-form-item__content {
            .el-input {
              width: auto;
            }

            display: flex;
            column-gap: 10px;
          }
        }

        &.user-avatar-item {
          height: 100%;

          .el-form-item__content {
            .avatar-wrapper {
              padding: 24px;
              width: 100%;
              border: var(--z-trader-border-card);
              display: flex;
              justify-content: center;
              margin-bottom: 16px;
            }
          }
        }
      }
    }

    .change-password-form {
      max-width: 50%;
    }
  }
}
</style>