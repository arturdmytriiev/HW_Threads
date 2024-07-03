package org.example;

import java.util.Scanner;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class FizzBuzz {
    private int n;
    private Semaphore semFizz, semBuzz, semFizzBuzz, semNumber;
    private AtomicInteger currentNumber = new AtomicInteger(1);

    public FizzBuzz(int n) {
        this.n = n;
        this.semNumber = new Semaphore(1);
        this.semFizz = new Semaphore(0);
        this.semBuzz = new Semaphore(0);
        this.semFizzBuzz = new Semaphore(0);
    }

    public void fizz() throws InterruptedException {
        while (true) {
            semFizz.acquire();
            if (currentNumber.get() > n) {
                semNumber.release();
                break;
            }
            if (currentNumber.get() % 3 == 0 && currentNumber.get() % 5 != 0) {
                System.out.print("fizz ");
                currentNumber.incrementAndGet();
                semNumber.release();
            } else {
                semFizz.release();
            }
        }
    }

    public void buzz() throws InterruptedException {
        while (true) {
            semBuzz.acquire();
            if (currentNumber.get() > n) {
                semNumber.release();
                break;
            }
            if (currentNumber.get() % 5 == 0 && currentNumber.get() % 3 != 0) {
                System.out.print("buzz ");
                currentNumber.incrementAndGet();
                semNumber.release();
            } else {
                semBuzz.release();
            }
        }
    }

    public void fizzbuzz() throws InterruptedException {
        while (true) {
            semFizzBuzz.acquire();
            if (currentNumber.get() > n) {
                semNumber.release();
                break;
            }
            if (currentNumber.get() % 15 == 0) {
                System.out.print("fizzbuzz ");
                currentNumber.incrementAndGet();
                semNumber.release();
            } else {
                semFizzBuzz.release();
            }
        }
    }

    public void number() throws InterruptedException {
        while (true) {
            semNumber.acquire();
            if (currentNumber.get() > n) {
                semFizz.release();
                semBuzz.release();
                semFizzBuzz.release();
                break;
            }
            int num = currentNumber.get();
            if (num % 3 != 0 && num % 5 != 0) {
                System.out.print(num + " ");
                currentNumber.incrementAndGet();
            }
            if (num % 15 == 0) {
                semFizzBuzz.release();
            } else if (num % 3 == 0) {
                semFizz.release();
            } else if (num % 5 == 0) {
                semBuzz.release();
            } else {
                semNumber.release();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Write a number frotm 1 to n");
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        FizzBuzz fizzBuzz = new FizzBuzz(n);

        Thread a = new Thread(() -> {
            try {
                fizzBuzz.fizz();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread b = new Thread(() -> {
            try {
                fizzBuzz.buzz();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread c = new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread d = new Thread(() -> {
            try {
                fizzBuzz.number();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        a.start();
        b.start();
        c.start();
        d.start();

        try {
            a.join();
            b.join();
            c.join();
            d.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
