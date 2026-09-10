/*
 * 
 * 1. 완탐을 돌렸기에 시간초과날 수 있었음 -> 가지치기(pruning) -> 최소값을 비교하여 초과면 조기 종료
 * 2. 자잘한 조건 설정 실수 근데 이건 걍 돌려보면 되는 부분
 * 
*/
import java.util.Scanner;

public class Main {
	
    static int N;
    static int M;
  	static int min;
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test_case = sc.nextInt();
        
        for(int tc = 0; tc < test_case; tc++) {
        	N = sc.nextInt();
        	M = sc.nextInt();
        	int K = sc.nextInt();
        	min = Integer.MAX_VALUE;
        	
        	// 백트래킹
        	backtracking(0, 0, 0, 0);
        	
        	System.out.println("#" + (tc+1) + " " + min);	
        }
    }
    
    static void backtracking(int n, int m, int temp, int counter) {
    	
    	// 가지치기(pruning)
    	if(temp >= min) return; // 최소값 초과시 종료
      if((N - n) + m < M) return; // 필요정답수 못채울 시 종료
    	
    	// 만약 끝이면 종료
    	if(n == N && m == M) {
    		if(temp < min) {
    			min = temp;
    		}
    		return;
    	}
    
      if(n == N) return; // 끝을 넘으면 종료
    	
    	// 문제를 맞췄을 때
    	if(m < M) {
    		if((counter+1) == K) {
    			backtracking(n+1, m+1, 2*(temp + 1), 0);
    		}
    		else {
    			backtracking(n+1, m+1, temp+1, counter+1);
    		}
    	}
    	
    	// 문제를 못맞췄을 때
    	backtracking(n+1, m, temp, 0);
    }
}
