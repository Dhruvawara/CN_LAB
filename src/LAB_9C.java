//Using TCP/IP sockets, write a client – server program
//to make the client send the file
//name and to make the server send back the contents
//of the requested file if present.
//Client program
import java.io.*;
import java.net.Socket;
import java.util.Scanner;
public class LAB_9C {
    public static void main(String[] args) throws IOException {
        Socket socket;
        while (true){
            try {
                socket = new Socket("127.0.0.1",3000);
                OutputStream outputStream = socket.getOutputStream();
                System.out.print("Enter File name : ");
                Scanner scanner = new Scanner(System.in);
                String fileName = scanner.nextLine();
                PrintWriter printWriter = new PrintWriter(outputStream , true);
                printWriter.println(fileName);
                InputStream inputStream = socket.getInputStream();
                Scanner fileScanner = new Scanner(new InputStreamReader(inputStream));
                while (fileScanner.hasNext())
                    System.out.println(fileScanner.nextLine());
                printWriter.close();
                scanner.close();
                fileScanner.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}

//OUTPUT:
//        Enter File name : Test.txt
//        contents inside file
//        A
//        B
//        C
//        D
//        E
//        Enter File name : abc.test
//        File not found
//        Enter File name :