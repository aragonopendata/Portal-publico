create index IX_255E74BE on EAB_SN_SocialNetwork (companyId, groupId);
create index IX_B6D3F830 on EAB_SN_SocialNetwork (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_945CCAB2 on EAB_SN_SocialNetwork (uuid_[$COLUMN_LENGTH:75$], groupId);