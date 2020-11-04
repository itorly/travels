
CREATE TABLE t_user(
	id int(6) PRIMARY key auto_increment,
	username VARCHAR(60),
	password VARCHAR(60),
	email VARCHAR(60)
);



CREATE TABLE t_province(
	id int(6) PRIMARY key auto_increment,
	name VARCHAR(60),
	tag VARCHAR(80),
	placecounts int(4)
);


CREATE TABLE t_place(
	id int(6) PRIMARY key auto_increment,
	name VARCHAR(60),
	picpath VARCHAR(100),
	hottime TIMESTAMP,
	hotticket double(7,2),
	dimticket double(7,2),
	placedes VARCHAR(300),
	provinceid int(6) REFERENCES t_province(id)
);