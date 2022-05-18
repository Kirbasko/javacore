package lessonOneDot2;

public class Turtle extends Animal implements CanSwim, CanRun {
    private int swimmingSpeed;
    private int maxRun;

    public Turtle(String name, String color, int age) {
        super (name, color, age);
        this.swimmingSpeed = swimmingSpeed;
    }
/*
    public Turtle(String name, String color, int age) {
        super(name, color, age);
    }

 */

    public int getMaxRun() {
        return maxRun;
    }

    public void setMaxRun(int maxRun) {
        this.maxRun = maxRun;
    }

    public int getSwimmingSpeed() {
        return swimmingSpeed;
    }

    public void setSwimmingSpeed(int swimmingSpeed) {
        this.swimmingSpeed = swimmingSpeed;
    }

    @Override
    public void voice() {
        System.out.println("Черепаха издает звук!");
    }

    public double swim(Pool pool) {
        System.out.println("Я черепаха, я плыву!");
        System.out.println("Затратил " + pool.getLength() / swimmingSpeed);
        return pool.getLength() / swimmingSpeed;
    }
    public int run(Course course) {
        System.out.println("Я черепаха, я ели иду дистанцию");
        int timeToRun = course.getLength() / maxRun;
        System.out.println("Я прошла со скоростью " + timeToRun);
        return timeToRun;
    }
}
