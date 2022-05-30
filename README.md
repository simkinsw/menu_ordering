# Menu_Ordering #
Evive Software Engineer Test

## Requirements
#### General Use ####
* Java 11
* Maven

#### Dependencies ####
* JUnit 4.13.2
* Guava 10.0.1
* SLF4J

## Building ##
Clone the repository and run `mvn install`

## Usage ##
Run the program with `mvn compile exec:java -Dexec.args="<Input>"`

Input should be a meal (Breakfast, Lunch, or Dinner) followed by a space and a 
comma-separated list of Menu numbers, e.g. "Breakfast 1,2,2,3". 

The program will log the names of the items ordered or an error message if the 
order is invalid.

## Testing ##
Tests can be run with `mvn test` and include all 11 Sample Input/Outputs from the
Evive writeup.
