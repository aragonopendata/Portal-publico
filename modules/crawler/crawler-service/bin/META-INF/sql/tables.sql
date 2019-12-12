create table EAB_CRAWLER_Page (
	uuid_ VARCHAR(75) null,
	pageId LONG not null primary key,
	title VARCHAR(1000) null,
	contentType VARCHAR(1000) null,
	metaDescription VARCHAR(1000) null,
	metaKeywords VARCHAR(1000) null,
	content TEXT null,
	url VARCHAR(1000) null,
	categoryIds VARCHAR(1000) null,
	rootPageId LONG,
	parentPageId LONG,
	status LONG
);

create table EAB_CRAWLER_RootPage (
	uuid_ VARCHAR(75) null,
	rootPageId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	crawledDate DATE null,
	alias_ VARCHAR(75) null,
	inclusionRules VARCHAR(75) null,
	pageId LONG,
	scheduledCrawl BOOLEAN,
	depth INTEGER
);