import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

public class N3190_jeongun {
    static int N, K, L;
    static int arr[][];
    static int[] t;
    static char[] dir;
    static int dr[] = {0, 1, 0, -1};
    static int dc[] = {1, 0, -1, 0};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new int[N][N];
        K = sc.nextInt();

        //빈칸 0 사과 1 뱀 2
        for (int i = 0; i < K; i++) {
            int r = sc.nextInt();
            int c = sc.nextInt();
            arr[r-1][c-1] = 1;
        }

        L = sc.nextInt();
        t = new int[L];
        dir = new char[L];
        for(int i = 0; i < L; i++) {
            t[i] = sc.nextInt();
            dir[i] = sc.next().charAt(0);
        }

        System.out.println(snake());
    }
    static int snake() {
        Queue<int[]> q = new ArrayDeque<>();
        int startR = 0;
        int startC = 0;
        q.offer(new int[] {startR,startC});
        arr[startR][startC] = 2; //뱀 2

        int k = 0;
        int time = 0;
        int idx = 0;

        while(true) { //조건 걸어서 끝내기
            time++;
            int nr = startR + dr[k];
            int nc = startC + dc[k];

            //자기 몸 마주쳐도 break
            if(nr < 0 || nc < 0 || nr >= N || nc >= N || arr[nr][nc] == 2){
                break;
            }
            if(arr[nr][nc] == 1) {
                arr[nr][nc] = 2;
                q.offer(new int[] {nr,nc});

            } else { //사과 없을 경우 큐에 넣으면 길이 늘어나니까 꼬리 자르고 빈칸으로 만들기
                arr[nr][nc] = 2;
                q.offer(new int[]{nr,nc});
                int[] tail = q.poll();
                arr[tail[0]][tail[1]] = 0;
            }
            startR = nr;
            startC = nc;

            //회전
            if(idx < L && t[idx] == time) {
                if(dir[idx] =='L') { //좌회전
                    k = (k+3)%4;
                } else { //우회전
                    k = (k+1)%4;

                }
                idx++; //다음 명령 ㄱㄱ
            }
        }
        return time;
    }
}