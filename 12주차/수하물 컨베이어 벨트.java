/*
    A형과 다르게 변수를 직접 추가를 해야함, 입력값에 의존하면 안됨
    각 단계(함수)당 추가 temp와 같은 지역변수를 만들어서 풀어야함
*/


import java.io.*;
import java.util.*;

public class Main {
    static int N, B, turn, endX, endY;
    static int[][] map;
    static int[][] bag;
    
    static int[] point;
    // 아래, 왼쪽, 위쪽, 오른쪽
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    // 수하물 id 기입
    static int[][] visited;


    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 9){
                    endX = i;
                    endY = j;
                }
            }
        }
        // (t, r, c, w, moved, f, droped) 시간, 행, 열, 무게, 움직였는지, 파손여부, drop했는지
        B = Integer.parseInt(br.readLine());
        bag = new int[B][7];
        for(int i = 0; i < B; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++){
                bag[i][j] = Integer.parseInt(st.nextToken());
            }

            bag[i][1]--;
            bag[i][2]--;
            bag[i][4] = 0; // 움직였는지
            bag[i][5] = 0; // 파손여부
            bag[i][6] = 0; // drop했는지
        }
        point = new int[B];
        visited = new int[N][N];
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

            // 2단계 적재 및 파손
            place();

            // 3단계 수하물 투입
            drop();



            turn++;
        }
    }

    static void place(){
        if(visited[endX][endY] != 0){
            if(bag[visited[endX][endY] - 1][5] == 0){
                point[visited[endX][endY] - 1] = turn;
            }
            else{
                point[visited[endX][endY] - 1] = -turn;
            }
            visited[endX][endY] = 0;
        }

        for(int i = 0; i < B; i++){
            if(bag[i][4] >= 3){
                bag[i][5] = 1;
            }
        }
    }

    static void move(){
        int[][] winner = new int[N][N]; // 목표 칸에 누가 들어가야 할지 선택
        int[] can = new int[B];         // 1 = 이동 가능, 0 = 불가

        // 1) 같은 칸으로 몰린 경우: 목표 칸별 승자 정하기
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                // 컨베이어 벨트에 짐이 있으면
                if(visited[i][j] > 0){
                    int id = visited[i][j] - 1; // 짐의 id
                    int dir = map[i][j];
                    int nx = i + dx[dir - 1];
                    int ny = j + dy[dir - 1];

                    // 
                    int cur = winner[nx][ny] - 1; // 다음 장소에 짐 id

                    // 비어있거나, 더 무겁거나, 무게 같으면 번호 작은 쪽
                    if(cur == -1 || bag[id][3] > bag[cur][3]
                            || (bag[id][3] == bag[cur][3] && id < cur)){
                        winner[nx][ny] = id + 1;
                    }
                }
            }
        }

        // 2) 승자만 일단 이동 가능으로 표시
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(visited[i][j] > 0){
                    int id = visited[i][j] - 1;
                    int dir = map[i][j];
                    int nx = i + dx[dir - 1];
                    int ny = j + dy[dir - 1];

                    // 가능성만 체크 -> 앞칸 같이 가는 경우가 있어서
                    if(winner[nx][ny] == id + 1){
                        can[id] = 1;
                    }
                }
            }
        }

        // 3) 앞 칸이 막힌 경우: 멈춤이 더 안 퍼질 때까지 반복
        boolean changed = true;
        while(changed){
            changed = false;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    // 수하물이 있다면
                    if(visited[i][j] > 0){
                        int id = visited[i][j] - 1;
                        // 만약 움직일 수 없다면 스킵
                        if(can[id] == 0){
                            continue;
                        }
                        int dir = map[i][j];
                        int nx = i + dx[dir - 1];
                        int ny = j + dy[dir - 1];

                        int front = visited[nx][ny];
                        // 앞 칸 수하물이 못 움직이면 나도 못 움직임
                        if(front > 0 && can[front - 1] == 0){
                            can[id] = 0;
                            changed = true;
                        }
                    }
                }
            }
        }

        // 4) 일괄 반영 - 먼저 전부 지우고
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(visited[i][j] > 0){
                    int id = visited[i][j] - 1;
                    if(can[id] == 1){
                        visited[i][j] = 0;
                    }
                    else{
                        bag[id][4]++; // 못 움직임
                    }
                }
            }
        }
        // 그다음 새 위치에 기록
        for(int id = 0; id < B; id++){
            if(can[id] == 1){
                int dir = map[bag[id][1]][bag[id][2]];
                bag[id][1] += dx[dir - 1];
                bag[id][2] += dy[dir - 1];
                visited[bag[id][1]][bag[id][2]] = id + 1;
                bag[id][4] = 0; // 움직였으면 다시 0부터
            }
        }
    }

    static void drop(){
        // 3. turn이 된 bag 넣기
        // 2. 중복된 칸에 투입될 시 id 빠른 순
        // 1. 만약 수하물이 칸에 있으면 스킵
        int[][] winner = new int[N][N]; // 목표 칸에 누가 들어가야 할지 선택
        int[] can = new int[B];         // 1 = 이동 가능, 0 = 불가

        for(int id = 0; id < B; id++){
            // 만약 drop 안했고, 턴이 지났다면

            if(bag[id][6] == 0 && turn >= bag[id][0]){
                // 만약 수하물이 칸에 있으면 스킵
                if(visited[bag[id][1]][bag[id][2]] > 0){
                    continue;
                }
                // 중복된 칸에 다수 bag가 투입될 시 id 낮은걸로 swap
                if(winner[bag[id][1]][bag[id][2]] != 0 && winner[bag[id][1]][bag[id][2]] > id + 1){
                    can[winner[bag[id][1]][bag[id][2]] - 1] = 0;
                    winner[bag[id][1]][bag[id][2]] = id + 1;
                    can[id] = 1;
                }
                // turn이 된 bag 넣기
                else if(winner[bag[id][1]][bag[id][2]] == 0){
                    winner[bag[id][1]][bag[id][2]] = id + 1;
                    can[id] = 1;
                }
            }


            // visited에 기록, bag에 drop 체크
            for(int id = 0; id < B; id++){
                if(can[id] == 1){
                    visited[bag[id][1]][bag[id][2]] = id + 1;
                    bag[id][6] = 1;
                }
            }

        }



    }
}
