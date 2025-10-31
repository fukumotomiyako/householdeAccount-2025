<script lang="ts">
export default {
  props: {
    //親コンポーネントから渡されるデータ(プロパティ)を定義
    //ラジオボタン選択結果:String,
    // selectedIncomeType:String,
    //現在選択されているタイプ　登録時には未選択なため不要
    // selectedExpenditureType:String,
    //現在選択されているタイプ 登録時には未選択なため不要
    validatedNull: String,
    // 入力チェック
    selectRadioName: String,
    items: [
      //配列　支出費目
      {
        expenditure_expense_item_code: String,
        //支出費目　添字
        expenditure_expense_item_name: String,
        //支出費目　要素名
        expenditure_expense_item_name_kana: String,
      },
    ],
  },
  data() {
    //コンポーネントが持つデータ、状態を定義
    return {
      //使うためにオブジェクトを返す？
      //   selectIncome: this.selectedIncomeType,
      //selectIncomeの値(選択されているもの)をselectedIncomeTypeにわたす
      //   selectExpenditure: this.selectedExpenditureType,
      //selectExpenditureの値(選択されているもの)をselectedExpenditureTypeにわたす
      selectIncomeResult: '',
      //  　初期化
      selectExpenditureResult: '',
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
  methods: {
    //メソッドを定義　操作にたいしてどんな処理をするか　＠blurで呼ぶ関数のことかな
    setSelectIncome() {
      this.selectIncomeValidate()
      //入力チェック関数の呼び出し
      this.$emit('executeIncome-method', this.selectIncome, this.selectIncomeResult)
      // this.$emit('イベント名',データ)
      //選択された候補、エラーメッセージ
    },

    setSelectExpenditure() {
      this.selectExpenditureValidate()
      this.$emit('executeExpenditure-methods', this.selectExpenditure, this.selectExpenditureResult)
    },

    setNotSelect() {
      this.selectIncomeValidate()
      this.selectExpenditureValidate()
      this.$emit(
        'executeNotSelect-methods',
        this.selectIncome,
        this.selectIncomeResult,
        this.selectExpenditure,
        this.selectExpenditureResult
      )
    },

    selectIncomeValidate() {
      const selectIncome_error_message = this.selectIncomeCheckValidate(this.selectIncome)
      //selectIncomeCheckValidateを呼び出してincomeSelect_error_messageに入れる
      if (selectIncome_error_message != 'true') {
        this.selectIncomeResult = selectIncome_error_message
        //incomeSelect_error_messageがtrueじゃなかったら、incomeSelect_error_messageをselectIncomeResultに格納
      }
      this.selectIncomeResult = ''
      //tureだったらselectIncomeResultを空にする
    },

    selectExpenditureValidate() {
      const selectIncome_error_message = this.selectExcptionCheckValidate(this.selectIncome)
      //selectIncomeCheckValidateを呼び出してincomeSelect_error_messageに入れる
      if (selectIncome_error_message != 'true') {
        this.selectIncomeResult = selectIncome_error_message
        //incomeSelect_error_messageがtrueじゃなかったら、incomeSelect_error_messageをselectIncomeResultに格納
      }
      this.selectIncomeResult = ''
      //tureだったらselectIncomeResultを空にする
    },

    selectIncomeCheckValidate(selectIncome: any) {
      //収入が選択されているかのチェック　”any”はどんな型でもOK
      if (this.validatedNull == 'ture') {
        //this.validatedNullがnullが”ture”のときのみincometypeが未選択かどうかのチェックを行う
        if (!selectIncome) {
          //selectIncomeが未選択の場合
          return '選択してください'
        }
        return true
      }
      return true
    },

    selectExcptionCheckValidate(selectExpenditure: any) {
      if (this.validatedNull == 'ture') {
        if (!selectExpenditure) {
          return '選択してください'
        }
        return true
      }
      return true
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
            <!-- income_selectにselectsが入る　vlueは要素　keyは添字 -->
            {{ select_income.text }}
          </option>
        </select>
        <!-- <div>{{ "selectIncomeResult" }}</div> -->
      </div>
      <div>
        <label>{{ '支出費目：' }}</label>
        <select v-model="selectExpenditure" @blur="setSelectExpenditure" :disabled="true">
          <!-- ture　常に無効化 -->
          <option
            v-for="select_expenditure in items"
            :value="select_expenditure.expenditure_expense_item_code"
            :key="select_expenditure.expenditure_expense_item_name"
          >
            {{}}
          </option>
        </select>
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
            {{ select_income.text }}
          </option>
        </select>
        <div>{{ selectIncomeResult }}</div>
      </div>
      <div>
        <label>{{ '支出費目：' }}</label>
        <select v-model="selectExpenditure" @blur="setSelectExpenditure">
          <option
            v-for="select_expenditure in items"
            :value="select_expenditure.expenditure_expense_item_code"
            :key="select_expenditure.expenditure_expense_item_name"
          >
            {{ select_expenditure.expenditure_expense_item_name }}
          </option>
        </select>
        <div>{{ selectExpenditureResult }}</div>
      </div>
    </div>

    <div v-if="selectRadioName == '未選択'">
      <div>
        <label>{{ '収入種別：' }}</label>
        <select v-model="selectIncome" @blur="setNotSelect">
          <!-- イベント発生したら、選択された収入種別をv-modelに入れて、blurの関数を呼ぶ -->
          <option
            v-for="select_income in selects"
            :value="select_income.value"
            :key="select_income.text"
          >
            <!-- income_selectにselectsが入る　vlueは要素　keyは添字 -->
            {{ select_income.text }}
          </option>
        </select>
        <div>{{ selectIncomeResult }}</div>
      </div>
      <div>
        <label>{{ '支出費目：' }}</label>
        <select v-model="selectExpenditure" @blur="setNotSelect">
          <option
            v-for="select_expenditure in items"
            :value="select_expenditure.expenditure_expense_item_code"
            :key="select_expenditure.expenditure_expense_item_name"
          >
            {{ select_expenditure.expenditure_expense_item_name }}
          </option>
        </select>
        <div>{{ selectExpenditureResult }}</div>
      </div>
    </div>
  </div>
</template>