create unique index IX_EB3CCFD3 on EAB_CCP_CustomCategoryProperty (categoryId, key_[$COLUMN_LENGTH:75$]);
create index IX_D03139A6 on EAB_CCP_CustomCategoryProperty (key_[$COLUMN_LENGTH:75$]);
create index IX_C7930EC6 on EAB_CCP_CustomCategoryProperty (uuid_[$COLUMN_LENGTH:75$], companyId);
create unique index IX_8DFC8EC8 on EAB_CCP_CustomCategoryProperty (uuid_[$COLUMN_LENGTH:75$], groupId);
create index IX_510185E7 on EAB_CCP_CustomCategoryProperty (vocabularyId, key_[$COLUMN_LENGTH:75$], text_[$COLUMN_LENGTH:4000$]);