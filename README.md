CSCI 70300 -- 2025 SP

These are the starter code files for Project #3, the process scheduler.

How to Compile and Run

1. To compile: javac *.java
2. Create manifest.txt: Main-Class: schedulerTest
3. Build jar: jar cfm Goal1.jar manifest.txt *.class
4. Run jar: java -jar Goal1.jar

How to run jar file:

## Note on JAR Access Issues
After running (java -jar Goal1.jar) this command line if you get this (Error: Unable to access jarfile Goal1.jar), just use "cd bin" command line to be in the `bin/` directory.
Or,
just simply if you can't access the jar file using just simple (java -jar Goal1.jar) command line, 
It's likely because the JARs are inside the `bin/` directory.

Instead, use:
1. java -jar .\bin\Goal1.jar
2. java -jar .\bin\Goal2.jar
3. java -jar .\bin\Goal3.jar
4. java -jar .\bin\Goal4.jar
5. java -jar .\bin\Goal5.jar

## Goal 5: Mode Switching
Common Weighted Round Robin (CWRR): java -jar .\bin\Goal4.jar CWRR
I/O Weighted Round Robin (IWRR): java -jar .\bin\Goal4.jar IWRR 

## Goal 5: Final Jar
Even there is no change for the code in Goal 5 except packaging, so we run the CWRR AND IWRR from Goal 4 code. However, we still create a Final jar named Goal5.jar

To run Goal5 jars:
1. java -jar Goal5.jar RR  (java -jar bin/Goal5.jar RR)
2. java -jar bin/Goal5.jar CWRR
3. java -jar bin/Goal5.jar IWRR


del *.class  # Windows
# or
rm *.class   # Mac/Linux
