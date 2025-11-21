<script lang="ts">
import { constants } from 'buffer'
import NumberInput from '../Atoms/NumberInputAtoms.vue'
export default {
  components: { NumberInput },
  emits: ['executeFrom-method', 'executeTo-method'],
  data() {
    return {
      fromAmount: '',
      toAmount: '',
      fromAmountResult: '',
      toAmountResult: '',
      fromAmountValidation: '',
      toAmountValidation: '',
    }
  },
  methods: {
    setFromAmount(price: any, priceResult: any, priceValidation: any) {
      this.fromAmount = price
      this.fromValidate(priceResult, priceValidation)
      this.$emit(
        'executeFrom-method',
        this.fromAmount,
        this.fromAmountResult,
        this.fromAmountValidation
      )
    },

    fromValidate(priceResult: any, priceValidation: any) {
      const from_error_message = this.fromcheckValidate(this.fromAmount)
      if (from_error_message === true) {
        this.fromAmountResult = priceResult
        this.fromAmountValidation = priceValidation
      } else {
        this.fromAmountResult = from_error_message
        this.fromAmountValidation = false
      }
    },

    fromcheckValidate(fromAmount: any) {
      if (this.toAmount) {
        if (fromAmount > this.toAmount) {
          return 'toより小さい値を入力してください'
        } else {
          return true
        }
      }
      return true
    },

    setToAmount(price: any, priceResult: any, priceValidation: any) {
      this.toAmount = price
      this.toValidate(priceResult, priceValidation)
      this.$emit('executeTo-method', this.toAmout, this.toAmountResult, this.toAmountValidation)
    },

    toValidate(priceResult: any, priceValidation: any) {
      const to_error_message = this.tocheckValidate(this.toAmount)
      if (to_error_message === true) {
        this.toAmountResult = priceResult
        this.toAmountValidation = priceValidation
      } else {
        this.toAmountResult = to_error_message
        this.toAmountValidation = false
      }
    },

    tocheckValidate(toAmount: any) {
      if (this.fromAmount) {
        if (this.fromAmount > toAmount) {
          return 'fromより大きい値を入力してください'
        } else {
          return true
        }
      }
      return true
    },
  },
}
</script>

<template>
  <p>{{ 'from' }}</p>
  <p>{{ fromAmountResult }}</p>
  <p>{{ fromAmountValidation }}</p>
  <p>{{ 'to' }}</p>
  <p>{{ toAmountResult }}</p>
  <p>{{ toAmountValidation }}</p>
  <NumberInput @execute-method="setFromAmount" />~<NumberInput @execute-method="setToAmount" />
</template>