-- operazione 1
INSERT INTO scuderia(nome, paese) VALUES ('[stringaNome]', '[stringaPaese]');

-- operazione 2
INSERT INTO vettura(modello, scuderia) VALUES ('', '');
-- dopo aver controllato che i tipi siano diversi
INSERT INTO componente(codice, vettura, dataIstallazione, costo, tipo, nCilindri, tipoMotore, cilindrata, nMarce, peso, materiale, costruttore) Values ();
-- x3
UPDATE costruttore SET nComponenti = nComponenti + 1 WHERE ragioneSociale = [costruttore];
-- x3

-- operazione 3
INSERT INTO pilota(vettura, nome, cognome, dataNascita, nazionalita, tipo, nLicenze, data1Licenza) VALUES ();

-- operazione 4
UPDATE pilota 
SET quotaFinanziamento = [valore]
WHERE codicePilota = [codice] AND quotaFinanziamento IS NULL;

-- operazione 5
INSERT INTO iscrizione(gara, vettura) VALUES (,);

-- operazione 6
SELECT vettura FROM iscrizione WHERE gara = [gara];
-- per ogni vettura:
UPDATE iscrizione SET esito = [esito], motivoRitiro = [motivo] WHERE gara = [gara] AND vettura = [vetturai];
UPDATE vettura SET punti = punti + [valore calcolato] WHERE numeroGara = [vettura];

-- operazione 7 
SELECT DISTINCT tipo FROM componente WHERE vettura = [vettura];


-- operazione 8
SELECT scuderia, SUM(quotaFinanziamento) AS somma
FROM vettura JOIN pilota
ON vettura.numeroGara = pilota.vettura
WHERE quotaFinanziamento IS NOT NULL
GROUP BY scuderia;

-- operazione 9	
SELECT scuderia, COUNT(CASE WHEN quotaFinanziamento IS NOT NULL THEN quotaFinanziamento END) AS conteggio
FROM vettura LEFT JOIN pilota
ON vettura.numeroGara = pilota.vettura
GROUP BY scuderia;

-- operazione 10
SELECT codicePilota, nomePilota, cognomePilota, nazionalitaPilota
FROM 
	(SELECT paese AS paeseGara, gara.nome AS nomeGara
	FROM circuito JOIN gara
	ON circuito.nome = gara.circuito) AS paesiGare
JOIN
	(SELECT gara, esito, codicePilota, nome AS nomePilota, cognome AS cognomePilota, nazionalita AS nazionalitaPilota
	FROM iscrizione JOIN pilota
	ON iscrizione.vettura = pilota.vettura) AS vetturePiloti
ON nomeGara = gara
WHERE paeseGara = nazionalitaPilota AND esito = 1;
--deve essere fatto il controllo per le posizioni che non devono essere ripetute nella stessa gara

-- operazione 11
SELECT scuderia, COUNT(CASE WHEN quotaFinanziamento IS NOT NULL THEN codicePilota END)/COUNT(codicePilota) AS percentualeGM
FROM vettura JOIN pilota
ON vettura.numeroGara = pilota.vettura
GROUP BY scuderia;

-- operazione 12
SELECT * FROM costruttore;

-- operazione 13
SELECT punti, numeroGara FROM vettura
ORDER BY punti DESC;

-- operazione 14
-- classifica per i motori turbo
SELECT punti, numeroGara 
FROM vettura JOIN componente
ON vettura.numeroGara = componente.vettura
WHERE  tipoMotore = 'TURBO'
ORDER BY punti DESC;
-- classifica per i motori aspirati
SELECT punti, numeroGara 
FROM vettura JOIN componente
ON vettura.numeroGara = componente.vettura
WHERE  tipoMotore = 'ASPIRATO'
ORDER BY punti DESC;

--operazione 15
SELECT scuderia, AVG(punti/tempo)
FROM vettura JOIN
	(SELECT vettura, SUM(gara.durata) AS tempo
	FROM gara JOIN iscrizione
	ON gara.nome = iscrizione.gara
	GROUP BY vettura) AS tempoVettura
ON vettura.numeroGara = tempoVettura.vettura
GROUP BY scuderia;
