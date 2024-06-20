public class Order {
    private int id;
    private boolean completed;
    private String attendant;


    public String getAttendant() {
        return attendant;
    }

    public void setAttendant(String attendant) {
        this.attendant = attendant;
    }


 

    public Order(int id) {
        this.id = id;
        this.completed = false;
        this.attendant = "Waiting";
    }

    public void finish(){
        this.setCompleted(true);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}

