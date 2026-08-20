/*

1. 문제 이해 못함 : 갈 수 없는 경로 0 차단 안했음
2. 기저조건 return 위치 잘못사용함

*/
import java.util.*;
import java.io.*;

public class Main{
    static boolean[] visited;
    static int N;
    static int[][] arr;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        visited = new boolean[N];
        visited[0] = true;
        arr = new int[N][N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                arr[i][j] = sc.nextInt();
            }
        }

        dfs(0, visited, 0);

        System.out.println(min);

    }

    static void dfs(int now, boolean[] visited, int value){

        // 기저 조건 : 전부 방문했을 때 min과 비교 후 종료
        boolean check = true;
        for(int i = 0; i < N; i++){
            if(!visited[i]){
                check = false;
            }
        }
        if(check){
            //다 방문하고 돌아갈 길 있을 때 되돌아갈 때 비용
            if(arr[now][0] != 0){
                value += arr[now][0];
                if(min > value){
                    min = value;
                }
            }
            return;
        }

        // 리커시브 조건 : 시작 행에서 visited 안한 행으로 가는 로직
        for(int i = 0; i < N; i++){
            // 방문을 안한 행 출발
            if(!visited[i] && arr[now][i] != 0){
                visited[i] = true;
                dfs(i, visited, value + arr[now][i]);
                visited[i] = false;
            }
        }
    }
}
