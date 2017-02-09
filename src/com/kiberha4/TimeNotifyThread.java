package com.kiberha4;

/**
 * Created by root on 09.02.17.
 */
public class TimeNotifyThread implements Runnable{
    private int secNotify;
    private Thread T;

    TimeNotifyThread(int sec){
        secNotify = sec;
        T = new Thread(this);
        T.start();
    }

    @Override
    public void run() {
        while (true){
            synchronized (Chronometr.lock) {
                try {
                    Chronometr.lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (!Chronometr.everySec && Chronometr.sec % secNotify == 0  ) {
                    System.out.println("Message "+secNotify+" sec");
                } else if (Chronometr.everySec){
                    System.out.println("Message "+secNotify+" sec(every 1 sec)");
                }
            }
        }
    }
}
