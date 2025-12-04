<script lang ="ts">
import date from '../Atoms/DateInputAtoms.vue'
export default {
  components: { date },
  props: ['setNullFlag'],
  emits: ['execute-method'],
  date() {
    return {
      fromDate: '',
      toDate: '', //Atomsから受け取った金額
      setDateResult: '', //Atomsから受け取ったエラーメッセージ
      setDateSizeResult: '', //Moleculesで出るエラーメッセージ
      setDateValidation: '', //Atomsから受けとった入力チェックの結果
      setDateSizeValidation: '', // Moleculesでの入力チェックの結果
    }
  },
  methods: {
    setDate(date: any, dateResult: any, dateValidation: any) {
      this.setDateResult = dateResult
      this.setDateValidation = dateValidation
      this.dateVakidate()
      this.$emit(
        'execute-method',
        this.fromDate,
        this.toDate,
        this.setDateResult,
        this.setDateSizeResult,
        this.setDateValidation,
        this.setDateSizeValidation
      )
    },

    dateVakidate() {
      const date_error_message = this.checkValidation()
      if (date_error_message === true) {
        this.setDateSizeResult = ''
        this.setDateSizeValidation = true
      } else {
        this.setDateSizeResult = date_error_message
        this.setDateSizeValidation = false
      }
    },

    checkValidation() {
      if (this.fromDate && this.toDate) {
        //from,to両方に値が入っていたら
        const fromDate = new Date(this.fromDate) //Dateオブジェクトに変換
        const toDate = new Date(this.toDate)
        if (fromDate > toDate) {
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
  <date class="modal_size" v-model="fromDate" :nullFlag="setNullFlag" @execute-method="setDate" />
  <label>{{ '~' }}</label>
  <date class="modal_size" v-model="toDate" :nullFlag="setNullFlag" @execute-method="setDate" />
</template>

<style scoped>
.modal_size {
  width: 143px; /* 横幅 */
  height: 30px; /* 高さ */
}
</style>