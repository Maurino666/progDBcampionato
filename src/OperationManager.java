import java.sql.*;
import java.util.*;

public class OperationManager extends DBmanager{

    private int lastNumeroGara;

    public int getLastNumeroGara(){
        return lastNumeroGara;
    }
    
    public OperationManager(Connection con){
        super(con);
        lastNumeroGara = fetchLastNumeroGara();//prendo l'ultimo numero gara dal database
    }   

    //funzione che prende lastNumeroGara dal DB tramite una SELECT
    private int fetchLastNumeroGara(){
        List<Map<String, Object>> selectResult = runQuery(
            "SELECT MAX(numeroGara) AS lastNumeroGara FROM vettura;");
        Object elemento = selectResult.get(0).get("lastNumeroGara");

        return ((Number)elemento).intValue();
    }

    public static String putApici(String str){
        if(str == null)
            return null;
        return "'" + str + "'";
    }
    
    public static int esitoToPunti(int esito){
        //tipo come mariocart
        return 50/esito;
    }

    public static String makeAttributi(String... stringhe){
        StringBuilder result = new StringBuilder();

        result.append("(");
        for (int i = 0; i < stringhe.length; i++ ) {
            if(stringhe[i] != null)
                result.append(stringhe[i]);
            else
                result.append("null");

            if (i < stringhe.length - 1) {
                result.append(", ");
            }
        }
        result.append(")");

        return result.toString();
    }
    

    // operazione 1: registrazione di una scuderia
    public int insertScuderia (String nome, String paese){
        
        String query = "INSERT INTO scuderia(nome, paese) VALUES " + makeAttributi(putApici(nome), putApici(paese)) + ";";
        return runUpdate(query);
    }

    //operazione 2: inserisce una vettura con i componenti 
    public int insertVettura(String modello, String scuderia){
        lastNumeroGara++;
        String query = "INSERT INTO vettura(numeroGara, modello, scuderia) VALUES" + makeAttributi(Integer.toString(lastNumeroGara), putApici(modello), putApici(scuderia)) + ";";
        return runUpdate(query); 

    }

    public int insertComponenteNoCheck (
        String codice, 
        String vettura, 
        String dataInstall, 
        String costo, 
        String tipo, 
        String nCilindri, 
        String tipoMotore, 
        String cilindrata, 
        String nMarce,
        String peso,
        String materiale,
        String costruttore
    ){
        String query = "INSERT INTO componente VALUES " + makeAttributi(
                codice,
                vettura, 
                putApici(dataInstall), 
                costo, 
                putApici(tipo), 
                nCilindri, 
                putApici(tipoMotore), 
                cilindrata,
                nMarce,
                peso,
                putApici(materiale),
                putApici(costruttore)
            ) + ";";    

        System.out.println(query);
        return runUpdate(query);
    }

    public int incrementCostruttore(String costruttore){
        String query = "UPDATE costruttore SET nComponenti = nComponenti + 1 WHERE ragioneSociale = " + putApici(costruttore) + ";";
        return runUpdate(query);
    }


    //operazione 3: aggiunta di un nuovo pilota alla vettura
    public int insertPilota(
        String vettura,
        String nome,
        String cognome,
        String dataNascita,
        String nazionalita,
        String tipo,
        String nLicenze,
        String data1Licenza
    ){

        String query = "INSERT INTO pilota(vettura, nome, cognome, dataNascita, nazionalita, tipo, nLicenze, data1Licenza) VALUES "+ makeAttributi(
                vettura,
                putApici(nome),
                putApici(cognome),
                putApici(dataNascita),
                putApici(nazionalita),
                putApici(tipo),
                nLicenze,
                putApici(data1Licenza)
            ) + ";"; 
        return runUpdate(query);
    }

    //operazione 4: registrazione di un finanziamento per una scuderia
    public int countNonGD(String pilota){
        String query = "SELECT COUNT(codicePilota) AS piloti\r\n" + //
                "FROM pilota\r\n" + //
                "WHERE quotaFinanziamento IS NULL AND vettura IN (\r\n" + //
                "\tSELECT vettura\r\n" + //
                "\tFROM pilota\r\n" + //
                "\tWHERE codicePilota = "+ pilota + ");";
        List<Map<String, Object>> result = runQuery(query);
        return ((Number)result.get(0).get("piloti")).intValue();
    }
    public int updateFinanziamento(String codicePilota, String quotaFinanziamento){
        String query = "UPDATE pilota SET quotaFinanziamento = " + quotaFinanziamento + " WHERE codicePilota = " + codicePilota + " AND quotaFinanziamento IS NULL;";
        return runUpdate(query);
    }

