public class MyThreadVer3 implements Runnable {
    Thread thread;

    MyThreadVer3(String name) {
        thread = new Thread(this, name);
    }
    public static MyThreadVer3 createAndStart(String name) {
        MyThreadVer3 myThreadVer3 = new MyThreadVer3(name);
        myThreadVer3.thread.start();
        return myThreadVer3;
    }
    public void run() {
        System.out.println(thread.getName() + " begins operation");
        try {
            for (int count = 0; count < 10; count++) {
                Thread.sleep(100);
                System.out.println(thread.getName() + " is executed, counter value: " + count);
            }
        } catch (InterruptedException e) {
            System.out.println(thread.getName() + " has been interrupted");
        }
        System.out.println(thread.getName() + " completes execution.");
    }
}

class MoreThreads2 {
    public static void main(String[] args) {
        System.out.println("The main thread starts running.");
        MyThreadVer3 myThread1 = MyThreadVer3.createAndStart("Child thread #1");
        MyThreadVer3 myThread2 = MyThreadVer3.createAndStart("Child thread #2");
        MyThreadVer3 myThread3 = MyThreadVer3.createAndStart("Child thread #3");

        Thread[] threads = { myThread1.thread, myThread2.thread, myThread3.thread };

        for (Thread thread : threads) {
            try {
                thread.join();
                System.out.println(thread.getName() + " has finished.");
            } catch (InterruptedException e) {
                System.out.println("The main thread has been interrupted");
            }
        }

        System.out.println("The main thread is concluding.");
    }
}
