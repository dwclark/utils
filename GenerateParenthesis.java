import java.util.*;

public class GenerateParenthesis {

    List<String> list = new ArrayList<>();

    private void add(final String s, int left, int right) {
        if(left == 0 && right == 0) {
            list.add(s);
            return;
        }

        //alternate between adding more and closing as soon as possible
        if(right > 0) add(s + ")", left, right - 1);
        if(left > 0) add(s + "(", left - 1, right + 1);
    }
    
    public List<String> generateParenthesis(int n) {
        add("", n, 0);
        return list;
    }

    public static void main(String[] args) {
        GenerateParenthesis sol = new GenerateParenthesis();
        for(String s : sol.generateParenthesis(3)) {
            System.out.println(s);
        }
    }
}
