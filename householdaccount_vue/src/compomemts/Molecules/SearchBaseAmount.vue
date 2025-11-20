<script lang="ts">
import NumberInput from '../Atoms/NumberInputAtoms.vue'
export default {
  components: { NumberInput },
  emits: ['executeFrom-method', 'executeTo-method'],
  data() {
    return {
      fromAmount: '',
      toAmout: '',
      fromAmountResult: '',
      toAmountResult: '',
      fromAmountValidation: '',
      toAmountValidation: '',
    }
  },
  methods: {
    setFromAmount(price: any, priceResult: any, priceValidation: any) {
      this.fromAmount = price
      this.fromAmountResult = priceResult
      this.fromAmountValidation = priceValidation
      this.$emit(
        'executeFrom-method',
        this.fromAmount,
        this.fromAmountResult,
        this.fromAmountValidation
      )
    },
    setToAmount(price: any, priceResult: any, priceValidation: any) {
      this.toAmout = price
      this.toAmountResult = priceResult
      this.toAmountValidation = priceValidation
      this.$emit('executeTo-method', this.toAmout, this.toAmountResult, this.toAmountValidation)
    },
  },
}
</script>
<template>
  <NumberInput @execute-method="setFromAmount" />~<NumberInput @execute-method="setToAmount" />
</template>