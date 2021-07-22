package item24;

// 멤버 클래스는 되도록 static으로 만들라

// 중첩 클래스란 다른 클래스 안에 정의된 클래스를 말한다.
// 중첩 클래스는 자신을 감싼 바깥 클래스에서만 쓰여야 하며, 그 외의 쓰임새가 있다면 톱레벨 클래스로 만들어야 한다.

public class MySet {
    
    static String name = "태산";

    public static void main(String[] args) {
        System.out.println(MySet.name);
    }
}
