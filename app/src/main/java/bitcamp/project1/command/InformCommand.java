package bitcamp.project1.command;

import bitcamp.project1.util.ArrayList;
import bitcamp.project1.vo.Finance;
import bitcamp.project1.vo.Wallet;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;

public class InformCommand {
  private final int PROGRESS_INCOME = 0;
  private final int PROGRESS_OUTCOME = 1;
  private final int PROGRESS_TOTAL = 2;

  ArrayList incomeList = new ArrayList();
  ArrayList outcomeList = new ArrayList();
  Object[] assetList = new ArrayList[3];

  public InformCommand(ArrayList incomeList, ArrayList outcomeList, Object[] assetList) {
    this.incomeList = incomeList;
    this.outcomeList = outcomeList;
    this.assetList = assetList;
  }

  public void executeInformCommand(String subTitle) {
    switch (subTitle) {
      case "총 지출 수입":
        viewTotal();
        break;
      case "일자별 수입 지출":
        viewDate(subTitle);
        break;
      case "항목별 수입 지출":
        viewCategory(subTitle);
        break;
      case "자산별 수입 지출":
        viewAsset();
        break;
    }
  }

  // 1.총 지출 수입 목록
  private void viewTotal() {
    int incomeTotal = allSum(true);
    int outcomeTotal = allSum(false);
    int total = incomeTotal - outcomeTotal;

    System.out.printf("총 수입 :  %d ", incomeTotal);
    printGraph(PROGRESS_INCOME, incomeTotal, total);

    System.out.printf("총 지출 : -%d ", outcomeTotal);
    printGraph(PROGRESS_OUTCOME, outcomeTotal, total);

    System.out.printf("총   계 : %d ", total);
    printGraph(PROGRESS_TOTAL, Math.abs(total), total);
  }

  private void viewDate(String subTitle) {
    Object[] uniqueDate = uniqueList(union(), subTitle);
    System.out.println("날짜 수입 지출 총계");
    for (Object obj : uniqueDate) {
      LocalDate date = (LocalDate) obj;
      int totalIncome = 0, totalOutcome = 0;
      for (Object in : incomeList.toArray()) {
        Finance income = (Finance) in;
        if (date.equals(income.getDate())) {
          totalIncome += income.getAmount();
        }
      }
      for (Object out : outcomeList.toArray()) {
        Finance outcome = (Finance) out;
        if (date.equals(outcome.getDate())) {
          totalOutcome += outcome.getAmount();
        }
      }
      System.out.printf("%s, %d, %d ,%d\n", date.toString(), totalIncome, totalOutcome,
          totalIncome - totalOutcome);
    }
  }

  private void viewCategory(String subTitle) {
    int incomeTotal = allSum(true);
    int outcomeTotal = allSum(false);

    Object[] uniqueIncome = uniqueList(incomeList, subTitle);
    System.out.println("[수입]----------------------");
    printFormatted("수입총합", PROGRESS_INCOME, Math.abs(incomeTotal), incomeTotal);
    for (Object obj : uniqueIncome) {
      String car = (String) obj;
      int total = 0;
      for (Object value : incomeList.toArray()) {
        Finance income = (Finance) value;
        if (car.equals(income.getCategory())) {
          total += income.getAmount();
        }
      }
      printFormatted(obj.toString(), PROGRESS_INCOME, Math.abs(total), incomeTotal);
    }

    Object[] uniqueOutcome = uniqueList(outcomeList, subTitle);
    System.out.println("[지출]----------------------");
    printFormatted("지출총합", PROGRESS_OUTCOME, Math.abs(outcomeTotal), outcomeTotal);
    for (Object obj : uniqueOutcome) {
      String car = (String) obj;
      int total = 0;
      for (Object value : outcomeList.toArray()) {
        Finance income = (Finance) value;
        if (car.equals(income.getCategory())) {
          total += income.getAmount();
        }
      }
      printFormatted(obj.toString(), PROGRESS_OUTCOME, Math.abs(total), outcomeTotal);
    }
  }

  private void viewAsset() {
    System.out.println("구분 잔고 수입 지출 총계");
    for (Object obj : assetList) {
      Wallet wallet = (Wallet) obj;
      if (wallet.getAssetType() == null) {
        continue;
      }
      int totalIncome = 0, totalOutcome = 0;
      for (Object in : incomeList.toArray()) {
        Finance income = (Finance) in;
        if (wallet.getAssetType().equals(income.getKindOfCome())) {
          totalIncome += income.getAmount();
        }
      }
      for (Object out : outcomeList.toArray()) {
        Finance outcome = (Finance) out;
        if (wallet.getAssetType().equals(outcome.getKindOfCome())) {
          totalOutcome += outcome.getAmount();
        }
      }
      System.out.printf("%s, %d, %d, %d, %d \n", wallet.getAssetType(), wallet.getDepositAmount(),
          totalIncome, totalOutcome, wallet.getDepositAmount() + totalIncome - totalOutcome);
    }

  }

  public void printGraph(int label, int value, int total) {
    String ansiBlue = "\033[94m";
    String ansiRed = "\033[91m";
    String ansiEnd = "\033[0m";
    String ansiBold = "\033[1m";
    String dotCode = "\u25AE";

    int barLength = 30;
    double ratio = total == 0 ? 0 : (double) value / Math.abs(total);
    int filledLength = (int) (ratio * barLength);

    if (label == 0) {
      for (int i = 0; i < filledLength; i++) {
        System.out.printf("%s%s%s", ansiBlue, dotCode, ansiEnd);
      }
    } else if (label == 1) {
      for (int i = 0; i < filledLength; i++) {
        System.out.printf("%s%s%s", ansiRed, dotCode, ansiEnd);
      }
    } else {
      for (int i = 0; i < filledLength; i++) {
        if (total < 0) {
          System.out.printf("%s%s%s", ansiRed, dotCode, ansiEnd);
        } else {
          System.out.printf("%s%s%s", ansiBlue, dotCode, ansiEnd);
        }
      }
    }
    System.out.println();
  }

  public void printFormatted(String text, int label, int value, int total) {
    String formattedValue = String.format("%,d", value);
    System.out.printf("%s", text);
    for (int i = 0; i < 8 - text.length(); i++) {
      System.out.print("  ");
    }
    System.out.print("|");
    for (int i = 0; i < 10 - formattedValue.length(); i++) {
      System.out.print(" ");
    }
    System.out.printf("%s원 ", formattedValue);
    printGraph(label, value, total);
  }

  // true -> income 비용 모두 더한 값 반환
  // false -> outcome 비용 모두 더한 값 반환
  private int allSum(boolean inOut) {
    int sum = 0;

    if (inOut) {
      for (Object obj : incomeList.toArray()) {
        Finance plusIncome = (Finance) obj;
        sum += plusIncome.getAmount();
      }
    } else {
      for (Object obj : outcomeList.toArray()) {
        Finance plusOutcome = (Finance) obj;
        sum += plusOutcome.getAmount();
      }
    }
    return sum;
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

  private Object[] uniqueList(ArrayList list, String subTitle) {
    HashSet<Object> set = new HashSet<>();
    for (Object obj : list.toArray()) {
      Finance value = (Finance) obj;
      if (value == null)
        return null;
      if (subTitle.equals("일자별 수입 지출")) {
        set.add(value.getDate());
      } else if (subTitle.equals("항목별 수입 지출")) {
        set.add(value.getCategory());
      }
    }
    Object[] arr = set.toArray();
    Arrays.sort(arr);
    return arr;
  }
}
