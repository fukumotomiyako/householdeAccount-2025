<script lang="ts">
export default {
  props: {
    buttonName: String,
  },
}
</script>

<template>
  <button>{{ buttonName }}</button>
  <!-- ボタンの名前を受け取って表示させるという処理のみ記述する -->
</template>