    //operazione 5: iscruzione di una vettura ad una gara
    public int insertIscrizione(String gara, String vettura){
        String query = "INSERT INTO iscrizione(gara, vettura) VALUES " + makeAttributi(putApici(gara), vettura) + ";"; 
        return runUpdate(query);
    }

    //operazione 6: registrazione del risultato conseguito da ciascuna vettura iscritta ad una gara
    public int countVettureGara(String gara){
        String query = "SELECT COUNT(vettura) as vetture FROM iscrizione WHERE gara = " + putApici(gara) + ";";
        List<Map<String, Object>> result = runQuery(query);
        return ((Number)result.get(0).get("vetture")).intValue();
    }

    public List<Map<String, Object>> getVettureGara (String gara){
        String query = "SELECT vettura FROM iscrizione WHERE gara = " + putApici(gara) + ";";
        return runQuery(query);
    }

    public int updateEsito(String gara, String vettura, String esito, String motivoRitiro){
        String query1, query2;
        int result1, result2;
        
        if(motivoRitiro == null){
            query1 = "UPDATE iscrizione SET esito = " + esito + " WHERE gara = " + putApici(gara) + " AND vettura = " + vettura + " AND esito IS NULL AND motivoRitiro IS NULL;";
            query2 = "UPDATE vettura SET punti = punti + " + Integer.toString(esitoToPunti(Integer.parseInt(esito))) + " WHERE numeroGara = " + vettura + ";";
            result1 = runUpdate(query1);
            result2 = runUpdate(query2);
            //restituisce -1 se c'è un errore in una delle due query, altrimenti la simma dei valori
            return (result1 == -1 || result2 == -1)? -1 : result1 + result2;
        }
    
        query1 = "UPDATE iscrizione SET motivoRitiro = " + putApici(motivoRitiro) + " WHERE gara = " + putApici(gara) + " AND vettura = " + vettura + " AND esito IS NULL AND motivoRitiro IS NULL;";
        System.out.println(gara + vettura + esito + motivoRitiro);
        return runUpdate(query1);
    }

    //operazione 7: verifica della possibilità di montare una componente su una vettura
    public int insertComponenteWithCheck(
        String codice, 
        String vettura, 
        String dataInstall, 
        String costo, 
        String tipo, 
        String nCilindri,
        String tipoMotore,
        String cilindrata,
        String nMarce,
        String peso,
        String materiale,
        String costruttore
    ){
        String query = "SELECT DISTINCT tipo FROM componente WHERE vettura = "+ vettura + ";";
        List<Map<String, Object>> checkTable = runQuery(query);
        boolean available = true;
        for(Map<String, Object> map : checkTable){
            if(tipo.equals(map.get("tipo"))){
                available = false;
                break;
            }
        }
        if(available == true)
            return insertComponenteNoCheck(
                    codice, 
                    vettura, 
                    dataInstall, 
                    costo, 
                    tipo, 
                    nCilindri, 
                    tipoMotore, 
                    cilindrata, 
                    nMarce, 
                    peso, 
                    materiale, 
                    costruttore);
        return 0;
    }

    //operazione 8: per ciascuna scuderia stampare la somma totale dei finanziamenti ricevuti
    public List<Map<String, Object>> getFinanziamentiScuderie (){
        String query = 
                "SELECT scuderia, SUM(quotaFinanziamento) AS somma\r\n" + //
                "FROM vettura JOIN pilota\r\n" + //
                "ON vettura.numeroGara = pilota.vettura\r\n" + //
                "WHERE quotaFinanziamento IS NOT NULL\r\n" + //
                "GROUP BY scuderia;";
        return runQuery(query);
    }

    //operazione 9: stampa annuale delle scuderie che hanno partecipato al campionato compreso il numero di finanziamenti
    public List<Map<String, Object>> getReportScuderie (){
        String query = 
                "SELECT scuderia, COUNT(CASE WHEN quotaFinanziamento IS NOT NULL THEN quotaFinanziamento END) AS conteggio\r\n" + //
                "FROM vettura LEFT JOIN pilota\r\n" + //
                "ON vettura.numeroGara = pilota.vettura\r\n" + //
                "GROUP BY scuderia;";
        return runQuery(query);
    }

