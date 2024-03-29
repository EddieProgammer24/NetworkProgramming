import java.net.*;

public class UDPEchoServer {
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(12345); // Choose any available port
            byte[] buffer = new byte[1024];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

            System.out.println("Echo Server started...");

            while (true) {
                socket.receive(packet);
                String receivedData = new String(packet.getData(), 0, packet.getLength());
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();
                System.out.println("Received from " + clientAddress + ":" + clientPort + ": " + receivedData);

                // Echo back to the client
                socket.send(new DatagramPacket(packet.getData(), packet.getLength(), clientAddress, clientPort));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
