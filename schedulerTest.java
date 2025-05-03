// schedulerTest.java: The testing program for our scheduler.  This should be modified to accept command line input.
//
// CSCI 70300 -- 2025 SP -- Edgar E. Troudt, Ph.D.
//
import java.util.Scanner;

public class schedulerTest {

	private static Clock            c;
	private static ProcessGenerator ps;
	private static ProcessorCore    pr;
	private static Scheduler	    sc;

	public static void main ( String args[] ) {
		if (args.length < 1) {
			System.out.println("Usage: java schedulerTest <RR|CWRR|IWRR>");
			return;
		}

		SchedulingMode mode;
		try {
			mode = SchedulingMode.valueOf(args[0].toUpperCase());
		} catch (IllegalArgumentException e) {
			System.out.println("Invalid scheduling mode. Please use: RR, CWRR, or IWRR.");
			return;
		}
		// everyone follows the computer's clock
		// instantiate the clock.
		c = new Clock();
		// generates a single processor core
		// instantiate the core.
		pr = new ProcessorCore(c);

		// generates the operating system's scheduling algorithm
		// instantiate the scheduler with a quantum of 5.
		sc = new Scheduler(c, pr, 5, mode);
		

		// simulates the user + OS generating needed processes to run
		// instantiate the process generator.
		ps = new ProcessGenerator(c, sc);


		/* FILL IN #9 all of the above. */

		// start our simulation
		c.start();	// start the clock
		pr.start();	// start the processor
		sc.start();	// start the scheduler
		ps.start();	// start the process generator
	}
}