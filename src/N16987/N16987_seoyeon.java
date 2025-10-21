package N16987;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N16987_seoyeon {
    static int N;
    static int[] S; // 내구도
    static int[] W; // 무게
    static int maxBrokenCount = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        S = new int[N];
        W = new int[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            S[i] = Integer.parseInt(st.nextToken());
            W[i] = Integer.parseInt(st.nextToken());
        }

        solve(0); // 0번

        System.out.println(maxBrokenCount);
    }

    public static void solve(int index) {
        // Base Case
        if (index == N) {
            int brokenCount = 0;
            for (int i = 0; i < N; i++) {
                if (S[i] <= 0) {
                    brokenCount++;
                }
            }
            maxBrokenCount = Math.max(maxBrokenCount, brokenCount);
            return;
        }

        //현재 손에 든 계란이 이미 깨져있다면 다음 계란으로 넘어감
        if (S[index] <= 0) {
            solve(index + 1);
            return;
        }

        boolean allOtherEggsBroken = true;
        for (int i = 0; i < N; i++) {
            // 자기 자신이 아니고 깨지지 않은 다른 계란이 있는지 확인
            if (i != index && S[i] > 0) {
                allOtherEggsBroken = false;
                break;
            }
        }

        // 이 경우의 깨진 계란 수를 계산하고 종료해야 함.
        if (allOtherEggsBroken) {
            solve(N);
            return;
        }


        // 깨지지 않은 다른 계란들을 하나씩 쳐봄
        for (int target = 0; target < N; target++) {
            // 자기 자신을 치거나 이미 깨진 계란은 칠 수 없음
            if (index == target || S[target] <= 0) {
                continue;
            }

            // 계란을 침
            S[index] -= W[target];
            S[target] -= W[index];

            // 다음 계란으로 넘어감
            solve(index + 1);

            S[index] += W[target];
            S[target] += W[index];
        }
    }
}
