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
  emits: ['execute-method'],
  data() {
    return {
      test: 'gggggg', //デバック用
      setSelectRadio: '収入', //モーダル開かれたときの初期選択
      setRadioName1: '収入',
      setRadioName2: '支出',
      setRadioName3: '指定なし', //ラジオボタン名指定
      validationFlag: true, //ボタンの不活性化判定用
      nullFlag: true,

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
      SearchDetail: {
        //検索情報が入る
        fromDate: '',
        toDate: '',
        selectIncome: '',
        selectExpenditure: '',
        fromAmount: '',
        toAmount: '',
        note: '',
      },
      errorMessage: {
        //エラーメッセージがはいる
        radioButtonResult: '',
        setDateResult: '',
        setDateSizeResult: '',
        selectIncomeResult: '',
        selectExpenditureResult: '',
        setPriceResult: '',
        setAmountResult: '',
        noteResult: '',
      },
      validation: {
        //入力チェックの結果が入る
        radioValidation: false,
        setDateValidation: false,
        setDateSizeValidation: false,
        incomeValidation: false,
        expenditureValidation: false,
        amountValidation: false,
        setPriceSizeValidation: false,
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

    incomeSearch: function () {
      try {
        axios
          .get('http://localhost:8080/api/income/detailSearch', this.SearchDetail)
          .then((response) => {
            console.log(response)
          })
      } catch (error) {
        console.log(error)
      }
    },

    expenditureSearch: function () {
      try {
        axios
          .get('http://localhost:8080/api/expenditure/detailSearch', this.SearchDetail)
          .then((response) => {
            console.log(response)
          })
      } catch (error) {
        console.log(error)
      }
    },

    notSpecifiedSearch: function () {
      try {
        axios
          .get('http://localhost:8080/api/notSpecified/detailSearch', this.SearchDetail)
          .then((response) => {
            console.log(response)
          })
      } catch (error) {
        console.log(error)
      }
    },

    executeSearch() {
      this.test = 'aaaaaa'
      if (this.setSelectRadio == '収入') {
        //検索メソッド呼び出し
        this.incomeSearch()
      } else if (this.setSelectRadio == '支出') {
        //検索メソッド呼び出し
        this.expenditureSearch()
      } else if (this.setSelectRadio == '指定なし') {
        //検索メソッド呼び出し
        this.notSpecifiedSearch()
      }
      this.test = 'ffffffff'
      this.$emit('execute-method') //モーダル非表示のため、親コンポーネントメソッド呼び出し
    },

    finalSelectRadio(setRadioName: any, radioButtonResult: any, radioValidation: any) {
      this.setSelectRadio = setRadioName
      //選択されたラジオボタンをプルダウン、入力チェックのためにセット
      this.errorMessage.radioButtonResult = radioButtonResult
      //エラーメッセージをセット
      this.validation.radioValidation = radioValidation
      //入力チェックの結果をセット
      this.validationCheck()
    },

    finalSetDate(
      fromDate: any,
      toDate: any,
      setDateResult: any,
      setDateSizeResult: any,
      setDateValidation: any,
      setDateSizeValidation: any
    ) {
      this.SearchDetail.fromDate = fromDate
      this.SearchDetail.toDate = toDate
      this.errorMessage.setDateResult = setDateResult
      this.errorMessage.setDateSizeResult = setDateSizeResult
      this.validation.setDateValidation = setDateValidation
      this.validation.setDateSizeValidation = setDateSizeValidation
      this.validationCheck()
    },

    finalSelectIncomeType(selectIncome: any, selectIncomeResult: any, incomeValidation: any) {
      this.SearchDetail.selectIncome = selectIncome
      this.errorMessage.selectIncomeResult = selectIncomeResult
      this.validation.incomeValidation = incomeValidation
      this.validationCheck()
    },

    finalSelectExpenditureType(
      selectExpenditure: any,
      selectExpenditureResult: any,
      expenditureValidation: any
    ) {
      this.SearchDetail.selectExpenditure = selectExpenditure
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
      this.SearchDetail.selectIncome = selectIncome
      this.errorMessage.selectIncomeResult = selectIncomeResult
      this.validation.incomeValidation = incomeValidation
      this.SearchDetail.selectExpenditure = selectExpenditure
      this.errorMessage.selectExpenditureResult = selectExpenditureResult
      this.validation.expenditureValidation = expenditureValidation
      this.validationCheck()
    },

    finalSetAmount(
      fromAmount: any,
      toAmount: any,
      setPriceResult: any,
      setAmountResult: any,
      setPriceValidation: any,
      setPriceSizeValidation: any
    ) {
      this.SearchDetail.fromAmount = fromAmount
      this.SearchDetail.toAmount = toAmount
      this.errorMessage.setPriceResult = setPriceResult
      this.errorMessage.setAmountResult = setAmountResult
      this.validation.amountValidation = setPriceValidation
      this.validation.setPriceSizeValidation = setPriceSizeValidation
      this.validationCheck()
    },

    finalSetNote(note: any, noteResult: any, noteValidation: any) {
      this.SearchDetail.note = note
      this.errorMessage.noteResult = noteResult
      this.validation.noteValidation = noteValidation
      this.validationCheck()
    },

    validationCheck() {
      //入力チェックの結果からボタンを押せるようにするかどうか判断
      if (this.setSelectRadio == '収入') {
        if (
          //どれか一つでもValidationに引っかかったら分岐に入るif文を作成
          //初期値trueにする
          //どれか一つでもfalseだったらvalidationFlag = true
          //validationを呼ぶ関数の中で分岐に入るかどうかの判断を行う (this.validation.setDateSizeValidation && this.validation.setDateValidation) ||
          //エラーメッセージで判別を行うようにする
          (this.validation.setDateSizeValidation && this.validation.setDateValidation) ||
          (this.validation.setPriceSizeValidation && this.validation.amountValidation) ||
          this.validation.incomeValidation ||
          this.validation.noteValidation
        ) {
          this.validationFlag = false
        } else {
          this.validationFlag = true
        }
      } else if (this.setSelectRadio == '支出') {
        if (
          (this.validation.setDateSizeValidation && this.validation.setDateValidation) ||
          (this.validation.setPriceSizeValidation && this.validation.amountValidation) ||
          this.validation.expenditureValidation ||
          this.validation.noteValidation
          //どれか一つでもtrueだったら　true=正常な入力　false=不正な入力
        ) {
          this.validationFlag = false //押せる
        } else {
          this.validationFlag = true //押せない
        }
      } else if (this.setSelectRadio == '指定なし') {
        if (
          (this.validation.setDateSizeValidation && this.validation.setDateValidation) ||
          (this.validation.setPriceSizeValidation && this.validation.amountValidation) ||
          this.validation.incomeValidation ||
          this.validation.expenditureValidation ||
          this.validation.noteValidation
        ) {
          this.validationFlag = false
        } else {
          this.validationFlag = true
        }
      }

      if (
        this.SearchDetail.fromDate ||
        this.SearchDetail.toDate ||
        this.SearchDetail.selectIncome ||
        this.SearchDetail.selectExpenditure ||
        this.SearchDetail.fromAmount ||
        this.SearchDetail.toAmount ||
        this.SearchDetail.note
      ) {
        this.validationFlag = false
      } else {
        this.validationFlag = true
      }
    },
  },
}
</script>
<template>
  <div id="modal">
    <div id="modal-content" class="modal">
      <p>{{ validation }}</p>
      <div>
        <label>収支区分：</label>
        <RadioButton
          :setRadioButton="setSelectRadio"
          :radioName1="setRadioName1"
          :radioName2="setRadioName2"
          :radioName3="setRadioName3"
          :notSelect="false"
          :nullFlag="false"
          @execute-method="finalSelectRadio"
        />
        <p>{{ errorMessage.radioButtonResult }}</p>
      </div>
      <div>
        <label>日付：</label>
        <Date :setNullFlag="false" @execute-method="finalSetDate" />
        <p>{{ errorMessage.setDateResult }}</p>
        <p>{{ errorMessage.setDateSizeResult }}</p>
      </div>
      <div>
        <div v-if="setSelectRadio == '収入'">
          <FormSelect
            :selectRadioName="setSelectRadio"
            :items="expenditureItems"
            :nullFlag="false"
            @executeIncome-method="finalSelectIncomeType"
          />
          <p>{{ errorMessage.selectIncomeResult }}</p>
        </div>

        <div v-if="setSelectRadio == '支出'">
          <FormSelect
            :selectRadioName="setSelectRadio"
            :items="expenditureItems"
            :nullFlag="false"
            @executeExpenditure-method="finalSelectExpenditureType"
          />
          <p>{{ errorMessage.selectExpenditureResult }}</p>
        </div>

        <div v-if="setSelectRadio == '指定なし'">
          <FormSelect
            :selectRadioName="setSelectRadio"
            :items="expenditureItems"
            :nullFlag="false"
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
        <NumberInput :setNullFlag="false" @execute-method="finalSetAmount" />
        <p>{{ errorMessage.setPriceResult }}</p>
        <p>{{ errorMessage.setAmountResult }}</p>
      </div>
      <div>
        <label>備考：</label>
        <TextArea @execute-method="finalSetNote" />
        <p>{{ errorMessage.noteResult }}</p>
      </div>
      <div>
        <p>{{ test }}</p>
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