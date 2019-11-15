import java.util.Stack;

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().isValid("()[]{}"));
    }

    public boolean isValid(String s) {
        if (s.equals("")) return true;
        char[] stack = new char[s.length()];
        int stackCur = -1;
        char[] split = s.toCharArray();
        for (char cur : split) {
            if (cur == '{' || cur == '(' || cur == '[') {
                stack[++stackCur] = cur;
            } else if (stackCur == -1
                    || cur == ')' && stack[stackCur--] != '('
                    || cur == ']' && stack[stackCur--] != '['
                    || cur == '}' && stack[stackCur--] != '{') {
                return false;
            }
        }
        return stackCur == -1;
    }
}