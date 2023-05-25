class Amigos {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String lista = "";
        String nome = "";

        lista = sc.nextLine();
        String[] listaAtual = lista.split(" ");

        lista = sc.nextLine();
        String[] novaLista = lista.split(" ");

        nome = sc.nextLine();

        if (nome.equals("Nao")) {
            for (int i = 0; i < listaAtual.length; i++)
                System.out.print(listaAtual[i] + " ");

            for (int i = 0; i < novaLista.length; i++)
                System.out.print(novaLista[i] + " ");

        } else {
            int pos = 0;
            for (int i = 0; i < listaAtual.length; i++, pos++) {
                if (listaAtual[i].equals(nome))
                    i = listaAtual.length;

                else {
                    System.out.print(listaAtual[i] + " ");
                }
            }

            for (int i = 0; i < novaLista.length; i++)
                System.out.print(novaLista[i] + " ");

            System.out.print(nome + " ");

            for (int i = pos; i < listaAtual.length; i++)
                System.out.print(listaAtual[i]+ " ");

        }

    }

}