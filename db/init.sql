CREATE TABLE dev.services
(
    id INTEGER AUTO_INCREMENT,
    name TEXT NOT NULL, 
    url TEXT NOT NULL,
    created DATETIME DEFAULT NOW(),
    modified DATETIME DEFAULT NOW(),
    status TEXT,
    PRIMARY KEY (id)
);

INSERT INTO dev.services (id, name, url, status)
VALUES (1, 'test_service1', 'http://test_service1:8081/', 'UNKNOWN');