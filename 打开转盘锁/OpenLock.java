import java.util.*;

public class OpenLock {

    public static void main(String[] args) {
        System.out.println(new OpenLock().openLock(new String[]{}, "2345"));
    }

    public int openLock(String[] deadends, String target) {
        Queue<String> queue = new LinkedList<>();
        Set<String> set = new HashSet<>(Arrays.asList(deadends));
        int count = 0;
        if (set.contains("0000")) {
            return -1;
        }
        queue.offer("0000");
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                if (current.equals(target)) {
                    return count;
                }
                addNeighbours(queue, current, set);
            }
            count++;
        }
        return -1;
    }

    private void addNeighbours(Queue<String> queue, String current, Set<String> set) {
        for (int i = 0; i < 4; i++) {
            String a = replace(current, i, (char) ('0' + ((current.charAt(i) - '0' + 1) % 10)));
            String b = replace(current, i, (char) ('0' + ((current.charAt(i) - '0' + 9) % 10)));
            if (!set.contains(a)) {
                queue.offer(a);
                set.add(a);
            }
            if (!set.contains(b)) {
                queue.offer(b);
                set.add(b);
            }
        }
    }

    private String replace(String sb, int index, char value) {
        char[] chars = sb.toCharArray();
        chars[index] = value;
        return new String(chars);
    }
}
