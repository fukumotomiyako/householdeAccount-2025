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
import Delete from '../src/compomemts/Organisms/Delete.vue'
import Detail from '../src/compomemts/Organisms/SearchDetail.vue'
import axios from 'axios'
import { createApp, ref } from 'vue'

export default {
  components: {
    regist,
    edit,
    Delete,
    Detail,
  },
  data() {
    return {
      test: 'cccccc', //デバック用
      setBalanceNo: '', //収支検索
      setIncomeNo: '', //収入削除
      setExpenditureNo: '', //支出削除
      regist_modal: false,
      edit_modal: false,
      delete_modal: false,
      detail_modal: false,
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
          this.searchResultBalanceInfo[i].incomeTypeName = '投資（Enum）'
        }
        const incomeYYYY = this.searchResultBalanceInfo[i].balanceDate.substring(0, 4)
        const incomeMM = this.searchResultBalanceInfo[i].balanceDate.substring(5, 7)
        const incomeDD = this.searchResultBalanceInfo[i].balanceDate.substring(8, 10)
        this.searchResultBalanceInfo[i].balanceDate = [incomeYYYY, incomeMM, incomeDD].join('/')
      }
    },

    searchBalanceInfo: function () {
      this.test = 'gggggggggggg'
      if (!this.searchBalanceCode) {
        this.searchResultBalanceInfo = ''
        this.searchResultChangeFormat()
      } else {
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

    executeEdit(balanceCode: any) {
      this.edit_modal = true
      this.setBalanceNo = balanceCode
    },

    editReturn() {
      this.edit_modal = false
    },

    //収入で削除をが押されたら呼び出される
    executeIncomeDelete(balanceCode: any) {
      //取得したCodeをsetIncomeNoにセット
      this.setIncomeNo = balanceCode
      //モーダル表示
      this.delete_modal = true
    },

    executeExpenditureDelete(balanceCode: any) {
      this.setExpenditureNo = balanceCode
      this.delete_modal = true
    },

    deleteReturn() {
      this.delete_modal = false
    },

    executeSearch() {
      this.detail_modal = true
    },

    detailSet(searchDetailInfo: any) {
      this.searchResultBalanceInfo = searchDetailInfo
      this.searchResultChangeFormat()
      this.detail_modal = false
    },

    detailCancel() {
      this.detail_modal = false
    },

    onBeforeInput(e: any) {
      if (e.inputType === 'insertParagraph' && !e.shiftKey) {
        e.preventDefault()
      }
    },
  },
}
</script>
 
