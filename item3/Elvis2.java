package item3;

import java.io.Serializable;

// API를 바꾸지 않고도 싱글턴이 아니게 변경할 수 있다.
// (getInstance()호출부 수정 없이 내부에서 private static 이 아닌 새 인스턴스를 생성해주면 된다)
// 유일한 인스턴스를 반환하던 팩터리 메서드가 호출하는 스레드 별로 다른 인스턴스를 넘겨주게 할 수 있다.

// 원한다면 정적 팩터리를 제네릭 싱글턴 팩터리로 만들 수 있다.

// 정적 팩터리의 메서드 참조를 공급자(supplier)로 사용할 수 있다.
// Supplier: get메서드 만을 가지고 아무 type이나 리턴할 수 있는 인터페이스.
public class Elvis2 implements Serializable {

    transient private static final Elvis2 INSTANCE = new Elvis2();

    private Elvis2() {
        // 리플렉션 API인 AccessibleObject.setAccessible을 사용해 private 생성자를 호출할 수 있다.
        // 리플렉션 API: java.lang.reflect, class 객체가 주어지면, 해당 클래스의 인스턴스를 생성하거나 메소드를 호출하거나, 필드에 접근할 수 있다.
        if (INSTANCE != null) {
            throw new RuntimeException("생성자를 호출할 수 없습니다.");
        }
    };

	public static Elvis2 getInstance() { 
        return INSTANCE; 
    }

    // 각 클래스를 직렬화한 후 역직렬화할 때 새로운 인스턴스를 만들어서 반환한다.
    // 역직렬화는 기본 생성자를 호출하지 않고 값을 복사해서 새로운 인스턴스를 반환한다. 그때 통하는게 readResolve() 메서드이다.
    // 이를 방지하기 위해 readResolve 에서 싱글턴 인스턴스를 반환하고, 모든 필드에 transient(직렬화 제외) 키워드를 넣는다.
	private Object readResolve() {
        return INSTANCE;
    }

}