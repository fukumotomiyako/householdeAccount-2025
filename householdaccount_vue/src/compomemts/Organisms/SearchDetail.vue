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
  emits: ['executeDetail-method'],
  data() {
    return {
      test: 'gggggg', //デバック用
      setSelectRadio: '収入', //モーダル開かれたときの初期選択
      setRadioName1: '収入',
      setRadioName2: '支出',
      setRadioName3: '指定なし', //ラジオボタン名指定
      validationCheckFlag: 1, //ボタンの活性化判断のための変数
      validationFlag: true, //ボタンの不活性化判定用
      nullFlag: true,
      i: [
        {
          incomeNo: '',
          incomeDate: '',
        },
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
      searchDetailInfo: {
        balanceCode: '',
        balanceType: '',
        balanceDate: '',
        incomeType: '',
        incomeTypeName: '',
        expenditureExpenseItemName: '',
        amount: '',
        note: '',
      },
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
        setFromPriceResult: '',
        setToPriceResult: '',
        setAmountResult: '',
        noteResult: '',
      },
      validation: {
        //入力チェックの結果が入る
        radioValidation: false,
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

    incomeSearch: async function () {
      try {
        const response = await axios.get('http://localhost:8080/api/income/detailSearch', {
          params: {
            fromDate: this.SearchDetail.fromDate,
            toDate: this.SearchDetail.toDate,
            selectIncome: this.SearchDetail.selectIncome,
            selectExpenditure: this.SearchDetail.selectExpenditure,
            fromAmount: this.SearchDetail.fromAmount,
            toAmount: this.SearchDetail.toAmount,
            note: this.SearchDetail.note,
          },
        })
        console.log(response)
        this.searchDetailInfo = response.data
        this.$emit('executeDetail-method', this.searchDetailInfo)
      } catch (error) {
        console.log(error)
      }
    },

    expenditureSearch: function () {
      try {
        axios
          .get('http://localhost:8080/api/expenditure/detailSearch', {
            params: {
              fromDate: this.SearchDetail.fromDate,
              toDate: this.SearchDetail.toDate,
              selectIncome: this.SearchDetail.selectIncome,
              selectExpenditure: this.SearchDetail.selectExpenditure,
              fromAmount: this.SearchDetail.fromAmount,
              toAmount: this.SearchDetail.toAmount,
              note: this.SearchDetail.note,
            },
          })
          .then((response) => {
            console.log(response)
            this.searchDetailInfo = response.data
            this.$emit('executeDetail-method', this.searchDetailInfo)
          })
      } catch (error) {
        console.log(error)
      }
    },

    notSpecifiedSearch: function () {
      try {
        axios
          .get('http://localhost:8080/api/notSpecified/detailSearch', {
            params: {
              fromDate: this.SearchDetail.fromDate,
              toDate: this.SearchDetail.toDate,
              selectIncome: this.SearchDetail.selectIncome,
              selectExpenditure: this.SearchDetail.selectExpenditure,
              fromAmount: this.SearchDetail.fromAmount,
              toAmount: this.SearchDetail.toAmount,
              note: this.SearchDetail.note,
            },
          })
          .then((response) => {
            console.log(response)
            this.searchDetailInfo = response.data
            this.$emit('executeDetail-method', this.searchDetailInfo)
          })
      } catch (error) {
        console.log(error)
      }
    },

    executeSearch() {
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
      if (setDateValidation && setDateSizeValidation) {
        //両方trueなら
        this.validationCheckFlag = 0 //validationCheckFlagを０にする
      } else {
        this.validationCheckFlag = 1 //それ以外ならfalse
      }
      this.validationCheck()
    },

    finalSelectIncomeType(selectIncome: any, selectIncomeResult: any, incomeValidation: any) {
      this.SearchDetail.selectIncome = selectIncome
      this.errorMessage.selectIncomeResult = selectIncomeResult
      if (incomeValidation) {
        this.validationCheckFlag = 0
      } else {
        this.validationCheckFlag = 1
      }
      this.validationCheck()
    },

    finalSelectExpenditureType(
      selectExpenditure: any,
      selectExpenditureResult: any,
      expenditureValidation: any
    ) {
      this.SearchDetail.selectExpenditure = selectExpenditure
      this.errorMessage.selectExpenditureResult = selectExpenditureResult
      if (expenditureValidation) {
        this.validationCheckFlag = 0
      } else {
        this.validationCheckFlag = 1
      }
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
      this.SearchDetail.selectExpenditure = selectExpenditure
      this.errorMessage.selectExpenditureResult = selectExpenditureResult
      if (incomeValidation && expenditureValidation) {
        this.validationCheckFlag = 0
      } else {
        this.validationCheckFlag = 1
      }
      this.validationCheck()
    },

    finalSetAmount(
      fromAmount: any,
      toAmount: any,
      setFromPriceResult: any,
      setToPriceResult: any,
      setAmountResult: any,
      setPriceValidation: any,
      setPriceSizeValidation: any
    ) {
      this.SearchDetail.fromAmount = fromAmount
      this.SearchDetail.toAmount = toAmount
      this.errorMessage.setFromPriceResult = setFromPriceResult
      this.errorMessage.setToPriceResult = setToPriceResult
      this.errorMessage.setAmountResult = setAmountResult
      if (setPriceValidation && setPriceSizeValidation) {
        this.validationCheckFlag = 0
      } else {
        this.validationCheckFlag = 1
      }
      this.validationCheck()
    },

    finalSetNote(note: any, noteResult: any, noteValidation: any) {
      this.SearchDetail.note = note
      this.errorMessage.noteResult = noteResult
      if (noteValidation) {
        this.validationCheckFlag = 0
      } else {
        this.validationCheckFlag = 1
      }
      this.validationCheck()
    },

    validationCheck() {
      if (this.validationCheckFlag == 1) {
        //validationCheckFlagが1なら
        this.validationFlag = true //validationFlagをtrue(ボタンを押せない)にする
      } else if (this.validationCheckFlag == 0) {
        if (
          //ひとつでも値が入っていたら
          this.SearchDetail.fromDate ||
          this.SearchDetail.toDate ||
          this.SearchDetail.selectIncome ||
          this.SearchDetail.selectExpenditure ||
          this.SearchDetail.fromAmount ||
          this.SearchDetail.toAmount ||
          this.SearchDetail.note
        ) {
          if (
            !this.errorMessage.radioButtonResult &&
            !this.errorMessage.setDateResult &&
            !this.errorMessage.setDateSizeResult &&
            !this.errorMessage.selectIncomeResult &&
            !this.errorMessage.selectExpenditureResult &&
            !this.errorMessage.setFromPriceResult &&
            !this.errorMessage.setToPriceResult &&
            !this.errorMessage.setAmountResult &&
            !this.errorMessage.noteResult
          ) {
            this.validationFlag = false //validationFlagをfalse(ボタンを押せる)にする
          } else {
            this.validationFlag = true //validationFlagをtrue(ボタンを押せない)にする
          }
        } else {
          this.validationFlag = true //validationFlagをtrue(ボタンを押せない)にする
        }
      }
    },
  },
}
</script>
<template>
  <div id="modal">
    <div id="modal-content" class="modal">
      <p class="modal__message">検索条件</p>
      <div>
        <div class="row">
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
        </div>
        <p class="errorMessage">{{ errorMessage.radioButtonResult }}</p>
      </div>
      <div>
        <label>{{ '収支日付：' }}</label>
        <Date :setNullFlag="false" @execute-method="finalSetDate" />
        <p class="errorMessage">{{ errorMessage.setDateResult }}</p>
        <p class="errorMessage">{{ errorMessage.setDateSizeResult }}</p>
      </div>
      <div>
        <div v-if="setSelectRadio == '収入'">
          <FormSelect
            :selectRadioName="setSelectRadio"
            :items="expenditureItems"
            :nullFlag="false"
            @executeIncome-method="finalSelectIncomeType"
          />
          <p class="errorMessage">{{ errorMessage.selectIncomeResult }}</p>
        </div>

        <div v-if="setSelectRadio == '支出'">
          <FormSelect
            :selectRadioName="setSelectRadio"
            :items="expenditureItems"
            :nullFlag="false"
            @executeExpenditure-method="finalSelectExpenditureType"
          />
          <p class="errorMessage">{{ errorMessage.selectExpenditureResult }}</p>
        </div>

        <div v-if="setSelectRadio == '指定なし'">
          <FormSelect
            :selectRadioName="setSelectRadio"
            :items="expenditureItems"
            :nullFlag="false"
            @executeNotSpecified-method="finalSelectNotSpecifiedType"
          />
          <div v-if="!errorMessage.selectIncomeResult">
            <p class="errorMessage">{{ errorMessage.selectExpenditureResult }}</p>
          </div>
          <div v-else>
            <p class="errorMessage">{{ errorMessage.selectIncomeResult }}</p>
          </div>
        </div>
      </div>
      <div>
        <label>{{ '金額　　：' }}</label>
        <NumberInput class="modal_action" :setNullFlag="false" @execute-method="finalSetAmount" />
        <p class="errorMessage">{{ errorMessage.setFromPriceResult }}</p>
        <p class="errorMessage">{{ errorMessage.setToPriceResult }}</p>
        <p class="errorMessage">{{ errorMessage.setAmountResult }}</p>
      </div>
      <div>
        <label class="text">{{ '備考　　：' }}</label>
        <TextArea @execute-method="finalSetNote" />
        <p class="errorMessage">{{ errorMessage.noteResult }}</p>
      </div>

      <div class="modal_action">
        <Button
          class="modal__btn"
          buttonName="検索"
          @click="executeSearch"
          :disabled="validationFlag"
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
  text-align: left;
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  border-radius: 10px;
}

.modal__message {
  margin-top: 5px;
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
  margin: 0;
  text-decoration: none;
  color: #000000;
  border: solid 2px #42a4f5;
  border-radius: 3px;
  transition: 0.4s;
  text-align: center;
  vertical-align: middle;
  font-size: 15px;
  background-color: #8dc0e9;
}

.modal__btn:hover {
  background: #42a4f5;
  color: white;
  cursor: pointer;
}

.btn:disabled {
  border-color: #c7c7c7;
  background-color: #eeeeee;
  color: #9b9b9b;
}

.btn:disabled:hover {
  background: #eeeeee;
  color: --btn-fg-disabled, #9b9b9b;
}

.modal_action {
  text-align: right;
}

.errorMessage {
  text-align: center;
}

.radio {
  text-align: center;
}

.row {
  display: grid;
  grid-template-columns: 21% 79%; /* 2列。比率 */
  align-items: center; /* 高さ方向の中央揃え */
}
.text {
  vertical-align: top;
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