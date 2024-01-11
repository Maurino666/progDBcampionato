CREATE SCHEMA campionato;
USE campionato;

-- creazione tabella circuito
CREATE TABLE circuito(
nome			VARCHAR(32) PRIMARY KEY,
paese			VARCHAR(32) NOT NULL,
nCurve			INT NOT NULL,
lunghezza		FLOAT NOT NULL
); 

-- creazione tabella gara
CREATE TABLE gara(
nome			VARCHAR(32) PRIMARY KEY,
data			DATE NOT NULL,
durata			TIME NOT NULL,
circuito		VARCHAR(32) NOT NULL,
tipo			ENUM('ASCIUTTO','BAGNATO') DEFAULT 'ASCIUTTO' NOT NULL,

FOREIGN KEY(circuito) REFERENCES circuito(nome)
ON UPDATE CASCADE
ON DELETE RESTRICT
);

-- creazione tabella scuderia
CREATE TABLE scuderia(
nome			VARCHAR(32) PRIMARY KEY,
paese			VARCHAR(32) NOT NULL
);

-- creazione tabella vettura
CREATE TABLE vettura(
numeroGara 		INT PRIMARY KEY,
modello			VARCHAR(32) NOT NULL,
scuderia		VARCHAR(32) NOT NULL,
punti           INT DEFAULT 0 NOT NULL,

FOREIGN KEY(scuderia) REFERENCES scuderia(nome)
ON UPDATE		CASCADE
ON DELETE 		RESTRICT
);

-- creazione tabella iscrizione
CREATE TABLE iscrizione(
gara			VARCHAR(32) NOT NULL,
vettura			INT NOT NULL,
esito			INT ,
motivoRitiro	ENUM('INCIDENTE','GUASTO MECCANICO','SQUALIFICA','ALTRO'),

FOREIGN KEY(gara) REFERENCES gara(nome)
ON UPDATE CASCADE
ON DELETE CASCADE,

FOREIGN KEY(vettura) REFERENCES vettura(numeroGara)
ON UPDATE CASCADE
ON DELETE CASCADE,

PRIMARY KEY(gara, vettura),

CHECK (NOT(esito IS NOT NULL AND motivoRitiro IS NOT NULL))
); 

-- creazione tabella pilota
CREATE TABLE pilota(
codicePilota		INT AUTO_INCREMENT PRIMARY KEY,			
vettura				INT NOT NULL,
nome				VARCHAR(32) NOT NULL,
cognome				VARCHAR(32) NOT NULL,
dataNascita			DATE NOT NULL,
nazionalita			VARCHAR(32) NOT NULL,
tipo				ENUM('AM','PRO') NOT NULL,
nLicenze			INT DEFAULT 1 CHECK(nLicenze > 0), -- Questo Check controlla che il numero di licenze inserite non sia minore di 0, però non impedisce che un AM non possa avere registrato questo dato 
data1Licenza		DATE,
quotaFinanziamento	FLOAT,

FOREIGN KEY(vettura) REFERENCES vettura(numeroGara)
ON UPDATE CASCADE
ON DELETE CASCADE,

-- Questo check serve per controllare che ad un PRO non sia assegnato un finanziamento
CHECK(NOT(tipo = 'PRO' AND quotaFinanziamento IS NOT NULL))
);

-- creazione tabella costruttore
CREATE TABLE costruttore(
ragioneSociale		VARCHAR(32) PRIMARY KEY,
nome				VARCHAR(32) NOT NULL,
sede				VARCHAR(32) NOT NULL,
nComponenti			INT DEFAULT 0 NOT NULL
);

-- creazione tabella componente
CREATE TABLE componente(
codice				INT NOT NULL,
vettura				INT NOT NULL,
dataInstallazione	DATE NOT NULL,
costo				FLOAT NOT NULL,
tipo				ENUM('TELAIO','CAMBIO','MOTORE') NOT NULL,
nCilindri			INT,
tipoMotore			ENUM('TURBO','ASPIRATO'),
cilindrata			FLOAT,
nMarce				TINYINT CHECK(nMarce IN (7,8)),
peso				FLOAT,
materiale			VARCHAR(32),
costruttore			VARCHAR(32) NOT NULL,

FOREIGN KEY(vettura) REFERENCES vettura(numeroGara)
ON UPDATE CASCADE
ON DELETE CASCADE,

FOREIGN KEY(costruttore) REFERENCES costruttore(ragioneSociale)
ON UPDATE CASCADE
ON DELETE RESTRICT,

PRIMARY KEY(codice,vettura),

-- Questi check servono per controllare che non vengano immessi dati che riguardano un componente di tipo diverso da quello inserito
CHECK(NOT(tipo <> 'TELAIO' AND (materiale IS NOT NULL OR peso IS NOT NULL))),
CHECK(NOT(tipo <> 'CAMBIO' AND nMarce IS NOT NULL)),
CHECK(NOT(tipo <> 'MOTORE' AND (tipoMotore IS NOT NULL OR cilindrata IS NOT NULL OR nCilindri IS NOT NULL)))
);

