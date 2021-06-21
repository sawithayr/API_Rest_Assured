Automate HTTP method of https://gorest.co.in/

There are 5 different scenarios available in the feature file :
1. GET : To verify total users data, total pages, limit, total list in a page (This scenario is quite often to fail due to frequent changes in response data of https://gorest.co.in/) 
2. POST : To Create new user, using scenario outline to save parameters. After created new user, the system will assert data inputed and response output is match.
3. GET : To get and verify new user data. **User id** must input or change manually due to my limitations to create dynamic objects
4. PUT : To edit data user, using scenario outline to save parameters. After edit data user, the system will assert data inputed and response output is match. **User id** must input or change manually due to my limitations to create dynamic objects
5. DELETE : To delete data user. Verify response code, and response message after data deleted. **User id** must input or change manually due to my limitations to create dynamic objects


Steps to run :
1. open the project
2. wait until dependency finish to load
3. open feature file at "src/test/java/features/PostGetPutDeleteMethod.feature"
4. right click in choosen scenario. note : *dont forget to adjust the data (user id)*


This project build with :
- Java
- Maven
- Rest Assured
- Cucumber
