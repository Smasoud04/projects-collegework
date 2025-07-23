public class Bicycle extends Vehicle implements Comparable<Bicycle> {
    private double weight;
    private static final double ACCURACY_RANGE = 0.5;
    
    
    public Bicycle() {
        super(1, 1); // 1 row, 1 seat per row
        this.weight = 0;
    }
    
    
    public Bicycle(Person driver) throws InvalidDriverException {
        super(driver, new int[]{1}); // 1 row, 1 seat
        this.weight = 0;
    }
    
    
    public Bicycle(Person driver, double weight) throws InvalidDriverException {
        super(driver, new int[]{1}); // 1 row, 1 seat
        setWeight(weight);
    }
    
    
    public double getWeight() {
        return weight;
    }
    
    
    public void setWeight(double w) {
        this.weight = (w >= 0) ? w : 0;
    }
    
    
    @Override
    public void setDriver(Person p) throws InvalidDriverException {
        if (p != null && p.getAge() >= 3) {
            personsOnBoard[0][0] = p;
        } else {
            throw new InvalidDriverException("Driver must be at least 3 years old");
        }
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Bicycle bicycle = (Bicycle) o;
        
        // Two bicycles are equal if their weights are within ACCURACY_RANGE
        return Math.abs(weight - bicycle.weight) <= ACCURACY_RANGE;
    }
    
   
    @Override
    public String toString() {
        String riderName = hasDriver() ? getDriver().getName() : "none";
        return "Bicycle [ rider= " + riderName + " | weight= " + weight + " ]";
    }
    
    
    @Override
    public int compareTo(Bicycle b) {
        double weightDiff = this.weight - b.weight;
        
        if (weightDiff < -ACCURACY_RANGE) {
            return -1;
        } else if (weightDiff > ACCURACY_RANGE) {
            return 1;
        } else {
            return 0;
        }
    }
    
    
    @Override
    public boolean loadPassenger(Person p) {
        // A bicycle cannot have passengers
        return false;
    }
    
    
    @Override
    public int loadPassengers(Person[] peeps) {
        // A bicycle cannot have passengers
        return 0;
    }
}