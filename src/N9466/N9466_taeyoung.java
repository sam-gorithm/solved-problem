package N9466;

import java.io.*;
import java.util.*;

public class N9466_taeyoung {
    static int[] checked; // 0: 방문 안함, 1: 방문 중(DFS 경로 안에 있음), 2: 방문 완료(사이클 여부 판단 끝)
    static int[] A;
    static int n, cnt;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            n = Integer.parseInt(br.readLine());
            A = new int[n + 1]; // A[i] : i가 함께하고 싶은 학생
            checked = new int[n + 1]; // 방문 체크

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }
            cnt = 0; // 팀을 이룬 학생 수

            for (int i = 1; i <= n; i++) {
                if (checked[i] == 0) { // 아직 방문 안한 노드만
                    dfs(i);
                }
            }
            System.out.println(n - cnt); // 팀에 속하지 않는 학생 수
        }
    }

    // 사이클 여부 확인 위한 DFS
    static void dfs(int cur) {
        checked[cur] = 1; // 현재 노드 방문 중 처리(현재 DFS 경로에 포함)

        int next = A[cur]; // 다음 노드 확인하기

        if (checked[next] == 0) { // 다음 노드 방문 안했다면
            dfs(next); // DFS 재귀 호출
        } else if (checked[next] == 1) { // 다음 노드 현재 방문 중이라면 -> 사이클(팀)
            // 사이클 몇 명으로 이루어졌는지 확인 위함
            while(next != cur) { // 현재 노드랑 같아질 때 까지
                cnt++; // 학생 수 1 증가
                next = A[next]; // 다음 노드
            }
            cnt++; // 현재 노드까지 포함해야 함
        }
        checked[cur] = 2; // 방문 완료 처리
    }
}
