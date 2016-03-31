create table um_user (
  n_id serial not null,
  n_login text not null,
  n_password VARCHAR(32) not null,
  n_name text,
  n_surname text,
  n_age integer,
  n_skype text,
  n_city text,
  CONSTRAINT pk_um_user PRIMARY KEY (n_id)
);
