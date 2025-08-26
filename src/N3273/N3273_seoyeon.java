package N3273;

import java.util.*;
import java.io.*;

public class N3273_seoyeon {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력받기
        int N = Integer.parseInt(br.readLine());

        // 크기가 N인 배열 선언
        int[] nArr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            // 배열 저장
            nArr[i] = Integer.parseInt(st.nextToken());
        }

        // 투 포인터 알고리즘을 사용하기 위해 배열을 오름차순으로 정렬
        Arrays.sort(nArr);

        // 세 번째 줄에서 목표 합계를 입력받음
        int targetNum = Integer.parseInt(br.readLine());

        // 포인터 초기화
        int start = 0; // 배열의 시작 인덱스
        int end = nArr.length - 1; // 배열의 끝 인덱스
        int sum = 0; // 두 포인터가 가리키는 값의 합을 저장할 변수
        int count = 0; // 합이 targetNum과 일치하는 쌍의 개수를 셀 변수

        // start 포인터가 end 포인터보다 앞에 있는 동안 반복
        while (start < end) {
            // 현재 start와 end 포인터가 가리키는 두 수의 합을 계산
            sum = nArr[start] + nArr[end];

            // 계산된 합이 목표 값과 일치하는지 확인
            if (sum == targetNum) {
                count++; // 일치하면 카운트 증가
            }

            // 포인터 이동 로직
            if (targetNum > sum) {
                // 합이 목표 값보다 작으면, 더 큰 수가 필요하므로 start 포인터를 오른쪽으로 한 칸 이동
                start += 1;
            } else {
                // 합이 목표 값보다 크거나 같으면, 더 작은 수가 필요하므로 end 포인터를 왼쪽으로 한 칸 이동
                // sum == targetNum일 때도 실행되어 end가 이동
                end -= 1;
            }
        }

        System.out.println(count);
    }
}
