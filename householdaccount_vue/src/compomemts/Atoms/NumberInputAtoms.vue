<script lang="ts">
export default {
  props: ['getPrice', 'nullFlag'], //入力されていた金額
  emits: ['execute-method'],

  data() {
    return {
      price: '', //入力された金額
      priceResult: '', //エラーメッセージ
      priceValidation: '', //入力チェックの結果
    }
  },
  mounted() {
    this.price = this.getPrice
  },

  methods: {
    setPrice() {
      this.priceValidate()
      this.$emit('execute-method', this.price, this.priceResult, this.priceValidation)
    },

    priceValidate() {
      const price_error_message = this.priceCheckValidate(this.price)
      if (price_error_message === true) {
        this.priceValidation = true
        this.priceResult = ''
      } else {
        this.priceValidation = false
        this.priceResult = price_error_message
      }
    },

    priceCheckValidate(price: any) {
      // const s = String(price ?? '').trim()
      if (this.nullFlag === true) {
        if (!price) {
          return '8文字以内で入力してください'
        }
        if (!/^\d+$/.test(price)) {
          return '数字のみ入力できます'
        }
        if (price.length > 8) {
          return '8文字以内で入力してください'
        }
        return true
      } else if (this.nullFlag === false) {
        if (!price) {
          return true
        }
        if (!/^\d+$/.test(price)) {
          return '数字のみ入力できます'
        }
        if (price.length > 8) {
          return '8文字以内で入力してください'
        }
        return true
      }
    },
  },
}
</script>

<template>
  <input class="amount" type="text" v-model="price" placeholder="8桁以内で入力" @blur="setPrice" />
</template>

<style scoped>
.amount {
  width: 300px; /* 横幅 */
  height: 35px; /* 高さ */
}
</style>