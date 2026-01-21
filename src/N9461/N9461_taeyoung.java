package N9461;

import java.util.*;
import java.io.*;

public class N9461_taeyoung {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        
        long[] P = new long[101];
        P[1] = P[2] = P[3] = 1;
        P[4] = P[5] = 2;
        int idx = 6;
        
        for (int t = 0; t < T; t++) {
        	int x = Integer.parseInt(br.readLine());
        	if (x >= idx) {
        		for (int i = idx; i <= x; i++) {
        			P[i] = P[i-1] + P[i-5];
        		}
        		idx = x + 1;
        	}
        	System.out.println(P[x]);
        }
    }
}
