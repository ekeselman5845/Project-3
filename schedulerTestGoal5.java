// schedulerTestGoal5.java: Updated testing program for Goal 5

public class schedulerTestGoal5 {

    private static Clock c;
    private static ProcessGenerator ps;
    private static ProcessorCore pr;
    private static Scheduler sc;

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java -jar Goal5.jar <RR|CWRR|IWRR>");
            return;
        }

        Scheduler.SchedulingMode mode;
        try {
            mode = Scheduler.SchedulingMode.valueOf(args[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid scheduling mode. Please use: RR, CWRR, or IWRR.");
            return;
        }

        c = new Clock();
        pr = new ProcessorCore(c);
        sc = new Scheduler(c, pr, 5, mode);
        ps = new ProcessGenerator(c, sc);

        c.start();
        pr.start();
        sc.start();
        ps.start();
    }
}
