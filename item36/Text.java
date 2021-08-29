package item36;

import java.util.EnumSet;
import java.util.Set;

public class Text {
    public static final int STYLE_BOLD = 1 << 0;
    public static final int STYLE_ITALIC = 1 << 1;
    public static final int STYLE_UNDERLINE = 1 << 2;
    public static final int STYLE_STRIKETHROUGH = 1 << 3;

    public void applyStyles(int styles) {
        // System.out.printf("Applying styles %s to text%n",
                // Objects.requireNonNull(styles));
    }

    // 사용 예
    public static void main(String[] args) {
        Text text = new Text();
        text.applyStyles(STYLE_BOLD | STYLE_ITALIC);
    }
}
// 이렇게 사용하는것을 비트필드라고 한다 이렇게 하는것은 집합연산을 통해서 하나의 값을 쓸려고 하는것이다.

// 이것은 구닥다리 스타일이다.

// 이것을 enumset을 이용하면 아래의 코드이다.
// 코드 36-2 EnumSet - 비트 필드를 대체하는 현대적 기법 (224쪽)
class Text2 {
    public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}

    // 어떤 Set을 넘겨도 되나, EnumSet이 가장 좋다.
    public void applyStyles(Set<Style> styles) {
        // System.out.printf("Applying styles %s to text%n",
        //         Objects.requireNonNull(styles));
    }


    // 사용 예
    public static void main(String[] args) {
        Text2 text = new Text2();
        text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
    }
}

