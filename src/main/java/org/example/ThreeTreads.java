package org.example;

import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreeTreads {
    AtomicInteger current = new AtomicInteger(1);
    BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    int n ;

    public ThreeTreads(int n)
    {
        this.n = n;
    }

    public void fizz()
    {
        while(current.get() < n)
        {
            if(current.get() % 3 == 0 && current.get() % 5 !=0)
            {
                try
                {
                    queue.put("fizz");
                    current.incrementAndGet();
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }
    public void buzz()
    {
        while(current.get() <= n)
        {
            if(current.get()%3!=0 && current.get()%5==0)
            {
                try
                {
                    queue.put("buzz");
                    current.incrementAndGet();
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }

    public void buzzfizz()
    {
        while(current.get() <= n)
        {
            if(current.get()%3==0 && current.get()%5 ==0)
            {
                try
                {
                    queue.put("buzzfizz");
                    current.incrementAndGet();
                }
                catch(InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
    }



    public void numbers()
    {
           while(current.get()<=n)
           {
               int num = current.get();
               if(num  %3 != 0 && num %5 !=0 ) {
                   try {
                      queue.put(String.valueOf(num));
                      current.incrementAndGet();
                   } catch (InterruptedException e) {
                       Thread.currentThread().interrupt();
                       break;
                   }
               }
           }
    }

    public void print()
    {
        while(current.get() <= n || !queue.isEmpty())
        {
            try
            {
                String value = queue.take();
                System.out.println(value);
            }
            catch (InterruptedException e)
            {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }



    public static void main(String[] args) {
        System.out.println("Write number from 1 to n count of numbers");
        Scanner sc = new Scanner(System.in);
        int num  = sc.nextInt();
        ThreeTreads threeTreads = new ThreeTreads(num);
        Thread A = new Thread (threeTreads::fizz);
        Thread B = new Thread (threeTreads::buzz);
        Thread C = new Thread (threeTreads::buzzfizz);
        Thread D = new Thread (threeTreads::numbers);
        A.start();
        B.start();
        C.start();
        D.start();

        try
        {
            A.join();
            B.join();
            C.join();
            D.join();
        }
        catch (InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        threeTreads.print();
    }
}
