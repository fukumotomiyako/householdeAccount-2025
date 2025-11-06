<script lang="ts">
export default {
  props: {
    validatedNull: String,
  },

  data() {
    return {
      price: '',
      priceResult: '',
      test: '',
    }
  },

  methods: {
    setPrice() {
      this.priceValidate()
      this.$emit('execute-method', this.price, this.priceResult)
    },

    priceValidate() {
      const price_error_message = this.priceCheckValidate(this.price)
      if (price_error_message == 'true') {
        this.test = 'gggg'
        this.priceResult = ''
      }
      this.priceResult = price_error_message
    },

    priceCheckValidate(price: any) {
      if (price.length >= 8) {
        // this.test = 'hhhhh'
        return '8文字以内で入力してください'
      }
      return 'true'
    },
  },
}
</script>

<template>
  <p>{{ test }}</p>
  <input type="number" v-model="price" placeholder="8桁以内で入力" @blur="setPrice" />
  <p>{{ priceResult }}</p>
</template>