

public class Gerontologist extends Doctor {
        private boolean performsHouseCalls; 
    private double visitFee;          
    
   
    public Gerontologist(String name) {
        super(name);
        this.performsHouseCalls = true;
        this.visitFee = 0.0;
    }
    
   
    public Gerontologist(String name, boolean performsHouseCalls, double visitFee) {
        super(name);
        this.performsHouseCalls = performsHouseCalls;
        this.visitFee = visitFee;
    }
    
   
    public boolean performsHouseCalls() {
        return performsHouseCalls;
    }
    
   
    public double getVisitFee() {
        return visitFee;
    }
    
   
    public void setPerformsHouseCalls(boolean performsHouseCalls) {
        this.performsHouseCalls = performsHouseCalls;
    }
   
    public void setVisitFee(double visitFee) {
        this.visitFee = visitFee;
    }
    
   
    public void addPatient(Patient patient) throws PatientException {
                int currentYear = 2025; 
        int age = currentYear - patient.getBirthYear();
        
        if (age <= 65) {
            throw new PatientException("Patient is too young for a gerontologist. Age: " + age);
        }
        
                super.addPatient(patient);
    }
    
  
   
    public String toString() {
        return super.toString() + 
               String.format("\nGerontologist: %s | visit fee=%02.2f", 
                       (performsHouseCalls ? "performs house calls" : "no house calls"), 
                       visitFee);
    }
    
    
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        if (!(obj instanceof Gerontologist)) {
            return false;
        }
        
        Gerontologist other = (Gerontologist) obj;
        return this.performsHouseCalls == other.performsHouseCalls && 
               Math.abs(this.visitFee - other.visitFee) <= 0.05; 
    }
}