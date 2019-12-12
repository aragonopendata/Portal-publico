create table EAB_AMVP_MostVisitedPage (
	uuid_ VARCHAR(75) null,
	mostVisitedPageId LONG not null primary key,
	path_ STRING null,
	visits INTEGER,
	title STRING null,
	position INTEGER,
	visible BOOLEAN
);