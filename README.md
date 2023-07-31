# The-Producer-Consumer-Problem-Solution
In this implementation, the producer generates random student information and wraps it in XML format using the `Itstudent` class. The XML file is named based on the generated number.

The producer then places the integer corresponding to the file name in the buffer queue.

The consumer continuously reads the content of the XML file shared with the buffer. It unwraps the XML file and gathers the student information into an `Itstudent` object. It calculates the average mark and determines whether the student passed or failed.

The consumer prints the student information, average mark, and pass/fail status on the screen.

The producer and consumer run concurrently in separate threads. The producer generates and wraps student information in XML files, while the consumer reads and processes the XML files.

In the generate_name function under the 'ITstudent' class, the random.choice function keeps on returning the same student name in some student files. Thats the challenge we have right now.
