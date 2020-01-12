package com.wuyue.net.udp.transString;

        import java.io.IOException;
        import java.net.DatagramPacket;
        import java.net.DatagramSocket;
        import java.net.InetAddress;
        import java.nio.charset.StandardCharsets;

/**
 * udp的发送端
 */
public class UdpCilent {
    public static void main(String[] args) throws IOException {
        DatagramSocket cilent = new DatagramSocket(20480);
        byte[] sendMsg = "来打我啊".getBytes(StandardCharsets.UTF_8);
        DatagramPacket datas = new DatagramPacket(sendMsg, 0, sendMsg.length, InetAddress.getLocalHost(), 10240);
        cilent.send(datas);
        cilent.close();
    }
}
