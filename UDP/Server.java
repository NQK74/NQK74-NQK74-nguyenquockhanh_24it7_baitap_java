package UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final int PORT = 12345;
    private static Map<String, ClientInfo> clients = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("UDP Server đang chạy...");
        try (DatagramSocket socket = new DatagramSocket(PORT)) {
            byte[] buffer = new byte[1024];
            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                InetAddress clientAddress = packet.getAddress();
                int clientPort = packet.getPort();

                if (message.startsWith("REGISTER:")) {
                    String[] parts = message.split(":");
                    if (parts.length == 3) {
                        String userName = parts[1];
                        int port = Integer.parseInt(parts[2]);
                        clients.put(userName, new ClientInfo(clientAddress, port));
                        System.out.println("Client đăng ký: " + userName + " (" + clientAddress + ":" + port + ")");

                        String confirmMessage = "Chào mừng " + userName + " đã tham gia phòng chat!";
                        broadcast(confirmMessage, socket);
                    }
                } else {
                    System.out.println("Nhận: " + message);
                    broadcast(message, socket);
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi server: " + e.getMessage());
        }
    }

    private static void broadcast(String message, DatagramSocket socket) {
        byte[] data = message.getBytes();
        synchronized (clients) {
            for (ClientInfo client : clients.values()) {
                try {
                    DatagramPacket packet = new DatagramPacket(data, data.length, client.address, client.port);
                    socket.send(packet);
                } catch (IOException e) {
                    System.out.println("Lỗi gửi tin tới " + client.address + ":" + client.port + ": " + e.getMessage());
                }
            }
        }
    }

    static class ClientInfo {
        InetAddress address;
        int port;

        ClientInfo(InetAddress address, int port) {
            this.address = address;
            this.port = port;
        }
    }
}