/*



*/


import java.io.*;
import java.util.*;

public class Main {
    static int N, M, cnt, answer;
    static int[][] map, id;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static ArrayList<Integer> ea;   // 다리 한쪽 섬
    static ArrayList<Integer> eb;   // 다리 반대쪽 섬
    static ArrayList<Integer> elen; // 다리 길이
    static int[] pick;              // 고른 다리 번호
    static boolean[] vis;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬번호
        id = new int[N][M]; // 이거 섬번호 매길 배열
        cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1 && id[i][j] == 0) {
                    cnt++;
                    label(i, j);
                }
            }
        }
