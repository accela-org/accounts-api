# accounts-api
Accounts service is used to handle user accounts.

Accounts built with spring boot framework as restfull back-end service.
Service operates with User and Address entities. Each user can have multiple addresses.
Application equiped with internal SQL database (**H2**).

DB schemas
1. **USER**

|ID|FIRST_NAME|LAST_NAME|
|--|----------|---------|

2. **ADDRESS**

|ID|CITY|STATE|POSTAL_CODE|USER_ID|
|--|----|-----|-----------|-------|

## API

Using accounts-api we are able to perform different operations with Users and their Addresses:
### USER
**GET /users** - retrieve all users

**PUT /users/{id}** - update user with given id

**DELETE /users/{id}** - delete user with given id

**POST /users** - add user. Request body required.

**GET /users/count** - get total number of users.

### ADDRESS
**POST /users/{id}/address** - add address to user

**PUT /users/address/{id}** - update address

**DELETE /users/address{id}** - delete address

## HOW TO RUN
Service can be run using Maven. Navigate to project's root directory in the terminal and do following:
1. Build application. Run `mvn clean install` command.
2. Run it - `mvn spring-boot:run`

Now we can use accounts service. It'll host on localhost:8080

## EXECUTE
To execute endpoints, we can use different tools:
* Browser (GETs only)
* PostMan
* Curl

1. In the browser's address bar print `localhost:8080/users`. we'll get list of prepopulated users in JSON format.
2. in the Postman choose POST command and enter address `localhost:8080/users/1/address`.
    in the Body tab select raw and choose JSON format for request body.
    Insert address:
    ```
    {
      "street" : "123 sTREET",
      "city" : "Dublin",
      "state" : "Co. Dublin",
      "postalCode" : "0001"
    }
    ```

   Send request.
3. Curl. In the terminal send command:
  ```
  curl --location --request POST 'localhost:8080/users' --header 'Content-Type: application/json' \
    --data-raw '{"firstName" : "John", "lastName" : "White"}'
  ```
  
## DB
Applcation is equipped with internal sql db - H2. To check data we can do following.
1. Run project.
2. In the console check logs for H2ConsoleAutoConfiguration and copy connection string:
      ```
      jdbc:h2:mem:d3193925-350a-45eb-8fe7-18df40c611e6
      ```
3. In the browser run localhost:8080/h2-console
4. Find **JDBC URL** field and past connection string.
5. Connect.
6. Choose db. Write queries and execute.