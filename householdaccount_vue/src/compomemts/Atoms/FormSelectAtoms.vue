<script lang="ts">
export default {
  props: {
    //親コンポーネントから渡されるデータ(プロパティ)を定義
    // 入力チェック
    selectRadioName: String, //選択されたラジオボタン
    getIncome: String, //選択されていた収入
    getExpenditure: String, //選択されていた支出
    items: [
      //配列　支出費目
      {
        expenditure_expense_item_code: String,
        //支出費目　添字
        expenditure_expense_item_name: String,
        //支出費目　要素
        expenditure_expense_item_name_kana: String,
      },
    ],
  },
  emits: ['executeIncome-method', 'executeExpenditure-method', 'executeNotSpecified-method'],
  data() {
    //コンポーネントが持つデータ、状態を定義
    return {
      selectIncome: '',
      selectExpenditure: '', //選択結果
      selectIncomeResult: '',
      selectExpenditureResult: '', //エラーメッセージ
      incomeValidation: '',
      expenditureValidation: '', //入力チェックの結果
      //　　初期化
      selects: [
        //プルダウンの候補　配列
        // { value: '0', text: '' },
        { value: '1', text: '給与' },
        { value: '2', text: '賞与' },
        { value: '3', text: '副業' },
        { value: '4', text: 'お小遣い' },
        { value: '5', text: '臨時収入' },
        { value: '6', text: '投資（Enum）' },
        //value　配列の添字
        //text　配列の要素
      ],
    }
  },
  mounted() {
    ;(this.selectIncome = this.getIncome), (this.selectExpenditure = this.getExpenditure)
  },
  methods: {
    //メソッドを定義　操作にたいしてどんな処理をするか　＠blurで呼ぶ関数
    setSelectIncome() {
      this.selectIncomeValidate()
      //入力チェック関数の呼び出し
      this.$emit(
        'executeIncome-method',
        this.selectIncome,
        this.selectIncomeResult,
        this.incomeValidation
      )
      // this.$emit('イベント名',データ)
      //選択された候補、エラーメッセージ
    },

    setSelectExpenditure() {
      this.selectExpenditureValidate()
      this.$emit(
        'executeExpenditure-method',
        this.selectExpenditure,
        this.selectExpenditureResult,
        this.expenditureValidation
      )
    },

    setNotSpecified() {
      this.selectIncomeValidate()
      this.selectExpenditureValidate()
      this.$emit(
        'executeNotSpecified-method',
        this.selectIncome,
        this.selectIncomeResult,
        this.selectExpenditure,
        this.selectExpenditureResult,
        this.incomeValidation,
        this.expenditureValidation
      )
    },

    selectIncomeValidate() {
      const selectIncome_error_message = this.selectIncomeCheckValidate(this.selectIncome)
      //selectIncomeCheckValidateを呼び出してincomeSelect_error_messageに入れる
      if (selectIncome_error_message === true) {
        this.incomeValidation = true
        this.selectIncomeResult = ''
        //tureだったらselectIncomeResultを空にする
      } else {
        this.incomeValidation = false
        this.selectIncomeResult = selectIncome_error_message
        //incomeSelect_error_messageがtrueじゃなかったら、incomeSelect_error_messageをselectIncomeResultに格納
      }
    },

    selectExpenditureValidate() {
      const selectExpenditure_error_message = this.selectExpenditureCheckValidate(
        this.selectExpenditure
      )
      //selectIncomeCheckValidateを呼び出してincomeSelect_error_messageに入れる
      if (selectExpenditure_error_message === true) {
        this.expenditureValidation = true
        this.selectExpenditureResult = ''
        //tureだったらselectIncomeResultを空にする
      } else {
        this.expenditureValidation = false
        this.selectExpenditureResult = selectExpenditure_error_message
        //incomeSelect_error_messageがtrueじゃなかったら、incomeSelect_error_messageをselectIncomeResultに格納
      }
    },

    selectIncomeCheckValidate(selectIncome: any) {
      //収入が選択されているかのチェック　”any”はどんな型でもOK
      if (!selectIncome) {
        //selectIncomeが未選択の場合
        return '選択してください'
      } else {
        return true
      }
    },

    selectExpenditureCheckValidate(selectExpenditure: any) {
      if (!selectExpenditure) {
        return '選択してください'
      } else {
        return true
      }
    },
  },
}
</script>

<template>
  <div>
    <div v-if="selectRadioName == '収入'">
      <div>
        <label>{{ '収入種別：' }}</label>
        <select v-model="selectIncome" @blur="setSelectIncome" placeholder="選択してください">
          <!-- イベント発生したら、選択された収入種別をv-modelに入れて、blurの関数を呼ぶ -->
          <option
            v-for="select_income in selects"
            :value="select_income.value"
            :key="select_income.text"
          >
            <!-- select_incomeにselectsが入る　vlueは要素　keyは添字 -->
            {{ select_income.text }}
          </option>
        </select>
      </div>
      <div>
        <label>{{ '支出費目：' }}</label>
        <select v-model="selectExpenditure" @blur="setSelectExpenditure" :disabled="true">
          <!-- ture　常に無効化 -->
          <option
            v-for="select_expenditure in items"
            :value="select_expenditure.expenditure_expense_item_name"
            :key="select_expenditure.expenditure_expense_item_code"
          >
            {{}}
          </option>
        </select>
        <div>{{}}</div>
      </div>
    </div>

    <div v-if="selectRadioName == '支出'">
      <div>
        <label>{{ '収入種別：' }}</label>
        <select v-model="selectIncome" @blur="setSelectIncome" :disabled="true">
          <!-- イベント発生したら、選択された収入種別をv-modelに入れて、blurの関数を呼ぶ -->
          <option
            v-for="select_income in selects"
            :value="select_income.value"
            :key="select_income.text"
          >
            <!-- income_selectにselectsが入る　vlueは要素　keyは添字 -->
            {{}}
          </option>
        </select>
        <div>{{}}</div>
      </div>
      <div>
        <label>{{ '支出費目：' }}</label>
        <select v-model="selectExpenditure" @blur="setSelectExpenditure">
          <option
            v-for="select_expenditure in items"
            :value="select_expenditure.expenditure_expense_item_name"
            :key="select_expenditure.expenditure_expense_item_code"
          >
            {{ select_expenditure.expenditure_expense_item_name }}
          </option>
        </select>
      </div>
    </div>

    <div v-if="selectRadioName == '指定なし'">
      <div>
        <label>{{ '収入種別：' }}</label>
        <select v-model="selectIncome" @blur="setNotSpecified">
          <!-- イベント発生したら、選択された収入種別をv-modelに入れて、blurの関数を呼ぶ -->
          <option
            v-for="select_income in selects"
            :value="select_income.value"
            :key="select_income.text"
          >
            <!-- select_incomeにselectsが入る　vlueは要素　keyは添字 -->
            {{ select_income.text }}
          </option>
        </select>
      </div>
      <div>
        <label>{{ '支出費目：' }}</label>
        <select v-model="selectExpenditure" @blur="setNotSpecified">
          <option
            v-for="select_expenditure in items"
            :value="select_expenditure.expenditure_expense_item_name"
            :key="select_expenditure.expenditure_expense_item_code"
          >
            {{ select_expenditure.expenditure_expense_item_name }}
          </option>
        </select>
      </div>
    </div>
  </div>
</template>