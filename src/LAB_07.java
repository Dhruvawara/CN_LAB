//Write a program for error detecting code using
// CRC-CCITT (16- bits).
import java.io.IOException;
import java.util.Scanner;
public class LAB_07 {

    static int[] divide( int divisor[], int rem[]){
        int cur=0;
        while (true){
            for (int i = 0 ; i < divisor.length ; i++){
                rem[cur+i] = (rem[cur+i] ^ divisor[i]);
            }
            while ( rem[cur] == 0 && cur != ((rem.length)-1) ){
                cur++;
            }
            if ( (rem.length - cur) < divisor.length ){
                break;
            }
        }
        return rem;
    }

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        int[] data,div,divisor,rem,crc;
        int dataBits,divisorBits,totalLength;

        System.out.print("Enter number of Data Bits : ");
        dataBits = in.nextInt();
        data = new int[dataBits];

        System.out.print("Enter Data Bits of Length "+ dataBits + " : ");
        for (int i = 0 ; i < dataBits ; i++){
            data[i] = in.nextInt();
        }

        System.out.print("\nEnter number of bits in Divisor : ");
        divisorBits = in.nextInt();
        divisor = new int[divisorBits];

        System.out.print("Enter Divisor Bits of length " + dataBits + " : ");
        for (int i = 0 ; i < divisorBits ; i++){
            divisor[i] = in.nextInt();
        }

        totalLength = dataBits + divisorBits - 1;
        div = new int[totalLength];
        rem = new int[totalLength];
        crc = new int[totalLength];

        //CRC Generation
        for (int i = 0 ; i < data.length ; i++){
            div[i] = data[i];
        }

        System.out.print("\nDividend (after appending 0's) are : ");
        for (int i= 0 ; i < div.length ; i++){
            System.out.print(div[i]);
        }

        for (int j= 0 ; j < div.length ; j++){
            rem[j] = div[j];
        }

        rem = divide(divisor,rem);

        for (int i = 0 ; i < div.length ; i++){         //Appending Dividend & Reminder
            crc[i] = (div[i] ^ rem[i]);
        }

        System.out.print("\nCRC code : ");
        for (int i = 0 ; i < crc.length ; i++){
            System.out.print(crc[i]);
        }

        //Error Detection
        System.out.print("\n\nEnter CRC code of total length " + totalLength + " : ");
        for (int i = 0 ; i < crc.length ; i++){
            crc[i] = in.nextInt();
        }

        for (int j = 0 ; j < crc.length ;j++){
            rem[j] = crc[j];
        }

        rem = divide(divisor,rem);

        for (int i = 0 ; i < rem.length ; i++){
            if ( rem[i] != 0){
                System.out.println("Error");
                break;
            }
            if (i == rem.length-1){
                System.out.println("No Error");
            }
        }
        in.close();
    }
}
//OUTPUT:
//Enter number of Data Bits : 7
//Enter Data Bits of Length 7 : 1 0 1 1 0 0 1
//Enter number of bits in Divisor : 3
//Enter Divisor Bits of length 7 : 1 0 1
//Dividend (after appending 0's) are : 101100100
//CRC code : 101100111
//Enter CRC code of total length 9 : 1 0 1 1 0 0 1 0 1
//Error

//Enter number of Data Bits : 7
//Enter Data Bits of Length 7 : 1 0 1 1 0 0 1
//Enter number of bits in Divisor : 3
//Enter Divisor Bits of length 7 : 1 0 1
//Dividend (after appending 0's) are : 101100100
//CRC code : 101100111
//Enter CRC code of total length 9 : 1 0 1 1 0 0 1 1 1
//No Error