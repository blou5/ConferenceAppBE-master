
--DB Schema
create schema conference_app_db;
CREATE TABLE "users" (
  "user_id" int primary key,
  "first_name" nvarchar(100),
  "last_name" nvarchar(100),
  "email" nvarchar(256),
  "password" nvarchar(256),
  "status" char(1)
);
CREATE TABLE "organizer" (
  "user_id" nchar() primary key references user(user_id),
  "Company_Name" nvarchar(50),
  "biography" nvarchar(300),
  "social_media" nvarchar(30000)
);
CREATE TABLE "speaker" (
  "speaker_id" nchar() ,
  "company_name" nvarchar(50),
  "biography" nvarchar(300),
  "title" nvarchar(50)
);

CREATE TABLE "participant" (
  "user_id" nchar()  references user(user_id),
  "par_nr" int,
  PRIMARY KEY ("user_id")
);

CREATE TABLE "speaker_rate" (
  "par_id" Type references Participant(user_id),
  "speaker_id" Type references Speaker(speaker_id),
  "stars" Type,
 PRIMARY KEY ("par_id","speaker_id")
);

CREATE TABLE "event" (
  "event_id" nchar() ,
  "title " nvarchar(30),
  "start_day" date,
  "end_day" date,
  "Location" nvarchar(100),
  "user_id" nchar() references organizer(user_id),
  "status" boolean,
  "capacity" int,
  PRIMARY KEY ("event_id")
);

CREATE TABLE "track" (
  "track_id" nchar(),
  "room_num" int,
  "room_type" varchar(35),
  "event_id" nchar() references events(event_id),
  "start_time" Type,
  "end_time" Type,
  PRIMARY KEY ("track_id")
);

CREATE TABLE "session" (
  "session_id" nchar(),
  "Title " varchar(30),
  "description" varchar(150),
  "Type" varchar(25),
  "Capacity" int,
  "track_id" Type references tracks(track_id),
  "start_time" Type,
  "end_time" Type,
  PRIMARY KEY ("session_id")
);

CREATE TABLE "session_Speaker" (
  "user_id" Type references speakers(speaker_id) ,
  "session_id" Type references sessions(session_id),
  PRIMARY KEY ("user_id","session_id")
);

CREATE TABLE "participant_Sessions(ticket`)" (
  "user_id" Type references participants(user_id),
  "session_id" Type references sessions(session_id),
  "zone" Type,
  "chair_nr" Type,
  "price" Type,
  "rating_stars" Type,
  PRIMARY KEY ("user_id","session_id")
);









