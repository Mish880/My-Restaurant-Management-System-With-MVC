package tm;

public class CustomerTM {

     private String Id;
     private String Name;
     private String address;
     private double salary;

    public CustomerTM() {
    }

    public CustomerTM(String id, String name, String address, double salary) {
        setId(id);
        setName(name);
        this.setAddress(address);
        this.setSalary(salary);
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
