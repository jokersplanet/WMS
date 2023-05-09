drop table class;

drop table department;

drop table expenditure;

drop table goods;

drop table "group";

drop table income;

drop table outboundrecords;

drop table scraprecords;

drop table unit;

drop table "user";

drop table warehouse;

drop table inboundrecords;

create table "group" (
id                   CHAR(255)                      not null,
name                 CHAR(255)                      not null,
primary key (id)
);

create table class (
id                   CHAR(255)                      not null,
name                 CHAR(255)                      not null,
groupid              CHAR(255)                      not null,
primary key (id),
foreign key (groupid)
      references "group" (id)
);

create table department (
id                   CHAR(255)                      not null,
name                 CHAR(255)                      not null,
notes                LONG VARBINARY,
primary key (id)
);

create table "user" (
uid                  INTEGER                      not null,
number               CHAR(255)                      not null,
password             CHAR(255)                      not null,
username             CHAR(255)                      not null,
gender               SMALLINT                       not null,
photo               IMAGE,
privilege            CHAR(5)                        not null,
birthday             DATE,
department           CHAR(5),
telephone            CHAR(20)                       not null,
address              LONG VARBINARY,
duties               LONG VARBINARY,
primary key (uid),
foreign key (department)
      references department (id)
);

create table warehouse (
id                   CHAR(255)                      not null,
head                 CHAR(255)                      not null,
value                MONEY                        not null,
notes                LONG VARBINARY,
address              LONG VARBINARY                 not null,
name                 CHAR(255)                      not null,
primary key (id),
foreign key (head)
      references "user" (uid)
);

create table unit (
id                   CHAR(255)                      not null,
name                 CHAR(255)                      not null,
primary key (id)
);

create table goods (
uid                  CHAR(255)                      not null,
war_id               CHAR(255),
name                 LONG VARBINARY                 not null,
housingtime          DATE,
outboundtime         DATE,
count                BIGINT                         not null,
price                MONEY,
value                MONEY,
lowerlimit           BIGINT,
upperlimit           BIGINT,
type                 LONG VARBINARY,
class                CHAR(255),
"group"              CHAR(255),
unity                CHAR(255),
primary key (uid),
foreign key (war_id)
      references warehouse (id),
foreign key ("group")
      references "group" (id),
foreign key (unity)
      references unit (id),
foreign key (class)
      references class (id)
);

create table inboundrecords (
recordnumber         CHAR(255)                      not null,
time                 TIME                           not null,
number               CHAR(255)                      not null,
count                BIGINT                         not null,
name                 CHAR(255)                      not null,
warehosuenumber      CHAR(255)                      not null,
supplier             LONG VARBINARY                 not null,
price                MONEY                        not null,
value                MONEY                        not null,
primary key (recordnumber),
foreign key (number)
      references goods (uid)
);

create table expenditure (
number               CHAR(255)                      not null,
recordnumber         CHAR(255),
time                 DATE                           not null,
value                MONEY                        not null,
destination          LONG VARBINARY,
primary key (number),
foreign key (recordnumber)
      references inboundrecords (recordnumber)
);

create table outboundrecords (
recordnumber         CHAR(255)                      not null,
number               CHAR(255)                      not null,
warehousenumber      CHAR(255)                      not null,
time                 DATE                           not null,
count                BIGINT                         not null,
name                 CHAR(255)                      not null,
custom               LONG VARBINARY                 not null,
price                MONEY                        not null,
value                MONEY                        not null,
primary key (recordnumber),
foreign key (number)
      references goods (uid)
);

create table income (
number               CHAR(255)                      not null,
recordnumber         CHAR(255),
time                 DATE                           not null,
value                CHAR(255)                      not null,
origin               LONG VARBINARY,
primary key (number),
foreign key (recordnumber)
      references outboundrecords (recordnumber)
);

create table scraprecords (
recordnumber         CHAR(255)                      not null,
warehousenumber      CHAR(255)                      not null,
number               CHAR(255)                      not null,
count                BIGINT                         not null,
price                MONEY                        not null,
value                MONEY                        not null,
time                 DATE                           not null,
reason               LONG VARBINARY                 not null,
name                 CHAR(255)                      not null,
primary key (recordnumber),
foreign key (number)
      references goods (uid)
);

