import java.io.*;
import java.util.*;

public class Main {
    static int N, B, turn;
    static int[][] map;
    static int[][] bag;
    static int[] point;
    // 아래, 왼쪽, 위쪽, 오른쪽
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] visited;


    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // (t, r, c, w) 시간, 행, 열, 무게
        B = Integer.parseInt(br.readLine());
        for(int i = 0; i < B; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++){
                bag[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        point = new int[B];
        visited = new boolean[N][N];
        turn = 1;
        simul();

        for(int i = 0; i < B; i++){
            System.out.print(point[i]+ " ");
        }
    }

    static void simul(){
        boolean check = true;
        while(check){

            // 1단계 수하물 이동
            move();


            // 3단계 수하물 투입
            drop();



            turn++;
        }
    }

    static void move(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(visited[i][j]){
                    int dir = map[i][j];
                    int nx = i + dx[dir - 1];
                    int ny = j + dy[dir - 1];

                    // 앞에 안막혀 있으면
                    if(visited[nx][ny]){
                        
                    }
                    // 막혀있으면




                }
            }
        }
    }

    static void drop(){


        for(int i = 0; i < B; i++){
            if(turn >= bag[i][0]){
                
            }
        }
    }
}
