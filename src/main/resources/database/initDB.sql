CREATE TABLE IF NOT EXISTS session
(
    ID     int          NOT NULL,
    CHAT_ID VARCHAR(200) NOT NULL,
    DATE   timestamp    NOT NULL
);
CREATE SEQUENCE session_id_seq START WITH 1 INCREMENT BY 1;