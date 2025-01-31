import java.io.*;

class Personagem {
    private String nome;
    private int altura;
    private double peso;
    private String corDoCabelo;
    private String corDaPele;
    private String corDosOlhos;
    private String anoNascimento;
    private String genero;
    private String homeworld;

    public Personagem() {
    }

    public Personagem(String nome, int altura, double peso, String corDoCabelo, String corDaPele,
            String corDosOlhos, String anoNascimento, String genero, String homeworld) {
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.corDoCabelo = corDoCabelo;
        this.corDaPele = corDaPele;
        this.corDosOlhos = corDosOlhos;
        this.anoNascimento = anoNascimento;
        this.genero = genero;
        this.homeworld = homeworld;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getAltura() {
        return altura;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getPeso() {
        return peso;
    }

    public void setCorDoCabelo(String corDoCabelo) {
        this.corDoCabelo = corDoCabelo;
    }

    public String getCorDoCabelo() {
        return corDoCabelo;
    }

    public void setCorDaPele(String corDaPele) {
        this.corDaPele = corDaPele;
    }

    public String getCorDaPele() {
        return corDaPele;
    }

    public void setCorDosOlhos(String corDosOlhos) {
        this.corDosOlhos = corDosOlhos;
    }

    public String getCorDosOlhos() {
        return corDosOlhos;
    }

    public String getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(String anoNascimento) {
        this.anoNascimento = anoNascimento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public Personagem clone() {
        Personagem cloned = new Personagem();

        cloned.nome = this.nome;
        cloned.altura = this.altura;
        cloned.peso = this.peso;
        cloned.corDoCabelo = this.corDoCabelo;
        cloned.corDaPele = this.corDaPele;
        cloned.corDosOlhos = this.corDosOlhos;
        cloned.anoNascimento = this.anoNascimento;
        cloned.genero = this.genero;
        cloned.homeworld = this.homeworld;

        return cloned;
    }
}

class Lista {
    static Personagem[] listaPersonagens = new Personagem[100];
    int contadorTamanho = 0;
    int numeroComparacoes = 0, numeroMovimentacoes = 0;
    int k = 10;

    public void inserir(Personagem personagem) throws Exception {
        if (contadorTamanho >= listaPersonagens.length) {
            throw new Exception("Erro!");
        }
        listaPersonagens[contadorTamanho] = personagem;
        contadorTamanho++;
    }

    public void ordenaPorQuicksort() {
        ordenaPorQuicksort(0, contadorTamanho - 1);
    }

    public void ordenaPorQuicksort(int esq, int dir) {
        int i = esq, j = dir;
        Personagem pivo = listaPersonagens[(esq + dir) / 2];
        while (i <= j) {
            while ((listaPersonagens[i].getCorDoCabelo()).compareTo(pivo.getCorDoCabelo()) < 0) {
                i++;

                numeroComparacoes++;
            }
            while ((listaPersonagens[j].getCorDoCabelo()).compareTo(pivo.getCorDoCabelo()) > 0) {
                j--;

                numeroComparacoes++;
            }
            if (i <= j) {
                swapPersonagem(i, j);
                i++;
                j--;

                numeroComparacoes++;
            }
        }

        numeroComparacoes++;

        if (esq < j) {
            numeroComparacoes++;

            ordenaPorQuicksort(esq, j);
        }
        if (i < k && i < dir) {
            numeroComparacoes++;

            ordenaPorQuicksort(i, dir);
        }
    }

    public void swapPersonagem(int i, int j) {
        Personagem personagemTemporario = listaPersonagens[i];
        listaPersonagens[i] = listaPersonagens[j];
        listaPersonagens[j] = personagemTemporario;

        numeroMovimentacoes += 3;
    }

    public void ordenaPorInsercao() {
        for (int i = 1; i < contadorTamanho; i++) {
            Personagem personagemTemporario = listaPersonagens[i];
            int j = i - 1;

            while ((j >= 0) && (((listaPersonagens[j].getCorDoCabelo())
                    .compareTo(personagemTemporario.getCorDoCabelo()) == 0)
                    && ((listaPersonagens[j].getNome())
                            .compareTo(personagemTemporario.getNome()) > 0))) {
                numeroComparacoes += 2; 
                numeroMovimentacoes++;
                listaPersonagens[j + 1] = listaPersonagens[j];
                j--;
            }

            numeroComparacoes++;
            numeroMovimentacoes++;
            listaPersonagens[j + 1] = personagemTemporario;
        }
    }

    public void imprimeResultados() {
        for (int i = 0; i < k; i++) {
            MyIO.print(" ## " + listaPersonagens[i].getNome());
            MyIO.print(" ## " + listaPersonagens[i].getAltura());
            if (listaPersonagens[i].getPeso() % 1 == 0) {
                MyIO.print(" ## " + (int) listaPersonagens[i].getPeso());
            } else {
                MyIO.print(" ## " + listaPersonagens[i].getPeso());
            }
            MyIO.print(" ## " + listaPersonagens[i].getCorDoCabelo());
            MyIO.print(" ## " + listaPersonagens[i].getCorDaPele());
            MyIO.print(" ## " + listaPersonagens[i].getCorDosOlhos());
            MyIO.print(" ## " + listaPersonagens[i].getAnoNascimento());
            MyIO.print(" ## " + listaPersonagens[i].getGenero());
            MyIO.print(" ## " + listaPersonagens[i].getHomeworld());
            MyIO.println(" ## ");
        }
    }
}

public class TP02Q22 {
    public static void main(String[] args) throws Exception {
        MyIO.setCharset("ISO-8859-1");
        long tempoInicial = System.currentTimeMillis();

        Lista listaPersonagens = new Lista();

        String caminhoArquivo = MyIO.readLine().replaceAll("é", "\u00e9");

        while (testaFim(caminhoArquivo) == false) {
            listaPersonagens.inserir(montaPersonagem(caminhoArquivo));
            caminhoArquivo = MyIO.readLine().replaceAll("é", "\u00e9");
        }

        listaPersonagens.ordenaPorQuicksort();
        listaPersonagens.ordenaPorInsercao();
        listaPersonagens.imprimeResultados();
    }

    public static boolean testaFim(String palavra) {
        boolean teste = false;

        if (palavra.equals("FIM")) {
            teste = true;
        }

        return teste;
    }

    public static Personagem montaPersonagem(String caminhoArquivo) throws Exception {
        RandomAccessFile leitura = new RandomAccessFile(caminhoArquivo, "r");

        String descricaoPersonagem = leitura.readLine().replaceAll("é", "\u00e9");

        Personagem personagem = new Personagem();
        int contadorDoisPontos = 0; 

        for (int i = 0; i < descricaoPersonagem.length(); i++) {
            if (descricaoPersonagem.charAt(i) == ':') {
                contadorDoisPontos++;

                switch (contadorDoisPontos) {
                    case 1:
                        personagem.setNome(leituraAtributo(descricaoPersonagem, i + 3));
                        break;
                    case 2:
                        String atributoInteiro = leituraAtributo(descricaoPersonagem, i + 3);
                        if (atributoInteiro.equals("unknown")) {
                            personagem.setAltura(0);
                        } else {
                            personagem.setAltura(Integer.parseInt(atributoInteiro));
                        }
                        break;
                    case 3:
                        String atributoDouble = leituraAtributo(descricaoPersonagem, i + 3).replaceAll(",", ".");
                        if (atributoDouble.equals("unknown")) {
                            personagem.setPeso(0);
                        } else {
                            personagem.setPeso(Double.parseDouble(atributoDouble));
                        }
                        break;
                    case 4:
                        personagem.setCorDoCabelo(leituraAtributo(descricaoPersonagem, i + 3));
                        break;
                    case 5:
                        personagem.setCorDaPele(leituraAtributo(descricaoPersonagem, i + 3));
                        break;
                    case 6:
                        personagem.setCorDosOlhos(leituraAtributo(descricaoPersonagem, i + 3));
                        break;
                    case 7:
                        personagem.setAnoNascimento(leituraAtributo(descricaoPersonagem, i + 3));
                        break;
                    case 8:
                        personagem.setGenero(leituraAtributo(descricaoPersonagem, i + 3));
                        break;
                    case 9:
                        personagem.setHomeworld(leituraAtributo(descricaoPersonagem, i + 3));
                        i = descricaoPersonagem.length();
                        break;
                    default:
                        break;
                }
            }
        }

        leitura.close();

        return personagem;
    }

    public static String leituraAtributo(String descricaoPersonagem, int index) {
        String atributo = new String();

        while (descricaoPersonagem.charAt(index) != '\'') {
            atributo += descricaoPersonagem.charAt(index);

            index++;
        }

        return atributo;
    }
}