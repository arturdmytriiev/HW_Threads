package org.example;

public class Time {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Thread time = new Thread(() -> {
            while (true) {
                long currentTime = System.currentTimeMillis();
                long elapsedTime = (currentTime - startTime) / 1000;
                System.out.printf("Time: %d seconds\n", elapsedTime);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        Thread anotherText = new Thread(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            while (true) {
                System.out.printf("It's been more than 5 seconds\n");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        });

        time.start();
        anotherText.start();
    }
}
