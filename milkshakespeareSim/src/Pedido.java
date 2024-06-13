public class Pedido {
    private int id;
    private boolean concluido;

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    private String produto;

    public Pedido(int id) {
        this.id = id;
        this.concluido = false;
    }

    public void concluir(){
        this.setConcluido(true);
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }
}

