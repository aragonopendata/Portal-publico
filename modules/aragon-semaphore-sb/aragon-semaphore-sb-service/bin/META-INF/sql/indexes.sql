create index IX_89BB591C on EAB_SEMAPHORE_Semaphore (groupId, serviceName[$COLUMN_LENGTH:75$]);
create unique index IX_A44FD46C on EAB_SEMAPHORE_Semaphore (uuid_[$COLUMN_LENGTH:75$], groupId);