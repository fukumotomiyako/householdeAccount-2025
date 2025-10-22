package Controller;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import Form.incomeForm;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173/")
public class registController {
	
//	incomeForm u(serviceに渡す入れ物);
	
//	@PostMapping("/income")
//	public List<income> {
//	}
	
	//登録(収入)
		@RequestMapping(value = "/income", method = RequestMethod.POST)
		//value=フロントエンドからのURLの指示
		
		public String incomeCreate( @RequestBody @Validated incomeForm incomeCommmand,BindingResult result){
			//バックエンドとフロントエンドのデータの受け渡しにはフォーム使用 Formにフロントエンドから渡される項目と同じものを用意
			//DBとバックエンドのやり取りはEntity

			//			incomeForm u(serviceに渡す入れ物);
			
//			u=incomeCommmand(フロントエンドから受け取る入れ物)＋version;
			
			if(result.hasErrors()) {
			     return "登録できません";
			    }
			
//			householdServices.postCreateIncomeInfo(incomeCommmand);
			
			System.out.println(incomeCommmand);
	 
			return "登録しました";
		}
	 
		
}
