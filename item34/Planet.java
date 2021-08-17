package item34;

enum Planet {
    // 열거 타입 상수 각각을 특정 데이터와 연결지으려면 생성자에서 데이터를 받아 인스턴스 필드에 저장하면 된다.
    MERCURY(3.302e+23, 2.439e6),
    VENUS(4.869e+24, 6.052e6),
    EARTH(5.975e+24, 6.378e6);

    // 열거 타입은 근본적으로 불변이라 모든 필드는 final이어야 한다.
    private final double mass; // 질량
    private final double radius; // 반지름
    private final double surfaceGravity; // 표면중력

    private static final double G = 6.67300E-11;

    // 생성자에서 계산하느 이유는 단순히 최적화를 위해서이다.
    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        surfaceGravity = G * mass / (radius * radius);
    }

    public double mass() { return mass; }
    public double radius() { return radius; }
    public double surfaceGravity() { return surfaceGravity; }

    public double surfaceWeight(double mass) {
        return mass * surfaceGravity; // F = ma
    }

}

class WeightTable {

    public static void main(String[] args) {
        double earthWeight = Double.parseDouble("50");
        double mass = earthWeight / Planet.EARTH.surfaceGravity();
        for (Planet p : Planet.values()) {
            System.out.printf("%s에서의 무게는 %f이다.%n", p, p.surfaceWeight(mass)); // toString 메서드는 상수 이름을 문자열로 반환하므로 println과 printf로 출력하기에 안성맞춤이다.
        }
    }

}
