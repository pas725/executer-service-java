package thread;

public class Worker extends Thread{
    private BlockingQueue<Runnable> q;
    private volatile boolean isRunning = true;
    private String name;

    Worker(BlockingQueue<Runnable> q, String name) {
        this.q = q;
        this.name = name;
    }

    public void run() {
        while (isRunning()) {
            try {
                Runnable task = q.take();
                System.out.println("Executing task : Worker-"+name);
                task.run();
            } catch (InterruptedException e) {
            }
        }
    }

    public synchronized void doStop() {
        System.out.println("Stopping Worker-"+name);
        isRunning = false;
        this.interrupt();
    }

    private synchronized boolean isRunning() {
        return isRunning;
    }
}
