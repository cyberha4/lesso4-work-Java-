package com.kiberha4;

import java.util.Random;

/**
 * Created by root on 09.02.17.
 */
public class Main {
    /*
    * 1. Напишите программу, которая каждую секунду отображает на экране данные о времени,
    * прошедшем от начала сессии, а другой ее поток выводит сообщение каждые 5 секунд
    * Предусмотрите возможность ежесекундного оповещения потока, воспроизводящего сообщение, потоком, отсчитывающим время.
    * 2. Не внося изменений в код потока-"хронометра" , добавьте еще один поток, который выводит на экран другое сообщение
     * каждые 7 секунд. Предполагается использование методов wait(), notifyAll().
    * */

    public static int sec = Chronometr.sec;
    //public static final Object lock = Chronometr.lock;

    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //Оповещение каждую секунду
                    Random rand = new Random();
                    if ((rand.nextInt(99) % 10) == 0)
                        Chronometr.everySec = true;
                    Chronometr.sec++;
                    System.out.println(Chronometr.sec + " sec");
                    synchronized (Chronometr.lock){
                        Chronometr.lock.notifyAll();
                    }
                }
            }
        });

        thread1.start();
        new TimeNotifyThread(5);
        new TimeNotifyThread(7);

    }
}
