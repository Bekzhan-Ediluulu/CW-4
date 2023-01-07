import java.util.Random;

public class Cat implements Feedable, Playable, Healable{
    private String name;
    private int age;
    private int satiety = randomSetter();
    private int mood = randomSetter();
    private int health = randomSetter();
    private int average = (satiety+mood+health)/3;
    private Categories category = defineCategory();

    public void setAverage() {
        this.average = (satiety+mood+health)/3;
    }

    public Cat(String name, int age) {
        try {
            if (name.trim().isEmpty()) throw new CatsException("Имя кота не может быть пустой!");
            this.name = name;
        } catch (CatsException e) {
            System.out.println(e.getMessage());
            this.name = "";
        }
        try {
            if (age < 1 || age > 18) throw new CatsException("Возраст кота должна быть от 1 до 18!");
            this.age = age;
        } catch (CatsException e) {
            System.out.println(e.getMessage());
        }
    }

    private int randomSetter() {
        Random r = new Random();
        return r.nextInt(61)+20;
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getSatiety() {
        return satiety;
    }

    public int getMood() {
        return mood;
    }

    public int getHealth() {
        return health;
    }
    public int getAverage() {
        return average;
    }

    private Categories defineCategory(){
        Categories category = null;
        if (age<=5) { category = Categories.YOUNG; } else if (age<=10) { category = Categories.ADULT; } else category = Categories.OLD;
        return category;
    }

    @Override
    public void feed() {
        switch (category) {
            case YOUNG: satiety = Math.min(100, satiety+7);
            mood = Math.min(100, mood+7);
            break;
            case ADULT: satiety = Math.min(100, satiety+5);
            mood = Math.min(100, mood+5);
            break;
            case OLD: satiety = Math.min(100, satiety+4);
            mood = Math.min(100, mood+4);
            break;
        }
        setAverage();
    }

    @Override
    public void heal() {
        switch (category) {
            case YOUNG: health = Math.min(100, health+7);
            mood = Math.max(0, mood-3);
            satiety = Math.max(0, satiety-3);
            break;
            case ADULT: health = Math.min(100, health+5);
            mood = Math.max(0, mood-5);
            satiety = Math.max(0, satiety-5);
            break;
            case OLD: health = Math.min(100, health+4);
            mood = Math.max(0, mood-6);
            satiety = Math.max(0, satiety-6);
            break;
        }
        setAverage();
    }

    @Override
    public void play() {
        switch (category) {
            case YOUNG: Math.min(100, health+7);
            mood = Math.min(100, mood+7);
            satiety = Math.max(0, satiety-3);
            break;
            case ADULT: Math.min(100, health+5);
            mood = Math.min(100, mood+5);
            satiety = Math.max(0, satiety-5);
            break;
            case OLD: Math.min(100, health+4);
            mood = Math.min(100, mood+4);
            satiety = Math.max(0, satiety-6);
            break;
        }
        setAverage();
    }

    public void setSatiety(int satiety) {
        this.satiety = Math.max(0, Math.min(100, satiety));
    }

    public void setMood(int mood) {
        this.mood = Math.max(0, Math.min(100, mood));
    }

    public void setHealth(int health) {
        this.health = Math.max(0, Math.min(100, health));
    }
}
