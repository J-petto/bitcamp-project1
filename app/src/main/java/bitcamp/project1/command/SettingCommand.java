package bitcamp.project1.command;

import bitcamp.project1.vo.BankAccount;
import bitcamp.project1.vo.Cash;
import bitcamp.project1.vo.CreditCard;

public class SettingCommand {
  public void executeSettingCommand(String subTitle) {

    Object[] settings = {new Cash(), new BankAccount(), new CreditCard()};
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
