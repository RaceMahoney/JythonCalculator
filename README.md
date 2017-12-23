###### This repository was cloned from a class repository from BitBucket

# Jyhton Calculator 
This project was a final project for Computer Science 220 Programming Languages. Our goal was to create a calculator program that utilized both Java and Python. Java to handle the GUI of the application and Python for the computations. 

## Installation
#### Requirements
* Java 8 or higher
* Python 2
* Jython 2.7

1. Clone repository onto local machine. 
1. If not already owned, install Jython [here](http://www.jython.org/downloads.html).
2. Locate the following files in your directories and determine the paths to them.
    * jython.jar
    * guava-19.0.jar 
    * jnr-constants-0.8.6.jar 
    * jnr-posix.jar  
    * antlr3-runtime-3.2.jar 
    * sm3-all.jar
3. Compile `ourCalc.java` like so `javac -cp .:/path/to/first/file:/path/to/second/file:/etc ourCalc.java`
4. It is important to remember that the classpath for each of the above files is included in the statement 
5. Once compliation is complete, run with `java ourCalc`


# Usage
When using the calculator it is important to note two things. 
Firstly, negative numbers represent their negative values with the negative sign AFTER the number (e.g. "20.0-" is the same as "-20.0"). 
Secondly, the use must only calculate expressions with one operator at a time (e.g. "2 + 2 + 2" is invalid).


# Authors
* [Brian Rogers](https://github.com/BrianJMRogers)
* [Race Mahoney](https://github.com/RaceMahoney)
* Nick Tocci
