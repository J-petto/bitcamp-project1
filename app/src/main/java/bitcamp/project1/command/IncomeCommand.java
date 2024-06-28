package bitcamp.project1.command;

import bitcamp.project1.Prompt.Prompt;
import bitcamp.project1.util.ArrayList;
import bitcamp.project1.vo.Finance;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IncomeCommand {
  private final int PROCESS_LIST = 0;
  private final int PROCESS_SEARCH = 1;
  private final int PROCESS_UPDATE = 2;
  private final int PROCESS_DELETE = 3;

  private final int ACCOUNT = 0;
  private final int CASH = 1;

  ArrayList incomeList = new ArrayList();

  public void autoIncomeData() {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    Finance income = new Finance();
    income.setKindOfCome("수입");
    income.setDate(LocalDate.parse("2024-06-27"));
    income.setCategory("월급");
    income.setAccount("현금");
    income.setAmount(500);
    income.setNo(Finance.getSeqNo());
    incomeList.add(income);

    Finance income1 = new Finance();
    income1.setKindOfCome("수입");
    income1.setDate(LocalDate.parse("2024-06-28"));
    income1.setCategory("용돈");
    income1.setAccount("현금");
    income1.setAmount(1500);
    income1.setNo(Finance.getSeqNo());
    incomeList.add(income1);

    Finance income2 = new Finance();
    income2.setKindOfCome("수입");
    income2.setDate(LocalDate.parse("2024-06-28"));
    income2.setCategory("월급");
    income2.setAccount("현금");
    income2.setAmount(300);
    income2.setNo(Finance.getSeqNo());
    incomeList.add(income2);
  }

  public void executeIncomeCommand(String subTitle) {
    switch (subTitle) {
      case "등록":
        createIncome();
        break;
      case "목록":
        listIncome();
        break;
      case "조회":
        searchIncome();
        break;
      case "변경":
        updateIncome();
        break;
      case "삭제":
        deleteIncome();
        break;
      default:
    }
  }

  private void createIncome() {
    Finance income = new Finance();
    income.setKindOfCome("수입");
    income.setDate(Prompt.inputDate("수입 날짜(yyyy-MM-dd)?"));
    income.setAmount(Prompt.inputInt("수입 금액?"));
    income.setAccount(Prompt.input("수입출처?"));
    income.setCategory(Prompt.input("카테고리?"));
    income.setNo(Finance.getSeqNo());
    incomeList.add(income);
  }

  private void listIncome() {
    System.out.println("날짜 항목 금액");
    printNoList(PROCESS_LIST);
  }

  private void searchIncome() {
    printNoList(PROCESS_SEARCH);
    int incomeNo = Prompt.inputInt("조회 할 수입 번호?");
    Finance searchedIncome = (Finance) incomeList.get(incomeList.indexOf(new Finance(incomeNo)));
    if (searchedIncome == null) {
      System.out.println("없는 수입 번호입니다.");
      return;
    }
    System.out.printf("수입날짜 : %s\n", searchedIncome.getDate());
    System.out.printf("수입금액 : %d\n", searchedIncome.getAmount());
    System.out.printf("수입출처 : %s\n", searchedIncome.getAccount());
    System.out.printf("카테고리 : %s\n", searchedIncome.getCategory());
  }

  public void updateIncome() {
    printNoList(PROCESS_UPDATE);
    int incomeNo = Prompt.inputInt("변경 할 수입 번호?");
    Finance updateIncome = (Finance) incomeList.get(incomeList.indexOf(new Finance(incomeNo)));
    if (updateIncome == null) {
      System.out.println("없는 수입 번호입니다.");
      return;
    }
    updateIncome.setDate(Prompt.inputDate("수입일(%s)?", updateIncome.getDate()));
    updateIncome.setAmount(Prompt.inputInt("수입금액(%s)", updateIncome.getAmount()));
    updateIncome.setAccount(Prompt.input("수입출처(%s)?", updateIncome.getAccount()));
    updateIncome.setCategory(Prompt.input("카테고리(%s)", updateIncome.getCategory()));
    System.out.println("변경 완료했습니다.");
  }

  private void deleteIncome() {
    printNoList(PROCESS_DELETE);
    int incomeNo = Prompt.inputInt("삭제 할 수입 번호?");
    Finance deletedIncome = (Finance) incomeList.get(incomeList.indexOf(new Finance(incomeNo)));
    if (deletedIncome != null) {
      incomeList.remove(incomeList.indexOf(deletedIncome));
      System.out.println("삭제 완료했습니다.");
    } else {
      System.out.println("없는 수입 번호입니다.");
    }
  }

  private void printNoList(int processNo) {
    for (Object object : incomeList.toArray()) {
      Finance income = (Finance) object;
      switch (processNo) {
        case PROCESS_UPDATE:
        case PROCESS_DELETE:
        case PROCESS_SEARCH:
          System.out.printf("%d. ", income.getNo());
        case PROCESS_LIST:
          System.out.printf("%s %s %s\n", income.getDate(), income.getCategory(),
              income.getAmount());
          break;
        default:
      }

    }
  }

  public ArrayList getIncomeList() {
    return incomeList;
  }
}
