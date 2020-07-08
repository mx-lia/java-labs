create table if not exists users (
	id bigint auto_increment,
    firstName varchar(255),
    secondName varchar(255),
    email varchar(255),
    password varchar(255),
    primary key(id)
);
create table if not exists cards (
	number varchar(255),
    validDate date,
    balance numeric(20,2),
    CVV varchar(3),
    user_id bigint,
     primary key(number)
);
alter table cards
  add constraint cards_users_fk
  foreign key (user_id) references users (id);
drop procedure if exists GetCards;
create procedure `GetCards`()
	not deterministic	
		select * from cards;
drop procedure if exists GetCardByNumber;
	create procedure `GetCardByNumber`(num varchar(255))
		not deterministic	
		select * from cards where number = num limit 1;
drop procedure if exists addCard;
	create procedure `addCard` (in number varchar(255), in validDate date, in balance numeric(20,2), in CVV varchar(3), in user_id bigint)
	not deterministic	
		insert into cards(number, validDate, balance, CVV, user_id) values (number, validDate, balance, CVV, user_id);
drop procedure if exists GetUser;
	create procedure `GetUser` (in email varchar(255), in password varchar(255))   
    not deterministic	
		select * from users where email = email and password = password LIMIT 1;
	drop procedure if exists pay;
	create procedure `pay` (in number varchar(255), in balance numeric(20,2))
    not deterministic	
		update cards set balance = balance where cards.number = number;
drop procedure if exists AddUser;
	create procedure `AddUser` (in firstname varchar(255), in secondname varchar(255), in email varchar(255), in password varchar(255))
    not deterministic	
		insert into users(firstName, secondName, email, password) values (firstname, secondname, email, password);