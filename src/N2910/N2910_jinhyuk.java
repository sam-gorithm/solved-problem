package N2910;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

//	빈도 정렬 / 실버 3 / 168ms
public class N2910_jinhyuk {
    public static void main(String[] args) {
    	
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int c = sc.nextInt();

        HashMap<Integer, Integer> map = new HashMap<>();	//<숫자, 횟수>
        ArrayList<Integer> list = new ArrayList<>();	    //입력순서 기억

        //입력
        for (int i = 0; i < n; i++) {
        	//처음나왔으면 리스트에 추가
            int num = sc.nextInt();
            if (!map.containsKey(num)) {
                list.add(num);
            }
            
            //빈도수 추가
            map.put(num, map.getOrDefault(num, 0) + 1);	//맵에서 num 유무 => value : 0
        }//입력

        //정렬
        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
            	
                int freq1 = map.get(o1);
                int freq2 = map.get(o2);
                return freq2 - freq1;	//빈도수 큰 수가 리스트 앞으로 오도록 내림차순 정렬
                
                //Collections.sort 안정정렬이라 빈도수 같으면 입력순서대로 정렬 유지
            }
        });	//정렬

        //결과출력
        for (int num : list) {
            int count = map.get(num);
            for (int i = 0; i < count; i++) {
                System.out.print(num + " ");
            }
        }
        
    }	//main
    
    
}	//class