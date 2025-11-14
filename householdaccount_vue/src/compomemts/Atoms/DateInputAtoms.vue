<script lang="ts">
export default {
  props: ['getDate', 'id'],

  data() {
    return {
      date: this.getDate,
      dateResult: '',
    }
  },

  methods: {
    setDate() {
      this.dateValidate()
      this.dateNumberValidate()
      this.$emit('execute-method', this.date, this.dateResult, this.dateNumberResult)
    },

    dateValidate() {
      const date_error_message = this.dateCheckValidate(this.date)
      if (date_error_message === true) {
        this.dateResult = ''
      } else {
        this.dateResult = date_error_message
      }
    },

    dateCheckValidate(date: any) {
      if (!date) {
        return '日付を入力してください'
      } else {
        return true
      }
    },

    dateNumberValidate() {
      const date_error_message = this.dateNumberCheckValidate(this.date)
      if (date_error_message === true) {
        this.dateNumberResult = ''
      } else {
        this.dateNumberResult = date_error_message
      }
    },

    dateNumberCheckValidate(date: any) {
      if (/^\d{4}\/\d{2}\/\d{2}$/.test(date)) {
        //正規表現　/../の中に書くことで、一致するか判定
        //^文字列の先頭という意味
        //d{4}数字が4回繰り返される
        //test　正規表現に一致するか判定　一致したらtrue 一致しなければfalse
        return '数字のみ入力できます'
      } else {
        return true
      }
    },
  },
}
</script>

<template>
  <input type="date" v-model="date" @blur="setDate" placeholder="YYYY/MM/DD" :id="id" />
  <p>{{ 'dateAtomsで出力' }}</p>
  <p>{{ dateNumberResult }}</p>
  <p>{{ dateResult }}</p>
</template>