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

    ArrayList incomeList;
    ArrayList outcomeList;
    Object[] assetList;

    String line = "------------------------------";
    String endLine = "==============================";

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

        System.out.println(endLine);
        printFormatted("총 수입", PROGRESS_INCOME, incomeTotal, total);
        printFormatted("총 지출", PROGRESS_OUTCOME, outcomeTotal, total);
        System.out.println(line);
        printFormatted("총 계", PROGRESS_TOTAL, Math.abs(total), total);
        System.out.println(endLine);

    }

    private void viewDate(String subTitle) {
        String endLine = "================================================";
        String line = "------------------------------------------------";
        Object[] uniqueDate = uniqueList(union(), subTitle);
        System.out.println(endLine);
        System.out.printf("%-8s %9s %10s %10s\n", "날짜", "수입", "지출", "총계");
        System.out.println(line);
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
            System.out.printf("%s: %8s원 | %8s원 | %8s원\n", date.toString(), String.format("%,d", totalIncome), String.format("%,d", totalOutcome),
                    String.format("%,d", totalIncome - totalOutcome));
        }
        System.out.println(endLine);
    }

    private void viewCategory(String subTitle) {
        int incomeTotal = allSum(true);
        int outcomeTotal = allSum(false);

        System.out.println(endLine);
        printFormatted("수입총합", PROGRESS_INCOME, Math.abs(incomeTotal), incomeTotal);
        System.out.println(line);
        Object[] uniqueIncome = uniqueList(incomeList, subTitle);
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

        System.out.println(endLine);
        printFormatted("지출총합", PROGRESS_OUTCOME, Math.abs(outcomeTotal), outcomeTotal);
        System.out.println(line);

        Object[] uniqueOutcome = uniqueList(outcomeList, subTitle);
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
        System.out.println(endLine);
    }

    private void viewAsset() {
        String endLine = "================================================================";
        String line = "----------------------------------------------------------------";
        System.out.println(endLine);
        System.out.printf("%-10s %10s %10s %10s %10s\n", "구분", "잔고", "수입", "지출", "총계");
        System.out.println(line);
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
            System.out.print(wallet.getAssetType());
            for (int i = 0; i < 6 - wallet.getAssetType().length(); i++) {
                System.out.print("  ");
            }
            int allTotal = wallet.getDepositAmount() + totalIncome - totalOutcome;
            System.out.printf(" | %8s원 | %8s원 | %8s원 | %8s원 \n", String.format("%,d", wallet.getDepositAmount()),
                    String.format("%,d", totalIncome), String.format("%,d", totalOutcome), String.format("%,d", allTotal));
        }
        System.out.println(endLine);
    }

    public void printGraph(int label, int value, int total) {
        String ansiBlue = "\033[94m";
        String ansiRed = "\033[91m";
        String ansiEnd = "\033[0m";
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
        final int VALUE_MAX_LENGTH = label == PROGRESS_OUTCOME || total < 0 ? 9 : 10;
        String formattedValue = String.format("%,d", value);
        System.out.printf("%s", text);

        for (int i = 0; i < 8 - text.length(); i++) {
            System.out.print("  ");
        }
        System.out.print("|");
        for (int i = 0; i < VALUE_MAX_LENGTH - formattedValue.length(); i++) {
            System.out.print(" ");
        }
        if (label == PROGRESS_OUTCOME || total < 0) {
            System.out.printf("-%s원 ", formattedValue);
        } else {
            System.out.printf("%s원 ", formattedValue);
        }
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
        for (int i = 0; i < outcomeSize; i++) {
            result.add(outcomeList.get(i));
        }
        return result;
    }

    private Object[] uniqueList(ArrayList list, String subTitle) {
        HashSet<Object> set = new HashSet<>();
        for (Object obj : list.toArray()) {
            Finance value = (Finance) obj;
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
