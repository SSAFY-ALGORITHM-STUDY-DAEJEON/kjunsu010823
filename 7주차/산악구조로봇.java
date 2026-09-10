/*
 * 오답노트
 * 1. 타입 잘못 적는 실수
 * 2. 문제 조건 잘못 적은 실수
 * 
*/
import java.util.Scanner;

public class Main {
	static int[][] arr;
	static int N;
	static int min;
	static boolean[][] visited;
	//상하좌우
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test_case = sc.nextInt();
        
        for(int tc = 1; tc <= test_case; tc++) {
        	N = sc.nextInt();
        	arr = new int[N][N];
        	visited= new boolean[N][N];
        	min = Integer.MAX_VALUE;
        	for(int i = 0; i < N; i++) {
        		for(int j = 0; j < N; j++) {
        			arr[i][j] = sc.nextInt();
        		}
        	}
        	
        	//DFS 
        	visited[0][0] = true;
        	DFS(0, 0, 0);
        	
        	System.out.println("#" + tc + " " + min);
        }
    }
    
    static void DFS(int fuel, int x, int y) {
      // 가지치기
      if (fuel >= min) {
        return;
      }
    	
    	// 종료 조건
    	if(x == N - 1 && y == N - 1) {
    		if(fuel < min) {
    			min = fuel;
    		}
    		return;
    	}
    	
    	//상하좌우 이동
    	for(int i = 0; i < 4; i++) {
    		int nx = x + dx[i];
    		int ny = y + dy[i];
    		if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
    			// 높이가 같은 곳 이동 -> 연료 1
    			if(arr[nx][ny] == arr[x][y]) {
    				visited[nx][ny] = true;
    				DFS(fuel + 1, nx, ny);
    				visited[nx][ny] = false;
    			}
    			
    			// 높이가 높은 곳 이동 -> 높이차의 두배
    			if(arr[nx][ny] > arr[x][y]) {
    				visited[nx][ny] = true;
    				DFS(fuel + 2*(arr[nx][ny] - arr[x][y]), nx, ny);
    				visited[nx][ny] = false;
    			}
    			
    			// 높이가 낲은 곳 이동 -> 연료 X
    			if(arr[nx][ny] < arr[x][y]) {
    				visited[nx][ny] = true;
    				DFS(fuel, nx, ny);
    				visited[nx][ny] = false;
    			}
    		}
    	}
    }
}
