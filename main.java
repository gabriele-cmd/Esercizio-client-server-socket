public static void Main(String args[]) {
    ClientStr cliente = new ClientStr();
    cliente.connetti();
    cliente.comunica();

    ServerStr servente = new ServerStr();
    servente.attendi ();
    servente.comunica();
}
