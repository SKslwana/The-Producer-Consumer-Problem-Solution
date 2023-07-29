//The Producer class
package producer.consumer.problem_using_socket_programming;

import java.io.*;
import static java.lang.System.in;
import java.net.*;

public class Producer 
{
    public static void main(String args[]) throws IOException, InterruptedException
    {
        Socket s=new Socket("localhost",7000);
        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
        
        //Inputs Ouput Streams
        PrintStream out = new PrintStream(s.getOutputStream());
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        
        while(true){
            System.out.println("Want to produce?");
            String console_inp=console.readLine();
            
         if(console_inp.equalsIgnoreCase("Yes")){
             String item=console.readLine();
             
             out.println(item);
             
             in.readLine();
             System.out.println("Producer produced-"+item);
         }
        }
        
    }
    
}
