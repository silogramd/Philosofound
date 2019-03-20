**Philosofound**
The back end to our philosofound project

Currently implemented: questions, answers, basic user info (name + email)
User is written but not functional for testing purposes - 
we want a working question answer interface first

To test:

1:
> sudo mysql --password

> mysql> create database db_example; -- Create the new database

> mysql> create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user

> mysql> grant all on db_example.* to 'springuser'@'%'; -- Gives all the privileges to the new user on the newly created database
(this allows our application to access the mysql database)

2:
> git clone https://github.com/JakeChvatal/philosofound-backend.git

> cd philosofound

> ./mvnw spring-boot:run
(starts the server in the terminal)

To test, visit these URLs:
> localhost:8080/api/all - shows what is stored

> localhost:8080/api/add/question?question=(question)

> localhost:8080/api/add/answer?questionID=(questionID)&answer=(answer)

where (question) is your question with '+' denoting spaces,
questionID is the number of the question(the front end will handle this questionID logic,
so the end user will not have to be concerned with it)
and (answer) is your answer

This should store things in your database if run correctly; 
the database (as of now) will reset every time you restart the application

Check out the database in MySQL Workbench while the server is running to monitor it!
