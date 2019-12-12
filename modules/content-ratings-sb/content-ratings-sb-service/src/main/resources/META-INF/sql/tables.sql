create table EAB_CR_Rating (
	uuid_ VARCHAR(75) null,
	ratingId LONG not null primary key,
	createDate DATE null,
	companyId LONG,
	classNameId LONG,
	classPK LONG,
	score DOUBLE,
	comment_ STRING null
);