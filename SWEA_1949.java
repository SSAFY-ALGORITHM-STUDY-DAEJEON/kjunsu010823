/*

오답노트
1. 방문 여부 확인을 누락함 (!visited[nx][ny])
2. 재귀 호출 시에 공사 기회(k)를 하드 코딩함
3. 백트래킹 미숙 -> 갈림길에서 백트래킹 할 때 반드시 초기화를 해야하는데
나는 안하고 전체 초기화만함

*/

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    static int N;
    static int K;
    static int highest;
    static int[][] map;
    static boolean[][] visited;
    static int max_length;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            N = sc.nextInt();
            K = sc.nextInt();
            map = new int[N][N];
            highest = 0;
            max_length = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    map[i][j] = sc.nextInt();
                    if(map[i][j] > highest){
                        highest = map[i][j];
                    }
                }
            }
            
            // 제일 높은곳에서 깍기 시작하기
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(map[i][j] == highest){
                        visited = new boolean[N][N];
                        dfs(i, j, 0, true);
                    }
                }
            }
            
            System.out.println("#" + test_case + " " + max_length);
		}
	}
    
    // K 깍는 거 있어야함
    public static void dfs(int x, int y, int count, boolean k){
        count++;
        visited[x][y] = true;
        
        for(int i = 0; i < 4; i++){
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            // 이동할 수 있으면
        	if(nx >= 0 && nx < N && ny >= 0 && ny < N && map[nx][ny] < map[x][y] && !visited[nx][ny]){
                dfs(nx, ny, count, k);
        	}
        	// 이동할 수 없으면
        	else if(nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]){
                for(int j = 1; j <= K; j++){
                    if(map[nx][ny] - j < map[x][y] && k){
                        map[nx][ny] -= j;
                        dfs(nx, ny, count, false);
                        map[nx][ny] += j;
                    }
                }
                if(max_length < count){
                    max_length = count;
                }
            }
            
            if(max_length < count){
                max_length = count;
            }
        }
        visited[x][y] = false;
    }
}
