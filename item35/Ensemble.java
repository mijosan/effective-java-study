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

// Enum class란?

// 우리가 흔히 상수를 정의할 때 final static string 과 같은 방식으로 상수를 정의를합니다. 하지만 이렇게 상수를 정의해서 코딩하는 경우 다양한 문제가 발생됩니다. 
// 따라서 이러한 문제점들을 보완하기 위해 자바 1.5버전부터 새롭게 추가된 것이 바로 "Enum" 입니다.
// Enum은 열거형이라고 불리며, 서로 연관된 상수들의 집합을 의미합니다.
// 기존에 상수를 정의하는 방법이였던 final static string 과 같이 문자열이나 숫자들을 나타내는 기본자료형의 값을 enum을 이용해서 같은 효과를 낼 수 있습니다.



// Enum의 장점

// Enum을 사용하면서 우리가 얻을 수 있는 이점은 다음과 같습니다.
// 1. 코드가 단순해지며, 가독성이 좋습니다.
// 2. 인스턴스 생성과 상속을 방지하여 상수값의 타입안정성이 보장됩니다.
// 3. enum class를 사용해 새로운 상수들의 타입을 정의함으로 정의한 타입이외의 타입을 가진 데이터값을 컴파일시 체크한다.
// 4. 키워드 enum을 사용하기 때문에 구현의 의도가 열거임을 분명하게 알 수 있습니다.
