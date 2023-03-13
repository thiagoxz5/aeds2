import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.DateFormat;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

class TP02Q01 {
    // ---------------------------------------------------------------------------------------------------------------
    // //

    public static boolean isFim(String s) {
        return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M');
    }

    // ---------------------------------------------------------------------------------------------------------------
    // //

    public static void main(String[] args) throws IOException {
        System.setProperty("file.encoding", "UTF-8");
        MyIO.setCharset("UTF-8");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
        String[] entrada = new String[1000];
        int numEntrada = 0;
        // Leitura da entrada padrao
        do {
            entrada[numEntrada] = br.readLine();
        } while (isFim(entrada[numEntrada++]) == false);
        numEntrada--; // Desconsiderar ultima linha contendo a palavra FIM
        // Para cada linha de entrada, gerando uma de saida contendo o numero de letras
        // maiusculas da entrada

        Personagem[] personagens = new Personagem[numEntrada];

        for (int i = 0; i < personagens.length; i++)
            personagens[i] = new Personagem();

        for (int i = 0; i < numEntrada; i++) {
            Arq.openRead(entrada[i]);
            personagens[i].ler(Arq.readLine());
            personagens[i].imprimir();
        }
    }
}

class Personagem implements Cloneable {
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

    public Personagem(String nome, int altura, double peso, String corDoCabelo, String corDaPele, String corDosOlhos,
            String anoNascimento, String genero, String homeworld) {
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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getCorDoCabelo() {
        return corDoCabelo;
    }

    public void setCorDoCabelo(String corDoCabelo) {
        this.corDoCabelo = corDoCabelo;
    }

    public String getCorDaPele() {
        return corDaPele;
    }

    public void setCorDaPele(String corDaPele) {
        this.corDaPele = corDaPele;
    }

    public String getCorDosOlhos() {
        return corDosOlhos;
    }

    public void setCorDosOlhos(String corDosOlhos) {
        this.corDosOlhos = corDosOlhos;
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

    @Override
    public Personagem clone() {
        try {
            return (Personagem) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Erro ao clonar objeto");
        }
    }

    public void ler(String linha) {
        linha = linha.replace(" ", ""); // remove os espaços
        linha = linha.replace("'", ""); // remove as ''
        linha = linha.replace("{", "");
        linha = linha.replace("}", "");
        linha = linha.replace(":", "");
        linha = linha.replace("name", "");
        linha = linha.replace("height", "");
        linha = linha.replace("mass", "");
        linha = linha.replace("hair_color", "");
        linha = linha.replace("skin_color", "");
        linha = linha.replace("eye_color", "");
        linha = linha.replace("birth_year", "");
        linha = linha.replace("gender", "");
        linha = linha.replace("homeworld", "");
        // limpa a linha

        int filmIndex = linha.indexOf("films");
        linha = linha.substring(0, filmIndex - 1);
        // remove as informações desnecessarias

        int nameIndex = linha.indexOf("name");
        int heightIndex = linha.indexOf("height");
        int massIndex = linha.indexOf("mass");
        int hair_colorIndex = linha.indexOf("hair_color");
        int skin_colorIndex = linha.indexOf("skin_color");
        int eye_colorIndex = linha.indexOf("eye_color");
        int birth_yearIndex = linha.indexOf("birth_year");
        int genderIndex = linha.indexOf("gender");
        int homeworldIndex = linha.indexOf("homeworld");

        this.nome = linha.substring(nameIndex + 4, heightIndex - 1);
        this.altura = Integer.parseInt(linha.substring(heightIndex + 6, massIndex - 1));
        this.peso = Double.parseDouble(linha.substring(massIndex + 4, hair_colorIndex - 1));
        this.corDoCabelo = linha.substring(hair_colorIndex + 10, skin_colorIndex - 1);
        this.corDaPele = linha.substring(skin_colorIndex + 10, eye_colorIndex - 1);
        this.corDosOlhos = linha.substring(eye_colorIndex + 9, birth_yearIndex - 1);
        this.anoNascimento = linha.substring(birth_yearIndex + 10, genderIndex - 1);
        this.genero = linha.substring(genderIndex + 6, homeworldIndex - 1);
        this.homeworld = linha.substring(homeworldIndex);

    }

    public void imprimir() {
        MyIO.println(" ## " + nome + "##" + altura + "##" + peso + "##" + corDoCabelo + "##" + corDaPele + "##"
                + corDosOlhos + "##" + anoNascimento + "##" + genero + "##" + homeworld);
    }

}
