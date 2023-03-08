import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
//벽 부수고 이동하기

public class Main {

    private static int C;
    private static int R;
    private static int[][] map;
    private static int res;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static class Node {
        int r, c, count, check;

        public Node(int r, int c, int count, int check) {
            this.r = r;
            this.c = c;
            this.count = count;
            this.check = check;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        res = Integer.MAX_VALUE;

        map = new int[R][C];

        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < str.length(); j++) {
                map[i][j] = str.charAt(j)-'0';
            }
        }

        bfs(0, 0);

        System.out.println(res == Integer.MAX_VALUE ? -1 : res);
    }

    private static void bfs(int sr, int sc) {

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(sr, sc, 1, 0));
        boolean[][][] visited = new boolean[R][C][2];
        visited[sr][sc][0] = true; //안부수고 방문

        while (!q.isEmpty()) {
            Node cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            int count = cur.count;

            if(r==R-1 && c==C-1){
                res = count;
                return;
            }

            for (int d=0; d<dr.length; d++){
                int nr = r+dr[d];
                int nc = c+dc[d];
                int ncheck = cur.check; //초기화!

                if(nr<0 || nc<0 || nr>=R || nc>= C || visited[nr][nc][ncheck]){
                    continue;
                }

                if(ncheck>=1){ //이미 부순적이 있다면 불가
                    if(map[nr][nc]==1){
                        continue;
                    }
                }

               if(ncheck==0){
                    if(map[nr][nc]==1){
                        ncheck += 1;
                    }
                }

                q.add(new Node(nr,nc,count+1, ncheck));
                visited[nr][nc][ncheck] = true;
            }
        }
    }
}