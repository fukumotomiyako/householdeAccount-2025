<script lang ="ts">
import Date from '../Atoms/DateInputAtoms.vue'
export default {
  components: { Date },
  emits: ['executeFrom-method', 'executeTo-method'],
  date() {
    return {
      fromDate: '',
      toDate: '',
      fromDateResult: '',
      toDateResult: '',
      fromDateValidation: '',
      toDateValidation: '',
    }
  },
  methods: {
    setFromDate(date: any, dateResult: any, dateValidation: any) {
      this.fromDate = date
      this.fromVaridate(dateResult, dateValidation)
      this.$emit('executeFrom-method', this.fromDate, this.fromDateResult, this.fromDateValidation)
    },

    fromVakidate(dateResult: any, dateValidation: any) {
      const from_error_message = this.fromcheckValidate(this.fromDate)
      if (from_error_message === true) {
        this.fromDateResult = dateResult
        this.fromDateValidation = dateValidation
      } else {
        this.fromDateResult = from_error_message
        this.fromDateValidation = false
      }
    },

    fromcheckValidate(fromDate: any) {
      if (fromDate > this.toDate) {
        return 'toより先の前の日付を入力してください'
      } else {
        return true
      }
    },

    setToDate(date: any, dateResult: any, dateValidation: any) {
      this.toDate = date
      this.toValidate(dateResult, dateValidation)
      this.$emit('executeTo-method', this.toDate, this.toDateResult, this.toDateValidation)
    },

    toValidate(dateResult: any, dateValidation: any) {
      const to_error_message = this.toCheckValidation(this.toDate)
      if (to_error_message === true) {
        this.toDateResult = dateResult
        this.toDateValidation = dateValidation
      } else {
        this.toDateResult = to_error_message
        this.toDateValidation = false
      }
    },

    toCheckValidation(toDate: any) {
      if (this.fromDate > toDate) {
        return 'fromより先の日付を入力してください'
      } else {
        return true
      }
    },
  },
}
</script>
<template><Date @execute-method="setFromDate" />~<Date @execute-method="setToDate" /></template>