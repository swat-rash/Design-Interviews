import java.util.*;
import java.util.concurrent.Semaphore;

enum LiftState {
    Busy,
    Free
}


class Lift{
    int id;
    LiftState ls = LiftState.Free;
    private Semaphore semaphore;
    
    
    Lift(int id){
        this.id = id;
        this.semaphore = new Semaphore(1);
    }
    
    public boolean serve(int waitTime) {
        try {
            semaphore.acquire();
            if (ls == LiftState.Free) {
                //System.out.println(ls);
                System.out.println("Lift " + id +  " serving req for " + waitTime + " secs - " + System.currentTimeMillis());
                ls = LiftState.Busy;
                semaphore.release();
                Thread.sleep(waitTime * 1000);
                ls = LiftState.Free;
                System.out.println("Lift " + id +  " served - " + System.currentTimeMillis());
                return true;
            }
        }
        catch(Exception ex) {
            System.out.println("Something went wrong");
            ls = LiftState.Free;
        }
        
        semaphore.release();
        return false;
    }
}

class Scheduler{
    Queue<Request> queue;
    List<Lift> lifts;
    private static final int SERVE_TIME = 2;
    
    Scheduler(List<Lift> lifts){
        this.queue = new LinkedList<>();
        this.lifts = lifts;
    }
    
    void schedule(Request request){
        queue.add(request);
        int waitTime = Math.abs(request.src - request.dest) * SERVE_TIME;
        new Thread(() -> {
            serve(waitTime);
        }).start();
    }
    
    
    void serve(int waitTime){
        while(true) {
            for(Lift lift : this.lifts) {
                if (lift.ls == LiftState.Free) {
                    if(lift.serve(waitTime)) {
                        queue.poll();
                        return;
                    }
                }
            }
        }
    }
}

class Request{
    int src;
    int dest; 
    
    Request(int s, int d) {
        this.src = s;
        this.dest = d;
    }
}



class Main{
    
    public static void main(String args[]){
        Lift lift1 = new Lift(1);
        List<Lift> lifts = new ArrayList<>();
        lifts.add(lift1);
        lifts.add(new Lift(2));
        lifts.add(new Lift(3));
        
        Scheduler scheduler = new Scheduler(lifts);
        Request request1 = new Request(1, 5);
        scheduler.schedule(request1);
        scheduler.schedule(new Request(2, 7));
        scheduler.schedule(new Request(6, 9));
        scheduler.schedule(new Request(5, 6));
        scheduler.schedule(new Request(6, 11));
        
    }
    
    
}
