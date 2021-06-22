package item13;

import java.util.ArrayList;
import java.util.List;

// 멤버 변수에 변경 가능한 참조 변수를 포함하고 있다면 깊은 복사가 수행될 수 있도록 직접 구현해야 한다. 
// 여러가지 방법이 있겠으나 일반적으로 아래 코드처럼 Address 클래스에도 clone 메서드를 구현한 뒤 Member 클래스의 clone 메서드 안에서 호출하는 방식으로 구현할 수 있다. 

// clone이 최선일까?
// 지금까지 Object에서 제공하는 객체 복사 기능인 clone()의 기본적인 사용 방법과 clone이 지원하지 않는 깊은복사를 구현하는 방법에 대해 간단히 정리해 보았다. 
// 이펙티브 자바를 읽은 사람은 알겠지만 사실 객체 복사를 위해 Cloneable 인터페이스를 구현하고 clone 메서드를 사용하는 것은 장점보다는 단점이 더 많다. 
// 실제로 이렇게 정리를 해보니까 불필요한 예외처리나 형 변환 등 불편한 점을 실제로 느낄 수 있었다.
// 이펙티브 자바에서 권장하는 것 처럼, 나 역시 Cloneable 인터페이스를 구현하는 것보다는 복사 생성자나 복사 팩토리 메서드를 직접 정의해서 사용하는 게 좀 더 좋은 방법이라 생각한다.
public class DeepCopyWithClone {

    public class Member implements Cloneable {

        private String name;
        private int age;
        private Address address;

        @Override
        public Member clone() {
            try {
                Address clonedAddress = address.clone();
                Member clonedMember = (Member) super.clone();
                clonedMember.setAddress(clonedAddress);

                return clonedMember;
            } catch (CloneNotSupportedException e) {
                // Cloneable을 구현했기 때문에 이 블록이 실행되는 일은 없다.
                return null;
            }
        }

        void setAddress(Address address) {
            this.address = address;
        }

    }
    
    public class Address implements Cloneable {

        private long zipCode;
        private String city;
    
        @Override
        public Address clone() throws CloneNotSupportedException {
            return (Address) super.clone();
        }

    }

}
