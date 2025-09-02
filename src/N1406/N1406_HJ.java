package N1406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class N1406_HJ {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String N = br.readLine();
		
		
		List<Character> input = new LinkedList<>();
		
		for (int i = 0; i < N.length(); i++) {
			input.add(N.charAt(i));
		}
		
		// 커서 초기화 -> 문장 맨 마지막
		// Linked Iterator 사용
		
		ListIterator<Character> cursor = input.listIterator();
		while(cursor.hasNext()) {
			cursor.next();
		}
		
		// 명령어의 개수
		int C = Integer.parseInt(br.readLine());
		
//		 명령어의 개수만큼 반복
		for (int i = 0; i < C; i++) {
			// 명령어의 종류는 L, D, B, P
			String command = br.readLine();
			
			switch (command.charAt(0)) {
			case 'L': {	// 커서를 왼쪽으로 옮김
				if (cursor.hasPrevious()) cursor.previous(); 
				break;
			}
			case 'D': {	// 커서를 오른쪽으로 옮김
				if (cursor.hasNext()) cursor.next();
				break;
			}
			case 'B' : {	// 커서 왼쪽에 있는 문자를 삭제
				if (cursor.hasPrevious()) {
					cursor.previous();
					cursor.remove();
				}
				break;
			}
			case 'P' : {	// 뒤에 오는 문자를 왼쪽에 추가
				char newInput = command.charAt(2);
				cursor.add(newInput);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + command);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (Character character : input) {
			sb.append(character);
		}
		System.out.println(sb.toString());
		
	}

}
