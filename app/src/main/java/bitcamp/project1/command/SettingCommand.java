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
      Cash cash = new Cash();
      System.out.println("현금");
      cash.setCuurentAmount(Prompt.inputInt("잔액?"));
      userSettingList[CASH] = cash;

      BankAccount bankAccount = new BankAccount();
      System.out.println("통장");
      bankAccount.setBankName(Prompt.input("은행명?"));
      bankAccount.setDepositAmount(Prompt.inputInt("잔액?"));
      userSettingList[BANK] = bankAccount;

      CreditCard creditCard = new CreditCard();
      System.out.println("신용카드");
      creditCard.setCardName(Prompt.input("카드사명?"));
      userSettingList[CREDIT] = creditCard;
      settingDone = false;
    } else {
      System.out.println("이미 계정이 생성되어 있습니다.");
    }
  }


  public void listISetting() {}

  public void searchSetting() {}

  public void updateSetting() {
    String command = Prompt.input("보유 현금을 수정하시겠습니까?(Y/N)");
    if(command.equalsIgnoreCase("Y")){

    }
    command = Prompt.input("보유 통장을 수정하시겠습니까?(Y/N)");
    if(command.equalsIgnoreCase("Y")){
      int cash = Prompt.inputInt("수정 현금?");
    }
  }
  public void deleteSetting() {}

  public Object[] getUserSettingList() {
    return userSettingList;
  }
}
