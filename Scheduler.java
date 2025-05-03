// Scheduler.java: Simulates the scheduling algorithm of the operating system.
//
// CSCI 70300 -- 2025 SP -- Edgar E. Troudt, Ph.D.
//

import java.util.LinkedList;
import java.util.Queue;


public class Scheduler extends Thread {

	public enum SchedulingMode {
		RR,
		CWRR,
		IWRR
	}
	private SchedulingMode mode;
	private ProcessorCore pcore;
	private Clock         clock;
	private int           quantum;
	// private Process	      queue;   // oops, probably should have a real queue here.
	// 				// this line is definitely not correct.

	private Queue<Process> highQueue = new LinkedList<>();
	private Queue<Process> mediumQueue = new LinkedList<>();
	private Queue<Process> lowQueue = new LinkedList<>();
	
	private int highQuantum = 5;
	private int mediumQuantum = 3;
	private int lowQuantum = 1;
	private int highWeight = 3;
    private int mediumWeight = 2;
    private int lowWeight = 1;    

	public Scheduler ( Clock clock, ProcessorCore pcore, int quantum, SchedulingMode mode) {
		this.pcore   = pcore;
		this.clock   = clock;
		this.quantum = quantum;
		this.mode = mode;
	}

	public void enqueue(Process proc) {
		System.out.println("\t\u001B[34mScheduler: Process #" + proc.getID() + " enqueued with priority " + proc.getPriority() + ".\u001B[0m");
		switch (proc.getPriority()) {
			case 3 -> highQueue.add(proc);
			case 2 -> mediumQueue.add(proc);
			case 1 -> lowQueue.add(proc);
		}
	}
	
	public void run() {
		switch (mode) {
			case RR -> runRR();
			case CWRR -> runCWRR();
			case IWRR -> runIWRR();
		}
	}
	private void runIWRR() {
		Process current = null;
		int ttl = 0;
	
		// Interleaved cycle index
		int iwrrIndex = 0;
	
		while (true) {
			if (current == null) {
				// Interleaved Weighted Round Robin logic
				switch (iwrrIndex % (highWeight + mediumWeight + lowWeight)) {
					case 0, 1, 2: // 3 chances for high priority
						if (!highQueue.isEmpty()) {
							current = highQueue.poll();
							ttl = highQuantum;
						}
						break;
					case 3, 4: // 2 chances for medium
						if (!mediumQueue.isEmpty()) {
							current = mediumQueue.poll();
							ttl = mediumQuantum;
						}
						break;
					case 5: // 1 chance for low
						if (!lowQueue.isEmpty()) {
							current = lowQueue.poll();
							ttl = lowQuantum;
						}
						break;
				}
				iwrrIndex++;
	
				if (current != null) {
					pcore.interrupt(current);
				}
			}
	
			if (current != null) {
				ttl--;
	
				if (ttl <= 0) {
					System.out.println("\t\u001B[34mScheduler: Quantum expired for P" + current.getID() + "\u001B[0m");
					switch (current.getPriority()) {
						case 3 -> highQueue.add(current);
						case 2 -> mediumQueue.add(current);
						case 1 -> lowQueue.add(current);
					}
					pcore.processYields();
					current = null;
				}
			}
			System.out.println("Queues: H=" + highQueue.size() +
				", M=" + mediumQueue.size() +
				", L=" + lowQueue.size());
	
			clock.semaphore();
		}
	}

private void runCWRR(){
	//Goal 3: Classic Weighted Round Robin
		// run infinitely.
		Process current = null;
		int ttl = 0;
	
		while (true) {
			// yield processes in the processor that have exhausted their quantum.
			// keep track of the quantum.
			// if there's a process to run, run it.
			if (current == null) {
				if (!highQueue.isEmpty()) {
					current = highQueue.poll();
					ttl = highQuantum;
					System.out.println("\t\u001B[34mScheduler: Selected P" + current.getID() + " from High Queue\u001B[0m");
				} else if (!mediumQueue.isEmpty()) {
					current = mediumQueue.poll();
					ttl = mediumQuantum;
					System.out.println("\t\u001B[34mScheduler: Selected P" + current.getID() + " from Medium Queue\u001B[0m");
				} else if (!lowQueue.isEmpty()) {
					current = lowQueue.poll();
					ttl = lowQuantum;
					System.out.println("\t\u001B[34mScheduler: Selected P" + current.getID() + " from Low Queue\u001B[0m");
				}
	
				if (current != null) {
					pcore.interrupt(current);
				}
			}
	
			if (current != null) {
				ttl--;
	
				if (ttl <= 0) {
					System.out.println("\t\u001B[34mScheduler: Quantum expired for P" + current.getID() + "\u001B[0m");
					switch (current.getPriority()) {
						case 3 -> highQueue.add(current);
						case 2 -> mediumQueue.add(current);
						case 1 -> lowQueue.add(current);
					}
					pcore.processYields();
					current = null;
				}
			}
			clock.semaphore();
		}
	}
	private void runRR() {
		Process current = null;
		int ttl = 0;
		
		while (true) {
			// If no process is currently running, pick the next process from the queue
			if (current == null) {
				if (!highQueue.isEmpty()) {
					current = highQueue.poll();
					ttl = highQuantum;
				} else if (!mediumQueue.isEmpty()) {
					current = mediumQueue.poll();
					ttl = mediumQuantum;
				} else if (!lowQueue.isEmpty()) {
					current = lowQueue.poll();
					ttl = lowQuantum;
				}
				
				if (current != null) {
					pcore.interrupt(current); 
				}
			}
			
			if (current != null) {
				ttl--;
				
				if (ttl <= 0) {
					System.out.println("\t\u001B[34mScheduler: Quantum expired for P" + current.getID() + "\u001B[0m");
					switch (current.getPriority()) {
						case 3 -> highQueue.add(current);  // High priority
						case 2 -> mediumQueue.add(current);  // Medium priority
						case 1 -> lowQueue.add(current);  // Low priority
					}
					pcore.processYields(); 
					current = null;  
				}
			}
	
			// print the size of each queue
			System.out.println("Queues: H=" + highQueue.size() +
					", M=" + mediumQueue.size() +
					", L=" + lowQueue.size());
			
			clock.semaphore();
		}
	}
	
}
