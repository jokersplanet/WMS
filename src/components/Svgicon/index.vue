<template>
    <svg class="svg-icon" aria-hidden="true">
      <use :xlink:href="iconName"></use>
    </svg>
  </template>
<script setup >
import { defineProps, computed } from 'vue'
const props = defineProps({
  icon: {
    type: String,
    required: true
  }
})
const iconName = computed(() => {
  return `#icon-${props.icon}`
})
</script>
<style lang="scss" scoped>
.svg-icon {
  width: 1em;
  height: 1em;
  vertical-align: -0.15em;
  fill: currentColor;
  overflow: hidden;
}
</style>
