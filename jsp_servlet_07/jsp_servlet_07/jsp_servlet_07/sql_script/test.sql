GRANT ALL PRIVILEGES ON *.* TO javauser@localhost
    IDENTIFIED BY 'javadude' WITH GRANT OPTION;
create database javatest;
use javatest;
create table testdata (
                          id int not null auto_increment primary key,
                          foo varchar(25),
                          bar int);
insert into testdata values(null, 'hello', 12345);