package item23;

// 예컨대 태그 클래스는 정사각형도 지원하도록 수정하려면 어디어디를 고쳐야 할지 각자 확인해보자..
// 클래스 계층구조에서라면 다음과 같이 정사각형이 사각형의 특별한 형태임을 아주 간단하게 반영할 수 있다.
class Square extends Rectangle {
    Square(double side) {
        super(side, side);
    }
}
