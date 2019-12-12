create index IX_2B285B13 on EAB_CI_ImportCategoryRegistry (categoryId);
create index IX_3E6BF5EF on EAB_CI_ImportCategoryRegistry (type_[$COLUMN_LENGTH:75$]);
create index IX_6C1BB5CE on EAB_CI_ImportCategoryRegistry (uuid_[$COLUMN_LENGTH:75$]);
create index IX_423F687A on EAB_CI_ImportCategoryRegistry (vocabularyId, type_[$COLUMN_LENGTH:75$]);