<script lang="ts">
export default {
  props: {
    buttonName: String,
  },
}
</script>

<template>
  <button class="btn">{{ buttonName }}</button>
  <!-- ボタンの名前を受け取って表示させるという処理のみ記述する -->
</template>


<style scoped>
.btn {
  width: 120px;
  border-radius: 6px;
  padding: 8px 16px;
}
</style>