/*Пять безмолвных философов сидят вокруг круглого стола, перед каждым философом стоит тарелка спагетти.
Вилки лежат на столе между каждой парой ближайших философов.
Каждый философ может либо есть, либо размышлять.
Философ может есть только тогда, когда держит две вилки — взятую справа и слева.

Можно брать только две вилки одновременно
Описать в виде кода такую ситуацию. Каждый философ должен поесть три раза*/

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Philosopher extends Thread{
    Random r1 = new Random();
    private int leftFork;
    private int rightFork;
    private int count;

    private String name;

    ArrayList<Integer> forks = new ArrayList<>();

    ReentrantLock locker;

    Philosopher(String name, int leftFork, int rightFork, ArrayList<Integer> forks, ReentrantLock lock){
        this.name = name;
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        locker = lock;
    }


    public void takeFork(ArrayList<Integer> forks, int leftFork, int rightFork){
        if (forks.get(leftFork) != 0 && forks.get(rightFork) != 0) {
            forks.set(leftFork,0);
            System.out.println(name + " took " + leftFork +" fork");
            forks.set(rightFork,0);
            System.out.println(name + " took " + rightFork +" fork");
        }
    }
    public void putdownFork(ArrayList<Integer> forks, int leftFork, int rightFork){
        if (forks.get(leftFork) == 0 && forks.get(rightFork) == 0) {
            forks.set(leftFork,leftFork);
            System.out.println(name + " put down " + leftFork +" fork");
            forks.set(rightFork,rightFork);
            System.out.println(name + " put down " + rightFork +" fork");
        }
    }

    public void eat(){
        System.out.println(name + " is eating...");
    }

    public void think(){
        System.out.println(name + " is thinking...");
    }

    @Override
    public void run() {
        for (int i = 0; i < 6; i++) {
            forks.add(i);
        }

        while(count != 3){
            locker.lock(); //lock
            if(r1.nextInt(2) == 1){
                takeFork(forks, leftFork, rightFork);
                try {
                    Thread.sleep(1000 + new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                eat();
                putdownFork(forks, leftFork, rightFork);
                count++;
                try {
                    Thread.sleep(1000 + new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            locker.unlock();
            if(r1.nextInt(2) == 0){
                think();
                try {
                    Thread.sleep(1000 + new Random().nextInt(1000));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.println(name + " finished");

    }
}
