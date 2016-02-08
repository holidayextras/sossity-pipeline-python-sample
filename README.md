
# sossity-identity-pipeline-java

A sample Sossity pipeline using Python to append a datetime to a message.

This project works using Jython -- a Python on the JVM. Jython generates JVM bytecode and has utilities for communication between Python and Java.

The key function is `PythonPipeline(String module, String filePath, String function)`. See `PythonComposer` for usage, and `Main.java` for an example of how this is invoked.

Your main Python function should expect a JSON string and return a JSON string. It should not return an Object.

Module: Python module name
FilePath: path to main Python file, with prepended /, relative to src/main/resources/Lib
Function: function to invoke in main python file

In production, the angled-dream wrapper will connect this to a PubSub for a data source and sink. `Main.java` is only used for testing.

The only file you need to change is PythonComposer, which tells Sossity which Python script and function to execute.


# Developing locally

1. `cd sossity-pipeline-python-sample`
1. Put all your Python files in` src/main/resources/Lib` -- this is the only place they can go. This is because `net.sf.mavenjython`, which packages the dependencies, only recognizes them in that directory.
1. Edit `PythonComposer.java` to give it the correct module, filepath, and function to execute.
1. Pull down the Python dependencies and install them in correct directory: `pip install -r src/main/resources/Lib/requirements.txt -t src/main/resources/Lib`
1. *Do NOT commit the dependencies to GitHub. The Sossity CircleCI scripts will pull them down and package it for Cloud Dataflow*.
1. Execute the python script how you normally would in the Python shell or from the CLI

# Building for manual deployment/simulator use

1. Make sure your package name is unique in the Sossity simulator if you'll be running it there.
1. `mvn package` to bundle all the Java and Python dependencies, assuming you ran `pip install`
1. This should create a `target/pythonsample-bundled-0.1-ALPHA.jar` which you can use in the simulator like any other Sossity jar file.

# How Sossity deploys it.

1. CircleCI pulls down the project from GitHub
2. CircleCI runs `pip` and installs requirements
3. It then runs `mvn install`, which builds a jar package that can be uploaded to/executed by Cloud Dataflow, like any other Java program

