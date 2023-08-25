# Multithreaded Club Simulation Program
This is a Java program that simulates a multithreaded, concurrent club scenario using a grid-based model. It demonstrates the movement and interaction of clubgoers in a simulated environment.

## Compilation and Execution
To compile and run the program, follow these steps:

1. Open a terminal.
2. Navigate to the root directory of the program (where the Makefile is located).

### Compilation
Use the make command to compile the Java classes. The compiled classes will be stored in the bin directory.

```bash
make
```
### Running the Simulation
After compilation, run the simulation with the make run command. Optionally, you can provide command-line arguments.

```bash
make run ARGS="noClubgoers gridX gridY max"
```

Replace noClubgoers, gridX, gridY, and max with the corresponding values for the number of clubgoers, the number of X grid cells, the number of Y grid cells, and the maximum people allowed in the club.

### Cleaning Up
To remove compiled classes and clean up the bin directory, use the following command:

```bash
make clean
```

## Note
This README assumes you have the Java Development Kit (JDK) installed, along with the javac and java commands. You'll also need the make command available to compile and run the program.
