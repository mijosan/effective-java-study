package itme18;

import java.util.Collection;
import java.util.HashSet;

// 상속보다는 컴포지션을 사용하라 !

// 같은 패키지에서의 상속이라면 안전한 방법이지만, 다른 패키지의 구체 클래스를 상속하는 일은 위험하다.
// 메서드 호출과 달리 상속은 캡슐화를 깨뜨린다. (상위 클래스는 릴리스마다 내부 구현이 달라질 수 있어, 하위 클래스의 동작에 이상이 생길 수 있다.)
// 클라이언트가 노출된 내부에 직접 접근할수 있다는 점.
// 다른 문제는 접어 두더라도 사용자를 혼란케 한다.
// (p.getProperties와 p.get 전자는 기본 동작 후자는 부모 클래스로부터 물려받은 메서드)
// 가장 심각한 문제는 클라이언트에서 상위 클래스를 직접 수정하여 하위 클래스의 불변식을 해칠 수 있다는 사실

// 마지막으로, 확장하려는 클래스의 API에 아무런 결함이 없어야 한다.
// 아니면 이러한 결함으로 여러 클래스의 API에 전파된다.
// 컴포지션으로는 이런 결함을 숨기는 새로운 API가 설계가 가능 !
// 상속은 상위 클래스의 API를 그 결함까지도 그대로 승계한다.
public class Inheritance<E> extends HashSet<E> {
    
    // 추가된 원소의 수
    private int addCount = 0;

    public Inheritance() {
    }

    public Inheritance(int initCap, float loadFactor) {
        super(initCap, loadFactor);
    }

    @Override
    public boolean add(E e) {
        addCount++;
        return super.add(e);
    }

    // 내부적으로 addAll 메서드는 add 메서드를 사용하고 있다.
    // 이런 내부 구현 방식은 HashSet 문서에는 (당연히) 쓰여 있지 않다.
    // 이 경우 하위 클래스에서 addAll 메서드를 재정의하지 않으면 문제가 고쳐진다. (제대로된 addCount값을 위해)
    // 그러나, 당장은 작동할지 몰라도 다음 릴리스에서는 구현 방식이 바뀔수 있다.
    // 또한, 다른 메서드가 추가되어 카운트를 셀수 없게 될수도 있다.
    // 또 다른 방법이 있는데 메서드를 자기가 새롭게 추가하는것이다. 그러나, 운 없게도 하필 다음 릴리스에서 새롭게 추가한 메서드와 시그니처가 같고 반환 타입은 다르다면 컴파일 조차 되지 않는다. 혹여나 반환타입이 같다고해도 위에서 발생한 문제가 또 발생한다는것이다.
    // 아무튼 문제가 엄청 많다... 그래서 컴포지션을 사용하는것이다.
    // 컴포지션 : 기존 클래스가 새로운 클래스의 구성요소로 쓰인다는 뜻에서 이러한 설계를 컴포지션(composition: 구성)이라 한다.
    // 새 클래스의 인스턴스 메서드들은 기존 클래스의 대응하는 메서드를 호출해 그 결과를 반환한다.
    // 이방식을 전달(forwarding)이라 하며, 새 클래스의 메서드들을 전달 메서드(forwarding method)라 부른다.
    // 그 결과 새로운 클래스는 기존 클래스의 내부 구현 방식의 영향에서 벗어나며, 심지어 기존 클래스에 새로운 메서드가 추가되더라도 전혀 영향받지 않는다.
    // 
    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }

}
