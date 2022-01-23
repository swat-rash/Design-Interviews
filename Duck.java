
/*
Understand Inheritance vs Composition using Duck class
*/


interface VehicleAction{
    void go(Vehicle vehicle);
}

class VehicleActionImpl implements VehicleAction{
    
    public void go(Vehicle vehicle){
        System.out.println("I am driving : " + vehicle.vehicleType);
    }
}

class Vehicle{
    
    long id;
    String vehicleType;
    
    Vehicle(long id, String vehicleType){
        this.vehicleType = vehicleType;
        this.id = id;
    }
    
}

class Duck{
    
    IFly fly;
    IQuack quack;
    
    void swim(){
        System.out.println("Swim");
    }
    
    void fly(){
        fly.fly();
    }
    
    void quack(){
        quack.quack();
    }
    
    void display(){
        System.out.println("Looks like a duck");
    }
    
    void setFly(IFly fly){
        this.fly = fly;
    }
    
}

interface IFly{
    void fly();
}

interface IQuack{
    void quack();
}

class NoFlyImpl implements IFly{
    void fly();
}

class FlyImpl implements IFly{
    void fly();
}

class NoQuackImpl implements IQuack{
    void quack();
}

class QuackImpl implements IQuack{
    void quack();
}

interface IQuack{
    void quack();
}

class MDuck extends Duck{
    
    MDuck(IFly fly){
        this.fly = fly;
    }
    
    @Override
    void display(){
        System.out.println("Looks like a Malad duck");
    }
}

class RDuck extends Duck{
    
    RDuck() {
        this.fly = new NoFlyImpl();
        this.quack = new QuackImpl();
    }
    @Override
    void fly(){
        System.out.println("No fly");
    }
}

public class Main
{
    Duck duck = new MDuck(new FlyImpl());
    public static void main(String[] args) {
        VehicleAction vehicleAction = new VehicleActionImpl();
        Vehicle vehicle1 = new Vehicle(1, "Formula 1");
        Vehicle vehicle2 = new Vehicle(2, "SUV");
        vehicleAction.go(vehicle1);
        vehicleAction.go(vehicle2);
    }
}

