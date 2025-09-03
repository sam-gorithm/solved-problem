package N1158;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class N1158_geonnam {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        
        LinkedList<Integer> list = new LinkedList<>();
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int c = 0;
        
        for(int i=1;i<=n;i++) {
        	list.add(i);
        }
        
        ListIterator<Integer> cursor = list.listIterator();
        for (int i = 0; i < x; i++) {
        	if(!cursor.hasNext()) {
        		cursor = list.listIterator();
        	}
            c = cursor.next();
        }
        sb.append(c);
        cursor.remove();
        
        while(list.size() != 0) {
        	for(int i=0;i<x;i++) {
        		if(!cursor.hasNext()) {
            		cursor = list.listIterator();
            	}
        		c = cursor.next();
        	}
        	sb.append(", "+c);
        	cursor.remove();
        }
        
        sb.append(">");
        bw.write(sb.toString());
        bw.flush();
    }
}
