package N18258;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class N18258_jinhyuk {
    public static void main(String[] args) throws IOException {

    		//Scanner 사용 시 '시간 초과'
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        //back 기능 구현 위해
        Deque<Integer> q = new ArrayDeque<>();
//		q.offer();		// push
//		q.pop();			// pop
//		q.size();		// size
//		q.isEmpty();		// empty
//		q.peek();		// front
//		q.peekLast();	// back
        
        int N = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            String command = st.nextToken(); // 첫 번째 문자열(명령어)을 가져옴

            switch (command) {
                case "push":
                    q.offer(Integer.parseInt(st.nextToken()));
                    break;
                case "pop":
                    if (q.isEmpty()) {
                        sb.append(-1).append('\n');
                    } else {
                        sb.append(q.pop()).append('\n');	//q.poll() = q.pop();
                    }
                    break;
                case "size":
                    sb.append(q.size()).append('\n');
                    break;
                case "empty":
                		if (q.isEmpty()) {
                			sb.append(1).append('\n');
                		} else {
                			sb.append(0).append('\n');
                		}
                    break;
                case "front":
                    if (q.isEmpty()) {
                        sb.append(-1).append('\n');
                    } else {
                        sb.append(q.peek()).append('\n');
                    }
                    break;
                case "back":
                    if (q.isEmpty()) {
                        sb.append(-1).append('\n');
                    } else {
                        sb.append(q.peekLast()).append('\n');
                    }
                    break;
            }
        }	//for
        
        //결과출력
        System.out.println(sb);
    }	//main
    
}

