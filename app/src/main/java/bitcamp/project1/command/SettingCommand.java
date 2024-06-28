package bitcamp.project1.command;

import bitcamp.project1.Prompt.Prompt;
import bitcamp.project1.util.ArrayList;
import bitcamp.project1.vo.BankAccount;
import bitcamp.project1.vo.Cash;
import bitcamp.project1.vo.CreditCard;

public class SettingCommand {
  private final int CASH = 0;
  private final int BANK = 1;
  private final int CREDIT = 2;

  ArrayList bankList = new ArrayList();
  ArrayList creditCardList = new ArrayList();
  ArrayList cashList = new ArrayList();
  Object[] settings = {bankList, creditCardList, cashList};

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
    Cash cash = new Cash();
    System.out.println("현금");
    cash.setCuurentAmount(Prompt.inputInt("잔액?"));
    ((ArrayList) settings[CASH]).add(cash);

    while (true) {
      System.out.println("통장");
      String reply = Prompt.input("계좌를 추가 하시겠습니까(Y/N)?");
      if (!reply.equalsIgnoreCase("y")) {
        break;
      }
      BankAccount bankAccount = new BankAccount();
      bankAccount.setBankName(Prompt.input("은행명?"));
      bankAccount.setDepositAmount(Prompt.inputInt("잔액?"));
      ((ArrayList) settings[BANK]).add(bankAccount);
    }

    while (true) {
      System.out.println("신용카드");
      String reply = Prompt.input("신용카드를 추가 하시겠습니까(Y/N)?");
      if (!reply.equalsIgnoreCase("y")) {
        break;
      }
      CreditCard creditCard = new CreditCard();
      creditCard.setCardName(Prompt.input("카드사명?"));
      ((ArrayList) settings[CREDIT]).add(creditCard);
    }
  }


  public void listISetting() {
  }

  public void searchSetting() {
  }

  public void updateSetting() {
    ArrayList cashArr = (ArrayList) settings[CASH];
    for(Object obj: cashArr.toArray()){

      System.out.printf("%d. %s", obj);
    }
    while (true){
      String reply = Prompt.input("현금 목록을 수정하시겠습니까?(Y/N)");
      if(reply.equalsIgnoreCase("n")){
        break;
      } else if (reply.equalsIgnoreCase("y")) {

      }

    }

  }

  public void deleteSetting() {
  }
}
