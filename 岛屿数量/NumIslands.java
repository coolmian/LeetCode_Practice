class NumIslands {
    public static void main(String[] args) {
        System.out.println(new NumIslands().numIslands(new char[][]{
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        }));
    }
    public int numIslands(char[][] grid) {
        MyCircularQueue queue = new MyCircularQueue(grid.length,2);
        int count = 0;
        for(int i=0;i<grid.length;++i){
            for(int j=0;j<grid[0].length;++j){
                if(grid[i][j] =='1'){
                    grid[i][j] = '0';
                    queue.offer(new Integer[]{i,j});
                    while (!queue.isEmpty()) {
                        for (int k = 0; k < queue.size(); ++k) {
                            Integer[] cur = queue.poll();
                            addNeighbour(cur, queue, grid);
                        }
                    }
                    ++count;
                }
            }
        }
        return count;
    }

    public void addNeighbour(Integer[] location, MyCircularQueue queue,char[][] grid){
        int x = location[0];
        int y = location[1];
        // 上边
        if (x>0){
            if(grid[x-1][y]=='1'){
                grid[x-1][y]='0';
                queue.offer(new Integer[]{x-1,y});

            }
        }
        // 下边
        if (x+1<grid.length){
            if(grid[x+1][y]=='1'){
                grid[x+1][y]='0';
                queue.offer(new Integer[]{x+1,y});

            }
        }
        // 左边
        if(y>0){
            if(grid[x][y-1]=='1'){
                grid[x][y-1]='0';
                queue.offer(new Integer[]{x,y-1});

            }
        }
        // 右边
        if(y+1<grid[0].length){
            if(grid[x][y+1]=='1'){
                grid[x][y+1]='0';
                queue.offer(new Integer[]{x,y+1});

            }
        }
    }
}

class MyCircularQueue {
    Integer[][] data;
    int head;
    int tail;
    int size;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k,int h) {
        data = new Integer[k][h];
        head = 0;
        tail = -1;
        size = 0;
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean offer(Integer[] value) {
        if(isFull()){
            return false;
        }

        tail = (tail+1) % data.length;
        data[tail] = value;
        ++size;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public Integer[] poll() {
        if(isEmpty()){
            return null;
        }
        Integer[] header = data[head];
        if(head==tail){
            head = 0;
            tail = -1;
        }else{
            head = (head + 1) % data.length;
        }
        --size;
        return header;
    }


    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return tail == -1;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return !isEmpty() && head == (tail + 1) % data.length;
    }

    public int size() {
        return size;
    }
}