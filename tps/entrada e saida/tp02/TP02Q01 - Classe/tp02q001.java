import java.io.*;
import java.util.*;

class Personagem {
    private String nome;
    private String altura;
    private String peso;
    private String corDoCabelo;
    private String corDaPele;
    private String corDosOlhos;
    private String anoNascimento;
    private String genero;
    private String homeWorld;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
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

    public String gethomeWorld() {
        return homeWorld;
    }

    public void sethomeWorld(String homeWorld) {
        this.homeWorld = homeWorld;
    }

    public Personagem() {
    };

    public Personagem(String nome, String altura, String peso, String corDoCabelo, String corDaPele, String corDosOlhos,
            String anoNascimento, String genero, String homeWorld) {
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.corDoCabelo = corDoCabelo;
        this.corDaPele = corDaPele;
        this.corDosOlhos = corDosOlhos;
        this.anoNascimento = anoNascimento;
        this.genero = genero;
        this.homeWorld = homeWorld;
    }

    public Personagem clone() {
        Personagem temp = new Personagem();
        temp.nome = this.nome;
        temp.altura = this.altura;
        temp.peso = this.peso;
        temp.corDoCabelo = this.corDoCabelo;
        temp.corDaPele = this.corDaPele;
        temp.corDosOlhos = this.corDosOlhos;
        temp.anoNascimento = this.anoNascimento;
        temp.genero = this.genero;
        temp.homeWorld = this.homeWorld;

        return temp;

    }

    public void ler(String linha) {

        int nameIndex = linha.indexOf("name");
        linha = linha.substring(nameIndex + 8);
        int aspasIndex = linha.indexOf("'");
        this.nome = linha.substring(0, aspasIndex);

        int heightIndex = linha.indexOf("height");
        linha = linha.substring(heightIndex + 10);
        aspasIndex = linha.indexOf("'");
        this.altura = linha.substring(0, aspasIndex);

        int massIndex = linha.indexOf("mass");
        linha = linha.substring(massIndex + 8);
        aspasIndex = linha.indexOf("'");
        String pesoString = linha.substring(0, aspasIndex);
        pesoString.replace(" unknown ", " 0 ");
        pesoString.replace(",", ".");
        this.peso = pesoString;

        int hairColorIndex = linha.indexOf("hair_color");
        linha = linha.substring(hairColorIndex + 14);
        aspasIndex = linha.indexOf("'");
        this.corDoCabelo = linha.substring(0, aspasIndex);

        int skinColorIndex = linha.indexOf("skin_color");
        linha = linha.substring(skinColorIndex + 14);
        aspasIndex = linha.indexOf("'");
        this.corDaPele = linha.substring(0, aspasIndex);

        int eyeColorIndex = linha.indexOf("eye_color");
        linha = linha.substring(eyeColorIndex + 13);
        aspasIndex = linha.indexOf("'");
        this.corDosOlhos = linha.substring(0, aspasIndex);

        int birthYearIndex = linha.indexOf("birth_year");
        linha = linha.substring(birthYearIndex + 14);
        aspasIndex = linha.indexOf("'");
        this.anoNascimento = linha.substring(0, aspasIndex);

        int genderIndex = linha.indexOf("gender");
        linha = linha.substring(genderIndex + 10);
        aspasIndex = linha.indexOf("'");
        this.genero = linha.substring(0, aspasIndex);

        int homeWorldIndex = linha.indexOf("homeworld");
        linha = linha.substring(homeWorldIndex + 13);
        aspasIndex = linha.indexOf("'");
        this.homeWorld = linha.substring(0, aspasIndex);

    }

    public void imprimir() {
        System.out.println(
                "## " + nome + " ## " + altura + " ## " + peso + " ## " + corDoCabelo + " ## " + corDaPele + " ## "
                        + corDosOlhos + " ## " + anoNascimento + " ## " + genero + " ## " + homeWorld + " ## ");

    }
}

class tp02q001 {
    public static boolean isFim(String l) {
        if (l.length() == 3 && l.charAt(0) == 'F' && l.charAt(1) == 'I' && l.charAt(2) == 'M') {
            return false;
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String[] entrada = new String[1000];
        int numEntrada = 0;

        do {
            entrada[numEntrada] = sc.nextLine();

        } while (isFim(entrada[numEntrada++]));
        numEntrada--;

        // System.out.println(entrada[0]);

        Personagem[] personagens = new Personagem[numEntrada];

        try {
            for (int i = 0; i < numEntrada; i++) {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(entrada[i])));
                String linha = br.readLine();

                personagens[i] = new Personagem();
                personagens[i].ler(linha);
                personagens[i].imprimir();

                br.close();
            }
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não Encontrado");
        }

        sc.close();
    }

}