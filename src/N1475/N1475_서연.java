package N1475;

import java.io.*;

public class N1475_서연 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 방 번호
        String N = br.readLine();

        // 0부터 9까지 각 숫자가 몇 개 필요한지 저장할 배열 선언
        int arr[] = new int[10];
        // 방 번호 문자열의 각 자리를 순회
        for (int i = 0; i < N.length(); i++) {
            // 문자를 숫자로 변환하여 해당 숫자의 인덱스에 개수를 1 증가시킴.
            arr[(N.charAt(i) - '0')] += 1;
        }

        // 필요한 세트의 최소 개수를 저장할 변수 (가장 많이 필요한 숫자의 개수임.)
        int max = 0;
        // 6과 9의 개수를 계산하기 위한 임시 변수
        int temp = 0;

        // 0부터 9까지 모든 숫자를 확인하며 필요한 세트 수를 계산함.
        for (int i = 0; i < arr.length; i++) {
            // 6과 9는 서로 뒤집어서 사용할 수 있으므로 필요한 총 개수를 합산함.
            temp = (arr[6] + arr[9]);

            // 필요한 6, 9의 총 개수가 홀수일 경우
            if (temp % 2 == 1) {
                temp /= 2; // 2로 나눈 몫
                temp += 1; // 1을 더해 올림 처리
                arr[6] = temp; // 계산된 필요 세트 수를 6과 9의 자리에 갱신
                arr[9] = temp;
            } else { // 짝수일 경우
                temp /= 2; // 2로 나눈 몫이 그대로 필요 세트 수가 됨
                arr[6] = temp;
                arr[9] = temp;
            }

            // 현재까지의 최댓값과 i번째에 필요한 세트 수를 비교하여 더 큰 값으로 갱신함.
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(String.valueOf(max));

        bw.flush();
        bw.close();
        br.close();
    }
}