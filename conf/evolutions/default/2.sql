# --- !Ups

create table stoppoverty_facebook_signature (
  user_id                   bigint not null,
  external_user             varchar(255),
  user_name                 varchar(255),
  user_token                varchar(255),
  expiry_date               bigint,
  created_date              timestamp,
  facebook_url              varchar(255),
  email                     varchar(255),
  constraint pk_stoppoverty_facebook_signatur primary key (user_id))
;


create sequence stoppoverty_facebook_signature_seq;

# --- !Downs

drop table if exists stoppoverty_facebook_signature cascade;

drop sequence if exists stoppoverty_facebook_signature_seq;