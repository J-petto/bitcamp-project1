package bitcamp.project1.command;

import bitcamp.project1.util.ArrayList;
import bitcamp.project1.vo.Income;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

//수정 (나중에삭제)//
public class InformCommand {
  ArrayList incomeList = new ArrayList();
  ArrayList outcomeList = new ArrayList();
  ArrayList totalList = new ArrayList();

  public InformCommand(ArrayList incomeList, ArrayList outcomeList) {
    this.incomeList = incomeList;
    this.outcomeList = outcomeList;
  }

  public void executeInformCommand(String subTitle) {
    switch (subTitle) {
      case "총 지출 수입":
        viewTotal();
        break;
      case "일자별 수입 지출":
        //        System.out.println(Arrays.toString(uniqueList()));
        viewDate();
        break;
      case "항목별 수입 지출":
        viewCategory();
        break;
    }
  }

  public Object[] concat() {
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
  private void viewTotal() {
    System.out.println("총수입 총지출 총합");

    int incomeTotal = allSum(true);
    int outcomeTotal = allSum(false);
    int total = incomeTotal - outcomeTotal;

    System.out.printf("%d %d %d\n", incomeTotal, outcomeTotal, total);
    System.out.printf("총 수입 : %d원 ", incomeTotal);
    for (int i = 0; i < (incomeTotal / 100); i++) {
      System.out.print("\u25AE" + " ");
    }
    System.out.println();
    System.out.printf("총 지출 : %d원 ", outcomeTotal);
    for (int i = 0; i < (outcomeTotal / 100); i++) {
      System.out.print("\u25A0");
    }
    System.out.printf("총계 : %d원", total);
    for (int i = 0; i < (total / 100); i++) {
      System.out.print("\u25A0");
    }
  }

  // true -> income 비용 모두 더한 값 반환
  // false -> outcome 비용 모두 더한 값 반환
  private int allSum(boolean inOut) {
    int sum = 0;

    if (inOut) {
      for (Object obj : incomeList.toArray()) {
        Income plusIncome = (Income) obj;
        sum += plusIncome.getAmount();
      }
    } else {
      for (Object obj : outcomeList.toArray()) {
        Income plusOutcome = (Income) obj;
        sum += plusOutcome.getAmount();
      }
    }
    return sum;
  }

  // 2. 일자별 수입 지출 목록
  private void viewDate() {
    Object[] uniqueDate = uniqueDate(union());
    System.out.println("날짜 수입 지출 총계");
    for (Object obj : uniqueDate) {
      LocalDate date = (LocalDate) obj;
      int totalIncome = 0, totalOutcome = 0;
      for (int i = 0; i < incomeList.size(); i++) {
        Income income = (Income) incomeList.get(i);
        if (date.equals(income.getDate())) {
          totalIncome += income.getAmount();
        }
      }
      for (int i = 0; i < outcomeList.size(); i++) {
        Income outcome = (Income) outcomeList.get(i);
        if (date.equals(outcome.getDate())) {
          totalOutcome += outcome.getAmount();
        }
      }
      System.out.printf("%s, %d, %d ,%d\n", date.toString(), totalIncome, totalOutcome,
          totalIncome - totalOutcome);
    }
  }

  private void totalArray() {
    for (Object obj : incomeList.toArray()) {
      Income addIncome = (Income) obj;
      totalList.add(addIncome);
    }
    for (Object obj : outcomeList.toArray()) {
      Income addOutcome = (Income) obj;
      totalList.add(addOutcome);
    }
  }

  //3. 품목별 수입 지출 목록
  private void viewCategory() {
    Object[] uniqueIncome = uniqueList(incomeList);
    for (Object obj : uniqueIncome) {
      String car = (String) obj;
      int total = 0;
      for (Object value : incomeList.toArray()) {
        Income income = (Income) value;
        if (car.equals(income.getCategory())) {
          total += income.getAmount();
        }
      }
      System.out.printf("%s, %d\n", obj.toString(), total);
    }

    Object[] uniqueOutcome = uniqueList(outcomeList);
    for (Object obj : uniqueOutcome) {
      String car = (String) obj;
      int total = 0;
      for (Object value : outcomeList.toArray()) {
        Income income = (Income) value;
        if (car.equals(income.getCategory())) {
          total += income.getAmount();
        }
      }
      System.out.printf("%s, %d\n", obj.toString(), total);
    }
  }

  private ArrayList union() {
    int incomeSize = incomeList.size();
    int outcomeSize = outcomeList.size();
    ArrayList result = new ArrayList();
    for (int i = 0; i < incomeSize; i++) {
      result.add(incomeList.get(i));
    }
    for (int i = incomeSize; i < outcomeSize; i++) {
      result.add(incomeList.get(i));
    }
    return result;
  }

  private Object[] uniqueDate(ArrayList list) {
    HashSet<LocalDate> set = new HashSet<>();
    for (Object obj : list.toArray()) {
      Income value = (Income) obj;
      set.add(value.getDate());
    }

    Object[] arr = set.toArray();
    Arrays.sort(arr);
    return arr;
  }

  private Object[] uniqueList(ArrayList list) {
    HashSet<String> set = new HashSet<>();
    for (Object obj : list.toArray()) {
      Income value = (Income) obj;
      set.add(value.getCategory());
    }
    Object[] arr = set.toArray();
    Arrays.sort(arr);
    return arr;
  }
}