    //operazione 10: visualizzare i piloti che hanno vinto nel circuito di casa
    public List<Map<String, Object>> getPilotiCasaVincenti(){
        String query = 
                "SELECT codicePilota, nomePilota, cognomePilota, nazionalitaPilota\r\n" + //
                "FROM \r\n" + //
                "\t(SELECT paese AS paeseGara, gara.nome AS nomeGara\r\n" + //
                "\tFROM circuito JOIN gara\r\n" + //
                "\tON circuito.nome = gara.circuito) AS paesiGare\r\n" + //
                "JOIN\r\n" + //
                "\t(SELECT gara, esito, codicePilota, nome AS nomePilota, cognome AS cognomePilota, nazionalita AS nazionalitaPilota\r\n" + //
                "\tFROM iscrizione JOIN pilota\r\n" + //
                "\tON iscrizione.vettura = pilota.vettura) AS vetturePiloti\r\n" + //
                "ON nomeGara = gara\r\n" + //
                "WHERE paeseGara = nazionalitaPilota AND esito = 1;";
        return runQuery(query);
    }

    //operazione 11: per ciascuna scuderia visualizzare la percentuale gentlemand driver di cui si compone l'equipaggio
    public List<Map<String, Object>> getPercentualeGD (){
        String query = 
                "SELECT scuderia, COUNT(CASE WHEN quotaFinanziamento IS NOT NULL THEN codicePilota END)/ COUNT(codicePilota) * 100 AS percentualeGD\r\n" + //
                "FROM vettura JOIN pilota\r\n" + //
                "ON vettura.numeroGara = pilota.vettura\r\n" + //
                "GROUP BY scuderia;";
        return runQuery(query);
    }

    //operazione 12: stampa mensile dei costruttori compreso il numero di componenti che ha fornito
    public List<Map<String, Object>> getCostruttori (){
        String query = "SELECT * FROM costruttore;";
        return runQuery(query);
    }

    //operazione 13: stampa della classifica finale dei punti conseguiti da tutte le vetture
    public List<Map<String, Object>> getClassificaVetture (){
        String query = 
                "SELECT punti, numeroGara FROM vettura\r\n" + //
                "ORDER BY punti DESC;";
        return runQuery(query);
    }

    //operazione 14: stampa classifiche finali di punti per tipo di motore
    public List<Map<String, Object>> getClassificaMotoreAsp (){
        String query = 
                "SELECT punti, numeroGara \r\n" + //
                "FROM vettura JOIN componente\r\n" + //
                "ON vettura.numeroGara = componente.vettura\r\n" + //
                "WHERE  tipoMotore = 'ASPIRATO'\r\n" + //
                "ORDER BY punti DESC;";
        return runQuery(query);
    }
    
    public List<Map<String, Object>> getClassificaMotoreTurbo (){
        String query = 
                "SELECT punti, numeroGara \r\n" + //
                "FROM vettura JOIN componente\r\n" + //
                "ON vettura.numeroGara = componente.vettura\r\n" + //
                "WHERE  tipoMotore = 'TURBO'\r\n" + //
                "ORDER BY punti DESC;";
        return runQuery(query);
    }


    //operazione 15: report che elenchi ciascuna scuderia sulla base del rapporto punti/minuti di gara, mediando tra le macchine appartenenti a ciascuna scuderia
    public List<Map<String, Object>> getReportPuntiMinuti() {

        String query = "SELECT scuderia, AVG(vettura.punti / tempoVettura.tempo) AS media_punti_per_tempo\r\n" +//
                        "FROM vettura JOIN \r\n" + //
                        "\t(SELECT vettura, (SUM(TIME_TO_SEC(gara.durata)/60)) AS tempo\r\n"+ //
                        "\tFROM gara JOIN iscrizione ON gara.nome = iscrizione.gara\r\n" +//
                        "\tGROUP BY vettura) AS tempoVettura\r\n" + //
                        "\tON vettura.numeroGara = tempoVettura.vettura\r\n" + //
                        "\tGROUP BY scuderia;";

                        return runQuery(query);
    }

}