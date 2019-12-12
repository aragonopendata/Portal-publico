create table EAB_PQ_QueuedProcess (
	uuid_ VARCHAR(75) null,
	queuedProcessId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	executionStartedDate DATE null,
	executionFinishedDate DATE null,
	processName VARCHAR(75) null,
	jsonParameters STRING null,
	status INTEGER
);