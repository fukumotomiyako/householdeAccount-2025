<script lang="ts">
export default {
  props: {
    getNote: String, //入力されていた備考
  },
  emits: ['execute-method'],

  data() {
    return {
      note: '', //入力結果
      noteResult: '', //エラーメッセージ
      noteValidation: '', //入力チェックの結果
    }
  },

  mounted() {
    this.note = this.getNote
  },

  methods: {
    setNote() {
      this.noteValidate()
      this.$emit('execute-method', this.note, this.noteResult, this.noteValidation)
    },

    noteValidate() {
      const note_error_messege = this.noteCheckValidate(this.note)
      if (note_error_messege === true) {
        this.noteValidation = true
        this.noteResult = ''
      } else {
        this.noteValidation = false
        this.noteResult = note_error_messege
      }
    },

    noteCheckValidate(note: any) {
      if (note) {
        if (note.length > 200) {
          //noteが200文字以上
          return '200文字以内で入力してください'
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
  <textarea v-model="note" @blur="setNote" placeholder="200文字以内で入力"> </textarea>
</template>