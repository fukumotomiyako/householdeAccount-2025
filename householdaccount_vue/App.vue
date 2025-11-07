<script lang = "ts">
import regist from '../src/compomemts/Organisms/Regist.vue'

export default {
  components: {
    regist,
  },
  data() {
    return {
      regist_modal: false,
    }
  },

  methods: {
    excuteRegist() {
      this.regist_modal = true
      //このファイルで定義したregist_modal
    },

    returnScreen() {
      this.regist_modal = false
    },
  },
}
</script>

<template>
  <div>
    <button @click="excuteRegist">収支登録</button>
    <div v-if="regist_modal == true">
      <regist @execute-method="returnScreen" />
    </div>
    <p>APP</p>
    <p>resist_Modal</p>
    <p>{{ regist_modal }}</p>
  </div>
</template>