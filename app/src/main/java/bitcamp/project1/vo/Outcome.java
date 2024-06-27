package bitcamp.project1.vo;

import java.time.LocalDate;
import java.util.Objects;

public class Outcome {
  static final int CASH = 0;
  static final int CARD = 1;
  private static int seqNo;

  private int no;
  private int amount;
  private LocalDate date;
  private int accountType;
  private String category;

  public Outcome() {}
  public Outcome(int no) {
    this.no = no;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    Outcome outcome = (Outcome) o;
    return no == outcome.no;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(no);
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

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
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
