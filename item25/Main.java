package item25;
// 톱레벨 클래스는한 파일에 하나만 담으라

// 문제점
// 소스 파일 하나에 여러개의 톱레벨 클래스를 선언함으로 컴파일 에러는 발생하지 않지만 예상치 못한 결과가 나타날 수 있다. 컴파일러에 어느 소스 파일을 먼저 건네느냐에 따라 동작이 달라지기 때문이다.

// 소스 파일 하나에는 반드시 톱레벨 클래스(인터페이스) 를 하나만 담자.
// 이 규칙만 따른다면 컴파일러가 한 클래스에 대한 정의를 여러 개 만들어 내는 일은 사라진다.
// 소스 파일을 어떤 순서로 컴파일하든 바이너리 파일이나 프로그램의 동작이 달라지는 일은 결코 일어나지 않을 것이다.
public class Main {
    public static void main(String[] args) {
        System.out.println(Utensil.NAME + Dessert.NAME);
    }
}

// 해결 1 : 서로 다른 소스 파일로 분리
// 해결 2 : 정적 멤버 클래스를 사용한 예제
// 해결 2의 장점 : 다른 클래스에 딸린 부차적인 클래스라면 정적 멤버 클래스로 만드는 쪽이 일반적으로 더 나을 것이다.
// 읽기 좋고 private으로 선언하면 접근 범위도 최소로 관리할 수 있기 때문
class Main2 {
    public static void main(String[] args) {
        System.out.println(Utensil.NAME + Dessert.NAME);
    }

    private static class Utensil {
        static final String NAME = "pan";
    }

    private static class Dessert {
        static final String NAME = "cake";
    }

}
