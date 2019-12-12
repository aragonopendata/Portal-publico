create index IX_E2917735 on EAB_MG_Area (uuid_[$COLUMN_LENGTH:75$]);

create index IX_D5FFFEEF on EAB_MG_Content (areaId);
create index IX_8CFA0A1B on EAB_MG_Content (assignedUserId);
create index IX_331A9C4C on EAB_MG_Content (lastModifiedUserId);
create index IX_B6452DCC on EAB_MG_Content (url[$COLUMN_LENGTH:4000$]);
create index IX_3EC08C61 on EAB_MG_Content (uuid_[$COLUMN_LENGTH:75$]);

create index IX_47928376 on EAB_MG_ContentImage (contentOriginId);
create index IX_D545AB00 on EAB_MG_ContentImage (uuid_[$COLUMN_LENGTH:75$]);

create unique index IX_E98D3862 on EAB_MG_ContentMetadata (contentId);
create index IX_81CE92F2 on EAB_MG_ContentMetadata (uuid_[$COLUMN_LENGTH:75$]);

create index IX_557C06EA on EAB_MG_ContentRelated (contentParentId);
create index IX_CC8762F0 on EAB_MG_ContentRelated (uuid_[$COLUMN_LENGTH:75$]);

create index IX_F044E622 on EAB_MG_ContentUrl (contentOriginId);
create index IX_A5E734AC on EAB_MG_ContentUrl (uuid_[$COLUMN_LENGTH:75$]);

create index IX_3BFDD677 on EAB_MG_Rule (typeId);
create index IX_B3C07DA6 on EAB_MG_Rule (uuid_[$COLUMN_LENGTH:75$]);

create index IX_70D47BE8 on EAB_MG_Template (uuid_[$COLUMN_LENGTH:75$]);