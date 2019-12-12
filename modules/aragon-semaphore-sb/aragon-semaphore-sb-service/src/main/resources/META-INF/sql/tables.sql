create table EAB_SEMAPHORE_Semaphore (
	uuid_ VARCHAR(75) null,
	semaphoreId LONG not null primary key,
	groupId LONG,
	serviceName VARCHAR(75) null,
	maxUsers LONG,
	currentUsers LONG
);