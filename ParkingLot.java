// Non-tested code
class Vehicle{
    String license;
    int type;
    
    Vehicle(int license, int type){
        this.license = license;
        this.type = type;
    }
}

class Truck extends Vehicle{
    
    Truck(int license, int type){
        super(license, type);
    }
}

class Car extends Vehicle{
    Car(int license, int type){
       super(license, type);
    }
}

class SUV extends Vehicle{
    
    SUV(int license, int type){
        super(license, type);
    }
}

class ParkingSpot extends Spot{
    
    int type;
    
    ParkingSpot(int type, int level, int number){
        this.type  = type;
        super(level, number);
    }
}

class Spot{
    int level;
    int number;
    
    Spot(int level, int number){
        this.level = level;
        this.number = number;
    }
}

class ParkingLot{
    
    ParkingSpot[][] parkingSpots;
    Comparator cmpSpot;

    ParkingLot(int level, int num){
        this.parkingSpots = new ParkingSpot[level][num];
        cmpSpot = new Comparator<Spot>(){
        public int compare(Spot a, Spot b){
            if(a.level == b.level){
                return a.number - b.number;
            }
            return a.level - b.level;
        }
    };
        
    }
    create(){
        for(int i = 0; i < 3; i++){ 
            for(int j = 0; j < 6; j++)
                parkingSpots[i][j] = new parkingSpot(1, i, j);
            for(int j = 6; j < 9; j++)
                parkingSpots[i][j] = new parkingSpot(2, i, j);
            for(int j = 9; j < 10; j++)
                parkingSpots[i][j] = new parkingSpot(3, i, j);
        }
    }
    
}

class ParkingSystem{
    
    ParkingLot lot;
    Map<Vehicle, Spot> parkedMappings;
    PriorityQueue<Spot> minHeapC;
    PriorityQueue<Spot> minHeapT;
    PriorityQueue<Spot> minHeapS;
    
    ParkingSystem(ParkingLot lot, Comparator<Spot> cmp){
        this.lot = lot;
        this.parkedMappings = new HashMap<>();
        minHeapC = new PriorityQueue<Spot>(cmp);
        minHeapT = new PriorityQueue<Spot>(cmp);
        minHeapS = new PriorityQueue<Spot>(cmp);
    }
    
    void park(Vehicle v){
        Spot spot = null;
        if(v.type == 1)
            spot = minHeapC.poll();
        if(v.type == 2)
            spot = minHeapS.poll();
        if(v.type == 3)
           spot = minHeapT.poll();
        map.put(v, spot);
    }
    
    void unpark(Vehicle v){
        ParkingSpot spot = parkedMappings.get(v);
        if(spot != null){
            if(v.type == 1)
                minHeapC.add(spot);
            if(v.type == 2)
                minHeapS.add(spot);
            if(v.type == 3)
                minHeapT.add(spot);
            map.remove(spot);
        }else
            System.out.println("Car not parked");
    }
}

class Main{
    
    public static void main(String[] args) {
        Vehicle car = new Car("CAR", 1);
        Vehicle suv = new Car("SUV", 2);
        Vehicle truck = new Car("TRUCK", 3);
        
        ParkingLot lot = new ParkingLot();
        lot.create();
        ParkingSystem ps = new ParkingSystem(lot.parkingSpots, );
        ps.park(car);
        ps.park(suv);
        ps.park(truck);
        ps.unpark(car);
    }
    
}
