import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static List<Cat>cats = new ArrayList<>();
    public static void main(String[] args) {

        cats.add(new Cat("Rom", 11));
        cats.add(new Cat("Dom", 3));
        cats.add(new Cat("Bom", 5));
        printCats(cats);
        menu();

    }
    public static void menu(){
        System.out.println("Доступные действия:\n" +
                "1-Накормить кота\n" +
                "2-Поиграть с котом\n" +
                "3-Поход к ветеринару\n" +
                "4-Завести нового кота\n" +
                "5-Следующий день\n");
        action();
        printCats(cats);
    }
    public static void action(){
        int action = selectAction();
        if (action<=3){
            int catNum = selectCat();
            doAction(action, catNum);
        } else if (action==4) {
            addCat();
        } else {

        }
    }
    public static void nextDay(){

    }
    public static void deleteIncorrect(){
        cats.removeIf(cat -> (cat.getAge()==0));
        cats.removeIf(cat -> (cat.getName().trim().isEmpty()));
    }
    public static void addCat(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите имя нового кота: \n");
        String name = sc.nextLine();
        System.out.print("Введите возраст нового кота: \n");
        int age = 0;
        try {
            age = sc.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Вы можете вводить только целые числа от 1 до 18!");
            age = 0;
        }
        cats.add(new Cat(name, age));
        if (cats.get(cats.size()).getName().trim().isEmpty() || cats.get(cats.size()).getAge()==0) System.out.println("Не удалось добавить кота, так как вы ввели некорректные данные.");
        deleteIncorrect();
    }
    public static void doAction(int actionNum, int catNum){
        switch (actionNum) {
            case 1: cats.get(catNum).feed();
                System.out.printf("Вы покормили кота по имени %s!\n", cats.get(catNum).getName());
                break;
            case 2: cats.get(catNum).play();
                System.out.printf("Вы поиграли с котом по имени %s!\n", cats.get(catNum).getName());
                break;
            case 3: cats.get(catNum).heal();
                System.out.printf("Вы вылечили кота по имени %s!\n", cats.get(catNum).getName());
                break;
        }
    }
    public static int selectAction() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите действие, которое хотите совершить (1-5): ");
        int action = 0;
        try {
            while (true) {
                action = sc.nextInt();
                if (action<=5 && action>=1) break;
                System.out.print("Введите цифру от 1 до 5 для выбора действия!\n" +
                        "Повторный ввод: ");
            }
        } catch (InputMismatchException e) {
            System.out.println("Можно вводить только целые числа от 1 до 5!");
            action();
        }
        return action;
    }
    public static int selectCat(){
        Scanner sc = new Scanner(System.in);
        int catNum = 0;
        printCats(cats);
        System.out.printf("\nВыберите кота, чтобы выполнить действие (1-%d): ", cats.size());
        catNum = sc.nextInt();
        return catNum-1;

    }
    public static void printCats(List<Cat>cats){
        deleteIncorrect();
        cats.sort((o1, o2) -> o2.getAverage() - o1.getAverage());
        String line = "---+-------------+-------------+----------+--------------+-------------+-----------------+";
        System.out.println(line);
        System.out.printf(" # |%8s     |%10s   |%9s |%12s  |%10s   |%16s |\n","имя","возраст","здоровье","настроение","сытость","средний уровень");
        System.out.println(line);
        for (Cat cat :
                cats) {
            System.out.printf(" %d |%8s     |%10s   |%9s |%12s  |%10s   |%16s |\n",cats.indexOf(cat)+1, cat.getName(), cat.getAge(), cat.getHealth(),cat.getMood(),cat.getSatiety(), cat.getAverage());
        }
        System.out.println(line);
    }
}