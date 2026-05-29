# Contiguous Memory Allocation Simulator

## Overview
This project simulates the memory allocation process performed by an Operating System using the **First Fit Algorithm**. It provides a practical implementation of contiguous memory management with dynamic hole tracking.

## Features
- **First Fit Algorithm**: Efficiently finds and allocates the first available hole large enough for a process
- **Hole Tracking**: Maintains a holes table that tracks all available memory chunks
- **Dynamic Updates**: Continuously updates the holes table as memory is allocated to processes
- **Simulation-based**: Demonstrates OS memory management without complex deallocation logic

## Algorithm Overview
The First Fit algorithm searches through available memory holes in order and allocates memory to the first hole that is sufficiently large. This approach reduces search time compared to other allocation strategies by avoiding unnecessary iterations through all available holes.

### How It Works
1. Start from the beginning of the holes table
2. Search for the first hole with size >= requested memory
3. Allocate memory in that hole
4. Update the holes table by adjusting or removing the used hole
5. Continue tracking remaining available memory

## Complexity Analysis

### Time Complexity
- **Best Case**: O(1) - When the first hole is large enough for allocation
- **Average Case**: O(n) - Where n is the number of holes in the holes table
- **Worst Case**: O(n) - When the process needs to traverse all holes before finding a suitable one

### Space Complexity
- **O(n)** - Where n is the number of processes/holes being tracked in the holes table


## Example Output
```
Initial Memory: 1000 MB

Process 1 (Size: 100 MB) - Allocated Successfully
Holes Table:
  Hole 1: Start=100, Size=900

Process 2 (Size: 150 MB) - Allocated Successfully
Holes Table:
  Hole 1: Start=250, Size=750

Process 3 (Size: 2000 MB) - Allocation Failed (Insufficient Memory)
```

## Advantages of First Fit
- Faster allocation compared to Best Fit and Worst Fit algorithms
- Simple and easy to implement
- Reduces fragmentation compared to naive approaches

## Limitations
- This is a simulation and does not include deallocation functionality, as it is beyond the scope of this project
- Assumes contiguous memory allocation model
- Does not handle external fragmentation mitigation
- First Fit may leave scattered small holes that cannot be used

## Future Enhancements
- Deallocation and garbage collection
- Memory compaction to handle fragmentation
- Support for other allocation algorithms (Best Fit, Worst Fit, Next Fit)
- Visualization of memory allocation process
- Performance comparison between different allocation strategies
- Virtual memory support

## Author
Prajwal CV

## References
- Operating System Concepts (Silberschatz, Galvin, Gagne)
- Memory Management in Operating Systems
