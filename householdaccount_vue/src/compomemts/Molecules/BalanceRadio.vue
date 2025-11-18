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
      radioButtonResult: '', //エラーメッセージ
      setRadioName: '', //選択されたラジオボタン名
      radioValidation: '', //入力チェックの結果(true or false)
    }
  },
  methods: {
    setSelectRadio(selectRadioName: any) {
      this.setRadioName = selectRadioName
      //選択されたラジオボタン名をsetRadioNameに入れる
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
</template>