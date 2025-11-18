<script lang="ts">
import RadioButtton from '../Molecules/SearchBalanceRadio.vue'
import Date from '../Molecules/SearchBaseDate.vue'
import NumberInput from '../Molecules/SearchBaseAmount.vue'
import FormSelect from '../Atoms/FormSelectAtoms.vue'
import TextArea from '../Atoms/TextArea.vue'
import Button from '../Atoms/ButtonAtoms.vue'
export default {
  components: { RadioButtton, NumberInput, FormSelect, Button, TextArea, Date },
  data() {
    return {
      setSelectRadio: '支出', //モーダル開かれたときの初期選択
      setRadioName1: '収入',
      setRadioName2: '支出',
      setRadioName3: '指定なし', //ラジオボタン名指定
    }
  },
}
</script>

<template>
  <div>
    <label>収支区分：</label>
    <RadioButton />
  </div>
  <div>
    <label>日付：</label>
    <Date />
  </div>
  <div>
    <FormSelect />
  </div>
  <div>
    <label>金額：</label>
    <NumberInput />
  </div>
  <div>
    <label>備考：</label>
    <TextArea />
  </div>
  <div><Button buttonName="検索" /></div>
</template>