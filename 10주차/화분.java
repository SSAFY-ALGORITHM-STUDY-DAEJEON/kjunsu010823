import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int P;
	static int[][] h;
	static int[] arr;
	static int max;
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());
            max = 0;

            h = new int[2][N];
            for (int k = 0; k < 2; k++) {
                st = new StringTokenizer(br.readLine());
                for (int i = 0; i < N; i++) {
                    h[k][i] = Integer.parseInt(st.nextToken());
                }
            }
            
            dfs(0, 0, 0);
            
            System.out.println("#" + tc + " " + max);
        }
    }
    
    static void dfs(int idx, int result, int grow) {
    	if(idx == N) {
    		if(result > max) {
    			max = result;
    		}
    		return;
    	}
    	// 비료 1 선택할 때
    	if(grow == 1) {
    		dfs(idx + 1, result + h[0][idx] - P, 1);
    	}
    	else {
    		dfs(idx + 1, result + h[0][idx], 1);
    	}
    	// 비료 2 선택할 때
    	if(grow == 2) {
    		dfs(idx + 1, result + h[1][idx] - P, 2);
    	}
    	else {
    		dfs(idx + 1, result + h[1][idx], 2);
    	}
    }
}
