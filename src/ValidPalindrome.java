public class ValidPalindrome {
    public boolean isPalindrome(String s) {

        if(s.length()>0)
            s = s.replaceAll("\\s+","");
        int start=0;
        int end = s.length()-1;
        while(start<end){
            if(!Character.isAlphabetic(s.charAt(start))){
                start++;
            }
            if(!Character.isAlphabetic(s.charAt(end))){
                end--;
            }
            if(Character.toLowerCase(s.charAt(start))!=Character.toLowerCase(s.charAt(end)))
                return false;
            else{
                start++;
                end--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new ValidPalindrome().isPalindrome("A man, a plan, a canal: Panama"));
    }
}
