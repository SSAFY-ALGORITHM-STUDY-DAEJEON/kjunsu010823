import java.io.*;
import java.util.*;

public class Main {
    static int Q, result, D, W;
    static StringBuilder sb = new StringBuilder();
    static StringTokenizer st;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static ArrayList<Integer> ruby_W, ruby_V;
    static int[][] memo;

    public static void main(String[] args) throws Exception{
        Q = Integer.parseInt(br.readLine());
        ruby_W = new ArrayList<>();
        ruby_V = new ArrayList<>();
        // 0번 더미 (보석 번호 = 인덱스)
        ruby_W.add(0);
        ruby_V.add(0);

        for(int turn = 1; turn <= Q; turn++){
            st = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(st.nextToken());
            result = 0;

            // 보석 준비
            if(cmd == 1){
                ready();
            }
            // 보석 입고
            else if(cmd == 2){
                input();
            }
            // 보석 판매
            else if(cmd == 3){
                sell();
            }
            // 진열
            else if(cmd == 4){
                W = Integer.parseInt(st.nextToken());
                memo = new int[ruby_W.size()][W + 1];
                for(int i = 0; i < ruby_W.size(); i++){
                    for(int j = 0; j <= W; j++){
                        memo[i][j] = -1;
                    }
                }
                result = dp(0, W);
            }
            // 세트 구성
            else if(cmd == 5){
                set();
            }

            if(cmd == 3 || cmd == 4 || cmd == 5){
                sb.append(result).append('\n');
            }
        }
        System.out.print(sb);
    }

    static void ready(){
        int N = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++){
            ruby_W.add(Integer.parseInt(st.nextToken()));
            ruby_V.add(Integer.parseInt(st.nextToken()));
        }
    }

    static void input(){
        ruby_W.add(Integer.parseInt(st.nextToken()));
        ruby_V.add(Integer.parseInt(st.nextToken()));
    }

    static void sell(){
        int idx = Integer.parseInt(st.nextToken());
        if(idx >= ruby_W.size() || (ruby_W.get(idx) == 0 && ruby_V.get(idx) == 0)){
            result = -1;
        }
        else{
            result = ruby_V.get(idx);
            ruby_W.set(idx, 0);
            ruby_V.set(idx, 0);
        }

    }

    // idx번 보석부터 보고, 남은 무게가 w일 때 최대 가치
    // 판매된 보석은 무게·가치 0이라 골라도 영향 없음
    static int dp(int idx, int w){
        if(idx == ruby_W.size()){
            return 0;
        }
        if(memo[idx][w] != -1){
            return memo[idx][w];
        }

        // 안 고른다
        int res = dp(idx + 1, w);
        // 고른다
        if(ruby_W.get(idx) <= w){
            int pick = dp(idx + 1, w - ruby_W.get(idx)) + ruby_V.get(idx);
            if(pick > res){
                res = pick;
            }
        }

        memo[idx][w] = res;
        return res;
    }

    // 무게가 0 인건 패스 (판매됨)
    // 무게별 개수를 세고 누적합으로 무게 차이 D 이하인 쌍 개수 계산
    static void set(){
        D = Integer.parseInt(st.nextToken());
        int[] cnt = new int[3001];
        for(int i = 1; i < ruby_W.size(); i++){
            if(ruby_W.get(i) != 0){
                cnt[ruby_W.get(i)]++;
            }
        }

        int[] sum = new int[3001];
        for(int w = 1; w <= 3000; w++){
            sum[w] = sum[w - 1] + cnt[w];
        }

        result = 0;
        for(int w = 1; w <= 3000; w++){
            if(cnt[w] == 0){
                continue;
            }
            // 같은 무게끼리
            result += cnt[w] * (cnt[w] - 1) / 2;
            // 무게 w+1 ~ w+D 인 보석들과
            int end = w + D;
            if(end > 3000){
                end = 3000;
            }
            result += cnt[w] * (sum[end] - sum[w]);
        }
    }
}
