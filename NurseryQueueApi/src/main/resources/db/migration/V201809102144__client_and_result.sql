CREATE TABLE client(
  id serial PRIMARY KEY NOT NULL,
  createdat TIMESTAMP NOT NULL DEFAULT now(),
  updatedat TIMESTAMP NOT NULL DEFAULT now(),
  active BOOLEAN NOT NULL DEFAULT FALSE,
  description TEXT,
  service CHARACTER VARYING(128) NOT NULL,
  login CHARACTER VARYING(256) NOT NULL,
  password CHARACTER VARYING(256) NOT NULL
);

ALTER TABLE client OWNER TO nq_admins;
GRANT ALL ON TABLE client TO nq_admins;
GRANT SELECT ON TABLE client TO nq_users;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE client TO nq_systems;

CREATE UNIQUE INDEX client_service_login_uindex ON client(service, login);

CREATE TABLE result(
  id serial PRIMARY KEY NOT NULL,
  createdat TIMESTAMP NOT NULL DEFAULT now(),
  updatedat TIMESTAMP NOT NULL DEFAULT now(),
  active BOOLEAN NOT NULL DEFAULT FALSE,
  description TEXT,
  idclient INT references client(id) NOT NULL,
  firstNurseryName CHARACTER VARYING(256),
  firstNurseryStanding INT,
  secondNurseryName CHARACTER VARYING(256),
  secondNurseryStanding INT,
  thirdNurseryName CHARACTER VARYING(256),
  thirdNurseryStanding INT
);

ALTER TABLE result OWNER TO nq_admins;
GRANT ALL ON TABLE result TO nq_admins;
GRANT SELECT ON TABLE result TO nq_users;
GRANT SELECT, UPDATE, INSERT, DELETE ON TABLE result TO nq_systems;