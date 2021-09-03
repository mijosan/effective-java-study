package item50;

import java.util.Date;

// 적시에 방어적 복사본을 만들라

// 클라이언트가 여러분의 불변식을 깨드리려 혈안이 되어 있다고 가정하고 방어적으로 프로그래밍해야 한다.
// 목적이 불변 객체를 만들기 위해서만은 아니다. 메서드든 생성자든 클라이언트가 제공한 객체의 참조를 내부의 자료구조에 보관해야 할 때면
// 그 객체가 잠재적으로 변경될 수 있는지를 생각해야 한다.
// 확신할 수 없다면 복사본을 만들어 저장해야 한다. (배열도 마찬가지)

// 이상의 모든 작업에서 우리는 되도록 불변 객체들을 조합해 객체를 구성해야 방어적 복사를 할 일이 줄어든다는 교혼을 얻을 수 있다.
// Period예제의 경우 자바 8이상으로 개발해도 된다면 Instant(혹은 LocalDateTime이나 ZonedDateTime)를 사용하라.

// 방어적 복사에는 성능 저하가 따르고, 또 항상 쓸수 있는 것도 아니다.
// 방어적 복사를 생략해도 되는 상황은 해당 클래스와 그 클라이언트가 상호 신뢰할 수 있을 때, 혹은 불변식이 깨지더라도 그영향이 오직 호출한 클라이언트로 국한될 때로 한정해야한다.
public class Period {
    
    private final Date start;
    private final Date end;

    // Before
    // public Period(Date start, Date end) {
    //     if (start.compareTo(end) > 0) {
    //         throw new IllegalArgumentException();
    //     }

    //     this.start = start;
    //     this.end = end;
    // }
    
    // After
    // 이번 아이템은 예전에 작성된 낡은 코드들을 대처하기 위한 것이다.
    // 생성자에서 받은 가변 매개변수 각각을 방어적으로 복사해야 한다.
    // 그런 다음에는 Period 인스턴스 안에서는 원본이 아닌 복사본을 사용한다.
    public Period(Date start, Date end) {
        // 방어적 복사를 매개변수 유효성 검사 전에 수행하면 위험에서 해방될 수있다.

        // 매개변수가 제3자에 의해 확장될 수 있는 타입이라면 방어적 복사본을 만들 때 clone을 사용해서는 안 된다.
        this.start = new Date(start.getTime());
        this.end = new Date(end.getTime());

        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException();
        }
    }

    // 두 번째 공격을 막아내려면 단순히 접근자가 가변 필드의 방어적 복사본을 반환하면 된다.
    // 새로운 접근자까지 갖추면 Period는 완벽한 불변으로 거듭난다.
    // 아무리 악의 적인 혹은 부주의한 프로그래머라도 시작 시각이 종료 시각보다 나중일 수 없다는 불변식을 위배할 방법은 없다. (네이티브 메서드나 리플렉션 같이 언어 외적인 수단을 동원하지 않고는)
    // 모든 필드가 객체 안에 완벽하게 캡슐화되었다.
    // 생서자와 달리 clone을 사용해도 된다. Period가 가지고 있는 Date는 java.util.Date임이 확실하기 때문
    public Date start() {
        return new Date(start.getTime());
    }

    public Date end() {
        return new Date(end.getTime());
    }

    public static void main(String[] args) {
        // 첫번째 공격 !
        // 얼핏 이 클래스는 불변처럼 보이고, 시작 시각이 종료 시각보다 늦을 수 없다는 불변식이 무리 없이 지켜질 것 같다.
        // 하지만 Date가 가변이라는 사실을 이용하면 어렵지 않게 그 불변식을 깨드릴 수 있다.
        Date start = new Date();
        Date end = new Date();
        Period p = new Period(start, end);
        end.setYear(78);

        // 자바 8 이후로는 Date 대신 불변인 Instant를 사용하면 된다.(혹은 LocalDateTime이나 ZonedDateTime)
        // Date는 낡은 API이니 새로운 코드를 작성할 때는 더 이상 사용하면 안된다.

        // 두번째 공격 !
        Date start1 = new Date();
        Date end1 = new Date();
        Period p2 = new Period(start1, end1);
        p2.end().setYear(78); // p의 내부를 변경했다 !
    }

}
