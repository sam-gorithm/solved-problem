package N11651;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

//	좌표 정렬하기 / 실버 5 / 1132ms
public class N11651_jinhyuk {
	//static
	//Point 클래스
	public static class Point {
		int x;
		int y;
		
		//생성자
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		//toString
		@Override
		public String toString() {
			return x + " " + y + "\n";
		}
		
	}	//Point
	
	
	//main
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		Point[] p = new Point[N];
		
		for(int i=0; i<N; i++) {
			int x = sc.nextInt();
			int y = sc.nextInt();
			p[i] = new Point(x, y);
		}//입력
		
		//Point 객체배열 정렬
		Arrays.sort(p, new Comparator<Point>() {
			
			@Override
			public int compare(Point o1, Point o2) {
				if(o1.y == o2.y) {
					//x좌표가 같으면 y좌표 기준 오름차순 정렬
					return o1.x - o2.x;
				} else {
					return o1.y - o2.y;
				}
			}
			
		});//Point 객체배열 정렬
		
		//1 ~ 100,000개 출력
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			//Point 객체 toString 출력 형식에 맞게 오버라이딩한 상태
			sb.append(p[i]);
		}
		
		//결과출력
		System.out.println(sb);
		
	}	//main
	
	
}	//class
