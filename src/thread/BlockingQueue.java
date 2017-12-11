package thread;

import java.util.LinkedList;
import java.util.List;

public class BlockingQueue<T> {
    private List<T> queue;
    private int LIMIT;
    private Object lock = new Object();

    BlockingQueue(int maxSize) {
        this.LIMIT = maxSize;
        queue = new LinkedList<>();
    }

    public void add(T e) throws InterruptedException {
        // do some work
        synchronized (lock) {
            while (queue.size() == LIMIT) {
                lock.wait();
            }
            queue.add(e);
            lock.notify();
        }
    }

    public T take() throws InterruptedException {
        synchronized (lock) {
            while (queue.size() == 0)
                lock.wait();
            T obj = queue.remove(0);
            lock.notify();
            return obj;
        }
    }
}
