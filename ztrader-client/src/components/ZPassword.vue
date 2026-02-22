<script setup>
import {computed, ref} from "vue";
import visibleIcon from "@/assets/visible.svg";
import invisibleIcon from "@/assets/invisible.svg";

const {size, placeholder} = defineProps({
  size: {type: String, default: 'default'},
  placeholder: {type: String, default: '请输入密码'}
})
const password = defineModel()
const passwordVisible = ref(false)
const iconSize = ref({
  small: 12,
  default: 18,
  large: 24
})
</script>

<template>
  <el-input class="z-trader-password" :size="size" :type="passwordVisible?'text':'password'" :placeholder="placeholder"
            v-model="password">
    <template v-slot:prefix>
      <img :width="iconSize[size]" src="@/assets/lock.svg"/>
    </template>
    <template v-slot:suffix>
      <img :width="iconSize[size]" :src="passwordVisible ? visibleIcon : invisibleIcon" style="cursor: pointer"
           @click="passwordVisible = !passwordVisible"/>
    </template>
  </el-input>
</template>

<style lang="scss">

</style>