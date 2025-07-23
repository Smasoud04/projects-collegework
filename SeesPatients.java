
	public interface SeesPatients {
		static final int MAX_PATIENTS = 100;
		void addPatient(Patient p) throws PatientException;
		Patient [] getPatients();
		String getPatientsAsString();
		boolean isPatient(Patient p);
	}



