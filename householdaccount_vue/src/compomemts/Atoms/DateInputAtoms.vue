<script lang="ts">
export default {
  props: ['getDate', 'id', 'nullFlag'],
  emits: ['execute-method'],

  data() {
    return {
      date: '', //入力された日付
      dateResult: '', //エラーメッセージ
      dateValidation: '', //入力チェックの結果(true or false)
    }
  },

  mounted() {
    this.date = this.getDate
  },

  methods: {
    setDate() {
      this.dateValidate()
      this.$emit('execute-method', this.date, this.dateResult, this.dateValidation)
    },

    dateValidate() {
      const date_error_message = this.dateCheckValidate(this.date)
      if (date_error_message === true) {
        this.dateValidation = true
        this.dateResult = ''
      } else {
        this.dateValidation = false
        this.dateResult = date_error_message
      }
    },

    dateCheckValidate(date: any) {
      if (this.nullFlag === true) {
        if (!date) {
          return '日付を入力してください'
        } else if (/^\d{4}\/\d{2}\/\d{2}$/.test(date)) {
          //正規表現　/../の中に書くことで、一致するか判定
          //^文字列の先頭という意味
          //d{4}数字が4回繰り返される
          //test　正規表現に一致するか判定　一致したらtrue 一致しなければfalse
          return '数字のみ入力できます'
        } else {
          return true
        }
      } else if (this.nullFlag === false) {
        if (/^\d{4}\/\d{2}\/\d{2}$/.test(date)) {
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
  <input class="date" type="date" v-model="date" @blur="setDate" placeholder="YYYY/MM/DD" />
</template>

<style scoped>
.date {
  width: 300px; /* 横幅 */
  height: 35px; /* 高さ */
}
</style>