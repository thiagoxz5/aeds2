import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

class Personagem {
    private String name;
    private int height;
    private double weight;
    private String colorHair;
    private String skinColor;
    private String eyeColor;
    private String yearOfBirth;
    private String gender;
    private String homeworld;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setHeight(String height) {
        if (height.contains("unknown")) {
            this.height = 0;
        } else {
            this.height = Integer.parseInt(height);
        }
    }

    public int getHeight() {
        return height;
    }

    public void setWeight(String weight) {
        if (weight.contains("unknown")) {
            this.weight = 0;
        } else {
            if (weight.contains(",")) {
                weight = weight.replace(",", "");
            }
            this.weight = Double.parseDouble(weight);
        }
    }

    public int getWeight() {
        return (int) weight;
    }

    public void setColorHair(String colorHair) {
        this.colorHair = colorHair;
    }

    public String getColorHair() {
        return colorHair;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public String getSkinColor() {
        return skinColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setHomeworld(String homeworld) {
        this.homeworld = homeworld;
    }

    public String getHomeworld() {
        return homeworld;
    }

    public void readLine(String line) {
        line = line.replace("{", "");
        line = line.replace("}", "");
        line = line.replace("\': ", "");

        String[] lines = line.split(" \'films");
        String[] attributes = lines[0].split("\'");

        setName(attributes[2]);
        setHeight(attributes[5]);
        setWeight(attributes[8]);
        setColorHair(attributes[11]);
        setSkinColor(attributes[14]);
        setEyeColor(attributes[17]);
        setYearOfBirth(attributes[20]);
        setGender(attributes[23]);
        setHomeworld(attributes[26]);
    }

    public void printPersonagem() {
        System.out.print(" ## " + getName() + " ## ");
        System.out.print(getHeight() + " ## ");
        System.out.print(getWeight() + " ## ");
        System.out.print(getColorHair() + " ## ");
        System.out.print(getSkinColor() + " ## ");
        System.out.print(getEyeColor() + " ## ");
        System.out.print(getYearOfBirth() + " ## ");
        System.out.print(getGender() + " ## ");
        System.out.print(getHomeworld() + " ## ");
        System.out.println();
    }
}

class List {
    private Personagem[] array;
    private int n;

    List() {
        array = new Personagem[100];
        n = 0;
    }

    List(int arrayLength) {
        array = new Personagem[arrayLength];
        n = 0;
    }

    public void insertStart(Personagem personagem) throws Exception {
        if (n >= array.length) {
            throw new Exception("Erro!");
        }
        for (int i = n; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = personagem;
        n++;
    }

    public void insertEnd(Personagem personagem) throws Exception {
        if (n >= array.length) {
            throw new Exception("Erro!");
        }
        array[n] = personagem;
        n++;
    }

    public void insert(Personagem personagem, int pos) throws Exception {
        if (n >= array.length || pos < 0 || pos > n) {
            throw new Exception("Erro!");
        }
        for (int i = n; i > pos; i--) {
            array[i] = array[i - 1];
        }
        array[pos] = personagem;
        n++;
    }

    public Personagem removeStart() {
        if (n == 0) {
            System.out.println("Erro");

        }
        Personagem resp = array[0];
        n--;
        for (int i = 0; i < n; i++) {
            array[i] = array[i + 1];
        }
        return resp;
    }

    public Personagem removeEnd() throws Exception {
        if (n == 0) {
            throw new Exception("Erro!");
        }
        return array[--n];
    }

    public Personagem remove(int pos) throws Exception {
        if (n == 0 || pos < 0 || pos >= n) {
            throw new Exception("Erro!");
        }
        Personagem resp = array[pos];
        n--;

        for (int i = pos; i < n; i++) {
            array[i] = array[i + 1];
        }
        return resp;
    }

    public void printList() {
        for (int i = 0; i < n; i++) {
            System.out.print("[" + i + "] ");
            array[i].printPersonagem();
        }
    }

}

class TP02Q03 {

    public static boolean isFim(String file) {
        return file.length() == 3 && file.charAt(0) == 'F' && file.charAt(1) == 'I' && file.charAt(2) == 'M';
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String file = new String(), line = new String();
        int num = 0;
        List list = new List();
        while (isFim(file) != true) {
            file = sc.nextLine();
            if (isFim(file) == true) {
                break;
            }
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                Personagem p = new Personagem();
                line = br.readLine();

                p.readLine(line);
                list.insertEnd(p);

                num++;
                br.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        num = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < num; i++) {
            file = sc.nextLine();
            String[] fileSplited = file.split(" ");

            try {
                Personagem p = new Personagem();
                BufferedReader br;
                switch (fileSplited[0]) {
                    case "II":
                        br = new BufferedReader(new FileReader(fileSplited[1]));
                        line = br.readLine();
                        p.readLine(line);
                        list.insertStart(p);
                        break;
                    case "IF":
                        br = new BufferedReader(new FileReader(fileSplited[1]));
                        line = br.readLine();
                        p.readLine(line);
                        list.insertEnd(p);
                        break;
                    case "RI":
                        System.out.println("(R) " + list.removeStart().getName());
                        break;
                    case "RF":
                        System.out.println("(R) " + list.removeEnd().getName());
                        break;
                    case "R*":
                        System.out.println("(R) " + list.remove(Integer.parseInt(fileSplited[1])).getName());
                        break;
                    case "I*":
                        br = new BufferedReader(new FileReader(fileSplited[2]));
                        line = br.readLine();
                        p.readLine(line);
                        list.insert(p, Integer.parseInt(fileSplited[1]));
                        break;

                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        list.printList();
        sc.close();
    }
}
