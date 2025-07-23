public class Car extends Vehicle implements Comparable<Car>, Announcements {
    protected int numDoors;
    protected int numWindows;
    
    
    public Car(int numDoors, int numWindows) {
        super(2, 2);  // 2 rows, 2 seats per row
        this.numDoors = numDoors;
        this.numWindows = numWindows;
    }
    
    
    public Car(int numDoors, int numWindows, int numSeatsPerRow) {
        super(2, numSeatsPerRow);  // 2 rows
        this.numDoors = numDoors;
        this.numWindows = numWindows;
    }
    
    
    public Car(int numDoors, int numWindows, int[] numSeatsPerRow) {
        super(numSeatsPerRow);
        this.numDoors = numDoors;
        this.numWindows = numWindows;
    }
    
   
    public Car(int numDoors, int numWindows, Person driver, int[] numSeatsPerRow) throws InvalidDriverException {
        super(driver, numSeatsPerRow);
        this.numDoors = numDoors;
        this.numWindows = numWindows;
    }
    
   
    public boolean canOpenDoor(Person p) {
        if (p == null || p.getAge() <= 5) {
            return false;
        }
        
        int[] location = getLocationOfPersonInVehicle(p);
        if (location[0] == -1) {
            return false; 
        }
        
        int row = location[0];
        int col = location[1];
        
      
        if (row >= numDoors / 2) {
            return false;
        }
        
      
        return col == 0 || col == numSeatsPerRow[row] - 1;
    }
    
   
    public boolean canOpenWindow(Person p) {
        if (p == null || p.getAge() <= 2) {
            return false;
        }
        
        int[] location = getLocationOfPersonInVehicle(p);
        if (location[0] == -1) {
            return false; 
        }
        
        int row = location[0];
        int col = location[1];
        
       
        if (row >= numWindows / 2) {
            return false;
        }
        
       
        return col == 0 || col == numSeatsPerRow[row] - 1;
    }
    
   
    public int getNumDoors() {
        return numDoors;
    }
    
   
    public int getNumWindows() {
        return numWindows;
    }
    
    
    @Override
    public boolean loadPassenger(Person p) {
        if (p == null) {
            return false;
        }
        
       
        for (int row = 0; row < numberOfRows; row++) {
            
            if (row == 0 && (p.getAge() < 5 || p.getHeight() < 36)) {
                continue;
            }
            
            for (int col = 0; col < numSeatsPerRow[row]; col++) {
                // Skip driver's seat (0,0)
                if (row == 0 && col == 0) {
                    continue;
                }
                
                if (personsOnBoard[row][col] == null) {
                    personsOnBoard[row][col] = p;
                    return true;
                }
            }
        }
        
        return false; // No available seats
    }
    
    
    @Override
    public int loadPassengers(Person[] peeps) {
        if (peeps == null) {
            return 0;
        }
        
        int loaded = 0;
        for (Person p : peeps) {
            if (loadPassenger(p)) {
                loaded++;
            }
        }
        
        return loaded;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Car car = (Car) o;
        
        if (numDoors != car.numDoors) return false;
        if (numWindows != car.numWindows) return false;
        if (numberOfRows != car.numberOfRows) return false;
        if (maxSeatsPerRow != car.maxSeatsPerRow) return false;
        
        // Check if seat configurations are the same
        if (numSeatsPerRow.length != car.numSeatsPerRow.length) {
            return false;
        }
        
        for (int i = 0; i < numSeatsPerRow.length; i++) {
            if (numSeatsPerRow[i] != car.numSeatsPerRow[i]) {
                return false;
            }
        }
        
        return true;
    }
    
   
    @Override
    public String toString() {
        StringBuilder seatsPerRow = new StringBuilder("[");
        for (int i = 0; i < numSeatsPerRow.length; i++) {
            seatsPerRow.append(numSeatsPerRow[i]);
            if (i < numSeatsPerRow.length - 1) {
                seatsPerRow.append(",");
            }
        }
        seatsPerRow.append("]");
        
        StringBuilder peopleNames = new StringBuilder();
        boolean first = true;
        
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numSeatsPerRow[row]; col++) {
                if (personsOnBoard[row][col] != null) {
                    if (!first) {
                        peopleNames.append(",");
                    }
                    peopleNames.append(personsOnBoard[row][col].getName());
                    first = false;
                }
            }
        }
        
        return String.format("Car: number of doors= %02d | number of windows = %02d | number of rows= %02d | seats per row= %s | names of people on board= %s",
                numDoors, numWindows, numberOfRows, seatsPerRow.toString(), peopleNames.toString());
    }
    
   
    @Override
    public int compareTo(Car c) {
        int thisTotalSeats = 0;
        for (int seats : numSeatsPerRow) {
            thisTotalSeats += seats;
        }
        
        int otherTotalSeats = 0;
        for (int seats : c.numSeatsPerRow) {
            otherTotalSeats += seats;
        }
        
        if (thisTotalSeats < otherTotalSeats) {
            return -1;
        } else if (thisTotalSeats > otherTotalSeats) {
            return 1;
        } else {
            return 0;
        }
    }
    
    
    @Override
    public String departure() {
        return "All Aboard\n";
    }
    
    
    @Override
    public String arrival() {
        return "Everyone Out\n";
    }
    
    
    @Override
    public String doNotDisturbTheDriver() {
        return "No Backseat Driving\n";
    }
}