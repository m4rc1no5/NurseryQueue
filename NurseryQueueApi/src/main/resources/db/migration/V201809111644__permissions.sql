ALTER TABLE client_id_seq OWNER TO nq_admins;
GRANT ALL ON SEQUENCE client_id_seq TO nq_admins;
GRANT SELECT ON SEQUENCE client_id_seq TO nq_users;
GRANT SELECT, UPDATE ON SEQUENCE client_id_seq TO nq_systems;

ALTER TABLE result_id_seq OWNER TO nq_admins;
GRANT ALL ON SEQUENCE result_id_seq TO nq_admins;
GRANT SELECT ON SEQUENCE result_id_seq TO nq_users;
GRANT SELECT, UPDATE ON SEQUENCE result_id_seq TO nq_systems;