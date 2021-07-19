package item21;

// 인터페이스는 구현하는 쪽을 생각해 설계하라

// 디폴트 메서드를 재정의하지 않은 모든 클래스에서 디폴트 구현이 쓰이게 된다.
// 그러나, 모든 기존 구현체들과 매끄럽게 연동되리라는 보장은 없다.
// 디폴트 메서드는 구현 클래스에 대해 아무것도 모른 채 합의 없이 무작정 "삽입"될 뿐이다.
// 생각할 수 있는 모든 상황에서 불변식을 해치지 않는 디폴트 메서드를 작성하기란 어려운 법이다.
// 인터페이스를 설계 할 때는 여전히 세심한 주의를 기울여야 한다.
// 따라서, 여러분도 서로 다른 방식으로 최소한 세 가지는 구현해봐야 한다.
public class DefaultMethod {

    // 위 코드를 보면 Predicate가 true를 반환하는 모든 원소를 제거한다. 코드를 이보다 더 범용적으로 구현하기도 어렵겠지만, 그렇다고 해서 현존하는 모든 Collection 구현체와 잘 어우어지는 것은 아니다.
    // 이의 대표적은 예가 org.apache.commons.collections.collection.SynchronizedCollecion이다. 이는  클라이언트가 제공한 객체로 락을 거는 능력을 추가로 제공한다. 즉, 모든 메서드에서 주어진 락 객체로 동기화한 후 내부 컬렉션 객체에 기능을 위임하는 래퍼 클래스이다.
    // 그러나 removeIf에서는 동기화에 관해 아무것도 모르므로 락 객체를 사용할 수 없다. 그 결과 멀티 스레드의 환경에서 removeIf를 호출할 경우 ConcurrentModificationException이 발생하거나 다른 예기치 못한 결과로 이어질 수 있다.
    // 이처럼 디폴트 메서드는 컴파일에 성공하더라도 기존 구현체에 런타임 오류를 일으킬 수 있다. 그러므로 인터페이스를 설계할 때는 여전히 세심한 주의를 기울여야 한다.
    default boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        boolean removed = false;
        final Iterator<E> each = iterator();
        while (each.hasNext()) {
            if (filter.test(each.next())) {
                each.remove();
                removed = true;
            }
        }
        return removed;
    }

}
// 자바 8 부터 인터페이스에는 디폴트 메서드가 도입되었고, 이를 통해 기존 인터페이스에 새로운 메서드를 추가할 수 있게 되었다.
// * 그러나 디폴트 메서드를 통해 새로운 메서드를 추가하더라도 모든 기존 구현체들과 매끄럽게 연동되지 않을 수 있다.
// * 따라서 인터페이스를 설계할 때는 세심한 주의를 기울여야 한다. 그리고 인터페이스를 수정하는 것이 가능한 경우도 있겠지만, 절대 그 가능성에 기대해서는 안 된다.
