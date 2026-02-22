<script setup>
const avatars = [
  "/avatars/avatar1.png",
  "/avatars/avatar2.png",
  "/avatars/avatar3.png",
  "/avatars/avatar4.png"
]
const visible = defineModel()
const emit = defineEmits(['change'])

function selectAvatar(url) {
  visible.value = false
  emit('change', url)
}
</script>

<template>
  <el-dialog :close-on-click-modal="false" v-model="visible" title="选择头像" class="z-trader-avatar-dialog">
    <div class="avatar-row">
      <el-image :src="avatars[0]" @click="selectAvatar(avatars[0])"/>
      <el-image :src="avatars[1]" @click="selectAvatar(avatars[1])"/>
    </div>
    <div class="avatar-row">
      <el-image :src="avatars[2]" @click="selectAvatar(avatars[2])"/>
      <el-image :src="avatars[3]" @click="selectAvatar(avatars[3])"/>
    </div>
  </el-dialog>
</template>

<style lang="scss">
.z-trader-avatar-dialog {
  .el-dialog__body {
    background-color: var(--z-trader-bg-color-base);
    padding: 24px;

    .avatar-row {
      display: flex;
      justify-content: center;
      gap: 24px;
      margin-bottom: 24px;

      .el-image {
        border-radius: 14px;
        cursor: pointer;

        &:hover {
          opacity: 0.8;
        }

        img {
          width: 256px;
        }
      }
    }
  }
}
</style>