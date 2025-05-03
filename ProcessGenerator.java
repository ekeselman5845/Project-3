// ProcessGenerator.java: A simulator that randomly spawns processes with a TTL (time to live).
//
// CSCI 70300 -- 2025 SP -- Edgar E. Troudt, Ph.D.
//

public class ProcessGenerator extends Thread {

	private int       numProc;
	private Clock 	  clock;
	private Scheduler scheduler;

	// constructor
	public ProcessGenerator ( Clock clock, Scheduler scheduler ) {
		// accept set values.
		/* FILL IN #4 */
		this.clock = clock;
		this.scheduler = scheduler;
		this.numProc = 0;
	}

	// main method of the ProcessGenerator
	public void run() {

		while ( true ) {
			// 60% chance that a process will be generated during any particular clock cycle.
			if ( Math.random() > /* FILL IN #5 */ 0.4 ) {
				// generate a random runtime
				int runtime = (int) (Math.random() * 20) + 1;
				// generate a random priority
				// 1 = high, 2 = medium, 3 = low
				int priority = (int) (Math.random() * 3) + 1;  // 1 to 3
				Process p = new Process(numProc, clock.getTime(), runtime, priority);

				System.out.println( "\t\u001B[32mProcess Generator: P" + numProc 
					+ " (" + clock.getTime() + "," + runtime + ")\u001B[0m" );

				// put the generated process into our scheduler
				/* FILL IN #6 */
				scheduler.enqueue(p);

				numProc++;
			}

			// end after 15 processes are created
			if ( numProc >= 15 ) {
				break;
			}

			// the semaphore holds this process until the next Clock cycle.
			clock.semaphore();

		}
	}
}