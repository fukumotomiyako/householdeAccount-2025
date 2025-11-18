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
    balanceNo: String, //編集対象支出No
  },
  emits: ['executeEdit-method'],
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
      validationFlag: true,
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
      validation: {
        //入力チェック
        dateValidation: true,
        incomeValidation: true,
        expenditureValidation: true,
        priceValidation: true,
        noteValidation: true,
      },

      errorMessage: {
        //エラーメッセージ
        dateResult: '',
        selectIncomeResult: '',
        selectExpenditureResult: '',
        priceResult: '',
        noteResult: '',
      },
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
          params: { No: this.balanceNo }, //支出Noを送る
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

    editIncome: function () {
      //変更結果(収入)をバックエンドに送るメソッド
      try {
        axios.patch('http://localhost:8080/api/income/edit', this.editInfo).then((response) => {
          console.log(response)
        })
        //レスポンスの結果をコンソールに出力
      } catch (error) {
        console.log(error)
      }
    },

    editExpenditure: function () {
      //変更結果(支出)をバックエンドに送るメソッド
      try {
        axios
          .patch('http://localhost:8080/api/expenditure/edit', this.editInfo)
          .then((response) => {
            console.log(response)
          })
        //レスポンスの結果をコンソールに出力
      } catch (error) {
        console.log(error)
      }
    },

    executekeep() {
      //変更内容保存処理
      if (this.editInfo.balanceType == '収入') {
        //ラジオボタン選択結果が収入なら
        this.editIncome()
      } else {
        this.editExpenditure()
      }
      this.executeCancel()
      //モーダルを閉じるメソッド呼び出し
    },

    executeCancel() {
      this.$emit('executeEdit-method')
    },

    editSetDate(date: any, dateResult: any, dateValidation: any) {
      this.editInfo.balanceDate = date
      this.errorMessage.dateResult = dateResult
      this.validation.dateValidation = dateValidation
      this.validationCheck()
    },
    editSelectIncome(selectIncome: any, selectIncomeResult: any, incomeValidation: any) {
      this.editInfo.incomeType = selectIncome
      this.errorMessage.incomeTypeResult = selectIncomeResult
      this.validation.incomeValidation = incomeValidation
      this.validationCheck()
    },
    editSelectExpenditure(
      selecctExpenditure: any,
      selectExpenditureResult: any,
      expenditureValidation: any
    ) {
      this.editInfo.expenditureExpenseItemName = selecctExpenditure
      this.errorMessage.expenditureTypeResult = selectExpenditureResult
      this.validation.expenditureValidation = expenditureValidation
      this.validationCheck()
    },
    editSetNumber(price: any, priceResult: any, priceValidation: any) {
      //編集した金額
      this.editInfo.amount = price
      this.errorMessage.priceResult = priceResult
      this.validation.priceValidation = priceValidation
      this.validationCheck()
    },
    editSetNote(note: any, noteResult: any, noteValidation) {
      this.editInfo.note = note
      //情報をまとめて送る
      this.errorMessage.noteResult = noteResult
      //バリデーションチェック行うためにれてる
      this.validation.noteValidation = noteValidation
      this.validationCheck()
    },
    validationCheck() {
      if (this.editInfo.balanceType == '収入') {
        if (
          this.validation.dateValidation &&
          this.validation.incomeValidation &&
          this.validation.priceValidation &&
          this.validation.noteValidation
        ) {
          //すべてtrueだったら
          this.validationFlag = false
        } else {
          //一つでもfalseがあったら
          this.validationFlag = true
        }
      } else if (this.editInfo.balanceType == '支出') {
        if (
          this.validation.dateValidation &&
          this.validation.expenditureValidation &&
          this.validation.priceValidation &&
          this.validation.noteValidation
        ) {
          this.validationFlag = false
        } else {
          this.validationFlag = true
        }
      }
    },
  },
}
</script>
<template>
  <div id="model">
    <div id="modal-content" class="modal">
      <h6>編集情報</h6>
      <div>
        <label>{{ '収支区分：' }}</label>
        <RadioButton
          :radioName1="setRadioName1"
          :radioName2="setRadioName2"
          :setRadioBotton="editInfo.balanceType"
          :notSelect="true"
        />
        <p>{{ errorMessage.radioButtonResult }}</p>
      </div>
      <div>
        <label>{{ '収支日付：' }}</label>
        <DateInput
          id="日付"
          :getDate="editInfo.balanceDate"
          :key="editInfo.balanceDate"
          @execute-method="editSetDate"
        />
        <p>{{ errorMessage.dateResult }}</p>
      </div>
      <div>
        <div v-if="editInfo.balanceType == '収入'">
          <FormSelect
            :selectRadioName="editInfo.balanceType"
            :getIncome="editInfo.incomeType"
            :items="expenditureItems"
            :key="editInfo.incomeType"
            @executeIncome-method="editSelectIncome"
          />
          <p>{{ errorMessage.selectIncomeResult }}</p>
        </div>

        <div v-if="editInfo.balanceType == '支出'">
          <FormSelect
            :selectRadioName="editInfo.balanceType"
            :getExpenditure="editInfo.expenditureExpenseItemName"
            :items="expenditureItems"
            :key="editInfo.expenditureExpenseItemName"
            @executeExpenditure-method="editSelectExpenditure"
          />
          <p>{{ errorMessage.selectExpenditureResult }}</p>
        </div>
      </div>
      <div>
        <label>{{ '金額：' }}</label>
        <NumberInput
          :getPrice="editInfo.amount"
          :key="editInfo.amount"
          @execute-method="editSetNumber"
        />
        <p>{{ errorMessage.priceResult }}</p>
      </div>
      <div>
        <label>備考：</label>
        <TextArea :getNote="editInfo.note" :key="editInfo.note" @execute-method="editSetNote" />
        <p>{{ errorMessage.noteResult }}</p>
      </div>

      <Button
        setButtonName1="保存"
        setButtonName2="キャンセル"
        :validatedNull="validationFlag"
        @executeButton1-method="executekeep"
        @executeButton2-method="executeCancel"
      />
    </div>
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