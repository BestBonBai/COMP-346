package comp346pa2w2020;

/** A2_programming part
 * @author 1 Hualin Bai (40053833)
 * @author 2 Bo Zhang (40108654)
 * @date 2020-10-26
 *
 *
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kerly Titus
 */
public class Comp346pa2driver {

    /** 
     * main class
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
    	Network objNetwork = new Network( );            /* Activate the network */
        objNetwork.start();

        /*TODO : implement two server threads in the main() method*/
        //create server thread 1
        Server objServer1 = new Server("Thread1");    /* Start the server */
        objServer1.start();
        //create server thread 2
        Server objServer2 = new Server("Thread2");    /* Start the server */
        objServer2.start();

        Client objClient1 = new Client("sending");          /* Start the sending client thread */
        objClient1.start();
        Client objClient2 = new Client("receiving");        /* Start the receiving client thread */
        objClient2.start();
        
      /*..............................................................................................................................................................*/


    }
    
 }
