package N1158;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class N1158_seoyeon {
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력받기
        StringTokenizer st= new StringTokenizer(br.readLine());
        int N= Integer.parseInt(st.nextToken());
        int K= Integer.parseInt(st.nextToken());

        // 연결리스트 선언
        LinkedList<Integer> list= new LinkedList<>();

        // 원소 삽입
        for(int i=1; i<=N; i++) {
            list.offer(i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");

        // 제거할 사람의 인덱스
        int removeIndex = 0;

        while (!list.isEmpty()) {
            // K-1 만큼 인덱스를 이동하고 리스트 크기로 나눠줌
            removeIndex = (removeIndex + K - 1) % list.size();

            // removeIndex에 있는 사람 제거하고 결과에 추가
            int removedPerson = list.remove(removeIndex);
            sb.append(removedPerson);

            if (!list.isEmpty()) {
                sb.append(", ");
            }
        }

        sb.append(">");

        bw.write(sb.toString());
        bw.flush();
    }
}
