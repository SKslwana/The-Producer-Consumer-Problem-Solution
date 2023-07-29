//This is the client program which connnects to the server.
//It sends requests for Critical Section whe it wants to access it
//It waits until the time Critical Section is empty
package producer.consumer.problem_using_socket_programming;

import java.io.*;
import java.net.*;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Monitor 
{
    public static Queue<String> item_q = new PriorityQueue<String>();
    static int capacity = 2;
    
    public static void print_q(){
        System.out.println("----Queue elements----");
        
        item_q.stream().forEach((s) -> {
            System.out.print(s+" | ");
         });
        System.out.println("\n-------------");
                
    }
    //Function called by producer thread
public static void produce(String value) throws InterruptedException
{
    
    //producer thread waits while list is full
    while (item_q.size()==capacity)
        Thread.sleep(5000);;
        
    System.out.println("Producer produced-" +value);
    //to insert the jobs in the list
    //synchronized(item_q){
        item_q.add(value);
        print_q();
        System.out.println("Lock with producer");
        Thread.sleep(5000);
        
    //}
        
    //Notifies the consumer thread that now it can start consuming
    //notify();
    Thread.sleep(5000);
}
//Function called by consumer thread
public static String consume() throws InterruptedException 
{
    //consumer thred waits while list is empty
    while (item_q.size()==0)
        Thread.sleep(5000);
    
    //to retrieve the first job in the list 
    String val=null;
    // synchronized(item_q){
       val = item_q.poll();
       System.out.println("Lock with consumer");
       Thread.sleep(5000);
     //}
    System.out.println("Consumer consumed-"+val);
    
    print_q();
    //and sleep
    Thread.sleep(5000);
    return val;
}
    
public static void main(String arg[])throws IOException, InterruptedException
{
    ServerSocket s=new ServerSocket(7000);
    BufferedReader sc = new BufferedReader(new InputStreamReader(System.in));
    
    //Accept producer 
    Socket ss1=s.accept();
    
    Thread producer;
        producer = new Thread(new Runnable() 
        {
                PrintStream out = new PrintStream(ss1.getOutputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(ss1.getInputStream()));
                
                @Override
                public void run()
        {
                while(true){
                String item=null;
                try{
                    item = in.readLine();
                } catch (IOException e){
                }
                
                    try {
                        produce(item);
                    } catch (InterruptedException e) {
                    }
                out.println("PRODUCE");
                }
        }
 });
 producer.start();
 //Accept conumer
 Socket ss2=s.accept();
 Thread consumer;
        consumer = new Thread(new Runnable() 
        {
            PrintStream out = new PrintStream(ss2.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(ss2.getInputStream()));
            
            
            @Override
            public void run()
            {
                while(true){
                    try{
                        in.readLine();
                        
                    }catch (IOException e){
                    }
                    String item=null;
                    try {
                        item = consume();
                        
                    }catch (InterruptedException e){
                    }
                    out.println(item);
                }
            }
        });
  consumer.start();
}
}
 
                
                

    

