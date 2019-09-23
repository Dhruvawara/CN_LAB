//Write a program for congestion control using leaky
//bucket algorithm.
import java.util.Scanner;

public class LAB_12 {
    public static void main(String[] args) {
        int drop = 0, mini, nsec, cap, count = 0, i, process;
        int inp[] = new int[25];
        Scanner in = new Scanner(System.in);

        System.out.print("Enter the Bucket Size:");
        cap = in.nextInt();
        System.out.print("Enter the Operation rate:");
        process = in.nextInt();
        System.out.print("Enter the no:of sec of Stimulation:");
        nsec = in.nextInt();
        System.out.println();

        for (i = 0; i < nsec; i++) {
            System.out.print("Enter the size of packet Entering at "+ (i+1) +" sec: ");
            inp[i] = in.nextInt();
        }

        System.out.println("\n|Second|Packet Received|Packet Sent|Packet Left|Packet Dropped|");

        for (i = 0 ; i < nsec ; i++){
            count += inp[i];
            if( count > cap ){
                drop = count - cap;
                count = cap;
            }
            mini = Math.min(count,process);
            count = count - mini;
            System.out.println("\t"+ (i+1) +"\t\t\t"+ inp[i] +"\t\t\t"+ mini +"\t\t\t"+ count +"\t\t\t"+ drop);
            drop = 0;
        }

        for ( ; count != 0 ; i++ ){
            if ( count > cap ){
                drop = count - cap;
                count = cap;
            }
            mini = Math.min(count,process);
            count = count - mini;
            System.out.println("\t"+ (i+1) +"\t\t\t0\t\t\t"+ mini +"\t\t\t"+ count +"\t\t\t"+ drop);
            drop = 0;
        }
    }
}
//OUTPUT:
//Enter the Bucket Size:5
//Enter the Operation rate:2
//Enter the no:of sec of Stimulation:3
//
//Enter the size of packet Entering at 1 sec: 5
//Enter the size of packet Entering at 2 sec: 4
//Enter the size of packet Entering at 3 sec: 3
//
//|Second|Packet Received|Packet Sent|Packet Left|Packet Dropped|
//    1			5			2			3			0
//    2			4			2			3			2
//    3			3			2			3			1
//    4			0			2			1			0
//    5			0			1			0			0