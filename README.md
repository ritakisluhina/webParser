# webParser

This parser extracts product information from the following website:

https://www.aboutyou.de/c/maenner/bekleidung-20290

This is a simple console application.

To run webParser you will need:
* Install at least Java 11
* Load webParser.jar file

## Input
Program will be executed in the following way: 
~$ java -jar webParser.jar

Enter a website and the full path and name of the file you want to create.

For Windows: C:\Documents\Newsletters\YOUR_NAME.json

For Mac: /Users/user/Desktop/YOUR_NAME.json


## Output
The extracted offers will be written into a JSON file.
A short summary with the following information will be printed to stdout:

* Amount of triggered HTTP requests
*  Amount of extracted products
