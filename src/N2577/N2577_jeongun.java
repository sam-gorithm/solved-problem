import java.util.Scanner;

public class N2577_jeongun {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();

        int result = A*B*C;
        String str = String.valueOf(result); //문자로 변환
        int[] count = new int[10];

        //0 ~ 9 숫자 각각 몇 번 썼는지 출력
        for(int i = 0; i < str.length(); i++) {
            char c = str.charAt(i); //문자 뽑고
            int num = c - '0'; //숫자로 변환
            count[num]++; //num 발견 시 count
        }
        for(int i = 0; i < count.length; i++) {
            System.out.println(count[i]);
        }
    }
}
