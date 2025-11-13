<script lang="ts">
export default {
  props: ['getPrice'],

  data() {
    return {
      price: this.getPrice,
      priceResult: '',
      test: '',
    }
  },

  methods: {
    setPrice() {
      this.priceNumberValidate()
      this.priceValidate()
      this.$emit('execute-method', this.price, this.priceResult, this.priceNumberResult)
    },

    priceValidate() {
      const price_error_message = this.priceCheckValidate(this.price)
      if (price_error_message === true) {
        this.priceResult = ''
      } else {
        this.priceResult = price_error_message
      }
    },

    priceCheckValidate(price: any) {
      if (!price || String(price).length > 8) {
        return '8文字以内で入力してください'
      } else {
        return true
      }
    },

    priceNumberValidate() {
      const price_error_message = this.priceNumberCheckValidate(this.price)
      if (price_error_message === true) {
        this.priceNumberResult = ''
      } else {
        this.price = ''
        this.priceNumberResult = price_error_message
      }
    },

    priceNumberCheckValidate(price: any) {
      if (!/^\d+$/.test(String(price))) {
        return '数字のみ入力できます'
      } else {
        return true
      }
    },
  },
}
</script>

<template>
  <input type="number" v-model="price" placeholder="8桁以内で入力" @blur="setPrice" />
  <p>{{ priceNumberResult }}</p>
  <p>{{ priceResult }}</p>
</template>