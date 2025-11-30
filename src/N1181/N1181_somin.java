package N1181;

import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class N1181_somin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();
        
        // 중복 제거를 위해
        HashSet<String> set = new HashSet<>();
        
        for (int i = 0; i < N; i++) {
            set.add(sc.next());
        }
        
        // 배열로 변환
        int size = set.size();
        String[] arr = new String[size];
        set.toArray(arr);
        
        // 정렬 
        // 짧은 것부터하고 사전 순 
        Arrays.sort(arr, new Comparator<String>() {
            public int compare(String s1, String s2) {
                if (s1.length() == s2.length()) {
                    return s1.compareTo(s2);
                } else {
                    return s1.length() - s2.length();
                }
            }
        });
        
        StringBuilder sb = new StringBuilder();
        for (String s : arr) {
            sb.append(s).append('\n');
        }
        System.out.println(sb);
    }
}