###### This repository was cloned from a class repository from BitBucket

# Running a Jython program
* Install Jython. You can find an install file online with a simple web search.
* Classpaths. You will need to locate the following files in your directories and determine the paths to them. Those files are: jython.jar, guava-19.0.jar, jnr-constants-0.8.6.jar, jnr-posix.jar, antlr3-runtime-3.2.jar, and sm3-all.jar. Once you have located these, you can compile the ourCalc.java file like so: < javac -cp .:/path/to/first/file:/path/to/second/file:/etc yourFileName.java>
* Run the file. Now that you have compiled your java program, run it as usual by typing < java yourFileName>

# Usage
When using the calculator it is important to note two things. Firstly, negative numbers represent their negative values with the negative sign AFTER the number (e.g. "20.0-"). Secondly, the use must only calculate expressions with one operator at a time (e.g. "2 + 2 + 2" is invalid).

# Enjoy!
