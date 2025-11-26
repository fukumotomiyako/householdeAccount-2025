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
      if (this.nullFlag === true) {
        if (!price || String(price).length > 8 || price == 0) {
          return '8文字以内で入力してください'
        } else if (!/^\d+$/.test(String(price))) {
          //正規表現
          //^　文字列の先頭
          //d　数字
          //$　文字列の末尾　数字だけで構成された文字列
          return '数字のみ入力できます'
        } else {
          return true
        }
      } else if (this.nullFlag === false) {
        if (String(price).length > 8 || price == 0) {
          return '8文字以内で入力してください'
        } else if (!/^\d+$/.test(String(price))) {
          return '数字のみ入力できます'
        } else {
          return true
        }
      }
    },
  },
}
</script>

<template>
  <input type="number" v-model="price" placeholder="8桁以内で入力" @blur="setPrice" />
</template>