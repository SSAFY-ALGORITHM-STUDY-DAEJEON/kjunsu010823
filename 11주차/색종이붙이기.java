import java.io.*;
import java.util.*;

public class Main {
    static int[][] map = new int[10][10];
    static int[] paper = {0, 5, 5, 5, 5, 5}; // 1~5 크기별 남은 색종이 수
    static int ans = Integer.MAX_VALUE;
    static boolean ok; // fill로 색종이를 붙였는지

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        for (int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 풀이 작성
        
        backtrack(0, 0, 0);

        if (ans == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
        
    }
    
    static void backtrack(int x ,int y, int total) {
    	// 마지막 행까지 다 보고 나서 종료
    	if(x == 10) {
    		if(ans > total) {
    			ans = total;
    		}
    		return;
    	}
    	// 같아도 더 볼 필요 없음
    	if(ans <= total) {
    		return;
    	}
    	
    	// 만약 0이면 넘어가기
    	if(map[x][y] == 0) {
    		if(y + 1 < 10) {
    			backtrack(x, y + 1, total);
    		}
    		else {
    			backtrack(x + 1, 0, total);
    		}
    		return;
    	}
    	
    	// 1이면 색종이 붙이기
    	if(map[x][y] == 1) {
    		int K = 5;
    		while(K > 0) {
    			// K 색종이가 남아있으면 붙여보기
    			if(paper[K] > 0) {
    				fill(x, y, K);
    				
    				// 붙였으면 다음 칸으로
    				if(ok) {
    					paper[K]--;
    					
    					if(y + K < 10) {
    						backtrack(x, y + K, total + 1);
    					}
    					else {
    						backtrack(x + 1, 0, total + 1);
    					}
    					
    					// 색종이 떼기 (0 -> 1)
    					for(int i = x; i < x + K; i++) {
    						for(int j = y; j < y + K; j++) {
    							map[i][j] = 1;
    						}
    					}
    					paper[K]++;
    				}
    			}
    			// 다음 크기로
    			K--;
    		}
    	}
    }
    
    // (x, y)부터 K x K 색종이가 덮이면 0으로 채우기
    static void fill(int x, int y, int K) {
    	ok = false;
    	// 범위 밖이면 못 놓음
    	if(x + K > 10 || y + K > 10) {
    		return;
    	}
    	// map에 0값이 있으면 못 놓음
    	for(int i = x; i < x + K; i++) {
    		for(int j = y; j < y + K; j++) {
    			if(map[i][j] == 0) {
    				return;
    			}
    		}
    	}
    	// 색종이 붙이기 (1 -> 0)
    	for(int i = x; i < x + K; i++) {
    		for(int j = y; j < y + K; j++) {
    			map[i][j] = 0;
    		}
    	}
    	ok = true;
    }
}
