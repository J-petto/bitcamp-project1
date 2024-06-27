package bitcamp.project1.util;

// 데이터 목록을 다루는 일을 할 객체의 사용법
// -> 즉, '그 객체에게 일을 시킬 때 다음의 메서드를 호출하여 일을 시켜라' 라고 지정하는 문법
public interface List {
    // 데이터를 더할 때 호출할 메서드
    // private 하면 안돼? 규칙은 공개적이여야함
    // public을 안붙이는건 default 아닌가? abstract는 기본 public임

    public abstract void add(Object value); // 규칙이기 때문에 메서드의 시그니처만 정의한다.
    abstract Object remove(int index);
    public Object get(int index);
    Object[] toArray();
    int indexOf(Object obj);

    int size();
}
