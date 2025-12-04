<script lang="ts">
import RadioButton from '../Atoms/RadioButtonAtoms.vue'

export default {
  components: { RadioButton },
  emits: ['execute-method'],
  props: ['setRadioButton', 'radioName1', 'radioName2', 'radioName3', 'notSelect', 'nullFlag'],
  date() {
    return {
      setRadioName: '',
      radioButtonResult: '',
      radioValidation: '',
    }
  },
  methods: {
    setSelectRadio(selectRadioName: any) {
      this.setRadioName = selectRadioName
      this.selectRadioValidate()
      this.$emit('execute-method', this.setRadioName, this.radioButtonResult, this.radioValidation)
    },
    selectRadioValidate() {
      const selectRadio_error_message = this.selectRadioCheckValidate(this.setRadioName)
      if (selectRadio_error_message === true) {
        this.radioValidation = true
        this.radioButtonResult = ''
      } else {
        this.radioValidation = false
        this.radioButtonResult = selectRadio_error_message
      }
    },

    selectRadioCheckValidate(setRadioName: any) {
      if (this.nullFlag === true) {
        if (!setRadioName) {
          return '選択してください'
        } else {
          return true
        }
      } else {
        return true
      }
    },
  },
}
</script>
<template>
  <div class="radio">
    <RadioButton
      :setRadio="setRadioButton"
      :radioName="radioName1"
      :select="notSelect"
      @execute-method="setSelectRadio"
    />
    <RadioButton
      :setRadio="setRadioButton"
      :radioName="radioName2"
      :select="notSelect"
      @execute-method="setSelectRadio"
    />
    <RadioButton
      :setRadio="setRadioButton"
      :radioName="radioName3"
      :select="notSelect"
      @execute-method="setSelectRadio"
    />
  </div>
</template>
<style scoped>
.radio {
  margin-left: 3px;
  margin-right: 14px;
  text-align: right;
}
</style>