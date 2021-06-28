package item16;

// public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라

// 1. API를 수정하지 않고 내부 표현을 바꿀수 있다.
// 2. private 접근제한자를 사용 하기 때문에 불변식을 보장할 수 있다.
// 3. 외부에서 필드에 접근 할때 부수 작업을 수행 할 수 있다.

// package-private 클래스 혹은 private 중첩 클래스라면 데이터 필드를 노출한다 해도 하등의 문제가 없다.
class Point {

    // public 클래스의 필드가 불변이라면 직접 노출할 때는 단점이 조금은 줄어 들지만
    // 여전히 결코 좋은 생각이 아니다. API를 변경하지 않고는 표현 방식을 바꿀 수 없고
    // 필드를 읽을 때 부수 작업을 수행할 수 없다는 단점은 여전하다. (직접 접근 하기 떄문에)
    // 단 불변식은 보장 할 수 있게 된다.
    public final double z;
    public final double d;

    private double x;
    private double y;

    public Point(double x, double y, double z, double d) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.d = d;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

}
