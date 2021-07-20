package item23;

// 계층구조의 루트가 될 추상 클래스를 정의하고, 태그 값에 따라 동작이 달라지는 메서드들을 루트 클래스의 추상 메서드로 정의 선언한다.

// 기존의 코드에 포함된 쓸데없는 코드도 모두 사라졌다.
// 각 의미를 독립된 클래스에 담아 관련 없던 데이터 필드를 모두 제거했다.
// 살아 남은 필드들은 모두 fianl이다. (태그 클래스는 관련없는 필드들도 모두 초기화를 해주어야 해서 비효율 적이다.)
// 그리고, 실수로 빼먹은 case문 때문에 런타임 오류가 날필요도 없고.
class Circle extends Figure2 {

    final double radius;

    Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double area() {
        return Math.PI * (radius * radius);
    }

}
