import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	static int min;
	static int M;
	static boolean[][] visited;
	static int[] arr;
	
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            min = Integer.MAX_VALUE;
            M = 0;
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] > 0 || map[i][j] < 0) {
                    	M++;
                    	visited[i][j] = true;
                    }
                }
            }
            arr = new int[M];
            
            
            // 순열로 풀기
            // 몬스터 처치와 고객집 가기 순서를 순열로
          
            perm(0);
          
            System.out.println("#" + tc + " " + min);
        }
    }
    
    static void perm(int idx) {
    	
    	// 기저 조건 + 계산
    	if(idx == M) {
    		cal();
    		return;
    	}
    	
    	//순열 템플릿
    	for(int i = 0; i < N; i++) {
    		for(int j = 0; j < N; j++) {
    			if(!visited[i][j]) {
    				continue;
    			}
    			
    			visited[i][j] = false;
    			arr[idx] = map[i][j];
    			perm(idx + 1);
    			visited[i][j] = true;
          
    		}
    	}
    }
    
    static void cal() {
    	// 먼저 몬스터 처리 순서와 고객집 방문 순서가 맞는지 확인
    	// 그다음 계산하고 min값과 비교
    	int cnt = 0;
    	for(int i = 0; i < M; i++) {
    		if(arr[i] > 0) {
    			for(int j = M - 1; j > i; j--) {
        			if(arr[i] == -arr[j]) {
        				cnt++;
        			}
        		}
    		}
    	}
    	if(cnt != M / 2) {
    		return;
    	}
    	
    	int temp = 0;
    	int x = 0;
    	int y = 0;
    	
    	for (int k = 0; k < M; k++) {
    		// 가지치기
    		boolean found = false;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == arr[k]) {
                        temp += Math.abs(x - i) + Math.abs(y - j);
                        x = i;
                        y = j;
                        found = true;
                    }
                }
                if(found) {
                	break;
                }
            }
        }
    	
    	if(min > temp) {
    		min = temp;
    	}
    	
    }
}
