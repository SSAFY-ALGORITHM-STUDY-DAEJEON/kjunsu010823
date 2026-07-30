import java.util.Scanner;
import java.io.FileInputStream;
import java.util.*;

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
            String S = sc.next();
            int[][] arr = new int[N][N];
            
            // 배열값 입력
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    arr[i][j] = sc.nextInt();
                }
            }
            
            if(S.equals("up")){
                // 0을 다 없애는 과정
                for(int j = 0; j < N; j++){
                    List<Integer> list = new ArrayList<>();
                    int count = 0;
                    for(int i = 0; i < N; i++){
                        if(arr[i][j] == 0){
                            count++;
                        }
                        else{
                            list.add(arr[i][j]);
                        }
                    }
                    for(int i = 0; i < count; i++){
                        list.add(0);
                    }
                    // 나머지 합치는 과정 -> 이것도 리스트로 만들고 arr에 저장 
                    int index = 0;
                    while(index < list.size() - 1){
                        // Integer로 list를 선언하였기 때문에 객체로 선언하였다. 자바에서 ==연산자는 객체가 오면 객체의 메모리값을 비교하기 때문에 equal()함수를 사용해야한다
                        if(list.get(index).equals(list.get(index + 1))){
                            list.set(index, list.get(index) * 2);
                            list.remove(index + 1);
                        }
                        index++;
                    }
                    // 리스트에 있는 것을 배열에 저장
                    for(int i = 0; i < N; i++){
                        if(i < list.size()){
                            arr[i][j] = list.get(i);
                        }
                        else{
                            arr[i][j] = 0;
                        }
                    }
                }
            }
            else if(S.equals("down")){
                for (int j = 0; j < N; j++) {
                    List<Integer> list = new ArrayList<>();
                    int count = 0;
                    // 아래에서부터 위로 탐색
                    for (int i = N - 1; i >= 0; i--) {
                        if (arr[i][j] == 0) {
                            count++;
                        } else {
                            list.add(arr[i][j]);
                        }
                    }
                    for (int i = 0; i < count; i++) {
                        list.add(0);
                    }
                    int index = 0;
                    while (index < list.size() - 1) {
                        if (list.get(index).equals(list.get(index + 1))) {
                            list.set(index, list.get(index) * 2);
                            list.remove(index + 1);
                        }
                        index++;
                    }
                    // 다시 배열에 저장할 때도 아래에서부터 위로 채움
                    for (int i = 0; i < N; i++) {
                        if (i < list.size()) {
                            arr[N - 1 - i][j] = list.get(i);
                        } else {
                            arr[N - 1 - i][j] = 0;
                        }
                    }
                }
            }
            else if(S.equals("left")){
                for (int i = 0; i < N; i++) {
                    List<Integer> list = new ArrayList<>();
                    int count = 0;
                    // 왼쪽에서 오른쪽으로 탐색
                    for (int j = 0; j < N; j++) {
                        if (arr[i][j] == 0) {
                            count++;
                        } else {
                            list.add(arr[i][j]);
                        }
                    }
                    for (int j = 0; j < count; j++) {
                        list.add(0);
                    }
                    int index = 0;
                    while (index < list.size() - 1) {
                        if (list.get(index).equals(list.get(index + 1))) {
                            list.set(index, list.get(index) * 2);
                            list.remove(index + 1);
                        }
                        index++;
                    }
                    // 배열에 저장 (왼쪽부터)
                    for (int j = 0; j < N; j++) {
                        if (j < list.size()) {
                            arr[i][j] = list.get(j);
                        } else {
                            arr[i][j] = 0;
                        }
                    }
                }
            }
            else if(S.equals("right")){
                for (int i = 0; i < N; i++) {
                    List<Integer> list = new ArrayList<>();
                    int count = 0;
                    // 오른쪽에서 왼쪽으로 탐색
                    for (int j = N - 1; j >= 0; j--) {
                        if (arr[i][j] == 0) {
                            count++;
                        } else {
                            list.add(arr[i][j]);
                        }
                    }
                    for (int j = 0; j < count; j++) {
                        list.add(0);
                    }
                    int index = 0;
                    while (index < list.size() - 1) {
                        if (list.get(index).equals(list.get(index + 1))) {
                            list.set(index, list.get(index) * 2);
                            list.remove(index + 1);
                        }
                        index++;
                    }
                    // 배열에 저장 (오른쪽부터)
                    for (int j = 0; j < N; j++) {
                        if (j < list.size()) {
                            arr[i][N - 1 - j] = list.get(j);
                        } else {
                            arr[i][N - 1 - j] = 0;
                        }
                    }
                }
            }
      
            System.out.println("#" + test_case);
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    System.out.print(arr[i][j] + " ");
                }
                System.out.println();
            }
		}
	}
}
