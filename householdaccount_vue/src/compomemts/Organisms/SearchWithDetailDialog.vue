<script lang="ts">
import RadioButton from '../Molecules/SearchBalanceRadio.vue'
import Date from '../Molecules/SearchBaseDate.vue'
import NumberInput from '../Molecules/SearchBaseAmount.vue'
import FormSelect from '../Atoms/FormSelectAtoms.vue'
import TextArea from '../Atoms/TextArea.vue'
import Button from '../Atoms/ButtonAtoms.vue'
import axios from 'axios'

export default {
  components: { RadioButton, NumberInput, FormSelect, Button, TextArea, Date },
  data() {
    return {
      setSelectRadio: '収入', //モーダル開かれたときの初期選択
      setRadioName1: '収入',
      setRadioName2: '支出',
      setRadioName3: '指定なし', //ラジオボタン名指定
      validationFlag: true, //ボタンの不活性化判定用

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
      errorMessage: {
        radioButtonResult: '',
        fromDateResult: '',
        toDateResult: '',
        selectIncomeResult: '',
        selectExpenditureResult: '',
        fromAmountResult: '',
        toAmountResult: '',
        noteResult: '',
      },
      validation: {
        radioValidation: false,
        fromDateValidation: false,
        toDateValidation: false,
        incomeValidation: false,
        expenditureValidation: false,
        fromAmountValidation: false,
        toAmountValidation: false,
        noteValidation: false,
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
    executeSearch() {},

    finalSelectRadio(setRadioName: any, radioButtonResult: any, radioValidation: any) {
      this.setSelectRadio = setRadioName
      this.errorMessage.radioButtonResult = radioButtonResult
      this.validation.radioValidation = radioValidation
      this.validationCheck()
    },

    finalFromDate(fromDate: any, fromDateResult: any, fromDateValidation: any) {
      this.errorMessage.fromDateResult = fromDateResult
      this.validation.fromDateValidation = fromDateValidation
      this.validationCheck()
    },

    finalToDate(toDate: any, toDateResult: any, toDateValidation: any) {
      this.errorMessage.toDateResult = toDateResult
      this.validation.toDateValidation = toDateValidation
      this.validationCheck()
    },

    finalSelectIncomeType(selectIncome: any, selectIncomeResult: any, incomeValidation: any) {
      this.errorMessage.selectIncomeResult = selectIncomeResult
      this.validation.incomeValidation = incomeValidation
      this.validationCheck()
    },

    finalSelectExpenditureType(
      selectExpenditure: any,
      selectExpenditureResult: any,
      expenditureValidation: any
    ) {
      this.errorMessage.selectExpenditureResult = selectExpenditureResult
      this.validation.expenditureValidation = expenditureValidation
      this.validationCheck()
    },

    finalSelectNotSpecifiedType(
      selectIncome: any,
      selectIncomeResult: any,
      selectExpenditure: any,
      selectExpenditureResult: any,
      incomeValidation: any,
      expenditureValidation: any
    ) {
      this.errorMessage.selectIncomeResult = selectIncomeResult
      this.validation.incomeValidation = incomeValidation
      this.errorMessage.selectExpenditureResult = selectExpenditureResult
      this.validation.expenditureValidation = expenditureValidation
      this.validationCheck()
    },

    finalFromAmount(fromAmount: any, fromAmountResult: any, fromAmountValidation: any) {
      this.errorMessage.fromAmountResult = fromAmountResult
      this.validation.fromAmountValidation = fromAmountValidation
      this.validationCheck()
    },

    finalToAmount(toAmout: any, toAmountResult: any, toAmountValidation: any) {
      this.errorMessage.fromAmountResult = toAmountResult
      this.validation.fromAmountValidation = toAmountValidation
      this.validationCheck()
    },

    finalSetNote(note: any, noteResult: any, noteValidation: any) {
      this.errorMessage.noteResult = noteResult
      this.validation.noteValidaion = noteValidation
      this.validationCheck()
    },

    validationCheck() {},
  },
}
</script>
<template>
  <div id="modal">
    <div id="modal-content" class="modal">
      <div>
        <label>収支区分：</label>
        <RadioButton
          :setRadioButton="setSelectRadio"
          :radioName1="setRadioName1"
          :radioName2="setRadioName2"
          :radioName3="setRadioName3"
          :notSelect="false"
          @execute-method="finalSelectRadio"
        />
        <p>{{ errorMessage.radioButtonResult }}</p>
      </div>
      <div>
        <label>日付：</label>
        <Date @executeFrom-method="finalFromDate" @executeTo-method="finalToDate" />
        <p>{{ errorMessage.fromDateResult }}</p>
      </div>
      <div>
        <div v-if="setSelectRadio == '収入'">
          <FormSelect
            :selectRadioName="setSelectRadio"
            :items="expenditureItems"
            @executeIncome-method="finalSelectIncomeType"
          />
          <p>{{ errorMessage.selectIncomeResult }}</p>
        </div>

        <div v-if="setSelectRadio == '支出'">
          <FormSelect
            :selectRadioName="setSelectRadio"
            :items="expenditureItems"
            @executeExpenditure-method="finalSelectExpenditureType"
          />
          <p>{{ errorMessage.selectExpenditureResult }}</p>
        </div>

        <div v-if="setSelectRadio == '指定なし'">
          <FormSelect
            :selectRadioName="setSelectRadio"
            :items="expenditureItems"
            @executeNotSpecified-method="finalSelectNotSpecifiedType"
          />
          <div v-if="!errorMessage.selectIncomeResult">
            <p>{{ errorMessage.selectExpenditureResult }}</p>
          </div>
          <div v-else>
            <p>{{ errorMessage.selectIncomeResult }}</p>
          </div>
        </div>
      </div>
      <div>
        <label>金額：</label>
        <NumberInput @executeFrom-method="finalFromAmount" @executeTo-method="finalToAmount" />
        <div v-if="!errorMessage.fromAmountResult">
          <p>{{ errorMessage.toAmountResult }}</p>
        </div>
        <div v-else>
          <p>{{ errorMessage.fromAmountResult }}</p>
        </div>
      </div>
      <div>
        <label>備考：</label>
        <TextArea @execute-method="finalSetNote" />
        <p>{{ errorMessage.noteResult }}</p>
      </div>
      <div>
        <Button buttonName="検索" @blur="executeSearch" :disabled="validationFlag" />
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