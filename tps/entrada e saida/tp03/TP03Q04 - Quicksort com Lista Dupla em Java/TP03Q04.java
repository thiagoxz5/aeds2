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

class DoubleCell {
    public Character character;
    public DoubleCell next, prev;

    public DoubleCell() {
        this(null, 0);
    }

    public DoubleCell(Character character, int number) {
        this.character = character;
        this.next = this.prev = null;
    }
}

class LinkedList {
    private DoubleCell first, last;
    public int numberOfelements;

    public LinkedList() {
        first = new DoubleCell();
        last = first;
        numberOfelements = 0;
    }

    public void insertEnd(Character character) {
        last.next = new DoubleCell(character, numberOfelements);
        last.next.prev = last;
        last = last.next;
        numberOfelements++;
    }

    void swap(DoubleCell i, DoubleCell j) {
        Character c = i.character;
        i.character = j.character;
        j.character = c;
    }

    public void quickSort() {
        quickSort(first.next, last);
    }

    public void quickSort(DoubleCell p, DoubleCell r) {
        if (p != r && p != r.next) {
            DoubleCell q = partition(p, r);
            quickSort(p, q.prev);
            quickSort(q.next, r);
        }
    }

    public DoubleCell partition(DoubleCell p, DoubleCell r) {
        String x = r.character.getColorHair();
        DoubleCell i = p.prev;
        for (DoubleCell j = p; j != r; j = j.next) {
            if (j.character.getColorHair().compareTo(x) < 0) {
                i = i.next;
                swap(i, j);
            }
        }
        swap(i.next, r);
        return i.next;
    }

    public void insertionSort() {
        for (DoubleCell i = first.next; i != last; i = i.next) {
            Character tmp = i.character;
            DoubleCell j = i.prev;
            while (j != first && j.character.getColorHair().compareTo(tmp.getColorHair()) == 0 && j.character.getName().compareTo(tmp.getName()) > 0){
                swap(j.next,j);
                j = j.prev;
            }
            j.next.character = tmp;
        }
    }

    public void show() {
        for (DoubleCell i = first.next; i != null; i = i.next) {
            i.character.printCharacter();
        }
    }

}

class TP03Q04 {

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
        list.quickSort();
        list.insertionSort();
        list.show();
        sc.close();
    }
}