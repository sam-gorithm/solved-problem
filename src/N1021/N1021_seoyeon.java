package N1021;

import java.util.*;
import java.io.*;

public class N1021_seoyeon {

    public static void main(String[] args) throws IOException {
        // TODO Auto-generated method stub
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] findNums = new int[M];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            findNums[i] = Integer.parseInt(st.nextToken());
        } // 입력받기

        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 1; i <= N; i++) {
            list.add(i);
        }

        // 4. 2번, 3번 연산 횟수 저장
        int ansCnt = 0;

        // 뽑으려는 각 숫자를 순서대로 처리
        for (int i = 0; i < M; i++) {
            // 6. 현재 뽑으려는 숫자가 몇 번째 인덱스인지 찾기
            int num = list.indexOf(findNums[i]);

            // 7. 현재 덱의 중간 지점을 계산
            int halfSize = list.size() / 2;

            // 8. 위에서 찾은 인덱스가 중간 지점보다 앞쪽에 있는지 뒤쪽에 있는지 찾기
            if (num <= halfSize) {

                // 9. 만약 중간 지점보다 앞쪽(중간 지점 포함)이라면
                // 왼쪽으로 이동시키는 것이 최소 이동 횟수가 됨
                for (int j = 0; j < num; j++) {
                    // 왼쪽으로 이동
                    list.add(list.pollFirst());
                    // 정답 카운트 변수 증가
                    ansCnt++;
                }

            } else {
                int rightMove= list.size()-num;
                for (int j = 0; j < rightMove; j++) {

                    // 10. 만약 중간 지점보다 뒤쪽에 있다면,
                    // 오른쪽으로 이동시키는 것이 최소 이동 횟수
                    list.addFirst(list.pollLast());
                    ansCnt++;

                }
            }
            // 11. 뽑으려는 숫자가 덱의 맨 앞에 위치함
            // 앞 값 삭제
            list.pollFirst();
        }
        // 정답출력
        System.out.println(ansCnt);



    }

}