package itme18;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

// 재사용할 수 있는 전달 클래스
// HashSet의 모든 기능을 정의한 Set 인터페이스를 활용해 설계되어 견고하고 아주 유연하다.
// Set의 인스턴스를 인수로 받는 생성자를 하나 제공한다.
// 임의의 Set에 계측 기능을 덧씌워 새로운 Set 으로 만드는 것이 이 클래스의 핵심이다.
// 상속 방식은 구체 클래스 각각을 따로 확장해야 하며, 지원하고 싶은 상위 클래스의 생성자 각각에 대응하는 생성자를 별도로 정의 해줘야 한다.
// 하지만 지금 선보인 컴포지션 방식은 한 번만 구현해두면 어떠한 Set 구현체라도 계측할 수 있으며, 기존 생성자들과도 함께 사용할 수 있다.
// 다른 Set 인스턴스를 감싸고 있다는 뜻에서 이러한 클래스와 같은 클래스를 래퍼 클래스라 하며,
// 다른 Set에 계측 기능을 덧씌운다는 뜻에서 데코레이션 패턴이라고 한다.
// 래퍼 클래스는 단점이 거의 없다. 한 가지, 래퍼 클래스가 콜백 프레임워크와는 어울리지 않는 다는 점만 주의하면 된다.
// 전달 메서드를 작성하는게 지루하겠지만, 재사용할 수 있는 전달 클래스를 인터페이스당 하나씩만 만들어 두면 원하는 기능을 덧씌우는 전달 클래스들을 아주 손쉽게 구현할 수 있다.
// 상속은 반드시 하위 클래스가 상위 클래스의 '진짜' 하위 타입인 상황에서만 쓰여야 한다.

// 래퍼 클래스이자 컴포지션 이용 (래퍼 클래스로 구현할 적당한 인터페이스가 있다면 무조건 쓰자)
// 기존의 Set인터페이스를 구현한 클래스들의 기능도 하면서 추가적인 기능 추가 가능 (컴포지션을 사용해서 가능함)
public class ForwardingSet<E> implements Set<E> { // Set을 wrap : 즉 래퍼 클래스라 부름
    
    private final Set<E> s; // 컴포지션 : 구성
    public ForwardingSet(Set<E> s) { this.s = s; }

    public void clear()               { s.clear();            }
    public boolean contains(Object o) { return s.contains(o); }
    public boolean isEmpty()          { return s.isEmpty();   }
    public int size()                 { return s.size();      }
    public Iterator<E> iterator()     { return s.iterator();  }
    public boolean add(E e)           { return s.add(e);      }
    public boolean remove(Object o)   { return s.remove(o);   }
    public boolean containsAll(Collection<?> c) { return s.containsAll(c); }
    public boolean addAll(Collection<? extends E> c){ return s.addAll(c);      }
    public boolean removeAll(Collection<?> c){ return s.removeAll(c);   }
    public boolean retainAll(Collection<?> c){ return s.retainAll(c);   }
    public Object[] toArray()          { return s.toArray();  }
    public <T> T[] toArray(T[] a)      { return s.toArray(a); }
    @Override public boolean equals(Object o){ return s.equals(o);  }
    @Override public int hashCode()    { return s.hashCode(); }
    @Override public String toString() { return s.toString(); }

}
