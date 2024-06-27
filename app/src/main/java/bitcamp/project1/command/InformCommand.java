package bitcamp.project1.command;

import bitcamp.project1.util.TotalList;
import bitcamp.project1.vo.Total;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class InformCommand {

  TotalList totalList = new TotalList(income,outcome);
  Object lsit = totalList.concat();

  public void executeInformCommand(String subTitle){
    switch (subTitle){
      case "총 수입 지출" :
        viewTotal();
        break;
      case "일자별 수입 지출":
        viewDate();
        break;
      case  "항목별 수입 지출":
        viesCategory();
        break;
    }
  }
  // 1.총 지출 수입 목록
  private void viewTotal(){
    HashMap<String,Integer> map = new HashMap<>();
    for (Total amonut : TotalAmount) {
      String str = amount.getKindOfCome();
      int money = amount.getAmount();
      if (map.containsKey(str)) {
        map.put(str, map.get(str) + money);
      } else {
        map.put(str, money);
      }
    }
    for(Map.Entry<String,Integer> entry:map.entrySet()){
      System.out.println(entry.getKey()+" : "+entry.getValue()+"원");
    }
  }
  // 2. 일자별 수입 지출 목록
  private void viewDate(){
  }
  //3. 품목별 수입 지출 목록
  private void viesCategory(){

  }

}
