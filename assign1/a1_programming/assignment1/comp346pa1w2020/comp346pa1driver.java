package comp346pa1w2020;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kerly Titus
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/** A1_programming part
 * @author 1 Hualin Bai (40053833)
 * @author 2 Bo Zhang (40108654)
 * @date 2020-09-25
 * create Thread method: (1)implement Runnable or (2) extends Thread.
 * Using method (2) extends Thread.
 *
 */
public class comp346pa1driver {

    /** 
     * main class
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
    	
    	 /*******************************************************************************************************************************************
    	  * TODO : implement all the operations of main class   																					*
    	  ******************************************************************************************************************************************/

        //print output files
        //File f1 = new File("output_debug.txt");
        //File f1 = new File("output_case1.txt");
        //File f1 = new File("output_case2.txt");
//        File f1 = new File("output_case3.txt");
//        f1.createNewFile();
//        FileOutputStream fileOutputStream = new FileOutputStream(f1);
//        PrintStream printStream = new PrintStream(fileOutputStream);
//        System.setOut(printStream);


    	 //Runnable interface method
        //create 1 network thread
        //Network objNetwork = new Network("network");            /* Activate the network */
        //Thread t_network = new Thread(objNetwork, "t_network");//create a new thread and set thread name
        //Thread t_network = new Thread(objNetwork);
        //t_network.start(); //starting network thread
        //test which is the current thread
        //System.out.println("**the current thread name is " + Thread.currentThread().getName());

    	 //implement the 4 threads (client 2, serve 1, network 1) through extends Thread class.

        //create 1 network thread
    	Network objNetwork = new Network("network");            /* Activate the network */
        objNetwork.start();

        //create 1 server thread
        Server objServer = new Server();                        /* Start the server */ 
        objServer.start();

        //the client has 2 threads including sending the transactions and receiving the completed transactions.
        Client objClient1 = new Client("sending");              /* Start the sending client */
        objClient1.start();

        Client objClient2 = new Client("receiving");            /* Start the receiving client */
        objClient2.start();

//        try{
//            objNetwork.join();
//            objServer.join();
//            objClient1.join();
//            objClient2.join();
//        }catch (InterruptedException e){
//            System.out.println("Thread interrupted.");
//            e.printStackTrace();
//        }





    }
}
