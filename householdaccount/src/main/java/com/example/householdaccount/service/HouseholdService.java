package com.example.householdaccount.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.householdaccount.entity.Expenditure;
import com.example.householdaccount.entity.ExpenditureItems;
import com.example.householdaccount.entity.Income;
import com.example.householdaccount.form.ExpenditureForm;
import com.example.householdaccount.form.IncomeForm;
import com.example.householdaccount.repository.mybatis.ExpenditureRepository;
import com.example.householdaccount.repository.mybatis.GetExpenditureItemsRepository;
import com.example.householdaccount.repository.mybatis.IncomeRepository;

@Service
public class HouseholdService {

	@Autowired
	GetExpenditureItemsRepository expenditureItemRepository;
	@Autowired
	IncomeRepository incomeRepository;
	@Autowired
	ExpenditureRepository expenditureRepository;

	// 支出費目取得
	public List<ExpenditureItems> getExpenditureItems() {
		// リポジトリから取得した情報を返している
		return expenditureItemRepository.findAll();
	}

	// 収入登録
	public Income createIncomeInfo(IncomeForm incomeCommand) {

		// 入力された日付をyyyy/mm/ddにしたい
		Date date = incomeCommand.getDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
		String strDate = sdf.format(date);

		// incomeNo(I+YYMM登録時点の年月+00000)を作成する
		Calendar cl = Calendar.getInstance();

		// 現時点の年取得
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		String strYear = sdfYear.format(cl.getTime());
		// 3番目の文字列から最後までを抽出
		String strfYear = strYear.substring(2);

		// 現時点の月取得
		SimpleDateFormat sdfMonth = new SimpleDateFormat("mm");
		String strMonth = sdfMonth.format(cl.getTime());

		String incomeNo = "I" + strfYear + strMonth + 00000;

		Income income = new Income(incomeNo, strDate, incomeCommand);
		// コンストラクタを呼び出し、引数にincomeNoなど不足しているものを入れる

		// Entity側で必要なものをセッターでデータを入れる
		// 不要なものは記述しない

		return incomeRepository.save(income);
	}

	// 支出登録
	public Expenditure createExpenditureInfo(ExpenditureForm expenditureCommand) {

		// 入力された日付をyyyy/mm/ddにしたい
		Date date = expenditureCommand.getDate();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
		String strDate = sdf.format(date);

		// expenditureNo(I+YYMM登録時点の年月+00000)を作成する
		Calendar cl = Calendar.getInstance();

		// 現時点の年取得
		SimpleDateFormat sdfYear = new SimpleDateFormat("yyyy");
		String strYear = sdfYear.format(cl.getTime());
		// 3番目の文字列から最後までを抽出
		String strfYear = strYear.substring(2);

		// 現時点の月取得
		SimpleDateFormat sdfMonth = new SimpleDateFormat("mm");
		String strMonth = sdfMonth.format(cl.getTime());

		String expenditureNo = "E" + strfYear + strMonth + 00000;

		Expenditure expenditure = new Expenditure(expenditureNo,expenditureCommand);

		return expenditureRepository.save(expenditure);
	}

}
