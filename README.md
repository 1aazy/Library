# Library (Spring Boot CRUD tutorial app)
* Small application that automates book accounting.
* Created for learning purposes. 

## Requirements
* JDK 17
* Apache Maven 3.6.3
* PostgreSQL 10+

## Tech stack
* Spring 
  * Boot 3
  * Data (JPA / Hibernate)
* Databases
  * PostgreSQL
* Thymeleaf
* Hibernate Validator
  
## Installation
Clone repository form GitHub.
```shell
git clone https://github.com/1aazy/Library.git
```
Rename "application.properties.origin" to "application.properties" 
and fill out this file with your data source settings.
```shell
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=
```
You can create a tables using "database.sql" file.
```shell
src/resources/static/database.sql
```

## Functionality
App has 2 main endpoints to view list of entities:
```shell
http://localhost:8080/people

http://localhost:8080/books
```

Books:</br>
<img src="media/booksIndex.png" width="400">

You can performe any CRUD operations on entities: </br>

To view one single entity clicking on it: </br>
<img src="media/onebook.png" width="400">

To delete entity click "Удалить"</br>

To edit entity click "Редактировать", fill in the fields and click the button:</br>
<img src="media/edit.png" width="400">

To create new entity click "Создать", fill in the empty fields and click the button:</br>
<img src="media/create.png" width="400">

You can assigned books to person:</br>
<img src="media/assign.png" width="400">
<img src="media/view.png" width="400">
And release books:</br>
<img src="media/release.png" width="400">
If a person has a book for more than 10 days, it is considered expired and is marked in red.</br>
<img src="media/expired.png" width="400">

## Database
Database has this structure (one-to-many relations):</br>
<img src="media\relations.png">




