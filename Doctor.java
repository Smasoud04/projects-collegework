

public class Doctor implements SeesPatients, Comparable<Doctor> {
    private static int numDoctors = 0;
    
    private String name;
    private int licenseNumber;
    private Patient[] patients;
    private int numberOfPatients;
    
   
    public Doctor(String name) {
        this.name = name;
        
        numDoctors++;
        this.licenseNumber = numDoctors;
        
        this.patients = new Patient[MAX_PATIENTS];
        this.numberOfPatients = 0;
    }
    
    
    public static int getNumDoctors() {
        return numDoctors;
    }
    

    public int getLicenseNumber() {
        return licenseNumber;
    }
   
    public String getName() {
        return name;
    }
    
   
    public int getNumberOfPatients() {
        return numberOfPatients;
    }
    
 
    public Patient[] getPatients() {
        return patients;
    }
   
    public String getPatientsAsString() {
        StringBuilder result = new StringBuilder();
        
        for (int i = 0; i < numberOfPatients; i++) {
            result.append(patients[i].toString());
            if (i < numberOfPatients - 1) {
                result.append(", ");
            }
        }
        
        return result.toString();
    }
    
   
    public void addPatient(Patient patient) throws PatientException {
        if (numberOfPatients >= MAX_PATIENTS) {
            throw new PatientException("Cannot add more patients. Maximum capacity reached.");
        }
        
        patients[numberOfPatients] = patient;
        numberOfPatients++;
    }
    

    public boolean isPatient(Patient patient) {
        for (int i = 0; i < numberOfPatients; i++) {
            if (patients[i].equals(patient)) {
                return true;
            }
        }
        return false;
    }
    

    public String toString() {
        return String.format("Doctor: name= %20s | license number= %06d | patients= %s", 
                name, licenseNumber, getPatientsAsString());
    }
    
 
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        
        Doctor other = (Doctor) obj;
        return name.equals(other.name) && numberOfPatients == other.numberOfPatients;
    }
    
  
    public int compareTo(Doctor other) {
        return Integer.compare(this.numberOfPatients, other.numberOfPatients);
    }
}