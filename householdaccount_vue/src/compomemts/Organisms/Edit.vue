<script lang="ts">
import RadioButton from '../Molecules/BalanceRadio.vue'
import DateInput from '../Atoms/DateInputAtoms.vue'
import FormSelect from '../Atoms/FormSelectAtoms.vue'
import NumberInput from '../Atoms/NumberInputAtoms.vue'
import TextArea from '../Atoms/TextArea.vue'
import Button from '../Molecules/ButtonGroup.vue'
import axios from 'axios'

export default {
  props: {
    balanceNo: String,
  },
  components: {
    //使用するコンポーネントを宣言
    RadioButton,
    DateInput,
    TextArea,
    FormSelect,
    NumberInput,
    Button,
  },
  data() {
    return {
      Test: '', //デバック用
      editInfo: {
        //DBから取得した登録情報
        balanceType: '',
        //DBから取得したラジオボタン
        balanceNo: '',
        //DBから取得した収支No
        balanceDate: '',
        //DBから取得した日付
        incomeType: '',
        //DBから取得した収入
        expenditureExpenseItemName: '',
        //DBから取得した支出費目
        amount: '',
        //DBから取得した金額
        note: '',
        //DBから取得した備考
      },

      setRadioName1: '収入',
      setRadioName2: '支出', //ラジオボタンの名前指定
      expenditureItems: [
        //支出費目
        {
          expenditure_expense_item_code: '',
          //支出費目　添字
          expenditure_expense_item_name: '',
          //支出費目　要素名
          expenditure_expense_item_name_kana: '',
          //支出費目　カナ
        },
      ],
    }
  },

  mounted() {
    this.getEditInfo()
    //モーダル表示時、編集データ表示のため最初に実行
    this.getExpenditureItems()
    //支出費目取得
  },
  methods: {
    async getEditInfo() {
      //編集するデータ取得
      try {
        const response = await axios.get('http://localhost:8080/api/search/balance?', {
          params: { No: this.balanceNo },
        })
        //APIで収支Noを送る
        this.editInfo = response.data
        //editInfoに取得した情報を入れる
      } catch (error) {
        console.log('編集データを取得できませんでした', error)
        //try内でエラーが出たら、ここにくる
      }
    },
    async getExpenditureItems() {
      try {
        const response = await axios.get('http://localhost:8080/api/expenditureItems')
        //     //これが完了されるまでモーダル表示されない
        this.expenditureItems = response.data
        //     //支出費目の関数に取得したデータを入れる
      } catch (error) {
        //     //tryの中が最後まで実行されなかったら呼ばれる
        console.log('取得できませんでした', error)
      }
    },
    executekeep() {
      //変更内容保存処理
      this.executeCancel()
      //モーダルを閉じるメソッド呼び出し
    },
    executeCancel() {
      this.$emit('executeEdit-method')
    },
  },
}
</script>
<template>
  <div id="modal-content" class="modal">
    <p>{{ editInfo }}</p>
    <div>
      <label>{{ '収支区分：' }}</label>
      <RadioButton :radioName1="setRadioName1" :radioName2="setRadioName2" />
    </div>
    <DateInput :getDate="editInfo.balanceDate" :key="editInfo.balanceDate" />
    <FormSelect :selectRadioName="editInfo.balanceType" :items="expenditureItems" />
    <div>
      <label>{{ '金額：' }}</label>
      <NumberInput :getPrice="editInfo.amount" :key="editInfo.amount" />
    </div>
    <div>
      <label>備考：</label>
      <TextArea :getNote="editInfo.note" :key="editInfo.note" />
    </div>
    <Button
      setButtonName1="保存"
      setButtonName2="キャンセル"
      @executeButton1-method="executekeep"
      @executeButton2-method="executeCancel"
    />
  </div>
</template>

<style scoped>
.modal {
  padding: 10px 20px;
  border: 2px solid #a5272a;
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

.modal__message {
  margin-top: 10px;
}

.modal__cancel {
  margin-right: 30px;
  font-size: 15px;
}

.modal__cancel:hover {
  cursor: pointer;
  color: rgb(14, 48, 240);
  font-weight: bold;
}

.modal__btn {
  display: inline-block;
  margin: 30px auto;
  text-decoration: none;
  width: 80px;
  height: 30px;
  text-decoration: none;
  color: #000000;
  border: solid 2px #a5272a;
  border-radius: 3px;
  transition: 0.4s;
  text-align: center;
  vertical-align: middle;
  font-size: 15px;
  background-color: #faebd7;
}

.modal__btn:hover {
  background: #a5272a;
  color: white;
  cursor: pointer;
}

#modal-overlay {
  z-index: 1;
  display: block;
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 120%;
  background-color: rgba(0, 0, 0, 0.75);
}
</style>