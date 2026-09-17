import java.util.*;
import java.io.*;

public class Solution {
	static int N;
	static ArrayList<Integer>[] list;
	static int max;
	
	public static void main(String args[]) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine().trim());
		
		
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine().trim());
			list = new ArrayList[N];
			for(int i = 0; i < N; i++) {
				list[i] = new ArrayList<>();
			}
			max = -1;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				int count = Integer.parseInt(st.nextToken());
				for(int j = 0; j < count; j++) {
					list[i].add(Integer.parseInt(st.nextToken()));
				}
			}
			
			bruteforce();
			
			System.out.println("#" + test_case + " " + max);
		}
	}
	static void bruteforce() {
		
		// 1학기 때 선수과목 없는거 다듣기 -> 그다음 선수과목 다듣기 -> 완료 시 max 비교
		int count = 0;
		ArrayList<Integer> target = new ArrayList<>();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < list[i].size(); j++) {
				if(list[i].get(j) == 0) {
					target.add(i);
				}
			}
		}
		
		// target에서부터 시작하기
		
		
		
		
		
		if(count > max) {
			max = count;
		}
		
		
		
		
	}
}
