//Write a program for simple RSA algorithm to encrypt
// and decrypt the data.
import java.util.Scanner;

public class LAB_11{
    private static int gcd(long m, long n){
        int r;
        while ( n != 0 ){
            r = (int) (m%n);
            m = n;
            n = r;
        }
        return (int)m;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter message : ");
        String message = in.nextLine();

        int size = message.length();
        char[] msg = new char[size];
        int[] enc = new int[size];
        int[] dec = new int[size];
        int p,q,n,e,d,phi,i,j,k;

        for (i = 0 ; i < size ; i++)
            msg[i] = message.charAt(i);
        System.out.print("Message(ASCII) to be Encrypted is : ");
        for (i = 0 ; i < size ; i++)
            System.out.print((char)msg[i]);

        System.out.print("\nEnter two Prime numbers : ");
        p = in.nextInt();
        q = in.nextInt();
        n = p * q;
        phi = (p - 1) * (q - 1);

        for (i = 2 ; i < phi ; i++)
            if ( gcd(i,phi) == 1 )
                break;
        e = i;

        for (k = 2 ; k < phi ; k++)
            if ( ((e * k - 1) % phi) == 0 )
                break;
        d = k;

        System.out.println("Value of e = "+ e +" & d = "+d);

        int[] num = new int[size];
        for (int x = 0 ; x < size ; x++) {
            num[x] = msg[x];
        }

        for (i = 0 ; i < size ; i++){
            enc[i] = 1;
            for (j = 0 ; j < e ; j++)
                enc[i] = (enc[i] * num[i]) % n;
        }

        for (i = 0 ; i < size ; i++){
            dec[i] = 1;
            for (j = 0 ; j < d ; j++)
                dec[i] = (dec[i] * enc[i]) % n;
        }

        System.out.print("\nEncrypted message : ");
        for (i = 0 ; i < size ; i++)
            System.out.print((char)enc[i]);

        System.out.print("\nDecrypted message : ");
        for (i = 0 ; i < size ; i++)
            System.out.print((char)dec[i]);
    }
}

//OUTPUT:
//Enter message : HELLO
//Message(ASCII) to be Encrypted is : 7269767679
//Enter two Prime numbers : 109 113
//Value of e = 5 & d = 9677
//
//Message(ASCII) to be Decrypting is : 7269767679
//Encrypted message : 315163729341934111142
//Decrypted message : HELLO