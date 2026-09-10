/*
 * 오답노트
 * 1. 종료 조건 위치 고려 안함
 * 2. DFS에 넘어가는 인자 값 조심하기
 * 3. 방향(dx, dy) 잘 적기
 * 
*/
import java.util.Scanner;

public class Main {
	static int[][] arr;
	static int N;
	static int min;
	static int apple;
	//좌, 하, 우, 상 
	static int[] dx = {0, 1, 0, -1};
	static int[] dy = {1, 0, -1, 0};

	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test_case = sc.nextInt();
        
        for(int tc = 1; tc <= test_case; tc++) {
        	N = sc.nextInt();
        	arr = new int[N][N];
        	min = Integer.MAX_VALUE;
        	apple = 0;
        	for(int i = 0; i < N; i++) {
        		for(int j = 0; j < N; j++) {
        			arr[i][j] = sc.nextInt();
        			if(apple < arr[i][j]) {
        				apple = arr[i][j];
        			}
        		}
        	}
        	
        	//DFS
        	DFS(0, 1, false, 0, 0, 0);
        	
        	System.out.println("#" + tc + " " + min);
        }
    }
    
    static void DFS(int count, int now_A, boolean spin, int x, int y, int view) {
    	
    	if(count >= min) return;
    	
    	// 애플인지 확인
    	if(arr[x][y] == now_A) {
    		now_A++;
    	}
    	
    	// 종료 조건
    	if(now_A == apple) {
    		if(count < min) {
    			min = count;
    		}
    		return;
    	}
    	
    	
    	// 스핀할지
    	if(!spin) {
    		view = (view + 1) % 4;
    		DFS(count + 1, now_A, true, x, y, view);
    	}
    	
    	// 직진할지
    	int nx = x + dx[view];
    	int ny = y + dy[view];
    	if(nx >= 0 && nx < N && ny>= 0 && ny < N) {
    		DFS(count, now_A, false, nx, ny, view);
    	}
    	
    }

}
