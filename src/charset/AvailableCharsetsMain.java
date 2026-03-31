package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.SortedMap;

public class AvailableCharsetsMain {

    public static void main(String[] args) {
        // 이용 가능한 모든 Charset [자바 제공 + OS 제공 모두 합쳐서]
        SortedMap<String, Charset> charsets = Charset.availableCharsets();  // Charset : 문자 집합
        for (String charsetName : charsets.keySet()) {
            System.out.println("charsetName = " + charsetName);
        }

        System.out.println("=====");
        // 문자로 조회(대소문자 구분X), MS949, ms949, x-windows-949
        Charset charset1 = Charset.forName("MS949");    // Charset 에서 내가 원하는 이름으로 꺼낸다.
        System.out.println("charset1 = " + charset1);   // x-windows-949 가 출력되는 이유는, MS949 의 FULL 네임이 x-windows-949 이다.

        // 별칭 조회
        Set<String> aliases = charset1.aliases();   // x-windows-949 의 별칭들을 조회.
        for (String alias : aliases) {
            System.out.println("alias = " + alias);
        }

        // UTF-8 문자로 조회
        Charset charset2 = Charset.forName("UTF-8");
        System.out.println("charset2 = " + charset2);

        // UTF-8 상수로 조회
        Charset charset3 = StandardCharsets.UTF_8;  // UTF-8 은 대표적으로 사용하는 인코딩이므로, java 에서 상수로 지정해놓았다.
        System.out.println("charset3 = " + charset3);

        // 시스템의 기본 Charset 조회
        // 개발하다보면 인코딩을 지정하지 않고 문서를 불러오기, 저장 할 때가 있는데, 이 경우 이 시스템에 기본으로 인코딩이 지정이 되게 함.
        Charset defaultCharset = Charset.defaultCharset();
        System.out.println("defaultCharset = " + defaultCharset);   // 보통 UTF-8 이 지정되지만, PC 환경마다 다를 수 있다.
    }
}
