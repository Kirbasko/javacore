package lessonOneDot2;

public class Course {
    private int length;

    public Course(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLongRun(CanRun run) {
        return run.run(this);
    }
 //   public double getTimeToOvercomePool(CanSwim swimmer) {
 //       return swimmer.swim(this);
}