<template>
  <div>
    <div>
      <header>家計簿システム</header>
    </div>
    <!-- 検索値入力テキストエリア -->
    <div class="text">
      <input
        type="text"
        v-model="searchBalanceCode"
        class="search_text"
        placeholder="収支Noを入力"
        @keyup.enter="searchBalanceInfo"
      />
    </div>

    <!-- 詳細検索モーダル表示 -->
    <span class="detail" @click="executeSearch">詳細検索▼</span>
    <div v-if="detail_modal == true">
      <Detail @executeDetail-method="detailSet" @executeCancel-method="detailCancel" />
    </div>

    <!-- 登録モーダル表示 -->
    <div class="registButton">
      <button class="regist" @click="excuteRegist">収支登録</button>
    </div>
    <div v-if="regist_modal == true">
      <regist @execute-method="returnScreen" />
    </div>

    <!-- 編集モーダル表示 -->
    <div v-if="edit_modal == true">
      <edit :balanceNo="setBalanceNo" @executeEdit-method="editReturn" />
    </div>

    <!-- 削除モーダル表示 -->
    <div v-if="delete_modal == true">
      <Delete
        :expenditureNo="setExpenditureNo"
        :incomeNo="setIncomeNo"
        @executeDelete-method="deleteReturn"
      />
    </div>

    <!-- 検索結果一覧表示 -->
    <div class="table_box" v-if="searchFrag == true">
      <table class="fixed">
        <colgroup>
          <col style="width: 200px" />
          <col style="width: 150px" />
          <col style="width: 200px" />
          <col style="width: 150px" />
          <col style="width: 150px" />
          <col style="width: 550px" />
          <col style="width: 100px" />
          <col style="width: 100px" />
        </colgroup>
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
            <td>
              <button class="editButton" @click="executeEdit(balancedata.balanceCode)">編集</button>
            </td>
            <td>
              <button class="deleteButton" @click="executeIncomeDelete(balancedata.balanceCode)">
                削除
              </button>
            </td>
          </tr>
          <tr v-if="balancedata.balanceType == '支出'">
            <td>{{ balancedata.balanceCode }}</td>
            <td>{{ balancedata.balanceType }}</td>
            <td>{{ balancedata.balanceDate }}</td>
            <td>{{ balancedata.expenditureExpenseItemName }}</td>
            <td>{{ balancedata.amount }}</td>
            <td>{{ balancedata.note }}</td>
            <td>
              <button class="editButton" @click="executeEdit(balancedata.balanceCode)">編集</button>
            </td>
            <td>
              <button
                class="deleteButton"
                @click="executeExpenditureDelete(balancedata.balanceCode)"
              >
                削除
              </button>
            </td>
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
.editButton {
  width: 120px;
  padding: 8px 16px;
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
.editButton:hover {
  background: #42a4f5;
  color: white;
  cursor: pointer;
}

.deleteButton {
  width: 120px;
  border-radius: 6px;
  padding: 8px 8px;
  display: inline-block;
  margin: 0%;
  text-decoration: none;
  color: #000000;
  border: solid 2px #e07777;
  border-radius: 3px;
  transition: 0.4s;
  text-align: center;
  vertical-align: middle;
  font-size: 15px;
  background-color: #e07777;
}
.deleteButton:hover {
  background: #c75050;
  color: white;
  cursor: pointer;
}

header {
  font-size: 40px;
  color: white;
  line-height: 2.5;
  max-height: 100ch;
  background: #000000;
  width: 100%;
}

.logo {
  font-size: 25px;
  display: block;
  margin-left: 15px;
}

#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}

table {
  width: 100%;
  border-collapse: collapse;
}

.table_box {
  height: 600px;
  overflow-y: auto;
  -webkit-overflow-scrolling: touch;
}

.table.fixed {
  table-layout: fixed;
  width: 100%;
  border-collapse: collaps;
}

table th {
  text-align: center;
  border: 3px solid #fff;
  font-size: 20px;
  color: #fff;
  padding: 10px 10px;
}

table td {
  text-align: center;
  background: #f5f5f5;
  border: 3px solid #fff;
  font-size: 20px;
  padding: 10px 10px;
}

/*スクロールバー*/
.sticky {
  position: sticky;
  top: 0;
  left: 0;
  background: #a0d1d1;
  border-top: none;
  border-bottom: none;
  overflow-wrap: normal;
}

.sticky:before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}

.regist {
  width: 180px;
  height: 70px;
  padding: 8px 16px;
  display: inline-block;
  margin: 6px;
  text-decoration: none;
  color: #000000;
  border: solid 2px #42a4f5;
  transition: 0.4s;
  border-radius: 3px;
  text-align: center;
  vertical-align: middle;
  font-size: 30px;
  background-color: #8dc0e9;
}
.regist:hover {
  background: #42a4f5;
  color: white;
  cursor: pointer;
}

.registButton {
  text-align: right;
  margin-top: 3px;
  margin-right: 200px;
}

.search_text {
  font-size: 25px;
  width: 600px;
  height: 60px;
}
/* テキストボックスサイズ */
.text {
  position: absolute;
  top: 121px;
  left: 15px;
}

.detail {
  position: absolute;
  top: 134px;
  left: 485px;
  font-size: 25px;
}

.detail:hover {
  cursor: pointer;
}
</style>