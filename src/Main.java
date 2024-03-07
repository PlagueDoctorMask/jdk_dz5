import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> forks = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            forks.add(i);
        }
        System.out.println(forks);
        ReentrantLock locker = new ReentrantLock();
        Thread t1 = new Thread(new Philosopher("Pain",1, 2, forks, locker));
        Thread t2 = new Thread(new Philosopher("Konan", 2, 3, forks, locker));
        Thread t3 = new Thread(new Philosopher("Itachi", 3, 4, forks, locker));
        Thread t4 = new Thread(new Philosopher("Kisame", 4, 5, forks, locker));
        Thread t5 = new Thread(new Philosopher("Sasori", 5, 1, forks, locker));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
    }
}