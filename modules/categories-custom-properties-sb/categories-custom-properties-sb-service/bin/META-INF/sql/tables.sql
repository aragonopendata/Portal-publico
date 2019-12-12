create table EAB_CCP_CustomCategoryProperty (
	uuid_ VARCHAR(75) null,
	CustomCategoryPropertyId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	vocabularyId LONG,
	categoryId LONG,
	key_ VARCHAR(75) null,
	text_ STRING null
);