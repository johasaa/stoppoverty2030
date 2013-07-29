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
  personal_signature        boolean,
  constraint pk_signature_model primary key (id))
;

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

create sequence signature_model_seq;

create sequence stoppoverty_facebook_signature_seq;




# --- !Downs

drop table if exists signature_model cascade;

drop table if exists stoppoverty_facebook_signature cascade;

drop sequence if exists signature_model_seq;

drop sequence if exists stoppoverty_facebook_signature_seq;

