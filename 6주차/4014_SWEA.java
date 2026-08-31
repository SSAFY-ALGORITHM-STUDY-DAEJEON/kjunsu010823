/*

1. 높이차 체크 잘못함
2. total을 경사로 하나 놓을 때 체크해야하는데 -> 경사로를 깔때마다 체크해버림
3. 경사로 방향 실수

*/

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
            int X = sc.nextInt();
            int[][] arr = new int[N][N];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    arr[i][j] = sc.nextInt();
                }
            }
            int total = 0;

            //가로 확인
            for(int i = 0; i < N; i++){
                // 이 줄이 경사로가 이미 깔려 있는가
                boolean[] visited = new boolean[N];
                // 이 줄이 건설 가능한가 -> 나중에 total 체크용
                boolean ok = true;                              // [추가] 이 줄 건설 가능 여부
                for(int j = 0; j < N - 1; j++){
                    if(arr[i][j + 1] - arr[i][j] == -1){        // 내려감 → 낮은 쪽은 '오른쪽'
                        if(j + X < N){                          // [수정] 범위 체크
                            int temp = 0;
                            for(int k = 1; k <= X; k++){        // [수정] j+1 ~ j+X
                                if(arr[i][j + k] == arr[i][j + 1] && !visited[j + k]){
                                    temp++;
                                }
                            }
                            if(temp == X){
                                for(int k = 1; k <= X; k++){
                                    visited[j + k] = true;
                                }
                            } else {
                                ok = false; break;              // [추가] 못 놓으면 실패
                            }
                        } else {
                            ok = false; break;                  // [추가]
                        }
                    }
                    else if(arr[i][j + 1] - arr[i][j] == 1){    // 올라감 → 낮은 쪽은 '왼쪽'
                        if(j - X + 1 >= 0){                     // [수정] => 를 >= 로
                            int temp = 0;
                            for(int k = 0; k < X; k++){         // [수정] j-X+1 ~ j
                                if(arr[i][j - k] == arr[i][j] && !visited[j - k]){
                                    temp++;
                                }
                            }
                            if(temp == X){
                                for(int k = 0; k < X; k++){
                                    visited[j - k] = true;
                                }
                            } else {
                                ok = false; break;              // [추가]
                            }
                        } else {
                            ok = false; break;                  // [추가]
                        }
                    }
                    else if(arr[i][j + 1] - arr[i][j] != 0){    // [수정] 높이차 2 이상 전부
                        ok = false; break;
                    }
                }
                if(ok) total++;                                 // [수정] 줄 단위로 +1
            }


            //세로 확인
            for(int i = 0; i < N; i++){
                boolean[] visited = new boolean[N];
                boolean ok = true;
                for(int j = 0; j < N - 1; j++){
                    if(arr[j + 1][i] - arr[j][i] == -1){        // 내려감 → 낮은 쪽은 '아래쪽'
                        if(j + X < N){                          // [수정] i가 아니라 j
                            int temp = 0;
                            for(int k = 1; k <= X; k++){
                                if(arr[j + k][i] == arr[j + 1][i] && !visited[j + k]){
                                    temp++;
                                }
                            }
                            if(temp == X){
                                for(int k = 1; k <= X; k++){
                                    visited[j + k] = true;
                                }
                            } else {
                                ok = false; break;
                            }
                        } else {
                            ok = false; break;
                        }
                    }
                    else if(arr[j + 1][i] - arr[j][i] == 1){    // 올라감 → 낮은 쪽은 '위쪽'
                        if(j - X + 1 >= 0){                     // [수정] i가 아니라 j
                            int temp = 0;
                            for(int k = 0; k < X; k++){
                                if(arr[j - k][i] == arr[j][i] && !visited[j - k]){
                                    temp++;
                                }
                            }
                            if(temp == X){
                                for(int k = 0; k < X; k++){
                                    visited[j - k] = true;
                                }
                            } else {
                                ok = false; break;
                            }
                        } else {
                            ok = false; break;
                        }
                    }
                    else if(arr[j + 1][i] - arr[j][i] != 0){
                        ok = false; break;
                    }
                }
                if(ok) total++;
            }

			System.out.println("#" + test_case + " " + total);
		}
	}
}
