package N18258;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class N18258_geonnam {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        ArrayDeque<Integer> q = new ArrayDeque<>(); //큐 선언

        int n = Integer.parseInt(br.readLine());

        for(int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String first = st.nextToken();

            switch (first) { // 명령어 비교
                case "push":
                    int second = Integer.parseInt(st.nextToken());
                    q.offer(second);
                    break;
                case "pop":
                    if(!q.isEmpty()) {
                        sb.append(q.pollFirst()).append("\n");
                    }else {
                        sb.append(-1).append("\n");
                    }
                    break;
                case "size":
                    sb.append(q.size()).append("\n");
                    break;
                case "empty":
                    if(q.isEmpty()) {
                        sb.append(1).append("\n");
                    }else {
                        sb.append(0).append("\n");
                    }
                    break;
                case "front":
                    if(!q.isEmpty()) {
                        sb.append(q.peekFirst()).append("\n");
                    }else {
                        sb.append(-1).append("\n");
                    }
                    break;
                case "back":
                    if(!q.isEmpty()) {
                        sb.append(q.peekLast()).append("\n");
                    }else {
                        sb.append(-1).append("\n");
                    }
                    break;
                default:
                    break;
            }
        }
        System.out.println(sb);
    }
}
