<script lang="ts">
export default {
  props: {
    setRadio: String, //最初に選択されている状態にしたいラジオボタン名
    radioName: String, //表示するラジオボタンの名前
    select: Boolean, //選択可否のtrueかfalse
  },
  emits: ['execute-method'],
  data() {
    return {
      selectRadioName: '',
    }
  },

  methods: {
    setRadioButton() {
      this.selectRadioName = this.radioName
      //選択されたラジオボタン名をselectRadioNameに入れて送る
      this.$emit('execute-method', this.selectRadioName)
    },
  },
}
</script>

<template>
  <span class="radio" v-if="radioName == setRadio">
    <input type="radio" name="radioCategory" @change="setRadioButton" checked :disabled="select" />
    {{ radioName }}
  </span>
  <span class="radio" v-else>
    <input type="radio" name="radioCategory" @change="setRadioButton" :disabled="select" />
    {{ radioName }}
  </span>
</template>

<style scoped>
.radio {
  font-size: 20px;
}
</style>
