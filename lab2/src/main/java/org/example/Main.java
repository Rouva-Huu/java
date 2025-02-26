package org.example;

public class Main {

    private static final int NUM_THREADS = 4;
    private static final int CALCULATION_LENGTH = 30;
    private static final int DELAY = 200;

    public static void main(String[] args) {
        // создаем и запускаем потоки
        for (int i = 0; i < NUM_THREADS; i++) {
            Thread thread = new Thread(new CalculationTask(i + 1));
            thread.start();
        }
    }

    static class CalculationTask implements Runnable {
        private final int threadNumber;
        private final long startTime;

        public CalculationTask(int threadNumber) {
            this.threadNumber = threadNumber;
            this.startTime = System.currentTimeMillis();
        }

        @Override
        public void run() {
            long threadId = Thread.currentThread().threadId();
            StringBuilder progressBar = new StringBuilder();

            for (int i = 0; i < CALCULATION_LENGTH; i++) {
                // System.out.print("\033[H\033[2J");
                // System.out.flush();
                progressBar.append("=");

                // выводим информацию о потоке и прогресс-бар
                System.out.printf("Thread %d (ID: %d) [%-" + CALCULATION_LENGTH + "s]%n",
                        threadNumber, threadId, progressBar.toString());
                try {
                    Thread.sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;

            System.out.printf("Thread %d (ID: %d) finished in %d ms%n",
                    threadNumber, threadId, totalTime);
        }
    }
}