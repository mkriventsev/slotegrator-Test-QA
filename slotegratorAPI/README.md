# Slotegrator API test

Developed in Java with Rest Assured 

### Execution
`mvn clean test` - to run from command line. Also you may run them inside the IDE.

During the execution all request/recieve logs will be in the terminal window.

#### /src/test/java/com/slotegrator/api/ folder:

`Constants.java` - Constants, like token,  URIs, Paths;
 
 `Player.java` - Player object class;
 
 `SlotegratorAPITests.java` - Api Test file;
 
 `RequestHelper.java` - helper for authentication, generate requests, set base urls, etc.
 
 #### /src/tst/resources folder:
 
 Schemas of JSON response:
 * `createNewUserResponseJSONSchema.json` - create new player, `POST /players/` request 
 * `getUserResponseJSONSchema.json` - get info about player, `GET /players/{userId}` request 
 
