// colorCode.java: Demonstrates printing in color to the command line.

// REFERENCE NOTE:
// Asked CoPilot: Can Java print in color to the command line?
// on 2/28/2025.

public class colorCode {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static void main(String[] args) {
        System.out.println(ANSI_RED + "This text is red!" + ANSI_RESET);
        System.out.println(ANSI_GREEN + "This text is green!" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "This text is yellow!" + ANSI_RESET);
    }
}