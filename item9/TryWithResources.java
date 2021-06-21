package item9;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

// try-finally에서의 문제들을 try-with-resources가 깔끔하게 해결한다.
// try-with-resources를 사용하려면 해당 자원이 
// AutoCloseable 인터페이스를 구현해야 한다.
// 왜냐하면 close 메소드를 무조건 호출하기 위해 AutoCloseable를 사용하기 때문이다.
// AutoCloseable 인터페이스는 close 메서드 하나만 정의되어 있다.
// 자바 라이브러리와 서드파티 라이브러리들의 수많은 클래스와 인터페이스가
// 이미 AutoCloseable을 구현하거나 확장해뒀다. 
// 닫아야 하는 클래스를 작성한다면 AutoCloseable을 반드시 구현하여 
// try-with-resources를 사용하자.
public class TryWithResources {
    
    static String firstLineOfFile(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            return br.readLine();
        }
    }

    // 또한 try-finally보다 문제를 진단하기도 훨씬 편하다.
    // firstLineOfFile 메소드를 보자.
    // readLine과 코드에는 보이지 않는 close 모두에서 예외가 발생한다면
    // 스택 추적 내역에 readLine에서 발생한 예외가 먼저 표시된다.
    // 그리고 close에서 발생한 예외는 숨겨졌다는 꼬리표인 
    // suppressed를 달고 그 이후에 같이 출력된다. 
    // try-finally와 달리 첫 번째 예외부터 확인할 수 있는 것이다.
    // 또한 아래처럼 java 7에서 Throwble에 추가된 getSuppressed 메서드를 이용하면 
    // 프로그램 코드에서 가져올 수도 있다.
    static void copy(String src, String dst) throws IOException {
        try (InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dst)) {
            byte[] buf = new byte[3];
            int n;
            while ((n = in.read(buf)) >= 0)
                out.write(buf, 0, n);
        } catch (Throwable e) {
            Throwable[] suppExe = e.getSuppressed();

            for (int i = 0; i < suppExe.length; i++) {
                System.out.println("Suppressed Exceptions:");
                System.out.println(suppExe[i]);
            }
        }
    }

}
