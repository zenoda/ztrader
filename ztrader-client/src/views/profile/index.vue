<script setup>
import {onMounted, ref, useTemplateRef} from "vue";
import {getUserProfile, saveUserProfile, changePassword} from "@/lib/admin/UserClient.js"
import {uploadFile} from "@/lib/file/FileClient.js"
import AvatarDialog from "@/views/profile/AvatarDialog.vue";
import ZPassword from "@/components/ZPassword.vue";
import {ElMessage} from "element-plus";
import ZView from "@/components/ZView.vue";

function onNavChange(item) {
  console.log(item)
}

const userProfile = ref({})
const baseInfoForm = useTemplateRef('baseInfoForm')
const changePasswordForm = useTemplateRef('changePasswordForm')
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
  try {
    await baseInfoForm.value.validate()
  } catch (err) {
    console.error(err)
    ElMessage({type: 'error', message: '请检查输入内容'})
    return
  }
  try {
    userProfile.value = await saveUserProfile(userProfile.value)
    ElMessage({type: 'success', message: '保存成功'})
  } catch (err) {
    console.error(err)
    ElMessage({type: 'error', message: '保存失败'})
  }
}

const passwordChangeRequest = ref({oldPassword: null, newPassword: null, confirmPassword: null})

async function onChangePassword() {
  try {
    await changePasswordForm.value.validate()
  } catch (err) {
    console.error(err)
    ElMessage({type: 'error', message: '请检查输入内容'})
    return
  }
  try {
    userProfile.value = await changePassword(passwordChangeRequest.value)
    ElMessage({type: 'success', message: '密码修改成功'})
  } catch (err) {
    if (err.isAxiosError) {
      ElMessage({type: 'error', message: `保存失败:${err.response.data.message}`})
    } else {
      console.error(err)
      ElMessage({type: 'error', message: `保存失败`})
    }
  }
}

const baseInfoFormRules = ref({
  nickname: {
    required: true,
    message: '请输入昵称'
  },
  phone: {
    required: true,
    message: '请输入手机号码'
  },
  email: {
    required: true,
    message: '请输入邮箱地址'
  },
  avatarUrl: {
    required: true,
    message: '请选择或上传头像'
  }
})

const changePasswordFormRules = ref({
  oldPassword: {
    required: true,
    message: '请输入当前密码'
  },
  newPassword: {
    required: true,
    message: '请输入新密码'
  },
  confirmPassword: [{
    required: true,
    message: '请再输入一次新密码进行确认'
  }, {
    validator(rule, value, callback) {
      return value === passwordChangeRequest.value.newPassword
    },
    message: '确认密码要与新密码相同'
  }]
})
</script>

<template>
  <z-view class="z-trader-user-profile" header="个人资料">
    <el-card shadow="never" class="user-profile-card large-border-radius">
      <el-row>
        <h3>基础信息</h3>
      </el-row>
      <el-form class="user-profile-form" label-width="120px" :model="userProfile" :rules="baseInfoFormRules"
               ref="baseInfoForm">
        <el-row>
          <el-col :span="12">
            <el-form-item label="用户名">
              <el-input v-model="userProfile.username" disabled/>
            </el-form-item>
            <el-form-item label="昵称" prop="nickname">
              <el-input v-model="userProfile.nickname"/>
            </el-form-item>
            <el-form-item class="flex-input-item" label="电话号码" prop="phone">
              <el-input v-model="userProfile.phone"/>
              <el-tag v-if="userProfile.phoneVerified" size="large" type="success">已验证</el-tag>
              <el-tag v-else size="large" type="info">未验证</el-tag>
            </el-form-item>
            <el-form-item class="flex-item" label="验证电话号码">
              <el-input placeholder="输入手机上收到的验证码"/>
              <el-button>发送验证码</el-button>
              <el-button type="primary">验证</el-button>
            </el-form-item>
            <el-form-item class="flex-input-item" label="邮箱地址" prop="email">
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
            <el-form-item class="user-avatar-item" prop="avatarUrl">
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
      <el-form class="change-password-form" label-width="120px" :rules="changePasswordFormRules"
               :model="passwordChangeRequest" ref="changePasswordForm">
        <el-form-item label="当前密码" prop="oldPassword">
          <z-password v-model="passwordChangeRequest.oldPassword"/>
        </el-form-item>
        <el-form-item label="新密码" prop="newPassword">
          <z-password v-model="passwordChangeRequest.newPassword"/>
        </el-form-item>
        <el-form-item label="确认新密码" prop="confirmPassword">
          <z-password v-model="passwordChangeRequest.confirmPassword"/>
        </el-form-item>
      </el-form>
      <el-row class="flex justify-end">
        <el-button type="primary" @click="onChangePassword">保存</el-button>
      </el-row>
    </el-card>
  </z-view>
</template>

<style lang="scss">
.z-trader-user-profile {
  .user-profile-card {
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