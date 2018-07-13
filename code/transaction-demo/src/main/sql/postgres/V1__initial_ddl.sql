CREATE TABLE AUTHOR (
	id NUMERIC(20, 0) NOT NULL PRIMARY KEY,
	first_name VARCHAR(200) NOT NULL,
	last_name VARCHAR(200) NOT NULL
);

CREATE TABLE GENRE (
	id NUMERIC(20, 0) NOT NULL PRIMARY KEY,
	short_name VARCHAR(10) NOT NULL,
	name VARCHAR(100) NOT NULL
);

