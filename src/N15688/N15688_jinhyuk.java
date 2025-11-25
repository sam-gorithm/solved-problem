package N15688;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

//	수 정렬하기 5 / 실버 5 / 16944ms
public class N15688_jinhyuk {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());	//N : 1 ~ 1,000,000
        
        ArrayList<Integer> list = new ArrayList<>();
        
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }//입력
        
        //비내림차순 정렬
        Collections.sort(list);
        
        //최대 1,000,000개 출력
        StringBuilder sb = new StringBuilder();
        
        for (int num : list) {
            sb.append(num).append('\n');
        }
        
        //결과출력
        System.out.println(sb.toString());
        
    }	//main
    
    
}	//class