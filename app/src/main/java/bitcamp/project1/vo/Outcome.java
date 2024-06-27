package bitcamp.project1.vo;

public class Outcome {
  static final int CASH = 0;
  static final int CARD = 1;

  private static int seqNo;
  private int no;
  private String date;
  private int amount;
  private int accountType;
  private String category;

  public Outcome() {}
  public Outcome(int no) {
    this.no = no;
  }

  public static int getSeqNo() {
    return ++seqNo;
  }

  public static void setSeqNo(int seqNo) {
    Outcome.seqNo = seqNo;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public int getAccountType() {
    return accountType;
  }

  public void setAccountType(int accountType) {
    this.accountType = accountType;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}
