package bitcamp.project1.vo;

import java.time.LocalDate;
import java.util.Objects;

public class Finance {
  private static int seqNo;
  private static int incomeNo;
  private static int outcomeNo;
  private String kindOfCome;

  private int no;
  private LocalDate date;
  private int amount;
  private int account;
  private String category;

  public Finance() {
  }

  public Finance(int no) {
    this.no = no;
  }

  public static int getSeqNo() {
    return seqNo++;
  }

  @Override
  public boolean equals(Object object) {
    if (this == object)
      return true;
    if (object == null || getClass() != object.getClass())
      return false;
    Finance income = (Finance) object;
    return no == income.no;
  }

  @Override
  public int hashCode() {
    return Objects.hash(no, date);
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = seqNo;
  }

  public int getAmount() {
    return amount;
  }

  public void setAmount(int amount) {
    this.amount = amount;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public int getAccount() {
    return account;
  }

  public void setAccount(int account) {
    this.account = account;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getKindOfCome() {
    return kindOfCome;
  }

  public void setKindOfCome(String kindOfCome) {
    this.kindOfCome = kindOfCome;
  }
}
