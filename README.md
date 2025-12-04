# BasketballAssignment 

The goal of the test project is to retrieve players for NBA Lakers team
and assert that every player has an average of 3-pointer per game (3PM)
more than one for the last 5 games. Test fails if average is less than 1
and passes if it is greater or equal to 1.

### About the project itself

- created with Maven
- uses Selenium + TestNG

### How to run

Selenium is set up to run headed, but can be set to headless if needed.

1. clone repository
2. add sportsdata.io API key as an enviormental variable
```
export SPORTSDATAIO_API_KEY="xxxxxxxxxxx"
```
3. run tests with Maven
```
mvn test
```
