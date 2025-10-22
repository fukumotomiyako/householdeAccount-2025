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
    RadioButton,
    DateInput,
    TextArea,
    FormSelect,
    NumberInput,
    Button,
  },

  data() {
    return {
      Test: 'true',
      setSelectRadio: '収入',
      setRadioName1: '収入',
      setRadioName2: '支出',
      setButtonName1: '保存',
      setButtonName2: 'キャンセル',
      incomeTypes: [
        { value: '1', text: '給与' },
        { value: '2', text: '賞与' },
        { value: '3', text: '副業' },
        { value: '4', text: 'お小遣い' },
        { value: '5', text: '臨時収入' },
        { value: '6', text: '投資（Enum）' },
      ],

      expenditureItems: [
        {
          expenditureExpenseItemCode: String,
          //支出費目　添字
          expenditureExpenseItemName: String,
          //支出費目　要素名
          expenditureExpenseItemNameKana: String,
        },
      ],

      registModal: true,
      validationCheck: true,

      inputCheck: {
        radioName: '',
        date: '',
        selectIncome: '',
        selectExpenditure: '',
        price: '',
        note: '',
      }, //いらなくない？

      validation: {
        radioButtonResult: '',
        dateResult: '',
        incomeTypeResult: '',
        expenditureTypeResult: '',
        priceResult: '',
        noteResult: '',
      },
    }
  },

  methods: {
    registIncome: function () {
      try {
        // const incomeData =  this.inputCheck
        // radioName: this.inputCheck.setSelectRadio,
        // date: this.inputCheck.data,
        // selectIncome: this.inputCheck.selectIncome,
        // price: this.inputCheck.price,
        // note: this.inputCheck.note,

        axios.post('http://localhost:8080/api/income', this.inputCheck).then((response) => {
          console.log(response)
        })
      } catch (error) {
        console.log(error)
      }
    },

    // async fetchExpenditureItems() {
    //   try {
    //     const response = await axios.get('http://localhost://8080/api/expenditureItems')
    //     this.expenditureItems = response.data
    //   } catch (error) {
    //     console.error('', error)
    //   }
    // },

    finalSelectRadio(setRadioName: any, radioButtonResult: any) {
      this.setSelectRadio = setRadioName
      this.inputCheck.radioName = setRadioName
    },

    finalSetDate(date: any, dateResult: any) {
      this.Test = false
      this.inputCheck.date = date
      this.validation.dateResult = dateResult
      this.validationCheck()
    },

    finalselectIncomeType(selectIncome: any, selectIncomeResult: any) {
      this.inputCheck.selectIncome = selectIncome
      this.validation.selectIncomeResult = selectIncomeResult
      this.validationCheck()
    },

    finalselectExprnditureType(selectExpenditure: any, selectExpenditureResult: any) {
      this.inputCheck.selectExpenditure = selectExpenditure
      this.validation.selectExpenditureResult = selectExpenditureResult
      this.validationCheck()
    },

    finalSetNumber(price: any, priceResult: any) {
      this.inputCheck.price = price
      this.validation.priceResult = priceResult
      this.validationCheck()
    },

    finalSetNote(note: any, noteResult: any) {
      this.inputCheck.note = note
      //値の上限加減などをチェックするためにいれてる
      this.validation.noteResult = noteResult
      //バリデーションチェック行うためにれてる
      this.validationCheck()
      //上で入れた値をチェックするために関数呼び出し
    },

    executeKeep() {
      this.registIncome()
      // 保存するよの処理
      this.executeCancel()
    },

    executeCancel() {
      this.Test = 'false'
      this.$emit('execute-method')
    },

    validationCheck() {
      if (this.setSelectRadio == '収入') {
        if (
          this.validation.dateResult ||
          this.validation.selectIncomeResult ||
          this.validation.selectExpenditureResult ||
          this.validation.priceResult ||
          this.validation.noteResult
        ) {
          this.validationCheck = true
        } else {
          this.validationCheck = false
        }
      } else if (this.setSelectRadio == '支出') {
        if (
          this.validation.dateResult ||
          this.validation.selectIncomeResult ||
          this.validation.selectExpenditureResult ||
          this.validation.priceResult ||
          this.validation.noteResult
        ) {
          this.validationCheck = true
        } else {
          this.validationCheck = false
        }
      }
      // if (
      //   !this.inputCheck.date &&
      //   !this.inputCheck.selectIncome &&
      //   !this.inputCheck.selectIncome &&
      //   !this.inputCheck.selectExpenditure &&
      //   !this.inputCheck.price &&
      //   !this.inputCheck.note
      // ) {
      //   this.validationCheck = true
      // }
    },
  },
}
</script>

<template>
  <div id="modal">
    <div id="modal-content" class="modal">
      <h6>登録情報</h6>
      <div>
        <p>{{ inputCheck }}</p>
        <label>{{ '収支区分：' }}</label>
        <RadioButton
          :radioName1="setRadioName1"
          :radioName2="setRadioName2"
          @execute-method="finalSelectRadio"
          validatedNull="validation"
        />
      </div>
      <div>
        <label>{{ '収支日付：' }}</label>
        <DateInput @execute-method="finalSetDate" validatedNull="validation" />
      </div>
      <FormSelect
        :selectRadioName="setSelectRadio"
        :items="expenceItems"
        @executeIncome-method="finalselectIncomeType"
        @executeExpenditure-method="finalselectExprnditureType"
        validatedNull="false"
      />
      <div>
        <label>{{ '金額：' }}</label>
        <NumberInput @execute-method="finalSetNumber" validatedNull="validation" />
      </div>
      <div>
        <label>備考：</label>
        <TextArea @execute-method="finalSetNote" validatedNull="false" />
      </div>

      <div>
        <Button
          setButtonName1="保存"
          setButtonName2="キャンセル"
          @executeButton1-method="executeKeep"
          @executeButton2-method="executeCancel"
        />
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