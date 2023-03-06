class Solution {

    public int solution(String[][] book_time) {
        int answer = 0;
        int[] rooms = new int[24*60 +10]; //최대 시간
        
        for(String[] t : book_time){
            String start = t[0];
            String end = t[1];
            
            rooms[calc(start)] +=1; //입실
            rooms[calc(end)+10] -= 1; //퇴실+청소
        }
        
        for(int i=1 ; i< 24*60 +10; i++){ //누적합
            rooms[i] += rooms[i-1];
            answer = Math.max(answer, rooms[i]);
        }
        
          return answer; //최소 객실 수
    }
    
    public int calc(String str) {
        String[] srr = str.split(":");
        int hour = Integer.parseInt(srr[0])*60; //시간
        int minute = Integer.parseInt(srr[1]); //분
        
        return hour+minute;

    }
}
//https://ksb-dev.tistory.com/269