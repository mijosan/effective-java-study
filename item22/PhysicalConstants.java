package item22;

// 인터페이스는 타입을 정의하는 용도로만 사용하라.

// 인터페이스는 자신을 구현한 클래스의 인스턴스를 참조할 수 있는 타입 역할을 한다.
// 달리 말해, 클래스가 어떤 인터페이스를 구현한다는 것은 자신의 인스턴스로 무엇을 할 수 있는지
// 클라이언트에 얘기해주는 것이다.

// 이 상수들을 사용하려는 클래스에서는 정규화된 이름을 쓰는걸 피하고자 그 인터페이스를 구현하곤한다.
// 상수 인터페이스 안티패턴은 인터페이스를 잘못 사용한 예다.
// 클래스 내부에서 사용하는 상수** 는 외부 인터페이스가 아니라 내부 구현에 해당한다.
// 따라서 *상수 인터페이스를 구현하는 것은 이 내부 구현을 클래스의 API로 노출하는 행위다.*
// 클래스가 어떤 상수 인터페이스를 사용하든 사용자에게는 아무런 의미가 없다.
// 문제점 : *오히려 사용자에게 혼란을 주기도 하며*, *더 심하게는 클라이언트 코드가 내부 구현에 해당하는 이 상수들에 종속되게 된다.*
// *final이 아닌 클래스가 상수 인터페이스를 구현한다면 모든 하위 클래스의 이름 공간이 그 인터페이스가 정의한
// 상수들로 오염되어 버린다.(자식 클래스까지 피해가감)*
// *이 상수들을 쓰지 않게 되더라도 바이너리 호환성을 위해 여전히 상수 인터페이스를 구현하고 있어야 한다.*
public interface PhysicalConstants {
    
    static final double AVOGADROS_NUMBER = 6.022_140_857e23;

    static final double BOLTZMANN_CONSTANT = 1.380_648_52e-23;

    static final double ELECTRON_MASS = 9.109_383_56e-31;

}

// *상수를 공개할 목적* 이라면 특정 클래스나 인터페이스와 강하게 연관된 상수라면 그 클래스나 인터페이스 자체에 추가 해야한다.
// 모든 숫자 기본 타입의 박싱 클래스가 대표적으로, Integer와 Double에 선언된 MIN_VALUE와 MAX_VALUE 상수가 이런 예이다.
// 열거 타입으로 나타내기적합한 상수라면 열거 타입으로 만들어 공개하면 된다.

// 인스턴스화할 수 없는 유틸리티 클래스에 담아 공개하자.
class PhysicalConstants2 {

    private PhysicalConstants2() { }  // 인스턴스화 방지 (상속 방지) => interface 안티 패턴의 문제점 해결

    // 아보가드로 수 (1/몰)
    public static final double AVOGADROS_NUMBER = 6.022_140_857e23;

    // 볼츠만 상수 (J/K)
    public static final double BOLTZMANN_CONST  = 1.380_648_52e-23;

    // 전자 질량 (kg)
    public static final double ELECTRON_MASS    = 9.109_383_56e-31;

}

// 유틸리티 클래스에 정의된 상수를 클라이언트에서 사용하려면 클래스 이름까지 함께 명시해야 한다.
// 유틸리티 클래스의 상수를 빈번히 사용하려면 정적 임포트 하여 클래스 이름은 생략할 수 있다.
class Test {
    double atoms(double mols) {
        return PhysicalConstants2.AVOGADROS_NUMBER * mols;
    }
}
