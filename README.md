# Public Library
This is a simple web application simulating a public library. It is deployed on a heroku server: https://my-own-webapp.herokuapp.com/

Application is under development.

## PostgreSQL
It uses a heroku postgres add-on to run a remote database for deployed application.

For development purposes local instance of postgres database is used instead. Credentials are stored as environmental variables in .env file.

## Running locally
Heroku CLI is used to run application in local environment. Run commands:
```sh
$ mvn clean install
$ heroku local
```
Application will run on local tomcat server under [localhost:8080](http://localhost:8080/).

It is important to create own .env file with JDBC_DATABASE_URL variable, as it is not tracked by git, because it contains confidential credentials.

Running it locally in any other (eg. on IDE configured Tomcat) way will require to pass own JDBC configuration in Datasource class.
## Deploying to heroku
To deploy this app to heroku run commands:
```sh
$ mvn clean install
$ git add .
$ git commit -m "message"
$ heroku war:deploy target/myOwnWebapp.war --app my-own-webapp
```
Unfortunately preferred way of deploying heroku apps via git is not working properly, and Heroku Maven Plugin must be used.

This section in mostly for my own use in case I forget.