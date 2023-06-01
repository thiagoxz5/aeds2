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

class Node {
    Character element;
    Node left, right;

    Node(Character element) {
        this(element, null, null);
    }

    Node(Character element, Node left, Node right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }

}

class BinaryTree {
    Node root;

    BinaryTree() {
        this.root = null;
    }

    void insert(Character x) throws Exception {
        root = insert(x, root);
    }

    Node insert(Character x, Node i) throws Exception {
        if (i == null) {
            i = new Node(x);
        } else if (x.getName().compareTo(i.element.getName()) < 0) {
            i.left = insert(x, i.left);
        } else if (x.getName().compareTo(i.element.getName()) > 0) {
            i.right = insert(x, i.right);
        } else {
            throw new Exception("ERRO!");
        }
        return i;
    }

    boolean search(String x) {
        System.out.print(x + " raiz");
        return search(x, root);
    }

    boolean search(String x, Node i) {
        boolean resp;
        if (i == null) {
            return false;
        } else if (x.compareTo(i.element.getName()) == 0) {
            resp = true;
        } else if (x.compareTo(i.element.getName()) < 0) {
            System.out.print(" esq");
            resp = search(x, i.left);
        } else {
            System.out.print(" dir");
            resp = search(x, i.right);
        }
        return resp;
    }

}

class TP04Q01 {

    public static boolean isFim(String file) {
        return file.length() == 3 && file.charAt(0) == 'F' && file.charAt(1) == 'I' && file.charAt(2) == 'M';
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        String file = new String(), line = new String();
        BinaryTree tree = new BinaryTree();

        while (isFim(file) != true) {
            file = sc.nextLine();
            if (isFim(file) == true) {
                break;
            }
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                Character p = new Character();
                line = br.readLine();
                p.readLine(line);
                tree.insert(p);
                br.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        file = sc.nextLine();
        do {
            if (tree.search(file)) {
                System.out.println(" SIM");
            } else {
                System.out.println(" NÃO");
            }
            file = sc.nextLine();

        } while (isFim(file) != true);
        sc.close();
    }
}
