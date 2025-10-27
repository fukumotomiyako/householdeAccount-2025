<script lang="ts">
export default {
  props: {
    radioName: String,
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
  <span v-if="radioName == '収入'">
    <input type="radio" name="radioCategory" @change="setRadioButton" checked />
    {{ radioName }}
  </span>
  <span v-else>
    <input type="radio" name="radioCategory" @change="setRadioButton" />
    {{ radioName }}
  </span>
</template>
