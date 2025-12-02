<script lang="ts">
import ButtonAtoms from '../Atoms/ButtonAtoms.vue'

export default {
  components: {
    ButtonAtoms,
  },
  emits: ['executeButton1-method', 'executeButton2-method'],
  props: {
    setButtonName1: String,
    setButtonName2: String,
    validatedNull: Boolean,
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
  <div class="modal_btn">
    <ButtonAtoms
      class="button1"
      :buttonName="setButtonName1"
      @click="executeButton1"
      :disabled="validatedNull"
    ></ButtonAtoms>
    <ButtonAtoms class="button2" :buttonName="setButtonName2" @click="executeButton2" />
  </div>
</template>

<style scoped>
.button1 {
  display: inline-block;
  margin: 0;
  text-decoration: none;
  color: #000000;
  border: solid 2px #42a4f5;
  border-radius: 3px;
  transition: 0.4s;
  text-align: center;
  vertical-align: middle;
  font-size: 15px;
  background-color: #8dc0e9;
}

.button1:hover {
  background: #42a4f5;
  color: white;
  cursor: pointer;
}

.button1:disabled {
  border-color: #c7c7c7;
  background-color: #eeeeee;
  color: #9b9b9b;
}

.button1:disabled:hover {
  background: #eeeeee;
  color: --btn-fg-disabled, #9b9b9b;
}

.button2 {
  display: inline-block;
  margin: 0;
  text-decoration: none;
  color: #000000;
  border: solid 2px #e07777;
  border-radius: 3px;
  transition: 0.4s;
  text-align: center;
  vertical-align: middle;
  font-size: 15px;
  background-color: #e07777;
}

.button2:hover {
  background: #c75050;
  color: white;
  cursor: pointer;
}

.button2:disabled {
  border-color: #c7c7c7;
  background-color: #eeeeee;
  color: #9b9b9b;
}

.button2:disabled:hover {
  background: #eeeeee;
  color: --btn-fg-disabled, #9b9b9b;
}
</style>