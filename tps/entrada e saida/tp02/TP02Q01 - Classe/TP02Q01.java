class TP02Q01 {
   // --------------------------------------------------------------------------------------------------------------- //
      
   public static boolean isFim(String s) { return (s.length() == 3 && s.charAt(0) == 'F' && s.charAt(1) == 'I' && s.charAt(2) == 'M'); } 
    
   // --------------------------------------------------------------------------------------------------------------- // 
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

    public Personagem(String nome, int altura, double peso, String corDoCabelo, String corDaPele, String corDosOlhos, String anoNascimento, String genero, String homeworld) {
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

    public void ler(String linha)
    {
        linha = linha.replace(" ", ""); //remove os espaços
        linha = linha.replace("'", ""); //remove as ''
        linha = linha.replace("name", "");
        linha = linha.replace("height", "");
        linha = linha.replace("mass", "");
        linha = linha.replace("hair_color", "");
        linha = linha.replace("skin_color", "");
        linha = linha.replace("eye_color", "");
        linha = linha.replace("birth_year", "");
        linha = linha.replace("gender", "");
        linha = linha.replace("homeworld", "");
        linha = linha.replace("films", "");
        linha = linha.replace(",", "");
        linha = linha.replace(":", " ");
        linha = linha.replace("{", "");
        linha = linha.replace("}", "");

        String[] dados  = linha.split(" ");
        this.nome = dados[0];
        this.altura = Integer.parseInt(dados[1]);
        this.peso = Integer.parseInt(dados[2]);
        this.corDoCabelo = dados[3];
        this.corDaPele = dados[4];
        this.corDosOlhos = dados[5];
        this.anoNascimento = dados[6];
        this.genero = dados[7];
        this.homeworld = dados[8];

    }


    public void imprimir() {
        MyIO.println(" ## " + nome + "##" + altura + "##" + peso + "##" + corDoCabelo + "##" + corDaPele + "##" + corDosOlhos + "##" + anoNascimento + "##" + genero + "##" + homeworld );
    }
}    