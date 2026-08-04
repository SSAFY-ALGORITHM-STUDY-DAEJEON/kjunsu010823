import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
        
		for(int test_case = 1; test_case <= T; test_case++)
		{
            int N = sc.nextInt();
            int M = sc.nextInt();
            char[][] board = new char[N][N];
            board[N / 2 - 1][N / 2 - 1] = 'W';
            board[N / 2][N / 2 - 1] = 'B';
            board[N / 2 - 1][N / 2] = 'B';
            board[N / 2][N / 2] = 'W';
            
            for(int i = 0; i < M; i++){
                int x = sc.nextInt() - 1;
                int y = sc.nextInt() - 1;
                int stn = sc.nextInt();
                // 시계방향 탐색
                int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
                int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
                
                // 흑돌
                if(stn == 1){
                    board[y][x] = 'B';
                    for(int j = 0; j < 8; j++){
                        int count = 0;
                        boolean temp = true;
                        // 원래의 x, y가 훼손되지 않도록 임시 변수 nx, ny 사용
                        int nx = x;
                        int ny = y;
                        while(temp){
                            count++;
                            nx += dx[j];
                            ny += dy[j];
                            // 범위를 벗어났거나 빈공간이면 나가기
                            if(nx < 0 || nx == N || ny < 0 || ny == N || board[ny][nx] == '\u0000'){
                                break;
                            }
                            // 흑돌이면 그 전부터 있던 자리에 싹다 흑돌로 바꾸기
                            else if(board[ny][nx] == 'B'){
                                // 작성하신 반대 방향((j + 4) % 8) 아이디어에 곱하기(k)를 적용
                                for(int k = 1; k < count; k++){
                                    board[ny + dy[(j + 4) % 8] * k][nx + dx[(j + 4) % 8] * k] = 'B';
                                }
                                break;
                            }
                        }
                    }
                }
                // 백돌
                else if(stn == 2){
                    board[y][x] = 'W';
                    for(int j = 0; j < 8; j++){
                        int count = 0;
                        boolean temp = true;
                        // 원래의 x, y가 훼손되지 않도록 임시 변수 nx, ny 사용
                        int nx = x;
                        int ny = y;
                        while(temp){
                            count++;
                            nx += dx[j];
                            ny += dy[j];
                            // 범위를 벗어났거나 빈공간이면 나가기
                            if(nx < 0 || nx == N || ny < 0 || ny == N || board[ny][nx] == '\u0000'){
                                break;
                            }
                            // 백돌이면 그 전부터 있던 자리에 싹다 백돌로 바꾸기
                            else if(board[ny][nx] == 'W'){
                                // 작성하신 반대 방향((j + 4) % 8) 아이디어에 곱하기(k)를 적용
                                for(int k = 1; k < count; k++){
                                    board[ny + dy[(j + 4) % 8] * k][nx + dx[(j + 4) % 8] * k] = 'W';
                                }
                                break;
                            }
                        }
                    }
                }
            }
            
            int cntB = 0;
            int cntW = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(board[i][j] == 'B'){
                        cntB++;
                    }
                    else if(board[i][j] == 'W'){
                        cntW++;
                    }
                }
            }
            
            System.out.println("#" + test_case + " " + cntB + " " + cntW);
		}
	}
}
