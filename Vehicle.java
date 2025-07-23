

public abstract class Vehicle {
    protected Person[][] personsOnBoard;
    protected int numberOfRows;
    protected int maxSeatsPerRow;
    protected int[] numSeatsPerRow;

   
    public Vehicle(int numRows, int numSeatsPerRow) {
        this.numberOfRows = numRows;
        this.maxSeatsPerRow = numSeatsPerRow;
        this.numSeatsPerRow = new int[numRows];
        for (int i = 0; i < numRows; i++) {
            this.numSeatsPerRow[i] = numSeatsPerRow;
        }
        
        personsOnBoard = new Person[numberOfRows][];
        for (int i = 0; i < numberOfRows; i++) {
            personsOnBoard[i] = new Person[this.numSeatsPerRow[i]];
        }
    }

   
    public Vehicle(int[] numSeatsPerRow) {
        this.numberOfRows = numSeatsPerRow.length;
        this.numSeatsPerRow = numSeatsPerRow.clone();
        
        // Find the maximum number of seats in any row
        this.maxSeatsPerRow = 0;
        for (int seats : numSeatsPerRow) {
            if (seats > this.maxSeatsPerRow) {
                this.maxSeatsPerRow = seats;
            }
        }
        
        personsOnBoard = new Person[numberOfRows][];
        for (int i = 0; i < numberOfRows; i++) {
            personsOnBoard[i] = new Person[this.numSeatsPerRow[i]];
        }
    }

  
    public Vehicle(Person driver, int[] numSeatsPerRow) throws InvalidDriverException {
        this(numSeatsPerRow);
        setDriver(driver);
    }

  
    public abstract boolean loadPassenger(Person p);

   
    public abstract int loadPassengers(Person[] peeps);

   
    public void setDriver(Person p) throws InvalidDriverException {
        if (p != null && p.hasDriverLicense()) {
            personsOnBoard[0][0] = p;
        } else {
            throw new InvalidDriverException("Driver must have a valid driver's license");
        }
    }

   
    public Person getDriver() {
        return personsOnBoard[0][0];
    }

   
    public boolean hasDriver() {
        return personsOnBoard[0][0] != null;
    }

    
    public int getNumberOfAvailableSeats() {
        int availableSeats = 0;
        for (int i = 0; i < numberOfRows; i++) {
            availableSeats += getNumberOfAvailableSeatsInRow(i);
        }
        return availableSeats;
    }

    
    public int getNumberOfAvailableSeatsInRow(int row) {
        if (row < 0 || row >= numberOfRows) {
            return -1;
        }
        
        int availableSeats = 0;
        for (int col = 0; col < numSeatsPerRow[row]; col++) {
            if (personsOnBoard[row][col] == null) {
                availableSeats++;
            }
        }
        return availableSeats;
    }

   
    public int getNumberOfPeopleOnBoard() {
        int count = 0;
        for (int i = 0; i < numberOfRows; i++) {
            count += getNumberOfPeopleInRow(i);
        }
        return count;
    }

   
    public int getNumberOfPeopleInRow(int row) {
        if (row < 0 || row >= numberOfRows) {
            return 0;
        }
        
        int count = 0;
        for (int col = 0; col < numSeatsPerRow[row]; col++) {
            if (personsOnBoard[row][col] != null) {
                count++;
            }
        }
        return count;
    }

   
    public Person getPersonInSeat(int row, int col) {
        if (row < 0 || row >= numberOfRows || col < 0 || col >= numSeatsPerRow[row]) {
            return null;
        }
        return personsOnBoard[row][col];
    }

  
    public int[] getLocationOfPersonInVehicle(Person p) {
        for (int row = 0; row < numberOfRows; row++) {
            for (int col = 0; col < numSeatsPerRow[row]; col++) {
                if (personsOnBoard[row][col] != null && personsOnBoard[row][col].equals(p)) {
                    return new int[] {row, col};
                }
            }
        }
        return new int[] {-1, -1};
    }

    
    public Person[] getPeopleInRow(int row) {
        if (row < 0 || row >= numberOfRows) {
            return null;
        }
        
        int peopleCount = getNumberOfPeopleInRow(row);
        if (peopleCount == 0) {
            return null;
        }
        
        Person[] people = new Person[peopleCount];
        int index = 0;
        
        for (int col = 0; col < numSeatsPerRow[row]; col++) {
            if (personsOnBoard[row][col] != null) {
                people[index++] = personsOnBoard[row][col].clone();
            }
        }
        
        return people;
    }

   
    public Person[][] getPeopleOnBoard() {
        Person[][] result = new Person[numberOfRows][];
        
        for (int row = 0; row < numberOfRows; row++) {
            result[row] = new Person[numSeatsPerRow[row]];
            for (int col = 0; col < numSeatsPerRow[row]; col++) {
                if (personsOnBoard[row][col] != null) {
                    result[row][col] = personsOnBoard[row][col].clone();
                }
            }
        }
        
        return result;
    }

   
    public boolean isPersonInVehicle(Person p) {
        int[] location = getLocationOfPersonInVehicle(p);
        return location[0] != -1;
    }

   
    public boolean isPersonDriver(Person p) {
        return hasDriver() && getDriver().equals(p);
    }
}