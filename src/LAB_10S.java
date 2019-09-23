//Server Program
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Scanner;

public class LAB_10S {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        DatagramSocket datagramSocket = null;
        try {
            datagramSocket = new DatagramSocket(1234);
            System.out.println("Server is Ready!!!");
            while (true) {
                byte buffer[] = new byte[1024];
                DatagramPacket datagramPacketRequest = new DatagramPacket(buffer,buffer.length);
                datagramSocket.receive(datagramPacketRequest);
                String msg = new String(datagramPacketRequest.getData());
                System.out.println("Client : "+ msg);
                System.out.print("Server : ");
                String m = scanner.nextLine();
                byte[] sendMessage = m.getBytes();
                DatagramPacket datagramPacketReplay = new 
					DatagramPacket(sendMessage,sendMessage.length,datagramPacketRequest.getAddress(),datagramPacketRequest.getPort());
                datagramSocket.send(datagramPacketReplay);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//OUTPUT:
////        Server is Ready!!!
////        Client : Hello
////        Server : Who are you Boi
////        Client : Im Zzzzz
////        Server : ok Then
////        Client : Cya
////        Server :
