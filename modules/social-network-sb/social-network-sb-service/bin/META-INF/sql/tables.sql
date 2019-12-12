create table EAB_SN_SocialNetwork (
	uuid_ VARCHAR(75) null,
	socialNetworkId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	userId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	url VARCHAR(75) null,
	imageId LONG,
	extraParameters VARCHAR(75) null,
	alt VARCHAR(75) null
);