<script lang="ts">
import ButtonAtoms from '../Atoms/ButtonAtoms.vue'

export default {
  components: {
    ButtonAtoms,
  },
  props: {
    setButtonName1: String,
    setButtonName2: String,
    validatedNull: String,
  },
  data() {
    return {}
  },

  methods: {
    executeButton1() {
      this.$emit('executeButton1-method')
    },
    executeButton2() {
      this.$emit('executeButton2-method')
    },
  },
}
</script>

<template>
  <div>
    <ButtonAtoms
      :buttonName="setButtonName1"
      @click="executeButton1"
      :disabled="validatedNull"
    ></ButtonAtoms>
    <ButtonAtoms :buttonName="setButtonName2" @click="executeButton2" />
  </div>
</template>