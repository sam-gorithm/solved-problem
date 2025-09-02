package N1158;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class N1158_HJ {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] inputs = br.readLine().split(" ");
        int N = Integer.parseInt(inputs[0]);
        int K = Integer.parseInt(inputs[1]);
        
		List<Integer> people = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			people.add(i);
		}
		List<Integer> result = new ArrayList<>();
		
		int idx = 0;
		while(!people.isEmpty()) {
			idx = (idx + K - 1) % people.size();
			result.add(people.remove(idx));
		}
		System.out.println(result.toString().replace("[", "<").replace("]", ">"));
	}

}
