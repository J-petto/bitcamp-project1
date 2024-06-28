package bitcamp.project1.command;

import bitcamp.project1.Prompt.Prompt;
import bitcamp.project1.util.ArrayList;
import bitcamp.project1.vo.BankAccount;
import bitcamp.project1.vo.Cash;
import bitcamp.project1.vo.CreditCard;

public class SettingCommand {
  final int CASH = 0;
  final int BANK = 1;
  final int CREDIT = 2;

  private Object[] userSettingList = new Object[3];

  private boolean settingDone = true;

  public void executeSettingCommand(String subTitle) {

    switch (subTitle) {
      case "등록":
        createSetting();
        break;
      case "목록":
        listISetting();
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
      userSettingList[CASH] = cash;

      BankAccount bankAccount = new BankAccount();
      System.out.println("통장");
      String reply = Prompt.input("계좌를 추가 하시겠습니까(Y/N)?");
      if (reply.equalsIgnoreCase("y")) {
        bankAccount.setBankName(Prompt.input("은행명?"));
        bankAccount.setDepositAmount(Prompt.inputInt("잔액?"));
        userSettingList[BANK] = bankAccount;
      }


      BankAccount creditCard = new BankAccount();
      System.out.println("신용카드");
      reply = Prompt.input("신용카드를 추가 하시겠습니까(Y/N)?");
      if (reply.equalsIgnoreCase("y")) {
        creditCard.setBankName(Prompt.input("카드사명?"));
        creditCard.setDepositAmount(Prompt.inputInt("사용액?"));
        userSettingList[CREDIT] = creditCard;
        settingDone = false;
      }
    } else {
      System.out.println("이미 계정이 생성되어 있습니다.");
    }
  }

  public void listISetting() {
    System.out.println("구분 상세내용 금액");
    for (Object obj : userSettingList) {
      BankAccount count = (BankAccount) obj;
      System.out.printf("%s %d\n", count.getBankName(), count.getDepositAmount());
    }
  }


  public void updateSetting() {
    String command = Prompt.input("보유 현금을 수정하시겠습니까?(Y/N)");
    if(command.equalsIgnoreCase("Y")){
      Cash cash = (Cash) userSettingList[CASH];
      cash.setCuurentAmount(Prompt.inputInt("수정 현금액?"));
    }
    command = Prompt.input("보유 통장을 수정하시겠습니까?(Y/N)");
    if(command.equalsIgnoreCase("Y")){
      BankAccount bankAccount = (BankAccount) userSettingList[CASH];
      bankAccount.setBankName(Prompt.input("수정 은행명?"));
      bankAccount.setDepositAmount(Prompt.inputInt("수정 통장 금액?"));
    }
    command = Prompt.input("보유 카드를 수정하시겠습니까?(Y/N)");
    if(command.equalsIgnoreCase("Y")){
      CreditCard creditCard = (CreditCard) userSettingList[CASH];
      creditCard.setCardName(Prompt.input("수정 카드사명?"));
      creditCard.setPayAmount(Prompt.inputInt("수정 카드 금액?"));
    }
  }

  public void deleteSetting() {
  }

  public Object[] getUserSettingList() {
    return userSettingList;
  }
}
