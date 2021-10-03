package com.example;
import java.io.*;
import java.net.*;

import javax.sound.sampled.SourceDataLine;

import jdk.javadoc.internal.doclets.formats.html.SourceToHTMLConverter;

public class ClientStr {
    
    String nomeServer = "localhost";
    int portaServer = 6789;
    Socket mioSocket;

    BufferedReader tastiera; //input dell'utente da tastiera (buffer)
    String stringaUtente;    //string inserita dall'UTENTE
    String stringaRicevutaDalServer; //stringa RICEVUTA dal SERVER
    DataOutputStream outVersoServer; //stream di output che va VERSO il SERVER
    DataInputStream inDalServer; //stream di input in arrivo nel Client DAL SERVER

    public Socket connetti(){ //Metodo che apre un Socket che stabilisce la CONNESSIONE con il SERVER

        System.out.println("2 CLIENT partito in esecuzione...");
        try{
            tastiera = new BufferedReader(new InputStreamReader(System.in)); //INPUT da tastiera
            mioSocket = new Socket(nomeServer, portaServer); //creazione di un SOCKET
            //mioSocket = new Socket(InetAddress.getLocalHost(), 6789);

            //associo due oggetti al socket per effetturare la scrittura e la lettura
            outVersoServer = new DataOutputStream(mioSocket.getOutputStream());
            inDalServer = new BufferedReader(new InputStreamReader (mioSocket.getInputStream()));
        }

        catch (UnknownHostException e){
            System.err.println("Host Sconosciuto");
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la connessione!");
            System.exit(1);
        }
        return mioSocket;
    }

    public void comunica(){
        try{ //leggo una riga
            System.out.println("4... inserisci la stringa da trasmettere al server: " + '\n');
            stringaUtente = tastiera.readLine(); //INPUT della stringa da tastiera
            System.out.println("5... invio la stringa al server e attendo..." + '\n');
            outVersoServer.writeBytes(stringaUtente + '\n'); //INVIO della stringa inserita al SERVER
            stringaRicevutaDalServer = inDalServer.readLine(); //leggo la RISPOSTA del SERVER
            System.out.println("8... risposta dal server " + '\n' + stringaRicevutaDalServer);

            System.out.println("9 CLIENT: termina elabortazione e chiude la connessione"); //CHIUDO la connessione
            mioSocket.close();
        } 

        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Errore durante la comunicazione con il server!");
            System.exit(1);
        }
    }
}
