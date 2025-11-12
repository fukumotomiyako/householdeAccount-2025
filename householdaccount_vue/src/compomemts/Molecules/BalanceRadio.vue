<script lang="ts">
import RadioButtonAtoms from '../Atoms/RadioButtonAtoms.vue'
export default {
  components: {
    RadioButtonAtoms,
  },
  props: {
    validatedNull: String,
    setRadioBotton: String,
    radioName1: String,
    radioName2: String,
    notSelect: String,
  },
  data() {
    return {
      radioButtonResult: '',
      setRadioName: '',
    }
  },
  methods: {
    setSelectRadio(radioName: any) {
      this.selectRadioValidate()
      this.setRadioName = radioName
      this.$emit('execute-method', this.setRadioName, this.radioButtonResult)
    },

    selectRadioValidate() {
      const selectRadio_error_message = this.selectRadioCheckValidate(this.radioName)
      if (selectRadio_error_message != 'true') {
        this.radioButtonResult = selectRadio_error_message
      }
      this.radioButtonResult = ''
    },

    selectRadioCheckValidate() {
      if (this.vlidalidatedNull == 'true') {
        if (!this.selectRadio) {
          return '選択してください'
        }
        return 'true'
      }
      return 'true'
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