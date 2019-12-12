create index IX_E7FAF219 on EAB_CRAWLER_Page (rootPageId);
create index IX_B0AAFB on EAB_CRAWLER_Page (uuid_[$COLUMN_LENGTH:75$]);

create index IX_E253BB4F on EAB_CRAWLER_RootPage (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_D0E0C991 on EAB_CRAWLER_RootPage (uuid_[$COLUMN_LENGTH:75$], groupId);