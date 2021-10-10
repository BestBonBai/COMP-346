/**
 * Class Monitor
 * To synchronize dining philosophers.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 */
public class Monitor
{
	/*
	 * ------------
	 * Data members
	 * ------------
	 */

	/*TODO: add member variables and methods to monitor*
	 * Using an enum to record the 3 different states of philosophers
	 * */
	private enum state {THINKING, HUNGRY, EATING, TALKING}
	//An array stores the current states of all philosophers
	private state[] philosopherState;
	//the number of philosophers
	private int numberOfPhilosophers;


	/**
	 * Constructor
	 */
	public Monitor(int piNumberOfPhilosophers)
	{
		// TODO: set appropriate number of chopsticks based on the # of philosophers
		//instance the philosopher state array
		this.numberOfPhilosophers = piNumberOfPhilosophers;
		philosopherState = new state[numberOfPhilosophers];

		//initial every philosopher's state is THINKING as start
		for(int i = 0; i< numberOfPhilosophers; i++){
			philosopherState[i] = state.THINKING;
		}

	}

	/*
	 * -------------------------------
	 * User-defined monitor procedures
	 * -------------------------------
	 */

	/**
	 * Grants request (returns) to eat when both chopsticks/forks are available.
	 * Else forces the philosopher to wait()
	 */
	public synchronized void pickUp(final int piTID)
	{
		// ...
		//record the index of piTID (such as 4, need 4-1=3 in the array)
		int index = piTID -1;

		//change the state of the philosopher to HUNGRY
		philosopherState[index] = state.HUNGRY;

		try{
			while(true){
				//test whether his/her neighbors are eating
				//recall tryToEatTest() if both neighbors are not eating then change his state to EATING, otherwise wait
				tryToEatTest(index);

				//check if the state is EATING
				if(philosopherState[index] != state.EATING){
					wait();
				}
				else{
					break;
				}
			}
		}
		catch (InterruptedException e){
			System.out.println("pickUp(): ");
			DiningPhilosophers.reportException(e);
			System.exit(1);
		}

	}

	/**
	 * When a given philosopher's done eating, they put the chopstiks/forks down
	 * and let others know they are available.
	 */
	public synchronized void putDown(final int piTID)
	{
		// ...
		////record the index of piTID (such as 4, need 4-1=3 in the array)
		int index = piTID - 1;
		//change the state of philosopher is THINKING if finishing EATING
		philosopherState[index] = state.THINKING;

		//check whether both neighbors are waiting to eat
		//if yes, then signal them
		tryToEatTest((index + (numberOfPhilosophers - 1)) % numberOfPhilosophers); //left neighbor
		tryToEatTest((index + 1) % numberOfPhilosophers);//right neighbor

	}

	/**
	 * Only one philopher at a time is allowed to philosophy
	 * (while she is not eating).
	 */
	public synchronized void requestTalk(final int piTID)
	{
		// ...
		//record the index of piTID (such as 4, need 4-1=3 in the array)
		int index = piTID - 1;

		try{
			while(true){
				//check if no one is talking now and the philosopher is not eating
				//if yes, then change his state to TALKING, otherwise wait()
				tryToTalkTest(index);

				if(philosopherState[index] != state.TALKING){
					wait();
				}
				else{
					break;
				}

			}
		}
		catch (InterruptedException e){
			System.out.println("requestTalk(): ");
			DiningPhilosophers.reportException(e);
			System.exit(1);
		}

	}

	/**
	 * When one philosopher is done talking stuff, others
	 * can feel free to start talking.
	 */
	public synchronized void endTalk(final int piTID)
	{
		// ...
		//record the index of piTID (such as 4, need 4-1=3 in the array)
		int index = piTID - 1;

		//change the state to THINKING if finishing talking
		philosopherState[index] = state.THINKING;

		//signal other philosopher who are waiting to talk
		notifyAll();
	}


	/*TODO: add methods**/
	 /**
	 * Try to let philosopher to eat if the state is hungry
	 * @param: int aPiIndex
	 */
	public void tryToEatTest(int aPiIndex){
		//set left and right neighbors to check their states
		state leftPhilosopherState = philosopherState[(aPiIndex + (numberOfPhilosophers - 1)) % numberOfPhilosophers];
		state rightPhilosopherState = philosopherState[(aPiIndex + 1) % numberOfPhilosophers];

		//Both neighbors are not eating and the current philosopher is hungry
		if(leftPhilosopherState != state.EATING && rightPhilosopherState != state.EATING &&
				philosopherState[aPiIndex] == state.HUNGRY){
			//change the state of the philosopher to EATING
			philosopherState[aPiIndex] = state.EATING;

			//if Philosopher is suspend, resume it; otherwise no effect
			this.notify();
		}


	}

	/**
	 * Try to let philosopher to talk if the state is not EATING
	 * @param: int aPiIndex
	 */
	public void tryToTalkTest(int aPiIndex){
		//check if the philosopher is not eating
		if(philosopherState[aPiIndex] == state.EATING){
			return;
		}
		//make sure no one is talking at the moment
		for(int i = 0; i<philosopherState.length; i++){
			if(philosopherState[i] == state.TALKING){
				return;
			}
		}

		//satisfy the two statements: no one talking , and the state is not eating
		//change state to TALKING
		philosopherState[aPiIndex] = state.TALKING;

	}

}

// EOF
