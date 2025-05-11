CSCI 70300 -- 2025 SP

These are the starter code files for Project #3, the process scheduler.

How to Compile and Run

1. To compile: javac *.java
2. Create manifest.txt: Main-Class: schedulerTest
3. Build jar: jar cfm Goal1.jar manifest.txt *.class
4. Run jar: java -jar Goal1.jar

How to run jar file:

## Note on JAR Access Issues
After running (java -jar Goal1.jar) this command line if you get this (Error: Unable to access jarfile Goal1.jar)
Or,
just simply if you can't access the jar file using just simple (java -jar Goal1.jar) command line, 
It's likely because the JARs are inside the `bin/` directory.

Instead, use:
1. java -jar .\bin\Goal1.jar
2. java -jar .\bin\Goal2.jar
3. java -jar .\bin\Goal3.jar
4. java -jar .\bin\Goal4.jar


## Goal 5: Mode Switching
Common Weighted Round Robin (CWRR): java -jar .\bin\Goal4.jar CWRR
I/O Weighted Round Robin (IWRR): java -jar .\bin\Goal4.jar IWRR 


del *.class  # Windows
# or
rm *.class   # Mac/Linux
