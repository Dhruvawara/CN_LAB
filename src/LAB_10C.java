//Write  a  program  on  datagram  socket  for  client/server
//to  display  the  messages  on
//client side, typed at the server side.
//Client Program
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class LAB_10C {
    public static void main(String[] args) throws IOException {
        DatagramSocket datagramSocket;
        Scanner scanner = new Scanner(System.in);
        try {
            datagramSocket = new DatagramSocket();
            InetAddress inetAddress = InetAddress.getByName("127.0.0.1");
            int port = 1234;
            while (true) {
                System.out.print("Client : ");
                String msg = scanner.nextLine();
                byte[] bytes = msg.getBytes();
                DatagramPacket datagramPacketRequest = new DatagramPacket(bytes,bytes.length,inetAddress,port);
                datagramSocket.send(datagramPacketRequest);
                byte buffer[] = new byte[1024];
                DatagramPacket datagramPacketReplay = new DatagramPacket(buffer,buffer.length);
                datagramSocket.receive(datagramPacketReplay);
                System.out.println("Server : "+ new String(datagramPacketReplay.getData()));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//OUTPUT:
//        Client : Hello
//        Server : Who are you Boi
//        Client : Im Zzzzz
//        Server : ok Then
//        Client : Cya
