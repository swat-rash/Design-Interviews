import

public class Main{
    public static void main(String[] args)(
        ParkingLot lot = new ParkingLot(10);
        Plane planeA = new Plane("AirIndia", "A", -1);
        Plane planeB = new Plane("Indigo", "I", -1);
        lot.tryLand(planeA);
        lot.tryLand(planeB);
    )
}

class ParkingLot{
    Semaphore sem;
    ParkingSpace[] parkingSpaces;
    int n;
    List<Plane> registeredPlanes;
    
    ParkingLot(int n){
        sem = new Semaphore(1);
        this.n = n;
        this.registeredPlanes = new LinkedList≤≥();
        for(int i = 1; i <= n; i++)
            parkingSpaces[i] = new ParkingSpace(i, null);
    }
    
    void Register(Plane Plane) {
        this.registeredPlanes.add(plane);
    }
    
    // TODO
    void Unregister(Plane Plane) {
        this.registeredPlanes.add(plane);
    }
    
    void Empty(Plane plane) {
        sem.acquire();
        for (int i = 1; i <= n; i++) {
            if (parkingSpaces[i].plane == plane)
                parkingSpaces[i] = null;
        }
        sem.release();
        
        for (int i = 0; i < registeredPlanes.size(); i++) {
            registeredPlanes[i].OnShouldLand();
        }
    }
    
    ParkingSpace tryLand(Plane plane){
        ParkingSpace space = null;
        sem.acquire();
        for(int i = 1; i <= n; i++){
            if(parkingSpace[i] == null){
                space = parkingSpace[i];  
            }
        }
        sem.release();
        if(space == null)
            System.out.println("No parking space available for flight: " + plane.flightNum);
        else
            System.out.println("Parking space available for flight: " + plane.flightNum + " " + i);
        return space;
    }
}

class ParkingSpace{
    
    int parkingNum;
    Plane plane;
    
    ParkingSpace(int parkingNum, Plane plane){
        this.parkingNum = parkingNum;
        this.plane = plane;
    }
    
    
}

class Plane{
    String airline;
    int flightNum;
    ParkingLot parkingLot;
    boolean hasLanded;
    
    Plane(String airline, int flightNum, ParkingLot parkingLot){
        this.airline = airline;
        this.flightNum = flightNum;
        this.parkingNum = parkingNum;
        this.parkingLot = parkingLot;
        
        this.parkingLot.register(this);
    }
    
    Land () {
        if ()
    }
    
    void OnShouldLand() {
        if (this.parkingLot.tryLand(this)) {
            this.OnLanded();
        }
    }
    
    void OnLanded() {
        this.parkingLot.Unregister(plane);
    }
    
    void Leave() {
        this.parkingLot.Empty(this);
    }
}
