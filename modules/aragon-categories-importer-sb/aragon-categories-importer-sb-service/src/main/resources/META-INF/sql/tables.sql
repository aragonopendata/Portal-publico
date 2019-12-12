create table EAB_CI_ImportCategoryRegistry (
	uuid_ VARCHAR(75) null,
	importCategoryRegistryId LONG not null primary key,
	vocabularyId LONG,
	categoryId LONG,
	importDate DATE null,
	type_ VARCHAR(75) null,
	comment_ VARCHAR(75) null
);