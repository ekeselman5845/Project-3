// Process.java: Simulates an individual process with an ID, start time and run time.
//
// CSCI 70300 -- 2025 SP -- Edgar E. Troudt, Ph.D.

public class Process {

	private final int pID;		// process ID
	private	      int start;	// start time
	private       int runLeft;	// remaining run time to completion
	private int priority; // 1, 2, or 3


	public Process ( int pID, int start, int runLeft, int priority ) {
		// simple validity checks - make sure that the values provides are reasonable.
		
		// FILL IN #2.
		if ( pID < 0 || start < 0 || runLeft < 0 || priority < 1 || priority > 3) {
			throw new IllegalArgumentException( "Process ID must be >= 0" );
		}
		this.pID = pID;
		this.start = start;
		this.runLeft = runLeft;
    	this.priority = priority;
	}

	// returns process ID
	public int getID() {
		// FILL IN #3.
		return pID;
	}
	public int getPriority() {
		return priority;
	}

	// causes the process to run for one unit of time.
	public boolean runUnit () {
		if ( runLeft == 0 ) {
			return false;
		}
		runLeft--;
		System.out.println( "\t\u001B[32m\u001B[4mProcess: Process #" + pID 
			+ " executes 1 unit -- beep, beep, bloop, bloop.\u001B[0m" );
		return true;
	}
}
