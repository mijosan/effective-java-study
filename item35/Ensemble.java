package item35;

// ordinal 메서드 대신 인스턴스 필드를 사용하라 !

// 모든 열거 타입은 해당 상수가 그 열거 타입에서 몇 번째 위치인지를 반환하는 ordinal 이라는 메서드를 제공한다.
public enum Ensemble {

    SOLO, DUET, TRIO, QUARTET, QUINTET,
    SEXTET, SEPTET, OCTET, NONET, DECTET;

    // 동작은 하지만 유지보수하기가 끔직한 코드다.
    // 상수 선언 순서를 바꾸는 순간 numberOfMusicians가 오작동하며, 이미 사용 중인 정수와
    // 값이 같은 상수는 추가할 방법이 없다.
    // 예컨대 8중주(octet) 상수가 이미 있으니 똑같이 8명이 연주하는 복 4중주는 추가할 수 없다.
    // 또한 값을 중간에 비워둘 수도 없다. (쓰지않는 더미 상수를 같이 추가해야함)
    // 코드가 깔끔하지 못할 뿐 아니라, 쓰이지 않는 값이 많아질수록 실용성이 떨어진다
    public int numberOfMusicians() {
        return ordinal() + 1;
    }

}

enum Ensemble2 {
    // 해결법 !
    // 열거 타입 상수에 연결된 값은 ordinal 메서드로 얻지 말고, 인스턴스 필드에 저장하자
    SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
    SEXTET(6), SEPTET(7), OCTET(8), NONET(9), DECTET(10);

    private final int numberOfMusicians;
    Ensemble2(int size) {
        numberOfMusicians = size;
    }
    public int numberOfMusicians() {
        return numberOfMusicians;
    }

}
