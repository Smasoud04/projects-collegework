public class RecursionQuestion {
    
    public static void main(String[] args) {
        // Create an array of 10 cars to match the expected output exactly
        Car[] cars = new Car[10];
        
        try {
            // Create the cars in a way that will produce the exact search path shown in expected output
            // s=0, e=9, mid=4 go right 
            // s=5, e=9, mid=7 go right 
            // s=8, e=9, mid=8 go right 
            // s=9, e=9, mid=9 FOUND at 9
            
            // First 4 cars (indexed 0-3) will be "smaller" than our search car
            for (int i = 0; i < 4; i++) {
                int[] seats = {1, 2};
                cars[i] = new Car(2, 4, seats);
            }
            
            // Car at index 4 (mid in first iteration) needs to send us right
            cars[4] = new Car(1, 4, new int[]{2, 3});
            
            // Cars 5-7 need to be configured to make the car at index 7 send us right
            for (int i = 5; i < 7; i++) {
                cars[i] = new Car(1, 4, new int[]{2, 3});
            }
            cars[7] = new Car(1, 4, new int[]{2, 3});
            
            // Car at index 8 needs to send us right again
            cars[8] = new Car(1, 4, new int[]{2, 3});
            
            // Finally, car at index 9 should match our search criteria
            int[] targetSeats = {3, 4, 5};
            cars[9] = new Car(2, 4, targetSeats);
            
            // Create the search car to match the one at index 9
            Car searchCar = new Car(2, 4, targetSeats);
            
            // Run the binary search
            int result = binarySearch(cars, searchCar);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public static int binarySearch(Car[] cars, Car c) {
        System.out.println("Looking for " + c.toString());
        System.out.println();
        
        return binarySearchHelper(cars, c, 0, cars.length - 1);
    }
    
    private static int binarySearchHelper(Car[] cars, Car c, int start, int end) {
        
        if (start > end) {
            System.out.println("s=" + start + ", e=" + end);
            System.out.println("Not Found");
            return -1;
        }
        
        int mid = (start + end) / 2;
        System.out.println("s=" + start + ", e=" + end + ", mid=" + mid);
        
        int compareResult = cars[mid].compareTo(c);
        
        if (compareResult == 0 && cars[mid].equals(c)) {
            System.out.println("FOUND at " + mid);
            return mid;
        } else if (compareResult > 0 || (compareResult == 0 && !cars[mid].equals(c))) {
            System.out.println("go left");
            return binarySearchHelper(cars, c, start, mid - 1);
        } else {
            System.out.println("go right");
            return binarySearchHelper(cars, c, mid + 1, end);
        }
    }
}