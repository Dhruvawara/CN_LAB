//Server Program
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
public class LAB_9S {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        Socket socket = null;
        try {
            serverSocket = new ServerSocket(3000);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                System.out.println("Server is Ready...\n");
                socket = serverSocket.accept();
                InputStream inputStream =socket.getInputStream();
                Scanner scanner = new Scanner(new InputStreamReader(inputStream));
                String fileName = scanner.nextLine();
                System.out.println("Reading contents of "+ fileName);
                Scanner fileScanner = new Scanner(new FileReader("C:/Users/dhruv/IdeaProjects/CN_LAB/out/production/CN_LAB/"+fileName));
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream,true);
                while (fileScanner.hasNext())
                    printWriter.println(fileScanner.nextLine());
                printWriter.close();
                serverSocket.close();
                scanner.close();
                fileScanner.close();
            }
            catch (FileNotFoundException e1) {
                System.out.println("File not Found");
                OutputStream outputStream = socket.getOutputStream();
                PrintWriter printWriter = new PrintWriter(outputStream,true);
                printWriter.println("File not found");
                printWriter.close();
            }
            catch (Exception e){ }
        }
    }
}


//OUTPUT:
//        Server is Ready...
//
//        Reading contents of Test.txt
//        Server is Ready...
//
//        Reading contents of abc.test
//        File not Found
//        Server is Ready...