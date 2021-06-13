package item3;

// 해당 클래스가 싱글턴임이 API에 명백히 드러난다.
// public static 필드가 final이니 절대로 다른 객체를 참조할 수 없다.
// 간결하다.
public class Elvis {

    transient public static final Elvis INSTANCE = new Elvis();

	private Elvis() {
        
        // 리플렉션 API인 AccessibleObject.setAccessible을 사용해 private 생성자를 호출할 수 있다.
        // 리플렉션 API: java.lang.reflect, class 객체가 주어지면, 해당 클래스의 인스턴스를 생성하거나 메소드를 호출하거나, 필드에 접근할 수 있다.
        if (INSTANCE != null) {
            throw new RuntimeException("생성자를 호출할 수 없습니다.");
        }
    };

    // 각 클래스를 직렬화한 후 역직렬화할 때 새로운 인스턴스를 만들어서 반환한다.
    // 역직렬화는 기본 생성자를 호출하지 않고 값을 복사해서 새로운 인스턴스를 반환한다. 그때 통하는게 readResolve() 메서드이다.
    // 이를 방지하기 위해 readResolve 에서 싱글턴 인스턴스를 반환하고, 모든 필드에 transient(직렬화 제외) 키워드를 넣는다.
    private Object readResolve() {
        return INSTANCE;
    }

}
