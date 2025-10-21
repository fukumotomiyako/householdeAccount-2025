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
      if (note_error_messege != 'ture') {
        this.noteResult = note_error_messege
      }
      this.noteResult = ''
    },

    noteCheckValidate() {
      if (this.validatedNull == 'ture') {
        if (this.note.length <= 200) {
          return 'ture'
        }
        return '200文字以内で入力してください'
      }
      return 'ture'
    },
  },
}
</script>

<template>
  <textarea v-model="note" @blur="setNote()" placeholder="200文字以内で入力"> </textarea>
</template>