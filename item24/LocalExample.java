package item24;
// 지역 클래스
// 지역 클래스는 지역변수를 선언할 수 있는 곳이라면 어디서든 선언할 수 있다.
// 그에 따라 유효 범위도 지역변수와 같다.

// 다른 중첩 클래스들의 공통점을 하나씩 가지고 있는데

// 멤버 클래스처럼 이름을 가질 수 있고 반복해서 사용할 수 있다.
// 비정적 문맥에서 사용될 때만 바깥 인스턴스를 참조할 수 있다.
// 정적 멤버는 가질 수 없으며, 가독성을 위해 짧게(10줄이하)로 작성되어야 한다.
public class LocalExample {
    private int number;

    public LocalExample(int number) {
        this.number = number;
    }

    public void foo() {
        // 지역변수처럼 선언해서 사용할 수 있다.
        class LocalClass {
            private String name;

            public LocalClass(String name) {
                this.name = name;
            }

            public void print() {
                // 비정적 문맥에선 바깥 인스턴스를 참조 할 수 있다.
                System.out.println(number + name);
            }

        }

        LocalClass localClass = new LocalClass("local");

        localClass.print();
    }
}

// 중첩 클래스가 한 메서드 안에서만 사용되며 해당 인스턴스를 생성하는 지점이 단 한곳이고 해당 타입으로 쓰기에 적합한 클래스나 인터페이스가 있다면 익명클래스로 만들고, 아니면 지역 클래스로 만들자.