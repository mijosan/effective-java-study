package item23;

// 태그 달린 클래스보다는 클래스 계층구조를 활용하라 !

// 단점 : 우선 열거 타입 선언, 태그 필드, switch 문등 쓸데없는 코드가 많다.
// 여러 구현이 한 클래스에 혼합돼 있어서 가독성도 나쁘다.
// 한마디로 태그 달린 클래스는 장황하고, 오류를 내기 쉽고, 비효율적이다.

// 해결법 : 클래스 계층구조 활용 (태그 달린 클래스는 클래스 계층구조를 어설프게 흉내낸 아류일 뿐)
class Figure {

    enum Shape {RECTANGLE, CIRCLE};

    // 태그 필드 - 현재 모양을 나타낸다.
    final Shape shape;

    // 다음 필드들은 모양이 사각형(RECTANGLE)일 때만 쓰인다.
    // 필드들을 final로 선언하려면 해당 의미에 쓰이지 않는 필드들까지 생성자에서 초기화해야 한다.
    double length;
    double width;

    // 다음 필드는 모양이 원(CIRCLE)일 때만 쓰인다.
    double radius;

    // 원용 생성자
    Figure(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    // 사각형용 생성자
    Figure(double length, double width) {
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    // 새로운 의미를 추가할 때마다 모든 switch 문을 찾아 새 의미를 처리하는 코드를 추가하는 번거로움이 있다.
    double area() {
        switch (shape) {
            case RECTANGLE:
                return length * width;
            case CIRCLE:
                return Math.PI * (radius * radius);
            default:
                throw new AssertionError(shape);
        }
    }

}