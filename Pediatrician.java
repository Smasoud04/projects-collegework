

public class Pediatrician extends Doctor {
        private boolean hasPrivatePractice; 
    private String hospitalName;        
    
    
    public Pediatrician(String name) {
        super(name);
        this.hasPrivatePractice = false;
        this.hospitalName = "";
    }
    
  
    public Pediatrician(String name, boolean hasPrivatePractice, String hospitalName) {
        super(name);
        this.hasPrivatePractice = hasPrivatePractice;
        this.hospitalName = hospitalName;
    }
    
   
    public boolean hasPrivatePractice() {
        return hasPrivatePractice;
    }
   
    public String getHospitalName() {
        return hospitalName;
    }
    
   
    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }
    
  
    public void addPatient(Patient patient) throws PatientException {
        int currentYear = 2025; 
        int age = currentYear - patient.getBirthYear();
        
        if (age >= 18) {
            throw new PatientException("Patient is too old for a pediatrician. Age: " + age);
        }
        
        super.addPatient(patient);
    }
    
 
    public String toString() {
        return super.toString() + 
               String.format("\nPediatrician: %s | hospital name=%15s", 
                       (hasPrivatePractice ? "has private practice" : "does not have private practice"), 
                       hospitalName);
    }
    
 
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof Pediatrician)) {
            return false;
        }
        
        Pediatrician other = (Pediatrician) obj;
        return this.hasPrivatePractice == other.hasPrivatePractice && 
               this.hospitalName.equals(other.hospitalName);
    }
}