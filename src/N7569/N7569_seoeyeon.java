package N7569;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class N7569_seoeyeon {

    static int M, N, H;
    static int[][][] box;
    static int ansDay; // 정답 카운트 변수
    static int[] dr = {-1, 1, 0, 0, 0, 0};
    static int[] dc = {0, 0, -1, 1, 0, 0};
    static int[] dh = {0, 0, 0, 0, -1, 1};
    static Deque<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        box = new int[H][N][M];

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < M; k++) {
                    box[i][j][k] = Integer.parseInt(st.nextToken());
                }
            }

        } // 입력 완료

        queue = new ArrayDeque<>();
        ansDay=0;

        // 시작점 탐색 (익은 토마토의 좌표는 모두 큐에 넣기
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    if(box[i][j][k]==1){
                        queue.add(new int[]{i, j, k});
                    }
                }
            }
        } // 시작점 탐색
        bfs();

        // 정답 출력 로직
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < M; k++) {
                    // 토마토가 익지 않았을 경우
                    if(box[i][j][k]==0){
                        ansDay= -1;
                        bw.write(ansDay+"");
                        bw.flush();
                        return;
                    }
                    // 토마토가 익었을 경우 (처음부터 익었을 경우도 여기에 포함됨.)
                    else{
                        ansDay= Math.max(ansDay, box[i][j][k]);
                    }
                }
            }
        }
        bw.write(ansDay-1+"");
        bw.flush();

    } // main

    private static void bfs() {
        while(!queue.isEmpty()){
            int[] curr= queue.remove();
            int h= curr[0];
            int r= curr[1];
            int c= curr[2];

            // 6방향 탐색
            for(int i=0; i<dr.length; i++){
                int nh= h+dh[i];
                int nr= r+dr[i];
                int nc= c+dc[i];

                // 배열 넘어갔는지 확인
                if(nh<0 || nh>=H || nr<0|| nr>=N||nc<0||nc>=M){
                    continue;
                }

                // -1이 거나 1이상이면 이미 새어진 것이므로 넘어감
                if(box[nh][nr][nc]==-1||box[nh][nr][nc]>=1){
                    continue;
                }

                // 탐색
                box[nh][nr][nc]= box[h][r][c]+1;
                queue.add(new int[]{nh, nr, nc});

            } // 탐색
        } // 비어있는지 확인
    }// bfs

}// class

// {{{}}}, {{{}}}
