create schema if not exists uber;

-- drop table if exists uber.station;

create table if not exists uber.station (
	stopID varchar(50) not null,
	stopName varchar(500),
	lat double,
	lon double,
	primary key(stopID)
);