<script lang="ts">
export default {
  props: {
    validatedNull: String,
  },

  data() {
    return {
      note: '',
      noteResult: '',
    }
  },

  methods: {
    setNote() {
      this.noteValidate()
      this.$emit('execute-method', this.note, this.noteResult)
    },

    noteValidate() {
      const note_error_messege = this.noteCheckValidate(this.note)
      if (note_error_messege == 'true') {
        this.noteResult = ''
      }
      this.noteResult = note_error_messege
    },

    noteCheckValidate(note: any) {
      if (note.length >= 2) {
        return '200文字以内で入力してください'
      }
      return 'true'
    },
  },
}
</script>

<template>
  <textarea v-model="note" @blur="setNote" placeholder="200文字以内で入力"> </textarea>
  <p>{{ noteResult }}</p>
</template>