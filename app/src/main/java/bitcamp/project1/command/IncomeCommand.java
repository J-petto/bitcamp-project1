package bitcamp.project1.command;

import bitcamp.project1.Prompt.Prompt;
import bitcamp.project1.util.ArrayList;
import bitcamp.project1.vo.Income;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class IncomeCommand {
    private final int PROCESS_LIST = 0;
    private final int PROCESS_SEARCH = 1;
    private final int PROCESS_UPDATE = 2;
    private final int PROCESS_DELETE = 3;

    private final int ACCOUNT = 0;
    private final int CASH = 1;

    ArrayList incomeList = new ArrayList();

    public void autoIncomeData(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        Income income = new Income();
        income.setKindOfCome("입금");
        income.setDate(LocalDate.parse("2024-06-27"));
        income.setCategory("월급");
        income.setAccount("현금");
        income.setAmount(500);
        income.setNo(Income.getSeqNo());
        incomeList.add(income);

        Income income1 = new Income();
        income1.setKindOfCome("입금");
        income1.setDate(LocalDate.parse("2024-06-28"));
        income1.setCategory("용돈");
        income1.setAccount("현금");
        income1.setAmount(1500);
        income1.setNo(Income.getSeqNo());
        incomeList.add(income1);

        Income income2 = new Income();
        income2.setKindOfCome("입금");
        income2.setDate(LocalDate.parse("2024-06-28"));
        income2.setCategory("월급");
        income2.setAccount("현금");
        income2.setAmount(300);
        income2.setNo(Income.getSeqNo());
        incomeList.add(income2);
    }

    public void executeIncomeCommand(String subTitle) {
        switch (subTitle) {
            case "등록":
                createIncome();
                break;
            case "목록":
                listIncome();
                break;
            case "조회":
                searchIncome();
                break;
            case "변경":
                updateIncome();
                break;
            case "삭제":
                deleteIncome();
                break;
            default:
        }
    }

    private void createIncome() {
        Income income = new Income();
        income.setKindOfCome("입금");
        income.setDate(Prompt.inputDate("입금일(yyyy-MM-dd)?"));
        income.setCategory(Prompt.input("항목 입력>"));
        income.setAccount(Prompt.input("계좌/현금 입력>"));
        income.setAmount(Prompt.inputInt("입금 금액 입력>"));
        income.setNo(Income.getSeqNo());
        incomeList.add(income);
    }

    private void listIncome() {
        System.out.println("입금/출금 날짜 항목 금액");
        printNoList(PROCESS_LIST);
    }

    private void searchIncome() {
        printNoList(PROCESS_SEARCH);
        int incomeNo = Prompt.inputInt("조회 할 입금 번호:");
        Income searchedIncome = (Income) incomeList.get(incomeList.indexOf(new Income(incomeNo)));
        if (searchedIncome == null) {
            System.out.println("없는 입금 번호입니다.");
            return;
        }
        System.out.printf("날짜 : %s\n", searchedIncome.getDate());
        System.out.printf("항목 : %s\n", searchedIncome.getCategory());
        System.out.printf("입금 방법 : %s\n", searchedIncome.getAccount());
        System.out.printf("입금 금액 : %d\n", searchedIncome.getAmount());
    }

    public void updateIncome(){
        printNoList(PROCESS_UPDATE);
        int incomeNo = Prompt.inputInt("변경 할 입금 번호:");
        Income deletedIncome = (Income) incomeList.get(incomeList.indexOf(new Income(incomeNo)));
        if (deletedIncome == null) {
            System.out.println("없는 입금 번호입니다.");
            return;
        }
        deletedIncome.setDate(Prompt.inputDate("입금일(yyyy-MM-dd)?"));
        deletedIncome.setCategory(Prompt.input("변경할 항목>"));
        deletedIncome.setAccount(Prompt.input("변경할 입금 계좌>"));
        deletedIncome.setAmount(Prompt.inputInt("변경할 입금 금액>"));
        System.out.println("변경 완료했습니다.");
    }

    private void deleteIncome() {
        printNoList(PROCESS_DELETE);
        int incomeNo = Prompt.inputInt("삭제 할 입금 번호>");
        Income deletedIncome = (Income) incomeList.get(incomeList.indexOf(new Income(incomeNo)));
        if (deletedIncome != null) {
            incomeList.remove(incomeList.indexOf(deletedIncome));
            System.out.println("삭제 완료했습니다.");
        }else {
            System.out.println("없는 입금 번호입니다.");
        }
    }

    private void printNoList(int processNo) {
        for (Object object : incomeList.toArray()) {
            Income income = (Income) object;
            switch (processNo){
                case PROCESS_UPDATE :
                case PROCESS_DELETE :
                case PROCESS_SEARCH :
                    System.out.printf("%d. ", income.getNo());
                case PROCESS_LIST :
                    System.out.printf("입금 %s %s %s\n", income.getDate(), income.getCategory(), income.getAmount());
                break;
                default:
            }

        }
    }

    public ArrayList getIncomeList() {
        return incomeList;
    }
}
