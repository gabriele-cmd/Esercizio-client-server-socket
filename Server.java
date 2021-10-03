import java.io.*;
import java.net.*;
import java.util.*;

import jdk.internal.org.jline.utils.InputStreamReader;

public class ServerStr{
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta = null;
    String stringaModificata = null;

    BufferedReader inDalClient;
    DataOutputStream outVersoClient;

    public Socket attendi (){
        try{
            System.out.println("1 SERVER partito in esecuzione...");
            server = new ServerSocket(6789); //CREO un server sulla porta 6789
            client = server.accept(); //rimane IN ATTESA di un CLIENT
            server.close(); //CHIUDO il server per INIBIRE altri client

            //associo due oggetti al socket del client per effettuare la scrittura e la lettura
            inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
            outVersoClient = new DataOutputStream(client.getOutputStream());
        }

        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("ERRORE durante l'istanza del server !");
            System.exit(1);
        }
        return client;
    }

    public void comunica (){
        try{
            //rimango IN ATTESA della riga trasmessa dal CLIENT
            System.out.println("3 benvenuto client, scrivi una frase e la trasformo in maiuscolo. Attendo...");
            stringaRicevuta = inDalClient.readLine(); //CLIENT che comunica
            System.out.println("6 ricevuta la stringa dal cliente: " + stringaRicevuta);

            //la MODIFICO e la RISPEDISCO al client
            stringaModificata = stringaRicevuta.toUpperCase(); //modifica in maiuscolo
            System.out.println("7 invio la stringa modificata al client...");
            outVersoClient.writeBytes(stringaModificata + '\n');

            //termina elaborazione sul server: CHIUDO LA CONNESSIONE del client
            System.out.println("9 SERVER: fine elaborazione... buona notte!");
            server.close(); //CHIUSURA SERVER
        }
    }
}