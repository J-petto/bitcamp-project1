package bitcamp.project1.vo;

import org.checkerframework.checker.index.qual.UpperBoundUnknown;

import java.util.Objects;

public class Income {
  private static int seqNo;

  private int no;
  private int amount;
  private String date;
  private String account;
  private String category;

  @Override
  public boolean equals(Object object) {
    if (this == object) return true;
    if (object == null || getClass() != object.getClass()) return false;
    Income income = (Income) object;
    return no == income.no;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(no);
  }

  public Income(){

  }

  public Income(int no){
    this.no = no;
  }

  public static int getSeqNo() {
    return seqNo++;
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

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }
}
