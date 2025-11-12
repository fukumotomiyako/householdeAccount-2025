<script lang="ts">
export default {
  props: {
    setRadio: String,
    radioName: String,
    select: String,
  },
  data() {
    return {
      selectRadioName: '',
    }
  },

  methods: {
    setRadioButton() {
      this.selectRadioName = this.radioName
      this.$emit('execute-method', this.selectRadioName)
    },
  },
}
</script>

<template>
  <span v-if="radioName == setRadio">
    <input type="radio" name="radioCategory" @change="setRadioButton" checked :disabled="select" />
    {{ radioName }}
  </span>
  <span v-else>
    <input type="radio" name="radioCategory" @change="setRadioButton" :disabled="select" />
    {{ radioName }}
  </span>
</template>
