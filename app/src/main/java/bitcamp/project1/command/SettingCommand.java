package bitcamp.project1.command;

import bitcamp.project1.Prompt.Prompt;
import bitcamp.project1.util.ArrayList;
import bitcamp.project1.vo.BankAccount;

public class SettingCommand {
  
  ArrayList totalCash = new ArrayList();
  ArrayList totalBank = new ArrayList();
  ArrayList totalCredit = new ArrayList();

  private boolean settingDone = true;

  public void executeSettingCommand(String subTitle) {

    switch (subTitle) {
      case "등록":
        createSetting();
        break;
      case "목록":
        listISetting();
        break;
      case "조회":
        searchSetting();
        break;
      case "변경":
        updateSetting();
        break;
      case "삭제":
        deleteSetting();
        break;
      default:
    }
  }

  public void createSetting() {
    if (settingDone == true) {
      BankAccount cash = new BankAccount();
      System.out.println("현금");
      cash.setBankName("현금");
      cash.setDepositAmount(Prompt.inputInt("잔액?"));
      totalCash.add(cash);

      BankAccount bankAccount = new BankAccount();
      System.out.println("통장");
      String reply = Prompt.input("계좌를 추가 하시겠습니까(Y/N)?");
      if (reply.equalsIgnoreCase("y")) {
        bankAccount.setBankName(Prompt.input("은행명?"));
        bankAccount.setDepositAmount(Prompt.inputInt("잔액?"));
        totalBank.add(bankAccount);
      }


      BankAccount creditCard = new BankAccount();
      System.out.println("신용카드");
      reply = Prompt.input("신용카드를 추가 하시겠습니까(Y/N)?");
      if (reply.equalsIgnoreCase("y")) {
        creditCard.setBankName(Prompt.input("카드사명?"));
        creditCard.setDepositAmount(Prompt.inputInt("사용액?"));
        totalCredit.add(creditCard);
        settingDone = false;
      }
    } else {
      System.out.println("이미 계정이 생성되어 있습니다.");
    }
  }

  public void listISetting() {
    System.out.println("구분 상세내용 금액");
    for (Object obj : totalCash.toArray()) {
      BankAccount cash = (BankAccount) obj;
      System.out.printf("%s %s %d\n", cash.getBankName(), cash.getDepositAmount());
    }

  }

  public void printList(ArrayList arr) {
    for (Object obj : arr.toArray()) {

    }

  }

  public void searchSetting() {
  }

  public void updateSetting() {
  }

  public void deleteSetting() {
  }
}
