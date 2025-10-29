<script lang="ts">
export default {
  props: {
    validatedNull: String,
  },

  data() {
    return {
      price: '',
      priceResult: 'aa',
    }
  },

  methods: {
    setPrice() {
      this.priceValidate()
      this.$emit('execute-method', this.price, this.priceResult)
    },

    priceValidate() {
      const price_error_message = this.priceCheckValidate(this.price)
      if (price_error_message != 'ture') {
        this.priceResult = price_error_message
      }
      this.priceResult = ''
      this.priceResult = 'bb'
    },

    priceCheckValidate() {
      if (this.validatedNull == 'true') {
        if (this.price.length <= 8) {
          return true
        }
        return '8文字以内で入力してください'
      }
      return true
    },
  },
}
</script>

<template>
  <p>{{ priceResult }}</p>
  <input type="number" v-model="price" placeholder="8桁以内で入力" @blur="setPrice" />
  <!-- <input type="number" v-model.number="price" placeholder="8桁以内で入力" @blur="setPrice" /> -->
</template>