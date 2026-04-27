package leet_again.stack;

import java.util.Stack;
/*
* | Token | Meaning              |
| ----- | -------------------- |
| `/`   | separator            |
| `.`   | current dir → ignore |
| `..`  | go back (pop stack)  |
| `abc` | valid folder → push  |

* */
public class SimplifyPath {
    public String simplifyPath(String path) {
        String[] tokens = path.split("/+");
        Stack<String> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        for(String token : tokens){
            if(token.equals("..") ){
                if( !stack.isEmpty()){
                    stack.pop();
                }
            }else if(!token.isEmpty() && !token.equals(".")){
                stack.push(token);
            }
        }


        for (String dir : stack) {
            result.append("/").append(dir);
        }

        return result.isEmpty() ? "/" : result.toString();
    }

    public static void main(String[] args) {
//        System.out.println(new SimplifyPath().simplifyPath("/home/"));
//        System.out.println(new SimplifyPath().simplifyPath("/home//foo/"));
//        System.out.println(new SimplifyPath().simplifyPath("/home/user/Documents/../Pictures"));
//        System.out.println(new SimplifyPath().simplifyPath("/../"));
        System.out.println(new SimplifyPath().simplifyPath("/.../a/../b/c/../d/./"));
    }
}
