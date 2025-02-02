import java.util.ArrayList;
import java.util.List;

class MinStack {
    List<int[]> list = null;

    public MinStack() {
        list = new ArrayList<>();
    }

    public void push(int val) {
        int[] top = list.isEmpty() ? new int[]{val, val} : list.get(list.size() - 1);
        if (val < top[1]) {
            list.add(new int[]{val, val});
        } else {
            list.add(new int[]{val, top[1]});
        }
    }

    public void pop() {
        if (!list.isEmpty())
            list.remove(list.size() - 1);
    }

    public int top() {
        if (!list.isEmpty()) {
            return list.get(list.size() - 1)[0];
        }
        return -1;
    }

    public int getMin() {
        if (!list.isEmpty()) {
            return list.get(list.size() - 1)[1];
        }
        return -1;
    }

    public static void main(String[] args) {
        MinStack obj = new MinStack();
        obj.push(-2);
        obj.push(0);
        obj.push(-3);
        System.out.println(obj.getMin());
        obj.pop();
        System.out.println(obj.top());
        System.out.println(obj.getMin());
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */