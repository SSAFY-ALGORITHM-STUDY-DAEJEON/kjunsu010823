/*

1. 조건의 순서를 고려해서 괄호 쳤어야함

*/

import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();

		for(int test_case = 1; test_case <= T; test_case++)
		{
            N = sc.nextInt();
            M = sc.nextInt();
            int R = sc.nextInt();
            int C = sc.nextInt();
            int L = sc.nextInt() - 1;
            int total = 0;
            
            map = new int[N][M];
            visited = new boolean[N][M];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    map[i][j] = sc.nextInt();
                }
            }
            
            // visited로 방문기록
            dfs(R, C, L);
            
            // 방문한 갯수 구해서 total에 저장
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(visited[i][j]){
                        total++;
                    }
                }
            }
            
            System.out.println("#" + test_case + " " + total);
		}
	}
    
    static void dfs(int x, int y, int count){
        visited[x][y] = true;
        if(count == 0){
            return;
        }
       
        //4방향 탐색
        for(int i = 0; i < 4; i++){
            int nx= x + dx[i];
            int ny= y + dy[i];
            if(nx >= 0 && ny >= 0 && nx < N && ny < M){
                // 1. 시작 통로 조건 충족 2. 종료 통료 조건 충족
                if(i == 0 && (map[x][y] == 1 || map[x][y] == 2 || map[x][y] == 4 || map[x][y] == 7)){
                    if(map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 5 || map[nx][ny] == 6){
                        dfs(nx, ny, (count - 1));
                    }
                }
                else if(i == 1 && (map[x][y] == 1 || map[x][y] == 2 || map[x][y] == 5 || map[x][y] == 6)){
                    if(map[nx][ny] == 1 || map[nx][ny] == 2 || map[nx][ny] == 4 || map[nx][ny] == 7){
                        dfs(nx, ny, (count - 1));
                    }
                }
                else if(i == 2 && (map[x][y] == 1 || map[x][y] == 3 || map[x][y] == 6 || map[x][y] == 7)){
                    if(map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 4 || map[nx][ny] == 5){
                        dfs(nx, ny, (count - 1));
                    }
                }
                else if(i == 3 && (map[x][y] == 1 || map[x][y] == 3 || map[x][y] == 4 || map[x][y] == 5)){
                    if(map[nx][ny] == 1 || map[nx][ny] == 3 || map[nx][ny] == 6 || map[nx][ny] == 7){
                        dfs(nx, ny, (count - 1));
                    }
                }
            }
        }
    }
}
