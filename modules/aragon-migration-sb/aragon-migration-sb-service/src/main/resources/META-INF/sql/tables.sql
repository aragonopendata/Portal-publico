create table EAB_MG_Area (
	uuid_ VARCHAR(75) null,
	areaId LONG not null primary key,
	name VARCHAR(75) null
);

create table EAB_MG_Content (
	uuid_ VARCHAR(75) null,
	contentId LONG not null primary key,
	title STRING null,
	url STRING null,
	contentType VARCHAR(75) null,
	shortExcerpt STRING null,
	excerpt STRING null,
	originalBodyHash VARCHAR(75) null,
	originalBody STRING null,
	areaId LONG,
	actionId LONG,
	statusId LONG,
	templateId LONG,
	tags STRING null,
	lastModifiedUserId LONG,
	assignedUserId LONG,
	userGroupId LONG,
	journalFolderId LONG,
	dateCreated DATE null,
	dateModified DATE null,
	comments STRING null,
	visibility INTEGER
);

create table EAB_MG_ContentImage (
	uuid_ VARCHAR(75) null,
	contentImageId LONG not null primary key,
	contentOriginId LONG,
	url STRING null
);

create table EAB_MG_ContentMetadata (
	uuid_ VARCHAR(75) null,
	contentMetadataId LONG not null primary key,
	contentId LONG,
	primaryBody STRING null,
	primaryImage STRING null,
	primaryVideo STRING null,
	secondaryBody STRING null,
	imagesGalleryTitle VARCHAR(75) null,
	imagesGalleryImages STRING null,
	linksListTitle VARCHAR(75) null,
	linksListLinks STRING null
);

create table EAB_MG_ContentRelated (
	uuid_ VARCHAR(75) null,
	contentRelatedId LONG not null primary key,
	contentParentId LONG,
	contentLinkedId LONG,
	url STRING null
);

create table EAB_MG_ContentUrl (
	uuid_ VARCHAR(75) null,
	contentUrlId LONG not null primary key,
	contentOriginId LONG,
	contentDestinationId LONG,
	urlOrigin STRING null,
	urlDestination STRING null,
	className VARCHAR(75) null
);

create table EAB_MG_Rule (
	uuid_ VARCHAR(75) null,
	ruleId LONG not null primary key,
	typeId INTEGER,
	userGroupId LONG,
	journalFolderId LONG,
	assignedUserId LONG,
	url STRING null,
	tags STRING null,
	actionId LONG,
	availableUserGroupId VARCHAR(75) null,
	userId LONG,
	modifiedDate DATE null
);

create table EAB_MG_Template (
	uuid_ VARCHAR(75) null,
	templateId LONG not null primary key,
	name VARCHAR(75) null
);