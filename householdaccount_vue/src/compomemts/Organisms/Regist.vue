<script lang="ts">
import RadioButton from '../Molecules/BalanceRadio.vue'
import DateInput from '../Atoms/DateInputAtoms.vue'
import FormSelect from '../Atoms/FormSelectAtoms.vue'
import NumberInput from '../Atoms/NumberInputAtoms.vue'
import TextArea from '../Atoms/TextArea.vue'
import Button from '../Molecules/ButtonGroup.vue'
import { ref } from 'vue'
import axios from 'axios'

export default {
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
      NoteTest: 'aa', //デバック用
      setSelectRadio: '収入', //モーダル開かれて最初に収入選択された状態にするため
      setRadioName1: '収入',
      setRadioName2: '支出', //ラジオボタンの名前指定
      setButtonName1: '保存',
      setButtonName2: 'キャンセル', //ボタンの名前指定
      incomeTypes: [
        { value: '1', text: '給与' },
        { value: '2', text: '賞与' },
        { value: '3', text: '副業' },
        { value: '4', text: 'お小遣い' },
        { value: '5', text: '臨時収入' },
        { value: '6', text: '投資（Enum）' }, //収入のプルダウン指定
      ],

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

      registModal: true, //モーダル表示なのでtrue
      validationFlag: true,

      inputCheck: {
        //入力結果が入る
        radioName: '',
        date: '',
        selectIncome: '',
        selectExpenditure: '',
        price: '',
        note: '',
      },

      errorMessage: {
        //エラーメッセージが入る
        radioButtonResult: '',
        dateResult: '',
        selectIncomeResult: '',
        selectExpenditureResult: '',
        priceResult: '',
        noteResult: '',
      },

      validation: {
        radioValidation: false,
        dateValidation: false,
        incomeValidation: false,
        expenditureValidation: false,
        priceValidation: false,
        noteValidation: true,
      },
    }
  },
  mounted() {
    this.getExpenditureItems()
    // モーダル表示のための情報取得のメソッドを最初に実行
  },

  methods: {
    async getExpenditureItems() {
      //   //非同期　支出費目プルダウン表示情報取得のためのメソッド
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

    registIncome: function () {
      //収入をバックエンドに送るメソッド
      try {
        axios.post('http://localhost:8080/api/income', this.inputCheck).then((response) => {
          //responseが正常化チェック
          console.log(response)
        })
      } catch (error) {
        // tryの中が最後まで終わらなかったら実行
        console.log(error)
      }
    },

    registExpenditure: function () {
      //支出をバックエンドに送るメソッド
      try {
        axios.post('http://localhost:8080/api/expenditure', this.inputCheck).then((response) => {
          console.log(response)
        })
      } catch (error) {
        // tryの中が最後まで終わらなかったら実行
        console.log(error)
      }
    },

    finalSelectRadio(setRadioName: any, radioButtonResult: any, radioValidation: any) {
      this.setSelectRadio = setRadioName //選択されたラジオボタン
      this.errorMessage.radioButtonResult = radioButtonResult
      this.validation.radioValidation = radioValidation
      this.inputCheck.radioName = setRadioName
    },

    finalSetDate(date: any, dateResult: any, dateValidation: any) {
      this.inputCheck.date = date
      //情報をまとめて送る
      this.errorMessage.dateResult = dateResult
      //バリデーションチェック行うためにれてる
      this.validation.dateValidation = dateValidation
      this.validationCheck()
      //上で入れた値をチェックするために関数呼び出し
    },

    finalselectIncomeType(incomeType: any, selectIncomeResult: any, incomeValidation: any) {
      this.inputCheck.selectIncome = incomeType
      this.errorMessage.selectIncomeResult = selectIncomeResult
      this.validation.incomeValidation = incomeValidation
      this.validationCheck()
    },

    finalselectExpenditureType(
      expenditureType: any,
      selectExpenditureResult: any,
      expenditureValidation: any
    ) {
      this.inputCheck.selectExpenditure = expenditureType
      this.errorMessage.selectExpenditureResult = selectExpenditureResult
      this.validation.expenditureValidation = expenditureValidation
      this.validationCheck()
    },

    finalSetNumber(price: any, priceResult: any, priceValidation: any) {
      this.inputCheck.price = price
      this.errorMessage.priceResult = priceResult
      this.validation.priceValidation = priceValidation
      this.validationCheck()
    },

    finalSetNote(note: any, noteResult: any, noteValidation: any) {
      this.inputCheck.note = note
      //情報をまとめて送る
      this.errorMessage.noteResult = noteResult
      //バリデーションチェック行うためにれてる
      this.validation.noteValidation = noteValidation
      this.validationCheck()
      //上で入れた値をチェックするために関数呼び出し
    },

    executeKeep() {
      if (this.setSelectRadio == '収入') {
        //選択されたラジオボタンが収入なら
        this.registIncome()
        //収入登録のメソッド呼び出し
      } else {
        this.registExpenditure()
        //支出なら支出登録のメソッド呼び出し
      }
      this.executeCancel()
      //モーダル閉じるメソッド呼び出し
    },

    executeCancel() {
      // キャンセルされたらモーダル閉じる処理
      this.$emit('execute-method')
    },

    validationCheck() {
      if (this.setSelectRadio == '収入') {
        if (
          this.validation.dateValidation &&
          this.validation.incomeValidation &&
          this.validation.priceValidation &&
          this.validation.noteValidation
          //すべてtrueだったら
        ) {
          this.validationFlag = false
        } else {
          this.validationFlag = true
        }
      } else if (this.setSelectRadio == '支出') {
        if (
          this.validation.dateValidation &&
          this.validation.expenditureValidation &&
          this.validation.priceValidation &&
          this.validation.noteValidation
        ) {
          this.validationFlag = false
          //すべてtrueだったら
        } else {
          this.validationFlag = true
        }
      }
    },
  },
}
</script>

