/**
 * Class DiningPhilosophers
 * The main starter.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 */

import java.io.*;

/**
 * A3_programming part
 *  * @author 1 Hualin Bai (40053833)
 *  * @author 2 Bo Zhang (40108654)
 *  * @date 2020-11-22
 * */


public class DiningPhilosophers
{
	/*
	 * ------------
	 * Data members
	 * ------------
	 */

	/**
	 * This default may be overridden from the command line
	 */
	public static final int DEFAULT_NUMBER_OF_PHILOSOPHERS = 4;

	/**
	 * Dining "iterations" per philosopher thread
	 * while they are socializing there
	 */
	public static final int DINING_STEPS = 10;

	/**
	 * Our shared monitor for the philosphers to consult
	 */
	public static Monitor soMonitor = null;

	/*
	 * -------
	 * Methods
	 * -------
	 */

	/**
	 * Main system starts up right here
	 */
	public static void main(String[] argv)
	{
		try
		{
			/*
			 * TODO:
			 * Should be settable from the command line
			 * or the default if no arguments supplied.
			 */
            /*TODO: Task 3: variable number of philosophers*
             *
             */

			//test to print output file
			//String fileName = "Output_incorrect_case.txt";
//			String fileName = "Output_num_10_case.txt";
//			File f1 = new File(fileName);
//			f1.createNewFile();
//			FileOutputStream fileOutputStream = new FileOutputStream(f1);
//			PrintStream printStream = new PrintStream(fileOutputStream);
//			System.setOut(printStream);
			//test for output

			int iPhilosophers = DEFAULT_NUMBER_OF_PHILOSOPHERS;

			//if no command line arguments, the default number will be used
            if(argv.length == 0){
                System.out.println("\n No Command Line Argument.\n" +
                                        "Default Usage: java DiningPhilosophers [" + iPhilosophers + "]\n" );
            }
            //if there are command line arguments
            else{
                //if the argument is not a positive integer, report the fact to the user
                if(Integer.parseInt(argv[0]) < 1){
                    System.err.println("% java DiningPhilosophers " + argv[0] + "\n"
                                        + argv[0] + " is not a positive decimal integer" + "\n\n"
                                        + "Usage: java DiningPhilosophers[NUMBER_OF_PHILOSOPHERS]\n" + "%");
                    System.exit(0);
                }
                //assign the command line arguments to the int iPhilosophers
                iPhilosophers = Integer.parseInt((argv[0]));
            }


			// Make the monitor aware of how many philosophers there are
			soMonitor = new Monitor(iPhilosophers);

			// Space for all the philosophers
			Philosopher aoPhilosophers[] = new Philosopher[iPhilosophers];


			System.out.println(iPhilosophers + " philosopher(s) came in for a dinner.");

			// Let 'em sit down
			for(int j = 0; j < iPhilosophers; j++)
			{
				aoPhilosophers[j] = new Philosopher();
				aoPhilosophers[j].start();
			}

			//System.out.println(iPhilosophers + " philosopher(s) came in for a dinner.");

			// Main waits for all its children to die...
			// I mean, philosophers to finish their dinner.
			for(int j = 0; j < iPhilosophers; j++)
				aoPhilosophers[j].join();

			System.out.println("All philosophers have left. System terminates normally.");
		}
		catch (NumberFormatException e){
		    //catch non number format
		    if(argv.length == 1){
                System.err.println("% java DiningPhilosophers " + argv[0] + "\n" +
                        "\"" + argv[0] + "\"" + " is not a positive decimal integer\n\n" +
                        "Usage: java DiningPhilosophers[NUMBER_OF_PHILOSOPHERS]\n" + "%\n");
            }
		    else{
                System.err.println("% java DiningPhilosophers: Please enter only 1 arguments!\n\n" +
                        "Usage: java DiningPhilosophers[NUMBER_OF_PHILOSOPHERS]\n" + "%\n");
            }
        }
		catch(InterruptedException e)
		{
			System.err.println("main():");
			reportException(e);
			System.exit(1);
		}
		//for output file test
//		catch (IOException e) {
//			e.printStackTrace();
//		}

	} // main()

	/**
	 * Outputs exception information to STDERR
	 * @param poException Exception object to dump to STDERR
	 */
	public static void reportException(Exception poException)
	{
		System.err.println("Caught exception : " + poException.getClass().getName());
		System.err.println("Message          : " + poException.getMessage());
		System.err.println("Stack Trace      : ");
		poException.printStackTrace(System.err);
	}
}

// EOF
