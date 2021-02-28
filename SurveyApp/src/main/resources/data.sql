insert into survey values(1, 1, 'Test Survey');

insert into question values(1, 'How are you?',1, 1);
insert into answer values(1,0,'Good' ,1);
insert into answer values(2,0,'Bad', 1);
insert into answer values(3,0,'OK', 1);

insert into question values(2, 'Test Question 1', 0, 1);
insert into answer values(4,0,'answer 1' ,2);
insert into answer values(5,0,'answer 2', 2);
insert into answer values(6,0,'answer 3', 2);


insert into survey values(2, 1, 'Test Survey 2');

insert into question values(3, 'How are you?',1,2);
insert into answer values(7,0,'answer 1' ,3);
insert into answer values(8,0,'answer 2', 3);
insert into answer values(9,0,'answer 3', 3);

insert into user values(1,'{noop}password','2017-07-23','ADMIN','ADMIN');
insert into user values(2,'{noop}password','2017-07-23','USER','USER');
