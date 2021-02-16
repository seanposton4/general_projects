//import java.io.PrintStream;
import java.util.concurrent.Semaphore;

public class Dine {

    private static boolean grieved;

    //Run the problem
    public static void main(String [] args) {
        Chopstick [] chopstick;
        Philosopher [] philosopher;

        grieved=false;

        //allocate the arrays 
        chopstick = new Chopstick [5];
        philosopher = new Philosopher [5];

        //create the sticks
        for(int i=0; i<5; i++) {
            chopstick[i] = new Chopstick();
        }

        //create the philosophers
        philosopher[0] = new Philosopher("Kant", chopstick[1], chopstick[0]);
        philosopher[1] = new Philosopher("Marx", chopstick[2], chopstick[1]);
        philosopher[2] = new Philosopher("Lao Tzu", chopstick[3], chopstick[2]);
        philosopher[3] = new Philosopher("Hemachandra", chopstick[4], chopstick[3]);
        philosopher[4] = new Philosopher("Ptahhotep", chopstick[0], chopstick[4]);

        //run the philosophers
        for(int i=0; i<5; i++) {
            philosopher[i].start();
        }

        //join the threads
        for(int i=0; i<5; i++) {
            try {
              philosopher[i].join();
            } catch (InterruptedException ex) {
                //nothing to do
            }
        }
        System.out.println("The philosophers are all dead.");
    }

    //return a random integer in range [min, max]
    public static int randomRange(int min, int max) 
    {
        int range = (max-min) + 1;
        return (int) (Math.random() * range) + min;
    }


    //The Philosophers
    public static class Philosopher extends Thread
    {
        // name of the philosopher
        private String name;

        // chopsticks on the left and right
        private Chopstick left;
        private Chopstick right;

        // the states the philosopher can be in
        private enum State {
            EATING,
            THINKING,
            WAITING, 
            DEAD
        };
        private State pstate;

		//private PrintStream printf;


        //create the philosopher
        public Philosopher(String name, Chopstick left, Chopstick right) {
            this.name = name;
            this.left = left;
            this.right = right;
            this.pstate = State.THINKING;
        }


        //Think for a random amount of time
        private void think() {
            try {
                //think for duration milliseconds
                pstate = State.THINKING;
                System.out.println(name + " is thinking.");

                //think for a random amount of time (between 0.5 and 1 second)
                sleep(randomRange(500, 1000));
            } catch(InterruptedException ex)  {
                //nothing to do on interruption
            }
        }


        //Eat for a random amount of time
        private void eat() throws InterruptedException {
            // ----------- Begin Your strategy -----------------------

            /* only take the chopsticks if they're both free so you don't 
                trip up any of the other philosophers
                I return if they're not both free so those without chopsticks
                aren't allowed to eat */
            if (left.isFree() && right.isFree()) {
                left.take(name);
                right.take(name);
            }
            else {
                return;
            }
            
            // ----------- End Your strategy -----------------------

            //eat for a random amount of time between 0.5 and 1 second
            System.out.println(name + " is eating.");
            pstate = State.EATING;
            sleep(randomRange(500, 1000));


            //put down the sticks
            left.putDown(name);
            right.putDown(name);
        }

       
        //Run the philosopher loop
        public void run() {
            while(pstate != State.DEAD) {
                if(grieved) {
                    System.out.println(name +" has died of grief.");
                    pstate = State.DEAD;
                    continue;
                }
                think();
                pstate = State.WAITING;
                Philosopher p = this;
                new Thread() {
                    public void run() {
                        try {
                            sleep(2000);
                            if(p.pstate == Philosopher.State.WAITING) {
                                p.pstate = Philosopher.State.DEAD;
                                p.interrupt();
                            }
                        } catch(InterruptedException ex) {
                            //nothing to do
                        }
                    }
                }.start();
                try {
                    eat();
                } catch (InterruptedException ex) {
                    if(pstate == State.DEAD) {
                        System.out.println(name + " has starved to death.");
                        grieved = true;
                    }
                }
            }
        }

    }


    //The chopsticks
    public static class Chopstick
    {
        private boolean free;
        private Semaphore lock;


        public Chopstick() {
            free = true;
            lock = new Semaphore(1);
        }


        //get the state
        public boolean isFree() {
            return free;
        }


        //pick up the stick (this may be interrupted)
        public synchronized void take(String name) throws InterruptedException {
            lock.acquire();
            System.out.println(name + " has taken a chopstick.");
            free = false;
        }


        //put the stick down
        public synchronized void putDown(String name) throws InterruptedException{
            free = true;
            System.out.println(name + " has put down a chopstick.");
            lock.release();
        }
    }

}