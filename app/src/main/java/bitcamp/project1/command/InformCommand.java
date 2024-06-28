package bitcamp.project1.command;

import bitcamp.project1.util.ArrayList;
import bitcamp.project1.vo.Income;
import bitcamp.project1.vo.Outcome;

public class InformCommand {
  ArrayList income, outcome;
  ArrayList totalList = new ArrayList();

  public InformCommand(ArrayList income, ArrayList outcome){
    this.income = income;
    this.outcome = outcome;
  }

  public void executeInformCommand(String subTitle){
    switch (subTitle){
      case "총 지출 수입" :
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
    System.out.println("총수입 총지출 총합");

    int incomeTotal = allSum(true);
    int outcomeTotal = allSum(false);
    int total = incomeTotal - outcomeTotal;

    System.out.printf("%d %d %d\n", incomeTotal, outcomeTotal, total);

  }

  private int allSum(boolean inOut){
    int sum = 0;

    if(inOut){
      for(Object obj : income.toArray()){
        Income plusIncome = (Income) obj;
        sum += plusIncome.getAmount();
      }
    }else {
      for(Object obj : outcome.toArray()){
        Outcome plusOutcome = (Outcome) obj;
        sum += plusOutcome.getAmount();
      }
    }
    return sum;
  }

  // 2. 일자별 수입 지출 목록
  private void viewDate(){
    ArrayList dateTotalArr = new ArrayList();
    for(Object obj : totalList.toArray()){

    }
  }

  private void totalArray(){
    for(Object obj : income.toArray()){
      Income addIncome = (Income) obj;
      totalList.add(addIncome);
    }
    for(Object obj : outcome.toArray()){
      Outcome addOutcome = (Outcome) obj;
      totalList.add(addOutcome);
    }
  }

  //3. 품목별 수입 지출 목록
  private void viesCategory(){

  }

}
