import java.util.Set;
import java.util.HashSet;

class Solution {
    public boolean solution(String[] phone_book) {
        
        boolean answer = true;
        int min = Integer.MAX_VALUE;
        int len = phone_book.length;
        // List<String> list = new ArrayList<>();
        Set<String> set = new HashSet<>();
        
        for(String str: phone_book){
            set.add(str);
            
        }
        
        for(String str: phone_book){
            for(int i=0; i<str.length(); i++){
                if(set.contains(str.substring(0, i))){
                    answer = false;
                }
            }
        }
        
      
        
        
        
        
        return answer;
    }
}