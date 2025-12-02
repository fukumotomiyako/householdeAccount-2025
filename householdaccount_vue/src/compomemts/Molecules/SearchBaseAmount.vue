<script lang="ts">
import NumberInput from '../Atoms/NumberInputAtoms.vue'
export default {
  components: { NumberInput },
  emits: ['execute-method'],
  props: ['setNullFlag'],
  data() {
    return {
      fromAmount: '',
      toAmount: '', //Atomsから受け取った金額
      setFromPriceResult: '', //Atomsから受け取ったエラーメッセージ
      setToPriceResult: '',
      setAmountResult: '', //Moleculesで出るエラーメッセージ
      setPriceValidation: '', //Atomsから受けとった入力チェックの結果
      setPriceSizeValidation: '', //Moleculesでの入力チェックの結果
    }
  },
  methods: {
    setAmount(price: any, priceResult: any, priceValidation: any) {
      if (price == this.fromAmount) {
        //priceとfromAmountの値が同じだったら、
        this.setFromPriceResult = priceResult //setFromPriceResultにpriceResultをいれる
      } else {
        this.setToPriceResult = priceResult //それ以外はsetToPriceResultにpriceResultをいれる
      }
      this.setPriceValidation = priceValidation
      this.amountValidate()
      this.$emit(
        'execute-method',
        this.fromAmount,
        this.toAmount,
        this.setFromPriceResult,
        this.setToPriceResult,
        this.setAmountResult,
        this.setPriceValidation,
        this.setPriceSizeValidation
      )
    },

    amountValidate() {
      const amount_error_message = this.checkValidate()
      if (amount_error_message === true) {
        this.setAmountResult = ''
        this.setPriceSizeValidation = true
      } else {
        this.setAmountResult = amount_error_message
        this.setPriceSizeValidation = false
      }
    },

    checkValidate() {
      if (this.fromAmount && this.toAmount) {
        if (this.fromAmount > this.toAmount) {
          return 'from<toの形式で入力してください'
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
  <NumberInput
    class="modal_size"
    v-model="fromAmount"
    :nullFlag="setNullFlag"
    @execute-method="setAmount"
  />
  <label>{{ '~' }}</label>
  <NumberInput
    class="modal_size"
    v-model="toAmount"
    :nullFlag="setNullFlag"
    @execute-method="setAmount"
  />
</template>

<style scoped>
.modal_size {
  width: 140px; /* 横幅 */
  height: 30px; /* 高さ */
}
</style>