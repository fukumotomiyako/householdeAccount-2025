<script lang="ts">
import RadioButtonAtoms from '../Atoms/RadioButtonAtoms.vue'
export default {
  components: {
    RadioButtonAtoms,
  },
  emits: ['execute-method'],
  props: {
    setRadioBotton: String, //最初に選択されている状態にしたいラジオボタン名
    radioName1: String, //表示するラジオボタンの名前
    radioName2: String, //表示するラジオボタンの名前
    notSelect: Boolean, //選択可否のtrueかfalse
  },
  data() {
    return {
      radioButtonResult: '',
      setRadioName: '',
    }
  },
  methods: {
    setSelectRadio(selectRadioName: any) {
      this.setRadioName = selectRadioName
      this.selectRadioValidate()
      this.$emit('execute-method', this.setRadioName, this.radioButtonResult)
    },

    selectRadioValidate() {
      const selectRadio_error_message = this.selectRadioCheckValidate(this.setRadioName)
      if (selectRadio_error_message === true) {
        this.radioButtonResult = ''
      } else {
        this.radioButtonResult = selectRadio_error_message
      }
    },

    selectRadioCheckValidate(setRadioName: any) {
      if (!setRadioName) {
        return '選択してください'
      } else {
        return true
      }
    },
  },
}
</script>

<template>
  <RadioButtonAtoms
    :radioName="radioName1"
    :setRadio="setRadioBotton"
    :select="notSelect"
    @execute-method="setSelectRadio"
  />
  <!-- radioNameはAtomsに渡しているだけ -->
  <RadioButtonAtoms
    :radioName="radioName2"
    :setRadio="setRadioBotton"
    :select="notSelect"
    @execute-method="setSelectRadio"
  />
  <p>{{ radioButtonResult }}</p>
</template>