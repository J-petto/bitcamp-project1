package bitcamp.project1.command;

import bitcamp.project1.Prompt.Prompt;
import bitcamp.project1.util.ArrayList;
import bitcamp.project1.vo.BankAccount;
import bitcamp.project1.vo.Cash;
import bitcamp.project1.vo.CreditCard;

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
      case "변경":ßß
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
      totalCash.add(cash);

      BankAccount bankAccount = new BankAccount();
      System.out.println("통장");
      bankAccount.setBankName(Prompt.input("은행명?"));
      bankAccount.setDepositAmount(Prompt.inputInt("잔액?"));
      totalBank.add(bankAccount);

      CreditCard creditCard = new CreditCard();
      System.out.println("신용카드");
      creditCard.setCardName(Prompt.input("카드사명?"));
      totalCredit.add(creditCard);
      settingDone = false;
    } else {
      System.out.println("이미 계정이 생성되어 있습니다.");
    }
  }


  public void listISetting() {
  }

  public void searchSetting() {
  }

  public void updateSetting() {
  }

  public void deleteSetting() {
  }
}
