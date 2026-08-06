package model;

public class Produto {

    private String nome;
    private double preco;
    private String categoria;
    private String forcenedor;
    private int estoque;
    private double custoDeCompra;
    private String descricao;


    public Produto(){}

    public Produto(String nome, double preco, String categoria, String forcenedor, int estoque, double custoDeCompra, String descricao) {
        this.nome = nome;
        this.preco = preco;
        this.categoria = categoria;
        this.forcenedor = forcenedor;
        this.estoque = estoque;
        this.custoDeCompra = custoDeCompra;
        this.descricao = descricao;
    }

    @Override
    public String toString(){
        return "Nome: " + getNome() +
                "\nPreço: " + getPreco() +
                "\nCategoria: " + getCategoria() +
                "\nFornecedor: " + getForcenedor()+
                "\nEstoque: " + getEstoque() +
                "\nCusto de compra: " + getCustoDeCompra()+
                "\nDescrição: " + getDescricao();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getForcenedor() {
        return forcenedor;
    }

    public void setForcenedor(String forcenedor) {
        this.forcenedor = forcenedor;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public double getCustoDeCompra() {
        return custoDeCompra;
    }

    public void setCustoDeCompra(double custoDeCompra) {
        this.custoDeCompra = custoDeCompra;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
