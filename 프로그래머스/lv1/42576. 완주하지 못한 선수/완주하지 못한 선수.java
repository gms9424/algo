import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

class Solution {
    public String solution(String[] participant, String[] completion) {
        String answer = "";
        
//1) 배열로 풀기
//         Arrays.sort(participant);
//         Arrays.sort(completion);
        
//         for(int i=0; i< completion.length; i++){
//             if(participant[i].equals(completion[i])){
//                 continue;
//             }else{
//                 return participant[i];
//             }
//         }
        
//         return participant[participant.length-1];
        
//2) hashmap으로 풀기
        
       Map<String, Integer> map = new HashMap<>();
        
        //map.getOrDefault(KEY, 0)
        //KEY에 매핑된 값이 없으면 0/ 키에 매핑된 값이 있으면 해당 VALUE를 불러온다
        for(String p : participant){
            map.put(p, map.getOrDefault(p, 0)+1);
        }
        for(String c : completion){
            map.put(c, map.getOrDefault(c, 0)-1);
        }
        
        for(String key : map.keySet()){
            if(map.get(key)!=0){
                return key;
            }
        }
        
        return answer;
    }
}