package thread;

import java.util.Random;

/**
 * Created by psagar on 12/6/2017.
 */
public class Tester {
    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<Integer> q = new BlockingQueue<>(5);

        Thread p = new Thread() {
          public void run() {
              while (true) {
                  try {
                      Random r = new Random();
                      int num = r.nextInt(50);
                      q.add(num);
                      System.out.println("Producer added : " + num);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
                  try {
                      Thread.sleep(250);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }
        };

        Thread c = new Thread() {
          public void run() {
              while (true) {
                  try {

                      int num = q.take();
                      System.out.println("Consumer removed : " + num);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
                  try {
                      Thread.sleep(1000);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }
        };
        p.start();
        c.start();

        p.join();
        c.join();
    }
}
