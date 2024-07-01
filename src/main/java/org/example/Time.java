package org.example;

import java.util.concurrent.atomic.AtomicInteger;


public class Time {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        AtomicInteger count = new AtomicInteger(0);

        Thread time = new Thread(() ->{
        while(true){
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - startTime;
            System.out.printf("Time: "+elapsedTime+"\n");
            try
            {
            Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        });
        Thread anotherText = new Thread(() ->{
            try
            {
                Thread.sleep(5000);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            while(true){
                System.out.printf("It's been 5 seconds\n");
                try
                {
                    Thread.sleep(5000);
                }
                catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });
        time.start();
        anotherText.start();
    }
}