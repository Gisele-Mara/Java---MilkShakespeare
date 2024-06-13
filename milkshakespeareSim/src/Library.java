public class Library extends Pedido {

    private String book;

    public Library (int id, String book){
        super(id);
        this.book = book;
    }


    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

}
