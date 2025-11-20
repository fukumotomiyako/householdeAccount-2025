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
      this.fromDateResult = dateResult
      this.fromDateValidation = dateValidation
      this.$emit('executeFrom-method', this.fromDate, this.fromDateResult, this.fromDateValidation)
    },
    setToDate(date: any, dateResult: any, dateValidation: any) {
      this.toDate = date
      this.toDateResult = dateResult
      this.toDateValidation = dateValidation
      this.$emit('executeTo-method', this.toDate, this.toDateResult, this.toDateValidation)
    },
  },
}
</script>
<template><Date @execute-method="setFromDate" />~<Date @execute-method="setToDate" /></template>