package item3;

// Elvis 타입의 인스턴스는 INSTANCE 하나뿐. 더이상 만들 수 없다.
// 복잡한 직렬화 상황이나 리플렉션 공격에서도 제2의 인스턴스가 생기는 일을 완벽히 막아준다.
// 최선의 방법이다.
// 단, 만들려는 싱글턴이 Enum 이외의 다른 상위 클래스를 상속해야 한다면 이 방법은 사용할 수 없다.
public enum Elvis3 {

    INSTANCE;
	
	public String getName() {
		return "Elvis3";
	}

}
