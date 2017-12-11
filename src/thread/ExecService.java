package thread;

import java.util.ArrayList;
import java.util.List;


public class ExecService {
    private BlockingQueue<Runnable> q;
    List<Worker> workerList;
    private int MAX_THREADS = 0;
    ExecService(int maxThreads) {
        this.MAX_THREADS = maxThreads;
        // Temporarily Queue size is kept fixed.
        int queueSize = 10;
        q = new BlockingQueue<>(queueSize);
        workerList = new ArrayList<>();
        for(int i=0; i<MAX_THREADS ;i++) {
            workerList.add(new Worker(q, ""+i));
        }
    }

    public void submit(Runnable r) throws InterruptedException {
        q.add(r);
    }

    public void start() {
        for (Worker w : workerList) {
            w.start();
        }
    }

    public void stop() {
        for (Worker w : workerList) {
            w.doStop();
        }
    }

}
