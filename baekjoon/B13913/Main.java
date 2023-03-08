import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;
//숨바꼭질4
public class Main {

    private static int N;
    private static int K;
    private static int res;
    private static int[] dc = {-1, 1, 2}; //이동방법
    private static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());//subin // N(0 ≤ N ≤ 100,000)
        K = Integer.parseInt(st.nextToken());//동생 //K(0 ≤ K ≤ 100,000)
        res = Integer.MAX_VALUE;
        parent = new int[100001];

        bfs(N, K);
        sb.append(res).append("\n");
        Stack<Integer> stack = new Stack<>();//후입선출
        stack.add(K);
        int idx = K;
        while(idx != N){     //!!!!! parent[idx]!=N하면 메모리 초과
              stack.add(parent[idx]);
              idx = parent[idx];
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }

        System.out.println(sb);

    }

    private static void bfs(int n, int k) {

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(n, 0));
        boolean[] visited = new boolean[100001];
        visited[n] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();
            int c = cur.c;
            int count = cur.count;

            if(c==k){
                res = count;
                return;
            }

            for(int d=0;d<dc.length;d++){
                int nc;
                if(dc[d]==2){// 순간 이동
                    nc = dc[d]*c;
                }else{ //걷기
                    nc = dc[d]+c;
                }

                if(nc<0 || nc>=100001 || visited[nc]){
                    continue;
                }

                visited[nc] = true;
                q.add(new Node(nc, count+1));
                parent[nc] = c;   //nc의 부모는 c

            }
        }
    }

    static class Node{
        int c, count;

        public Node(int c, int count) {
            this.c = c;
            this.count = count;
        }
    }
}