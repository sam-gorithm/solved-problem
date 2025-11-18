package N10814;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

//	나이순 정렬 / 실버 5 / 1024ms
public class N10814_jinhyuk {
	//static
	//Perosn 클래스
	public static class Person {
		int age;
		String name;
		
		//생성자
		public Person(int age, String name) {
			this.age = age;
			this.name = name;
		}

		//toString
		@Override
		public String toString() {
			return age + " " + name + "\n";
		}
		
		
	}	//Person
	
	
	//main
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		Person[] p = new Person[N];
		for(int i=0; i<N; i++) {
			int age = sc.nextInt();
			String name = sc.next();
			p[i] = new Person(age, name);
		}//입력
		
		//Person 객체배열 정렬
		Arrays.sort(p, new Comparator<Person>() {
			
			@Override
			public int compare(Person o1, Person o2) {
				return o1.age - o2.age;
			}
			
		});//Person 객체배열 정렬
		
		//1 ~ 100,000개 출력
		StringBuilder sb = new StringBuilder();
		
		for(int i=0; i<N; i++) {
			//Person 객체 toString 출력 형식에 맞게 오버라이딩한 상태
			sb.append(p[i]);
		}
		
		//결과출력
		System.out.println(sb);

	}	//main

	
}	//class