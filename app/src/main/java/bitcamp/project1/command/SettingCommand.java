package bitcamp.project1.command;

import bitcamp.project1.Prompt.Prompt;
import bitcamp.project1.vo.BankAccount;

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
    if (settingDone) {
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
      }
      settingDone = false;
    } else {
      System.out.println("이미 계정이 생성되어 있습니다.");
    }
  }

  public void listISetting() {
    System.out.println("구분 상세내용 금액");
    for (Object obj : userSettingList) {
      if (obj == null) {
        continue;
      }
      BankAccount count = (BankAccount) obj;
      System.out.printf("%s %d\n", count.getBankName(), count.getDepositAmount());
    }
  }

  public void updateSetting() {
    String command;
    BankAccount cash = (BankAccount) userSettingList[CASH];
    BankAccount bankAccount = (BankAccount) userSettingList[BANK];
    BankAccount creditCard = (BankAccount) userSettingList[CREDIT];
    while (true) {
      command = Prompt.input("보유 현금을 수정하시겠습니까?(Y/N)");
      if (command.equalsIgnoreCase("Y")) {
        cash.setDepositAmount((Prompt.inputInt("수정 현금액?")));
        System.out.println("수정했습니다.");
        break;
      } else if (command.equalsIgnoreCase("N")) {
        System.out.printf("기존 현금 유지했습니다(%d)\n", cash.getDepositAmount());
        break;
      } else {
        System.out.println("y 나 n 만 입력해주세요.");
      }
    }
    while (true) {
      command = Prompt.input("보유 통장을 수정하시겠습니까?(Y/N)");
      if (command.equalsIgnoreCase("Y")) {
        bankAccount.setBankName(Prompt.input("수정 은행명?"));
        bankAccount.setDepositAmount(Prompt.inputInt("수정 통장 금액?"));
        System.out.println("수정했습니다.");
        break;
      } else if (command.equalsIgnoreCase("N")) {
        System.out.printf("기존 통장을 유지했습니다(%s. %d)\n", bankAccount.getBankName(),
            bankAccount.getDepositAmount());
        break;
      } else {
        System.out.println("y 나 n 만 입력해주세요.");
      }
    }
    while (true) {
      command = Prompt.input("보유 카드를 수정하시겠습니까?(Y/N)");
      if (command.equalsIgnoreCase("Y")) {
        creditCard.setBankName(Prompt.input("수정 카드사명?"));
        creditCard.setDepositAmount(Prompt.inputInt("수정 카드 금액?"));
        System.out.println("수정했습니다.");
        break;
      } else if (command.equalsIgnoreCase("N")) {
        System.out.printf("기존 통장을 유지했습니다(%s. %d)\n", bankAccount.getBankName(),
            bankAccount.getDepositAmount());
        break;
      } else {
        System.out.println("y 나 n 만 입력해주세요.");
      }
    }
  }

  public void deleteSetting() {
    while (true) {
      String command = Prompt.input("초기 데이터를 삭제 하시겠습니까?(Y/N)");
      if (command.equalsIgnoreCase("Y")) {
        userSettingList[CASH] = null;
        userSettingList[BANK] = null;
        userSettingList[CREDIT] = null;
        settingDone = true;
        break;
      } else if (command.equalsIgnoreCase("N")) {
        System.out.println("초기 데이터를 유지했습니다");
        break;
      } else {
        System.out.println("y 나 n 만 입력해주세요.");
      }
    }
  }

  public Object[] getUserSettingList() {
    return userSettingList;
  }

  public boolean getSettingDone() {
    return settingDone;
  }
}
