package N2064;

import java.util.*;
import java.io.*;

public class N2064_taeyoung {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// ip를 long 타입으로 변환하여 입력
		int n = Integer.parseInt(br.readLine());

		long[] ip = new long[n];

		for (int i = 0; i < n; i++) {
			String S = br.readLine();
			String[] split = S.split("\\.");

			long l = 0;

			for (int j = 0; j < 4; j++) {
				l += Long.parseLong(split[j]) << 8 * (3 - j);
			}

			ip[i] = l;
		}

		// 정렬해서 최대, 최소 찾기
		Arrays.sort(ip);

		long max = ip[n - 1];
		long min = ip[0];
		
		long full = (long) Math.pow(2, 32) - 1; // 32비트 모두 1로 채운 수

		// 공통 비트 길이 계산
		int prefix = 32 - (int)(Math.floor(Math.log(max ^ min) / Math.log(2)) + 1);
		
		// 마스크 -> 공통 부분 1로 채우고 나머지 0 
		long mask = (full << (32 - prefix)) & full;
		// 네트워크 주소 -> ip 주소와 네트워크 마스크 일치하는 부분
		long network = min & mask;

		System.out.println(ltoip(network));
		System.out.println(ltoip(mask));
	}
	
	// long타입 수를 ip 형태로 바꾸는 함수
	static String ltoip(long l) {
		long a = (l >> 24) & 255;
		long b = (l >> 16) & 255;
		long c = (l >> 8) & 255;
		long d = l & 255;
		return String.format("%d.%d.%d.%d", a, b, c, d);
	}
}
