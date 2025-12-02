package N1181;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

//	단어 정렬 / 실버 5 / 744ms
public class N1181_jinhyuk {
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();

        String[] words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = sc.next();
        }//입력

        //정렬
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                //단어 길이가 같으면 => 사전 순 정렬 (a, b, c ...)
                if (s1.length() == s2.length()) {
                    return s1.compareTo(s2);
                }
                //단어 길이가 다르면 => 길이가 짧은 단어부터
                else {
                    return s1.length() - s2.length();
                }
            }
        });	//정렬

        //결과출력
        //첫 번째는 무조건 출력
        System.out.println(words[0]);
        //두 번째부터는 이전 단어와 다를때만 출력
        for (int i = 1; i < N; i++) {
            if (!words[i].equals(words[i - 1])) {
                System.out.println(words[i]);
            }
        }
        
    }	//main
    
    
}	//class