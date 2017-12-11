package thread;


public class ExecServiceTester {
    public static void main(String[] args) throws InterruptedException {
        ExecService service = new ExecService(5);

        for(int i=0; i<10; i++) {
            Runnable r = new Runnable() {
                @Override
                public void run() {
                    for(int j=0; j<10; j++) {
                      System.out.println("Thread : Number-"+j);
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Thread t = new Thread(r);
            t.setName("Name is "+ i);
            service.submit(t);
        }

        service.start();

        Thread.sleep(3000);
        service.stop();
    }
}
