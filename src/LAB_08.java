//Write a program to find the shortest path between
// vertices using bellman-ford algorithm.
import java.util.*;

public class LAB_08 {

    public static final int max_value=999;
    private int no_vertices;
    private int[] dist;
    public LAB_08(int no_vertices)  {
        this.no_vertices = no_vertices;
        dist = new int[no_vertices+1];
    }

    public static void main(String args[])  {
        Scanner scan = new Scanner(System.in);
        int source;
        int no_vertices;
        int[][] adj;
        System.out.print("Enter number of vertices: ");
        no_vertices = scan.nextInt();
        adj = new int[no_vertices+1][no_vertices+1];
        System.out.println("Enter adjacency matrix:");
        for(int i = 1 ; i <= no_vertices ; i++)
            for(int j = 1 ; j <= no_vertices ; j++)    {
                adj[i][j] = scan.nextInt();
                if( i == j )     {
                    adj[i][j] = 0;
                    continue;
                }
                if( adj[i][j] == 0 )
                    adj[i][j] = max_value;
            }

        System.out.print("Enter source vertex: ");
        source = scan.nextInt();
        LAB_08 b = new LAB_08(no_vertices);
        b.BellmanFordEvaluation(adj, source);
        scan.close();
    }

    public void BellmanFordEvaluation(int[][] adj, int source)  {
        for(int node = 1 ; node<=no_vertices ; node++)
            dist[node] = max_value;

        dist[source] = 0;

        for(int node = 1 ; node <= no_vertices ; node++)
            for(int sn = 1 ; sn <= no_vertices ; sn++)
                for(int dn = 1 ; dn <= no_vertices ; dn++)     {
                    if( adj[sn][dn] != max_value )
                        if( dist[dn] > dist[sn]+adj[sn][dn] )
                            dist[dn] = dist[sn]+adj[sn][dn];
                }

        for(int sn = 1 ; sn <= no_vertices ; sn++)
            for(int dn = 1 ; dn <= no_vertices ; dn++)    {
                if( adj[sn][dn] != max_value )
                    if( dist[dn] > dist[sn]+adj[sn][dn] ) {
                        System.out.println("Contains negative Cycle!!!");
                        return;
                    }
            }

        for(int vertex = 1 ; vertex <= no_vertices ; vertex++)
            System.out.println("Shortest distance from source "+ source +" to "+ vertex +" is "+ dist[vertex]);
    }
}

//OUTPUT:
//Enter number of vertices: 5
//Enter adjacency matrix:
//0 2 4 0 0
//0 0 1 -1 0
//0 0 0 0 0
//0 0 4 0 2
//0 0 0 0 0
//Enter source vertex: 1
//Shortest distance from source 1 to 1 is 0
//Shortest distance from source 1 to 2 is 2
//Shortest distance from source 1 to 3 is 3
//Shortest distance from source 1 to 4 is 1
//Shortest distance from source 1 to 5 is 3

//Enter number of vertices: 4
//Enter adjacency matrix:
//0 2 0 3
//0 0 1 0
//-8 0 0 0
//3 0 2 0
//Enter source vertex: 1
//Contains negative Cycle!!!