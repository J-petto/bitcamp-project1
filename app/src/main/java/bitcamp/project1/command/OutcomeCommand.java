package bitcamp.project1.command;

import bitcamp.project1.Prompt.Prompt;
import bitcamp.project1.util.ArrayList;
import bitcamp.project1.vo.Outcome;


public class OutcomeCommand {
  ArrayList outcomeList = new ArrayList();

  public void excuteOutcomeCommand(String command) {
    System.out.printf("[%s]\n", command);
    switch (command) {
      case "등록":
        addUser();
        break;
      case "목록":
        listUser();
        break;
      case "조회":
        viewUser();
        break;
      case "변경":
        updateUser();
        break;
      case "삭제":
        deleteUser();
        break;
    }
  }

  private void addUser() {
    Outcome outcome = new Outcome();
    outcome.setNo(Outcome.getSeqNo());
    outcome.setDate(Prompt.inputDate("지출일(yyyy-MM-dd)?"));
    outcome.setAmount(Prompt.inputInt("지출금액?"));
    outcome.setAccountType(Prompt.inputInt("결제방법(현금:0,카드:1)?"));
    outcome.setCategory(Prompt.input("지출종류?"));
    outcomeList.add(outcome);
  }

  private void listUser() {
    System.out.println("번호 지출날짜 지출금액");
    for (Object obj : outcomeList.toArray()) {
      Outcome outcome = (Outcome) obj;
      System.out.printf("%d %s %s\n", outcome.getNo(), outcome.getDate(), outcome.getAmount());
    }
  }

  private void viewUser() {
    int outcomeNo = Prompt.inputInt("지출번호?");
    Outcome outcome = (Outcome) outcomeList.get((outcomeList.indexOf(new Outcome(outcomeNo))));
    if (outcome == null) {
      System.out.println("없는 지출입니다.");
      return;
    }
    System.out.printf("지출날짜 : %s\n", outcome.getDate());
    System.out.printf("지출금액 : %s\n", outcome.getAmount());
    System.out.printf("결제방법 : %s\n", outcome.getAccountType());
    System.out.printf("지출종류 : %s\n", outcome.getCategory());
  }

  private void updateUser() {
    int outcomeNo = Prompt.inputInt("지출번호?");
    Outcome outcome = (Outcome) outcomeList.get((outcomeList.indexOf(new Outcome(outcomeNo))));
    if (outcome == null) {
      System.out.println("없는 지출입니다.");
      return;
    }
    outcome.setDate(Prompt.inputDate("지출일(%s):", outcome.getDate()));
    outcome.setAmount(Prompt.inputInt("결제금액(%s):", outcome.getAmount()));
    outcome.setAccountType(Prompt.inputInt("결제방법(%s):", outcome.getAccountType()));
    outcome.setCategory(Prompt.input("지출종류(%s):", outcome.getCategory()));
    System.out.println("변경했습니다.");
  }

  private void deleteUser() {
    int outcomeNo = Prompt.inputInt("지출번호?");
    Outcome deleteOutcome = (Outcome) outcomeList.get((outcomeList.indexOf(new Outcome(outcomeNo))));
    if (deleteOutcome != null) {
      outcomeList.remove(outcomeList.indexOf(deleteOutcome));
      System.out.printf("'%d 지출을 삭제했습니다.\n", deleteOutcome.getNo());
    } else {
      System.out.println("없는 지출입니다.");
    }
  }
}
