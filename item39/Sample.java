// 명명 패턴보다 애너테이션을 사용하라

// 명명 패턴의 담점
// 1. 오타가 나면 안 된다. 실수로 이름을 tsetSafety로 지으면 JUnit 3은 이 메서드를 무시하고 지나치기 때문에 개발자는 이테스트가 통과했다고 오해할 수 있다.
// 2. 올바른 프로그램 요소에서만 사용되리라 보증할 방법이 없다는 것이다. ex) 클래스 이름을 TestSafety로 지어 JUnit에 던져줬다고 해보자. 개발자는 이 클래스에 정의된 테스트 메서드들을 수행해주길 기대하겠지만 개발자가 의도한 테스트는 전혀 수행되지 않는다.
// 3. 프로그램 요소를 매개변수로 전달할 마땅한 방법이 없다는 것이다. 기대하는 예외 타입을 테스트에 매개변수로 전달해야 하는 상황이다. 예외의 이름을 테스트 메서드 이름에 덧붙이는 방법도 있지만, 보기도 나쁘고 깨지기도 쉽다.
public class Sample {
   @Test
   public static void m1() {}

   @Test
   public static void m3() {
        throw new RuntimeException("실패");
   }

   public void m4() {}

   @Test
   public void m5() {} // 잘못 사용한 예: 정적 메서드가 아니다.

   @Test
   public static void m7() {
        throw new RuntimeException("실패");
   }

   public static void m8() {}

}