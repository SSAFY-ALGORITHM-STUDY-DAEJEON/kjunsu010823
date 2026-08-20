/*

1. 문제 잘 읽기 -> 포탄이 계속 직진한다라는거 못읽음
2. dx, dy 문제로 풀면 더 간편해
3. else if 한줄이면 한줄로 쓰자 눈아프
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
            int H = sc.nextInt();
            int W = sc.nextInt();
            char[][] arr = new char[H][W];
            int x = 0;
            int y = 0;
            for(int i = 0; i < H; i++){
                String temp = sc.next();
                for(int j = 0; j < W; j++){
                    arr[i][j] = temp.charAt(j);
                    if(temp.charAt(j) == '^' || temp.charAt(j) == 'v' || temp.charAt(j) == '<' || temp.charAt(j) == '>'){
                        x = i;
                        y = j;
                    }
                }
            }
            int N = sc.nextInt();
            String cmd = sc.next();
            
            // 커맨드 처리
            for(int i = 0; i < N; i++){
                if(cmd.charAt(i) == 'U'){
                    if(x - 1 >= 0 && x - 1 < H && y >= 0 && y < W){
                        //만약 올라갈 수 있을 때
                        if(arr[x - 1][y] == '.'){
                            arr[x][y] = '.';
                            arr[x - 1][y] = '^';
                            x--;
                        }
                        //만약 올라갈 수 없을 때
                        else{
                            arr[x][y] = '^';
                        }
                    }
                    else{
                        arr[x][y] = '^';
                    }
                }
                else if(cmd.charAt(i) == 'D'){
                    if(x + 1 >= 0 && x + 1 < H && y >= 0 && y < W){
                        //만약 내려갈 수 있을 때
                        if(arr[x + 1][y] == '.'){
                            arr[x][y] = '.';
                            arr[x + 1][y] = 'v';
                            x++;
                        }
                        //만약 내려갈 수 없을 때
                        else{
                            arr[x][y] = 'v';
                        }
                    }
                    else{
                        arr[x][y] = 'v';
                    }
                }
                else if(cmd.charAt(i) == 'L'){
                    if(x >= 0 && x < H && y - 1 >= 0 && y - 1 < W){
                        //만약 왼쪽으로 갈 수 있을 때
                        if(arr[x][y - 1] == '.'){
                            arr[x][y] = '.';
                            arr[x][y - 1] = '<';
                            y--;
                        }
                        //만약 왼쪽으로 갈 수 없을 때
                        else{
                            arr[x][y] = '<';
                        }
                    }
                    else{
                        arr[x][y] = '<';
                    }
                }
                else if(cmd.charAt(i) == 'R'){
                    if(x >= 0 && x < H && y + 1 >= 0 && y + 1 < W){
                        //만약 오른쪽으로 갈 수 있을 때
                        if(arr[x][y + 1] == '.'){
                            arr[x][y] = '.';
                            arr[x][y + 1] = '>';
                            y++;
                        }
                        //만약 오른쪽으로 갈 수 없을 때
                        else{
                            arr[x][y] = '>';
                        }
                    }
                    else{
                        arr[x][y] = '>';
                    }
                }
                else if(cmd.charAt(i) == 'S'){
                    if(arr[x][y] == '^'){
                        int tx = x - 1; // 포탄의 현재 위치
                        while(tx >= 0){ // 맵을 벗어나지 않는 동안 직진
                            if(arr[tx][y] == '*'){ // 벽돌을 만나면
                                arr[tx][y] = '.';
                                break;
                            } else if(arr[tx][y] == '#'){ // 강철 벽을 만나면
                                break;
                            }
                            tx--;
                        }
                    }
                    else if(arr[x][y] == 'v'){
                        int tx = x + 1;
                        while(tx < H){
                            if(arr[tx][y] == '*'){
                                arr[tx][y] = '.';
                                break;
                            } else if(arr[tx][y] == '#'){
                                break;
                            }
                            tx++;
                        }
                    }
                    else if(arr[x][y] == '<'){
                        int ty = y - 1;
                        while(ty >= 0){
                            if(arr[x][ty] == '*'){
                                arr[x][ty] = '.';
                                break;
                            } else if(arr[x][ty] == '#'){
                                break;
                            }
                            ty--;
                        }
                    }
                    else if(arr[x][y] == '>'){
                        int ty = y + 1;
                        while(ty < W){
                            if(arr[x][ty] == '*'){
                                arr[x][ty] = '.';
                                break;
                            } else if(arr[x][ty] == '#'){
                                break;
                            }
                            ty++;
                        }
                    }
                }
            }
            
            System.out.print("#" + test_case + " ");
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    System.out.print(arr[i][j]);
                }
                System.out.println();
            }
        }
    }
}
