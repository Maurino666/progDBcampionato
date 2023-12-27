SELECT * FROM iscrizione;

SELECT gara.nome, gara.durata, iscrizione.vettura, iscrizione.esito, iscrizione.esito/gara.durata AS rapporto
FROM gara JOIN iscrizione 
ON gara.nome = iscrizione.gara;

SELECT scuderia, AVG(informazioniVettura.rapporto) AS rappotoPuntiTempo
FROM vettura JOIN
		(SELECT gara.nome, gara.durata, iscrizione.vettura, iscrizione.esito, iscrizione.esito/gara.durata AS rapporto
		FROM gara JOIN iscrizione 
		ON gara.nome = iscrizione.gara) AS informazioniVettura
ON vettura.numeroGara = informazioniVettura.vettura
GROUP BY scuderia; 

-- operazione 15 incompleta (mancano le conversioni)
SELECT SUM(iscrizione.esito) AS punti, SUM(gara.durata) AS tempo, SUM(iscrizione.esito) / SUM(gara.durata) AS rapporto, vettura
FROM gara JOIN iscrizione
ON gara.nome = iscrizione.gara
GROUP BY iscrizione.vettura;

SELECT scuderia, AVG(informazioniVettura.rapporto) AS rapportoPuntiTempo
FROM vettura JOIN
		(SELECT SUM(iscrizione.esito) / SUM(gara.durata) AS rapporto, vettura
		FROM gara JOIN iscrizione
		ON gara.nome = iscrizione.gara
		GROUP BY iscrizione.vettura) AS informazioniVettura
ON vettura.numeroGara = informazioniVettura.vettura
GROUP BY scuderia;
        
