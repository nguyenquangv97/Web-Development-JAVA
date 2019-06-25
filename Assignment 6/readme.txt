Assignment 6 design -

Front-end-

For the web UI, I have included Bootstrap 4 for web design. 

i created a landing page for my website. It is called index.jsp

Backend - 
In assignment 6 i created the Servlet package.

i also added an interface to help reading and writing file from the server 
and to the server. Every servlet i created implements this interface.

I create a socket inside the servlet to get data from the server.

----
how to run
----

run assignment 5 server driver: driver > ServerDriver.java

run assignment 5 client driver: driver > ClientDriver.java

upload file to server using assignment 5's client:

available files: 

TeslaModelS.txt
TeslaModel3LongRange.txt
TeslaModel3Standard.txt
TeslaModel3Performance.props
FocusWagonZTW.txt

run assignment 6 index.jsp. this will send an empty post request to the AutomobileList.java (Servlet)

then, the AutomobileList will open up a socket, use the interface to read and write
object from and to the server. it then store the in and out object inside a session to 
be forwarded to the next page.

now the servlet will redirect the page to CarList.jsp

inside CarList.jsp it gets the car list from the in and out object from the session.
the write to the user. it will ask user to select a car in the drop down menu. 
after the user submited, it will send a post request to Configuration.jsp.

this jsp will produce a form with drop down options for the user to select
then it direct to SetChoice.java (another servlet) 

the job of this servlet is to handle the post request from the Configuration.jsp
to set the choices.

then it foward the object toward the Result.jsp

Result.jsp displays the options that the user have selected. as well as the button to try again.

the configuration.jsp uses the auto object to prints out options 