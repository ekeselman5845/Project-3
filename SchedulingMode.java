// SchedulingMode.java enum defines the different scheduling algorithms for the OS scheduler.
// It allows for the selection of one of the following modes:
// - RR (Round-Robin): Simple round-robin scheduling where each process gets a fixed quantum of time.
// - CWRR (Classic Weighted Round Robin): Round-robin with weighted priorities, where higher-priority processes get larger time slices.
// - IWRR (Interleaved Weighted Round Robin): Similar to CWRR, but interleaves the execution of processes with different priorities for fairness.


public enum SchedulingMode {
    RR,
    CWRR,
    IWRR
}
