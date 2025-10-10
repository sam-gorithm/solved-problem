package N2448;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N2448_geonnam {
    static char[][] map; // 별 찍을 배열

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        map = new char[n][2*n-1];
        
        //공백 초기화
        for(int i=0;i<n;i++) {
        	for(int j=0;j<2*n-1;j++) {
        		map[i][j] = ' ';
        	}
        }
        
        //가로를 정중앙으로 설정
        re(0,n-1,n);
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<n;i++) {
        	sb.append(map[i]).append("\n");
        }
        System.out.println(sb);
    }
    
    static void re(int x, int y, int n) {
    	//위로 뾰족한 삼각형
    	if (n == 3) { 
            map[x][y] = '*';
            map[x + 1][y - 1] = '*';
            map[x + 1][y + 1] = '*';
            for (int i = -2; i <= 2; i++) {
                map[x + 2][y + i] = '*';
            }
            return;
        }
    	
    	//위 꼭짓점 기준으로 생각
        int half = n / 2;
        // 위쪽 삼각형
        re(x, y, half);
        // 왼쪽 아래 삼각형
        re(x + half, y - half, half);
        // 오른쪽 아래 삼각형
        re(x + half, y + half, half);
    }
}
