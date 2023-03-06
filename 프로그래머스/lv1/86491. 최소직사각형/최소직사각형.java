class Solution {
    public int solution(int[][] sizes) {
       
        int maxr = 0;
        int maxc = 0; 
        
        for(int i=0;i<sizes.length;i++){
            
            //가로보다 세로가 크면 자리 바꾸기
            if(sizes[i][0]<sizes[i][1]){
                int tmp = sizes[i][0];
                sizes[i][0] = sizes[i][1];
                sizes[i][1] = tmp;
            }
            

            if(maxr < sizes[i][0]){//가장 큰 가로
                maxr = sizes[i][0];
            }
            if(maxc < sizes[i][1]){ //가장 큰 세로
                maxc = sizes[i][1];
            }
        }  
       
        return maxr * maxc;
    }
}