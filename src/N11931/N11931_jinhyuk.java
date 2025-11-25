package N11931;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

//	수 정렬하기 4 / 실버 5 / 2360ms
public class N11931_jinhyuk {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        int N = sc.nextInt();	//N : 1 ~ 1,000,000
        
        ArrayList<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            list.add(sc.nextInt());
        }//입력
        
        //내림차순 정렬
        Collections.sort(list, Collections.reverseOrder());
        
        //최대 1,000,000개 출력
        StringBuilder sb = new StringBuilder();
        
        for (int num : list) {
            sb.append(num).append('\n');
        }
        
        //결과출력
        System.out.println(sb.toString());
        
    }	//main
    
    
}	//class