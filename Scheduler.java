// Scheduler.java: Simulates the scheduling algorithm of the operating system.
//
// CSCI 70300 -- 2025 SP -- Edgar E. Troudt, Ph.D.
//

import java.util.LinkedList;
import java.util.Queue;

public class Scheduler extends Thread {

	private ProcessorCore pcore;
	private Clock         clock;
	private int           quantum;
	// private Process	      queue;   // oops, probably should have a real queue here.
	// 				// this line is definitely not correct.

	private Queue<Process> queue = new LinkedList<>();

	private int           ttl;

	public Scheduler ( Clock clock, ProcessorCore pcore, int quantum ) {
		this.pcore   = pcore;
		this.clock   = clock;
		this.quantum = quantum;
	}

	public void enqueue ( Process proc ) {
		System.out.println( "\t\u001B[34mScheduler: Process #" 
			+ proc.getID() + " enqueued.\u001B[0m" );

		queue.add(proc);
		ttl   = quantum;

		// send the process to the processor.
		// obviously this is not proper behavior.
		// pcore.interrupt( queue );
	}

	public void run() {
		// run infinitely.
		Process current = null;
		ttl = 0;
		while ( true ) {
			// yield processes in the processor that have exhausted their quantum.
			
			// keep track of the quantum.

			/* FILL IN #8 */
			if (current == null && !queue.isEmpty()) {
				current = queue.poll();
				pcore.interrupt(current);
				ttl = quantum;
			}

			if (current != null) {
				ttl--;

				if (ttl <= 0) {
					System.out.println("\t\u001B[34mScheduler: Quantum expired for P" 
					+ current.getID() + "\u001B[0m");

					queue.add(current);

					pcore.processYields();
					current = null;
				}
			}
			clock.semaphore();
		}
	}
}