<template>
  <div id="modal">
    <div id="modal-content" class="modal">
      <h6>登録情報</h6>
      <div>
        <label>{{ '収支区分：' }}</label>
        <RadioButton
          :setRadioBotton="setSelectRadio"
          :radioName1="setRadioName1"
          :radioName2="setRadioName2"
          :notSelect="false"
          @execute-method="finalSelectRadio"
        />
        <p>{{ errorMessage.radioButtonResult }}</p>
      </div>
      <div>
        <label>{{ '収支日付：' }}</label>
        <DateInput @execute-method="finalSetDate" />
        <p>{{ errorMessage.dateResult }}</p>
      </div>
      <div>
        <FormSelect
          :selectRadioName="setSelectRadio"
          :items="expenditureItems"
          @executeIncome-method="finalselectIncomeType"
          @executeExpenditure-method="finalselectExpenditureType"
        />
        <div v-if="setSelectRadio == '収入'">
          <P>{{ errorMessage.selectIncomeResult }}</P>
        </div>
        <div v-if="setSelectRadio == '支出'">
          <p>{{ errorMessage.selectExpenditureResult }}</p>
        </div>
      </div>
      <div>
        <label>{{ '金額：' }}</label>
        <NumberInput @execute-method="finalSetNumber" />
        <p>{{ errorMessage.priceResult }}</p>
      </div>
      <div>
        <label>備考：</label>
        <TextArea @execute-method="finalSetNote" />
        <p>{{ errorMessage.noteResult }}</p>
      </div>

      <div>
        <Button
          :validatedNull="validationFlag"
          setButtonName1="保存"
          setButtonName2="キャンセル"
          @executeButton1-method="executeKeep"
          @executeButton2-method="executeCancel"
        />
        <!-- 入力内容がNGな場合はボタン不活性 -->
      </div>
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