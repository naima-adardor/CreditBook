package Model;

public class Client extends Person {
    int id;
    public Client(int id, String last_name, String first_name, String phone_number, String email, String address) {
        super(id, last_name, first_name, phone_number, email, address);
    }


    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }


}
