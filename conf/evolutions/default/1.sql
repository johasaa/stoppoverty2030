# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table signature_model (
  id                        bigint not null,
  first_name                varchar(255),
  last_name                 varchar(255),
  email                     varchar(255),
  organisation              varchar(255),
  group_name                varchar(255),
  number_of_signatures      bigint,
  facebook_url              varchar(255),
  personal_signature        boolean,
  constraint pk_signature_model primary key (id))
;

create sequence signature_model_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists signature_model;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists signature_model_seq;

