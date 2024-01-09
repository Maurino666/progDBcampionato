-- Popolamento della tabella circuito
INSERT INTO circuito (nome, paese, nCurve, lunghezza)
VALUES 
    ('Circuito Internazionale Napoli', 'Italia', 10, 4.5),
    ('Circuito di Siracusa', 'Italia', 8, 3.8),
    ('Circuito di Zurigo', 'Svizzera', 12, 5.2);

-- Popolamento della tabella gara
INSERT INTO gara (nome, data, circuito)
VALUES 
    ('Gara 1', '2024-01-10', 'Circuito Internazionale Napoli'),
    ('Gara 2', '2024-02-15', 'Circuito di Siracusa'),
    ('Gara 3', '2024-03-20', 'Circuito di Zurigo');

-- Popolamento della tabella scuderia
INSERT INTO scuderia (nome, paese)
VALUES 
    ('Red Bull', 'Austria'),
    ('Ferrari', 'Italia'),
    ('Haas', 'Stati Uniti');

-- Popolamento della tabella vettura
INSERT INTO vettura (modello, scuderia)
VALUES 
    (1, 'Pandino', 'Red Bull'),
    (2, 'Ferrari Roma', 'Ferrari'),
    (3, 'VF23', 'Haas');

-- Popolamento della tabella iscrizione
INSERT INTO iscrizione (gara, vettura)
VALUES 
    ('Gara 1', 1),
    ('Gara 1', 2),
    ('Gara 3', 3);

-- Popolamento della tabella pilota
INSERT INTO pilota (vettura, nome, cognome, dataNascita, nazionalita, tipo, nLicenze, data1Licenza, quotaFinanziamento)
VALUES 
    (1, 'Abdul', 'Rahul', '1990-01-01', 'Arabia Saudita', 'AM', NULL, '2020-01-01', 15000.00),
    (2, 'Matteo', 'Rossi', '1992-03-15', 'Italia', 'PRO', 5, NULL, NULL),
    (3, 'John', 'Smith', '1988-06-20', 'Stati Uniti', 'AM', NULL, '2018-01-01', NULL);

-- Popolamento della tabella costruttore
INSERT INTO costruttore (ragioneSociale, nome, sede, nComponenti)
VALUES 
    ('Brembo', 'Brembo', 'Stezzano', 50),
    ('AP Racing', 'AP Racing', 'Coventry', 60),
    ('Dallara', 'Dallara', 'Parma', 45);

-- Popolamento della tabella componente
INSERT INTO componente (codice, vettura, dataInstallazione, costo, tipo, nCilindri, tipoMotore, cilindrata, nMarce, peso, materiale, costruttore)
VALUES 
    (1, 3, '2022-01-10', 1500.00, 'TELAIO', NULL, NULL, NULL, NULL, 500.00, 'Acciaio', 'Dallara'),
    (2, 2, '2022-05-23', 1800.00, 'CAMBIO', NULL, NULL, NULL, 8, NULL, NULL, 'AP Racing'),
    (3, 1, '2022-03-06', 2000.00, 'MOTORE', 6, 'TURBO', 3.0, NULL, NULL, NULL, 'Brembo');
