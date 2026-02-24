<script setup>
import {useSlots} from "vue";

const {header} = defineProps({
  header: {type: String},
  noPadding: {type: Boolean, default: false}
})
const slots = useSlots()
</script>

<template>
  <div class="z-view" :class="{'no-padding': noPadding}">
    <div class="z-view__header" v-if="header||slots.header">
      <slot name="header">
        <h3 v-if="header">{{ header }}</h3>
      </slot>
    </div>
    <div class="z-view__body" v-if="slots.default"
         :class="{'no-header':!header&&!slots.header,'no-footer':!slots.footer}">
      <slot name="default"></slot>
    </div>
    <div class="z-view__footer" v-if="slots.footer"></div>
    <slot name="footer"></slot>
  </div>
</template>

<style lang="scss">
.z-view {
  .z-view__header {
    padding: 0 20px;
    height: 60px;
    display: flex;
    align-items: center;
  }

  .z-view__body {
    padding: 0 20px;

    &.no-padding {
      padding: 0;
    }

    &.no-header {
      padding-top: 20px;
    }

    &.no-footer {
      padding-bottom: 20px;
    }
  }

  .z-view__footer {
    padding: 0 20px;
    height: 60px;
  }
}
</style>