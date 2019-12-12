create index IX_BBCF0AA3 on EAB_PQ_QueuedProcess (groupId, processName[$COLUMN_LENGTH:75$], status);
create index IX_5FABA251 on EAB_PQ_QueuedProcess (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_59AA8913 on EAB_PQ_QueuedProcess (uuid_[$COLUMN_LENGTH:75$], groupId);