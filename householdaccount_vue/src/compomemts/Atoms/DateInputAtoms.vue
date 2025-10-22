<script lang="ts">
export default {
  props: {
    validatedNull: String,
  },

  data() {
    return {
      date: '',
      dateResult: '',
    }
  },

  methods: {
    setDate() {
      this.dateValidate()
      this.$emit('execute-method', this.date, this.dateResult)
    },

    dateValidate() {
      const date_error_message = this.dateCheckValidate(this.date)
      if (date_error_message != 'true') {
        this.dateResult = date_error_message
      }
      this.dateResult = ''
    },

    dateCheckValidate() {
      if (this.validatedNull == 'true') {
        if (this.date == null) {
          return '日付を入力してください'
        }
        return true
      }
      return true
    },
  },
}
</script>

<template>
  <input type="date" v-model="date" @blur="setDate" placeholder="YYYY/MM/DD" />
</template>