import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

class Character {
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

    public void printCharacter() {
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

class Cell {
    public Character character;
    public Cell prox;

    public Cell() {
        this(null);
    }

    public Cell(Character character) {
        this.character = character;
        this.prox = null;
    }
}

class LinkedList {
    private Cell first, last;
    private int n;

    public LinkedList() {
        first = new Cell();
        last = first;
    }

    public void insertEnd(Character character) {
        last.prox = new Cell(character);
        last = last.prox;
        n++;
    }

    public void insertStart(Character character) {
        Cell tmp = new Cell(character);
        tmp.prox = first.prox;
        first.prox = tmp;
        if (first == last) {
            last = tmp;
        }
        tmp = null;
        n++;
    }

    public void insert(Character character, int pos) throws Exception {
        if (n < pos || pos < 0) {
            throw new Exception("Erro!");
        } else if (pos == 0) {
            insertStart(character);
        } else if (pos == n) {
            insertEnd(character);
        } else {

            Cell i = first;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;
            Cell tmp = new Cell(character);
            tmp.prox = i.prox;
            i.prox = tmp;
            tmp = i = null;
        }
        n++;
    }

    public Character removeStart() throws Exception {
        if (first == last) {
            throw new Exception("Erro!");
        }
        Cell tmp = first;
        first = first.prox;
        Character element = first.character;
        tmp.prox = null;
        tmp = null;
        n--;
        return element;
    }

    public Character removeEnd() throws Exception {
        if (first == last)
            throw new Exception("Erro!");
        Cell i;
        for (i = first; i.prox != last; i = i.prox)
            ;
        Character element = last.character;
        last = i;
        i = last.prox = null;
        return element;
    }

    public Character remove(int pos) throws Exception {
        Character element;
        if (first == last || pos < 0 || pos >= n) {
            throw new Exception("Erro!");
        } else if (pos == 0) {
            element = removeStart();
        } else if (pos == n - 1) {
            element = removeEnd();
        } else {
            Cell i = first;
            for (int j = 0; j < pos; j++, i = i.prox)
                ;
            Cell tmp = i.prox;
            element = tmp.character;
            i.prox = tmp.prox;
            tmp.prox = null;
            i = tmp = null;
            n--;
        }
        return element;
    }

    public void show() {
        int j = 0;
        for (Cell i = first.prox; i != null; i = i.prox, j++) {
            System.out.print("[" + j + "] ");
            i.character.printCharacter();
        }
    }
}

class TP03Q01 {

    public static boolean isFim(String file) {
        return file.length() == 3 && file.charAt(0) == 'F' && file.charAt(1) == 'I' && file.charAt(2) == 'M';
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String file = new String(), line = new String();
        LinkedList list = new LinkedList();
        while (isFim(file) != true) {
            file = sc.nextLine();
            if (isFim(file) == true) {
                break;
            }
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                Character character = new Character();
                line = br.readLine();

                character.readLine(line);
                list.insertEnd(character);

                br.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        int num = Integer.parseInt(sc.nextLine());

        for (int i = 0; i < num; i++) {
            file = sc.nextLine();
            String[] fileSplited = file.split(" ");

            try {
                Character character = new Character();
                BufferedReader br;
                switch (fileSplited[0]) {
                    case "II":
                        br = new BufferedReader(new FileReader(fileSplited[1]));
                        line = br.readLine();
                        character.readLine(line);
                        list.insertStart(character);
                        break;
                    case "IF":
                        br = new BufferedReader(new FileReader(fileSplited[1]));
                        line = br.readLine();
                        character.readLine(line);
                        list.insertEnd(character);
                        break;
                    case "RI":
                        System.out.println("(R) " + list.removeStart().getName());
                        break;
                    case "RF":
                        System.out.println("(R) " + list.removeEnd().getName());
                        break;

                    case "R*":
                        System.out.println("(R) " +
                                list.remove(Integer.parseInt(fileSplited[1])).getName());
                        break;

                    case "I*":
                        br = new BufferedReader(new FileReader(fileSplited[2]));
                        line = br.readLine();
                        character.readLine(line);
                        list.insert(character, Integer.parseInt(fileSplited[1]));
                        break;

                    default:
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        list.show();
        sc.close();
    }
}
