package org.example;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreeTreads {
    AtomicInteger current = new AtomicInteger(1);
    int n;

    public ThreeTreads(int n) {
        this.n = n;
    }

    public void fizz() {
        while (current.get() < n) {
            if (current.get() % 3 == 0 && current.get() % 5 != 0) {
                try {
                    System.out.print("fizz\n");
                    current.incrementAndGet();
                    Thread.sleep(2000);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    public void buzz() {
        while (current.get() <= n) {
            if (current.get() % 3 != 0 && current.get() % 5 == 0) {
                try {
                    System.out.print("buzz\n");
                    current.incrementAndGet();
                    Thread.sleep(2000);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    public void buzzfizz() {
        while (current.get() <= n) {
            if (current.get() % 3 == 0 && current.get() % 5 == 0) {
                try {
                    System.out.print("buzzfizz\n");
                    current.incrementAndGet();
                    Thread.sleep(2000);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }


    public void numbers() {
        while (current.get() <= n) {
            int num = current.get();
            if (num % 3 != 0 && num % 5 != 0) {
                try {
                    System.out.printf(num +"\n");
                    current.incrementAndGet();
                    Thread.sleep(2000);
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Write number from 1 to n count of numbers");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        ThreeTreads threeTreads = new ThreeTreads(num);
        Thread A = new Thread(threeTreads::fizz);
        Thread B = new Thread(threeTreads::buzz);
        Thread C = new Thread(threeTreads::buzzfizz);
        Thread D = new Thread(threeTreads::numbers);
        A.start();
        B.start();
        C.start();
        D.start();

        try {
            A.join();
            B.join();
            C.join();
            D.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

    }
}