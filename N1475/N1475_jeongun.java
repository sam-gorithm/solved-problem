package BOJ_1475;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] count = new int [10];
        String str = sc.next();

        for(int i = 0; i < str.length(); i++) {
            int num = str.charAt(i) - '0'; //문자 뽑고 정수 변환 ..

            if(num == 9) {//6, 9 -> 공통
                num = 6;
            }
            count[num]++; //num 발견 시 count 6, 9는 같은 몸 ..
        }
        int common = (count[6]+1)/2; //6, 9 공통값 처리
        int result = 0;

        for(int i = 0; i < 10; i++) {
            if (i == 6) {
                result = Math.max(result, common); //count->6 이면 common과 최종값 비교 ..
            } else {
                result = Math.max(result, count[i]);
            }
        }
        System.out.println(result);
    }
}