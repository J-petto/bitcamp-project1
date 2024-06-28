package bitcamp.project1.command;

import bitcamp.project1.util.ArrayList;
import bitcamp.project1.vo.Income;
import bitcamp.project1.vo.Outcome;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

public class InformCommand {
  ArrayList incomeList = new ArrayList();
  ArrayList outcomeList = new ArrayList();

  public InformCommand(ArrayList incomeList,ArrayList outcomeList) {
    this.incomeList = incomeList;
    this.outcomeList = outcomeList;
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
        viewCategory();
        break;
    }
  }
  public Object[] concat(){
    int incomeSize = incomeList.size();
    int outcomeSize = outcomeList.size();
    Object[] result = new Object[incomeSize + outcomeSize];
    for (int i = 0; i < incomeSize; i++) {
      result[i] = incomeList.get(i);
    }
    for (int i = 0; i < outcomeSize; i++) {
      result[incomeSize + i] = outcomeList.get(i);
    }
    return result;
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
      for(Object obj : incomeList.toArray()){
        Income plusIncome = (Income) obj;
        sum += plusIncome.getAmount();
      }
    }else {
      for(Object obj : outcomeList.toArray()){
        Outcome plusOutcome = (Outcome) obj;
        sum += plusOutcome.getAmount();
      }
    }
    return sum;
  }

  // 2. 일자별 수입 지출 목록
  private void viewDate(){
    Object[] total = union();;
    Object[] uniqueDate = uniqueList();
    System.out.println("날짜 수입 지출 총계");
    for (Object obj : uniqueDate){
      LocalDate date = (LocalDate) obj;
      int totalIncome = 0, totalOutcome = 0;
      for (int i = 0; i < incomeList.size(); i++){
        Income income = (Income) incomeList.get(i);
        totalIncome += income.getAmount();
      }
      for (int i = 0; i < outcomeList.size(); i++){
        Outcome outcome = (Outcome) outcomeList.get(i);
        totalOutcome += outcome.getAmount();
      }
      System.out.printf("%s, %d, %d", date.toString(), totalIncome, totalOutcome);
    };

  }
  ArrayList totalList = new ArrayList();
  private void totalArray(){
    for(Object obj : incomeList.toArray()){
      Income addIncome = (Income) obj;
      totalList.add(addIncome);
    }
    for(Object obj : outcomeList.toArray()){
      Outcome addOutcome = (Outcome) obj;
      totalList.add(addOutcome);
    }
  }

  //3. 품목별 수입 지출 목록
  private void viewCategory(){
  }

  private Object[] union(){
    int incomeSize = incomeList.size();
    int outcomeSize = outcomeList.size();
    Object[] result = new Object[incomeSize + outcomeSize];
    for (int i = 0; i < incomeSize; i++) {
      result[i] = incomeList.get(i);
    }
    for (int i = incomeSize; i < outcomeSize; i++) {
      result[i] = outcomeList.get(i);
    }
    return result;
  }

  private Object[] uniqueList(){
    HashSet<LocalDate> set = new HashSet();
    for (int i = 0; i < outcomeList.size(); i++){
      Outcome outcome = (Outcome) outcomeList.get(i);
      set.add(outcome.getDate());
    }
    Object[] arr = set.toArray();
    Arrays.sort(arr);
    return arr;
  }
}
