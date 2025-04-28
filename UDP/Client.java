package UDP;

import javax.swing.*;
import java.awt.*;
import java.net.*;
import java.io.*;

public class Client {
    private JFrame frame;
    private JTextArea textArea;
    private JTextField messageField;
    private JTextField ipField;
    private JTextField portField;
    private JTextField nameField;
    private JButton connectButton;
    private JButton sendButton;

    private DatagramSocket socket;
    private String userName;
    private InetAddress serverAddress;
    private int serverPort;
    private int clientPort;

    public Client() {
        frame = new JFrame("UDP Chat Client");
        textArea = new JTextArea(20, 40);
        textArea.setEditable(false);
        messageField = new JTextField(30);
        ipField = new JTextField("localhost", 15);
        portField = new JTextField("12345", 5);
        nameField = new JTextField("Người dùng", 15);
        connectButton = new JButton("Kết nối");
        sendButton = new JButton("Gửi");

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("IP:"));
        topPanel.add(ipField);
        topPanel.add(new JLabel("Port:"));
        topPanel.add(portField);
        topPanel.add(new JLabel("Tên:"));
        topPanel.add(nameField);
        topPanel.add(connectButton);

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(messageField);
        bottomPanel.add(sendButton);

        frame.setLayout(new BorderLayout());
        frame.add(topPanel, BorderLayout.NORTH);
        frame.add(new JScrollPane(textArea), BorderLayout.CENTER);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        connectButton.addActionListener(e -> {
            try {
                serverAddress = InetAddress.getByName(ipField.getText());
                serverPort = Integer.parseInt(portField.getText());
                userName = nameField.getText().trim();
                if (userName.isEmpty()) {
                    userName = "Người dùng";
                }
                nameField.setEditable(false);
                connectButton.setEnabled(false);
                startClient();
            } catch (Exception ex) {
                textArea.append("Lỗi kết nối: " + ex.getMessage() + "\n");
            }
        });

        sendButton.addActionListener(e -> {
            String message = messageField.getText().trim();
            if (!message.isEmpty() && socket != null) {
                sendMessage(userName + ": " + message);
                messageField.setText("");
            }
        });

        messageField.addActionListener(e -> sendButton.doClick());
    }

    private void startClient() throws IOException {
        socket = new DatagramSocket();
        clientPort = socket.getLocalPort();

        String registerMessage = "REGISTER:" + userName + ":" + clientPort;
        sendMessage(registerMessage);

        textArea.append("Đã kết nối với server: " + serverAddress + ":" + serverPort + "\n");
        textArea.append("Đang lắng nghe trên cổng: " + clientPort + "\n");

        // Luồng nhận tin nhắn
        new Thread(() -> {
            try {
                byte[] buffer = new byte[1024];
                while (true) {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    String message = new String(packet.getData(), 0, packet.getLength());
                    if (!message.startsWith("REGISTER:")) {
                        SwingUtilities.invokeLater(() -> textArea.append(message + "\n"));
                    }
                }
            } catch (IOException e) {
                SwingUtilities.invokeLater(() -> textArea.append("Lỗi nhận tin: " + e.getMessage() + "\n"));
            }
        }).start();
    }
    private void sendMessage(String message) {
        try {
            byte[] data = message.getBytes();
            DatagramPacket packet = new DatagramPacket(data, data.length, serverAddress, serverPort);
            socket.send(packet);
            textArea.append("Đã gửi: " + message + "\n");
        } catch (IOException e) {
            textArea.append("Lỗi gửi tin: " + e.getMessage() + "\n");
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Client::new);
    }
}