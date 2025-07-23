

public class Person {
    private String name;
    private boolean hasDriverLicense;
    private int age;
    private int height;

   
    public Person(String name, boolean hasDriverLicense, int age, int height) {
        this.name = name;
        this.hasDriverLicense = hasDriverLicense;
        this.age = age;
        this.height = height;
    }

    
    public String getName() {
        return name;
    }

    
    public boolean hasDriverLicense() {
        return hasDriverLicense;
    }

    public int getAge() {
        return age;
    }

   
    public int getHeight() {
        return height;
    }

   
    public Person clone() {
        return new Person(this.name, this.hasDriverLicense, this.age, this.height);
    }

    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        
        Person person = (Person) o;
        
        if (hasDriverLicense != person.hasDriverLicense) return false;
        if (age != person.age) return false;
        if (height != person.height) return false;
        return name.equals(person.name);
    }

   
    @Override
    public String toString() {
        String licenseStatus = hasDriverLicense ? "has license" : "no license";
        return String.format("Person [name= %10s | age= %02d | height= %02d | %s]", 
                             name, age, height, licenseStatus);
    }
}