public class DailyTemperatures {
    public static void main(String[] args) {
        int[] res = new DailyTemperatures().dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        for (int re : res) {
            System.out.println(re);
        }
    }
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        // 倒着比较，这样可以利用已经比完的数字
        for(int i = T.length-2;i>=0;--i){
            for(int j = i+1;j < T.length;j+=res[j]){
                // res[j]指的是：正在和T[i]作比较的数T[j]，离大于他的一个数有多远，
                // j+res[j]就可以在下一次直接找这个数来比，省略了中间那些数字的比较
                if(T[j]>T[i]) {
                    res[i]=j-i;
                    break;
                }
                if(res[j]==0) break;
            }
        }
        return res;
    }
}
