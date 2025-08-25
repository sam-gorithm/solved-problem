package N2577;

import java.io.*;

public class N2577_seoyeon {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력 값
        int a = Integer.parseInt(br.readLine());
        int b = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());

        // 세 수의 곱을 계산하여 변수 a에 다시 저장함.
        a = a * b * c;

        // 계산된 결과를 문자열로 변환함.
        String input = String.valueOf(a);

        // 0부터 9까지 숫자가 몇 번 쓰였는지 개수를 저장할 배열을 선언함.
        int[] arr = new int[10];

        // 곱셈 결과 문자열의 길이만큼 반복문을 실행
        for(int i = 0; i < input.length(); i++) {
            // input.charAt(i)는 i번째 자리의 문자를 가져옴.
            // 문자에 '0'을 빼면 해당 문자의 실제 숫자 값을 얻을 수 있음.
            // 얻어낸 숫자를 인덱스로 사용하여 배열의 1 증가함.
            arr[input.charAt(i) - '0'] += 1;
        }

        // 배열의 모든 요소를 순회함.
        for(int n : arr) {
            bw.write(n + "\n");
        }

        // 출력
        bw.flush();
    }
}