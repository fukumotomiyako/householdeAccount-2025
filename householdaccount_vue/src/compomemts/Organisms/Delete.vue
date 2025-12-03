<script lang="ts">
import Button from '../Molecules/ButtonGroup.vue'
import axios from 'axios'

export default {
  components: {
    Button,
  },
  props: ['incomeNo', 'expenditureNo'], //削除対象No
  emit: ['executeDelete-method'],
  data() {
    return {}
  },
  methods: {
    //収入削除
    incomeDelete: function (incomeNo: any) {
      try {
        //APIにincomeNo埋め込む
        axios.put('http://localhost:8080/api/income/' + incomeNo + '/delete').then((response) => {
          //responseが正常化チェック
          console.log(response)
        })
      } catch (error) {
        // tryの中が最後まで終わらなかったら実行
        console.log(error)
      }
    },

    //支出削除
    expenditureDelete: function (expenditureNo: any) {
      try {
        axios
          .put('http://localhost:8080/api/expenditure/' + expenditureNo + '/delete')
          .then((response) => {
            //responseが正常化チェック
            console.log(response)
          })
      } catch (error) {
        // tryの中が最後まで終わらなかったら実行
        console.log(error)
      }
    },

    executeCancel() {
      this.$emit('executeDelete-method')
    },

    executeDelete() {
      if (this.incomeNo) {
        //incomeNoに値が入っていたら
        this.incomeDelete(this.incomeNo)
      } else {
        //incomeNoに値が入っていなかったら
        this.expenditureDelete(this.expenditureNo)
      }
      this.executeCancel()
    },
  },
}
</script>

<template>
  <div id="modal">
    <div id="modal-content" class="modal">
      <p>1件のデータを削除します。よろしいですか？</p>
      <Button
        class="button"
        setButtonName1="キャンセル"
        setButtonName2="削除"
        @executeButton1-method="executeCancel"
        @executeButton2-method="executeDelete"
      />
    </div>
  </div>
</template>

<style scoped>
.modal {
  padding: 10px 20px; /*枠の余白*/
  border: 2px solid #a5272a; /*枠線*/
  background: #faebd7;
  z-index: 2;
  display: block;
  text-align: center;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 10px;
}

:deep(.button1) {
  display: inline-block;
  margin: 0;
  text-decoration: none;
  color: #000000;
  border: solid 2px #96989e !important;
  border-radius: 3px;
  transition: 0.4s;
  text-align: center;
  vertical-align: middle;
  font-size: 15px;
  background-color: #96989e !important;
}
:deep(.button1:hover) {
  background: #36424b;
  color: white;
  cursor: pointer;
}

.button {
  text-align: left;
}
</style>