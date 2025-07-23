public class Bus extends Car {
    /**
     * Constructor with seat configuration
     * @param numSeatsPerRow Array specifying number of seats in each row
     */
    public Bus(int[] numSeatsPerRow) {
        super(2, ((2 * numSeatsPerRow.length) - 1), modifySeatsForBus(numSeatsPerRow));
    }
    
    /**
     * Constructor with driver and seat configuration
     * @param driver Person who will be the driver
     * @param numSeatsPerRow Array specifying number of seats in each row
     * @throws InvalidDriverException If the driver doesn't have a license
     */
    public Bus(Person driver, int[] numSeatsPerRow) throws InvalidDriverException {
        super(2, ((2 * numSeatsPerRow.length) - 1), driver, modifySeatsForBus(numSeatsPerRow));
    }
    
    /**
     * Helper method to modify seat configuration for bus
     * @param originalSeats Original seat configuration
     * @return Modified seat configuration with first row having 1 seat
     */
    private static int[] modifySeatsForBus(int[] originalSeats) {
        int[] modified = new int[originalSeats.length];
        modified[0] = 1; // First row always has 1 seat
        
        for (int i = 1; i < originalSeats.length; i++) {
            modified[i] = originalSeats[i];
        }
        
        return modified;
    }
    
    /**
     * Checks if a person can open a door
     * @param p Person to check
     * @return true if the person can open a door, false otherwise
     */
    @Override
    public boolean canOpenDoor(Person p) {
        if (p == null || p.getAge() <= 5 || p.getHeight() <= 40) {
            return false;
        }
        
        // Check if person is the driver
        if (isPersonDriver(p)) {
            return true;
        }
        
        // Find the person's location
        int[] location = getLocationOfPersonInVehicle(p);
        if (location[0] == -1) {
            return false; // Person is not in the vehicle
        }
        
        // Check if person is in the last populated row
        int lastPopulatedRow = -1;
        for (int row = numberOfRows - 1; row >= 0; row--) {
            if (getNumberOfPeopleInRow(row) > 0) {
                lastPopulatedRow = row;
                break;
            }
        }
        
        return location[0] == lastPopulatedRow;
    }
    
    /**
     * Checks if a person can open a window
     * @param p Person to check
     * @return true if the person can open a window, false otherwise
     */
    @Override
    public boolean canOpenWindow(Person p) {
        return super.canOpenWindow(p) && p.getAge() > 5;
    }
    
    /**
     * Returns a string representation of this Bus
     * @return String representation
     */
    @Override
    public String toString() {
        return "Bus is an extension of " + super.toString();
    }
    
    /**
     * Loads a passenger into the bus
     * @param p Person to load
     * @return true if loading was successful, false otherwise
     */
    @Override
    public boolean loadPassenger(Person p) {
        return super.loadPassenger(p);
    }
    
    /**
     * Loads multiple passengers into the bus
     * @param peeps Array of Person objects to load
     * @return Number of passengers successfully loaded
     */
    @Override
    public int loadPassengers(Person[] peeps) {
        return super.loadPassengers(peeps);
    }
    
    /**
     * Announces departure
     * @return Departure announcement for bus
     */
    @Override
    public String departure() {
        return super.departure() + "The Bus\n";
    }
    
    /**
     * Announces arrival
     * @return Arrival announcement for bus
     */
    @Override
    public String arrival() {
        return super.arrival() + "Of The Bus\n";
    }
    
    /**
     * Provides message about not distracting the driver
     * @return Message about not disturbing the driver on bus
     */
    @Override
    public String doNotDisturbTheDriver() {
        return super.doNotDisturbTheDriver() + "On The Bus\n";
    }
}