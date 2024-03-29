import java.io.*;

public class PipeExample {
    public static void main(String[] args) {
        try {
            PipedOutputStream output = new PipedOutputStream();
            PipedInputStream input = new PipedInputStream(output);

            // Start producer thread
            Thread producerThread = new Thread(new Producer(output));
            producerThread.start();

            // Start consumer thread
            Thread consumerThread = new Thread(new Consumer(input));
            consumerThread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

class Producer implements Runnable {
    private PipedOutputStream output;

    public Producer(PipedOutputStream output) {
        this.output = output;
    }

    @Override
    public void run() {
        try {
            for (int i = 1; i <= 5; i++) {
                String message = "Message " + i;
                output.write(message.getBytes());
                System.out.println("Produced: " + message);
                Thread.sleep(1000); // Simulate some processing time
            }
            output.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    private PipedInputStream input;

    public Consumer(PipedInputStream input) {
        this.input = input;
    }

    @Override
    public void run() {
        try {
            int data;
            while ((data = input.read()) != -1) {
                System.out.println("Consumed: " + (char) data);
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
