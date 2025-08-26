import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String N = sc.next();

        // 0부터 9까지의 숫자가 몇 번 사용되는지 세기 위한 배열
        int[] num = new int[10];

        // 입력된 방 번호의 각 자릿수를 순회
        for (char c : N.toCharArray()) {
            int digit = c - '0';  // 문자를 숫자로 변환

            // 6과 9는 뒤집어서 사용할 수 있으므로 
            if (digit == 6 || digit == 9) {
                num[6]++;
            } else {
                num[digit]++;
            }
        }

        // 6과 9를 합쳐서 센 값이므로 2로 나누고 올림 처리
        num[6] = (num[6] + 1) / 2;

        // 배열에서 가장 큰 값 (필요한 세트 수)
        int max = 0;
        for (int i = 0; i < 9; i++) {  // 0부터 8까지 검사 (9는 6에 포함)
            if (num[i] > max) {
                max = num[i];
            }
        }

        System.out.println(max);
    }
}