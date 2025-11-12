<script setup lang = "ts">
const header = ref([
  { text: '収支No' },
  { text: '収支区分' },
  { text: '収支日付' },
  { text: '種別' },
  { text: '金額' },
  { text: '備考' },
  { text: '編集' },
  { text: '削除' },
])
</script>

<script lang = "ts">
import regist from '../src/compomemts/Organisms/Regist.vue'
import edit from '../src/compomemts/Organisms/Edit.vue'
import axios from 'axios'
import { createApp, ref } from 'vue'

export default {
  components: {
    regist,
    edit,
  },
  data() {
    return {
      Text: '', //デバック用
      setBalanceNo: '',
      regist_modal: false,
      edit_modal: false,
      searchResultBalanceInfo: [
        {
          balanceCode: '',
          balanceType: '',
          amount: '',
          balanceDate: '',
          incomeType: '',
          incomeTypeName: '',
          expenditureExpenseItemName: '',
          note: '',
        },
      ],
    }
  },

  methods: {
    searchResultChangeFormat() {
      if (this.searchResultBalanceInfo.length === 0) {
        this.searchFrag = false
      } else {
        this.searchFrag = true
      }

      for (let i = 0; i < this.searchResultBalanceInfo.length; i++) {
        if (this.searchResultBalanceInfo[i].incomeType == '1') {
          this.searchResultBalanceInfo[i].incomeTypeName = '給与'
        } else if (this.searchResultBalanceInfo[i].incomeType == '2') {
          this.searchResultBalanceInfo[i].incomeTypeName = '賞与'
        } else if (this.searchResultBalanceInfo[i].incomeType == '3') {
          this.searchResultBalanceInfo[i].incomeTypeName = '副業'
        } else if (this.searchResultBalanceInfo[i].incomeType == '4') {
          this.searchResultBalanceInfo[i].incomeTypeName = 'お小遣い'
        } else if (this.searchResultBalanceInfo[i].incomeType == '5') {
          this.searchResultBalanceInfo[i].incomeTypeName = '臨時収入'
        } else if (this.searchResultBalanceInfo[i].incomeType == '6') {
          this.searchResultBalanceInfo[i].incomeTypeName = '投資'
        }
        const incomeYYYY = this.searchResultBalanceInfo[i].balanceDate.substring(0, 4)
        const incomeMM = this.searchResultBalanceInfo[i].balanceDate.substring(5, 7)
        const incomeDD = this.searchResultBalanceInfo[i].balanceDate.substring(8, 10)
        this.searchResultBalanceInfo[i].balanceDate = [incomeYYYY, incomeMM, incomeDD].join('/')
      }
    },

    searchBalanceInfo: function () {
      try {
        axios
          .get('http://localhost:8080/api/searchBalanceList/', {
            params: { ID: this.searchBalanceCode },
          })
          .then((response) => {
            console.log(response),
              (this.searchResultBalanceInfo = response.data),
              this.searchResultChangeFormat()
          })
      } catch (error) {
        console.error('There was an error fetching the users!', error)
        alert(error)
      }
    },

    excuteRegist() {
      this.regist_modal = true
      //このファイルで定義したregist_modal
      //登録モーダル呼び出し
    },

    returnScreen() {
      this.regist_modal = false
    },

    excuteEdit(balanceCode: any) {
      this.edit_modal = true
      this.setBalanceNo = balanceCode
    },

    editReturn() {
      this.edit_modal = false
    },
  },
}
</script>
 
<template>
  <div>
    <input
      type="text"
      v-model="searchBalanceCode"
      class="search_text"
      placeholder="Type here"
      @keyup.enter="searchBalanceInfo"
    />

    <!-- 登録モーダル表示 -->
    <button @click="excuteRegist">収支登録</button>
    <div v-if="regist_modal == true">
      <regist @execute-method="returnScreen" />
    </div>

    <!-- 編集モーダル表示 -->
    <p>{{ edit_modal }}</p>
    <div v-if="edit_modal == true">
      <edit :balanceNo="setBalanceNo" @executeEdit-method="editReturn" />
    </div>

    <div class="table_box" v-if="searchFrag == true">
      <table class="table_style">
        <thead>
          <tr>
            <th class="sticky" v-for="label in header" :key="label.key">
              {{ label.text }}
            </th>
          </tr>
        </thead>
        <tbody v-for="balancedata in searchResultBalanceInfo" :key="balancedata.balanceCode">
          <tr v-if="balancedata.balanceType == '収入'">
            <td>{{ balancedata.balanceCode }}</td>
            <td>{{ balancedata.balanceType }}</td>
            <td>{{ balancedata.balanceDate }}</td>
            <td>{{ balancedata.incomeTypeName }}</td>
            <td>{{ balancedata.amount }}</td>
            <td>{{ balancedata.note }}</td>
            <td><button @click="excuteEdit(balancedata.balanceCode)">編集</button></td>
            <td><button>削除</button></td>
          </tr>
          <tr v-if="balancedata.balanceType == '支出'">
            <td>{{ balancedata.balanceCode }}</td>
            <td>{{ balancedata.balanceType }}</td>
            <td>{{ balancedata.balanceDate }}</td>
            <td>{{ balancedata.expenditureExpenseItemName }}</td>
            <td>{{ balancedata.amount }}</td>
            <td>{{ balancedata.note }}</td>
            <td><button @click="excuteEdit(balancedata.balanceCode)">編集</button></td>
            <td><button>削除</button></td>
          </tr>
        </tbody>
      </table>
    </div>
    <div v-if="searchResultBalanceInfo.length === 0">
      <p class="logo">{{ '検索結果が見つかりません' }}</p>
    </div>
  </div>
</template>
 
<style scoped>
header {
  line-height: 1.5;
  max-height: 100ch;
  background: #000000;
  width: 100%;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

.search_text {
  width: 300px;
  height: 30px;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

.table_box {
  overflow-y: auto;
  height: 300px;
  width: auto;
  -webkit-overflow-scrolling: touch;
  border-top: 0;
  border-spacing: 0;
  border-bottom: #000;
}

table {
  border-spacing: 0;
  width: 100%;
  height: 100%;
  display: table;
  border-collapse: collapse;
  box-sizing: border-box;
  text-indent: initial;
  unicode-bidi: isolate;
  border-color: gray;
}

/*スクロールバー*/
.sticky {
  position: sticky;
  top: 0;
  left: 1;
  background: none;
  border-top: none;
  border-bottom: none;
  background-color: #d0cece;
  box-shadow: 1px 0 0 #d0cece;
  height: 100%;
  text-wrap: wrap;
}

.sticky:before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: #d0cece;
  z-index: -1;
}

table td {
  text-align: center;
  background: #f5f5f5;
  border: 3px solid white;
  border-spacing: 10px;
}
</style>