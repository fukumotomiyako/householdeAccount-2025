<script lang="ts">
export default {
  props: {
    validatedNull: String,
  },

  data() {
    return {
      date: '',
      dateResult: '',
      test: '',
    }
  },

  methods: {
    setDate() {
      this.dateValidate()
      this.$emit('execute-method', this.date, this.dateResult)
    },

    dateValidate() {
      const date_error_message = this.dateCheckValidate(this.date)
      if (date_error_message === true) {
        this.test = 'aaa'
        this.dateResult = ''
      }
      this.dateResult = date_error_message
    },

    dateCheckValidate(data: any) {
      if (data == null) {
        this.test = 'mmmm'
        return '日付を入力してください'
      }
      return true
    },

    // dateCheckValidate(data: any) {
    //   // if (this.validatedNull == 'true') {
    //   if (data == null) {
    //     this.test = 'gggg'
    //     return '日付を入力してください'
    //   }
    //   return true
    // },
    //   return true
    // },
  },
}
</script>

<template>
  <p>{{ test }}</p>
  <input type="date" v-model="date" @blur="setDate" placeholder="YYYY/MM/DD" />
  <p>{{ dateResult }}</p>
</template>