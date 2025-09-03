package N5397;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.ListIterator;

public class N5397_geonnam {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int t = Integer.parseInt(br.readLine()); //테스트 케이스 수
        
        for(int tc=0;tc<t;tc++) {
        	String text = br.readLine();
        	LinkedList<Character> list = new LinkedList<>(); //문자열 삽입, 삭제를 위해 LinkedList
        	//int cursor로 구현하니까 시간 초과 남
        	ListIterator<Character> cursor = list.listIterator();//노드 포인터와 같은 역할
        	
        	
        	for(int i=0;i<text.length();i++) {
        		char c = text.charAt(i);
        		
        		if(c == '<' && cursor.hasPrevious()) { //현재 커서 위치 전에 값이 있는지 확인
        			cursor.previous();
        		}else if(c == '>' && cursor.hasNext()) { // 커서 다음에 값이 있는지 확인
        			cursor.next();
        		}else if(c >= 'A' && c <= 'z' || c >= '0' && c <= '9') {
        			cursor.add(c);
        		}else if(c == '-' && cursor.hasPrevious()) { // 애초에 글자만 넣어놨으니까 cursor 전 값이 있다면 지우기
        			if(cursor.hasPrevious()) {
        				cursor.previous(); // 지금 커서 위치에는 값이 없으니까 한 칸 전으로
        				cursor.remove();
        			}
        		}
        	}
        	
        	//출력 방식때문에 자꾸 시간초과
        	StringBuilder sb = new StringBuilder();
        	for (Character ch : list) {
				sb.append(ch);
			}
        	
        	bw.write(sb.toString());
        	bw.write("\n");
        }
        bw.flush();
    }
}
