package in.h26698.shivtailor;

/**
 * Created by admin on 24-05-2018.
 */

public class Users {
String name , status ;
Integer number;
public Users(){

}
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNumber() {
    String numb = String.valueOf(number);
    return numb;}

    public void setNumber(Integer number) { this.number = number;}

    public Users(String name, String status , Integer number) {

        this.name = name;
        this.status = status;
        this.number = number;
    }
}
