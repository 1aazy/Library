INSERT INTO person(name, year_of_birth) VALUES ('Max', 2004),
                                               ('Vlad', 2005),
                                               ('Dima', 1999),
                                               ('Danya', 2002),
                                               ('Grisha', 2000);

-- set filed date now --
INSERT INTO book(person_id, title, author, year)
VALUES (1, 'Core Java Volume I: Fundamentals', 'Cay S. Horstmann', 2005),
       (2, 'Effective Java', 'Joshua Bloch', 2007),
       (3, 'Java: A Beginners Guide', 'Herbert Schildt', 2008),
       (4, 'Head First Java', 'Kathy Sierra & Bert Bates', 2015),
       (5, 'Java Concurrency in Practice', 'Joshua Bloch, and so on', 2000);


drop table book;
drop table person;



CREATE TABLE Person(
                       id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                       name varchar(200) NOT NULL,
                       year_of_birth int NOT NULL
);

CREATE TABLE Book (
                      id int PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
                      person_id int REFERENCES person(id) ON DELETE SET NULL,
                      title varchar(100) NOT NULL UNIQUE,
                      author varchar(200) NOT NULL,
                      year int NOT NULL,
                      date date
);
