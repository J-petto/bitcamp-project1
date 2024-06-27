package bitcamp.project1.util;

public class TotalList {
  public ArrayList income;
  public ArrayList outcome;

  public TotalList(ArrayList income, ArrayList outcome) {
    this.income = income;
    this.outcome = outcome;
  }

  public Object[] concat(){
    int incomeSize = income.size();
    int outcomeSize = outcome.size();
    Object[] result = new Object[incomeSize + outcomeSize];
    for (int i = 0; i < incomeSize; i++) {
      result[i] = income.get(i);
    }

    for (int i = 0; i < outcomeSize; i++) {
      result[incomeSize + i] = outcome.get(i);
    }

    return result;
  }
}
