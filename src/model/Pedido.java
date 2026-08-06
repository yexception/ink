package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Pedido {

    private Cliente cliente;
    private String id;
    private Status status;
    private LocalDate data;
    private ArrayList<ItemPedido> itens;

    public Pedido(){

    }

    public double getValorTotal(){
        double total = 0;
        for (ItemPedido item: itens){
            total += item.getSubtotal();
        }
        return total ;
    }
    public enum  Status{
        ABERTO(1, "ABERTO"),
        EM_PRODUCAO(2,"EM PRODUÇÃO" ),
        FINALIZADO(3, "FINZALIDADO"),
        CANCELADO(4,"CANCELADO");

        private final int codigo;
        private final String descricao;
        Status(int codigo, String descricao) {
            this.codigo = codigo;
            this.descricao = descricao;
        }
        public int getCodigo(){
            return codigo;
        }
        public String getDescricao(){
            return descricao;
        }
    }
    public Pedido(Cliente cliente, String id, LocalDate data, ArrayList<ItemPedido> itens) {
        this.cliente = cliente;
        this.id = id;
        this.status = Status.ABERTO;
        this.data = data;
        this.itens = itens;
    }



    @Override
    public String toString() {
        return "Pedido " +
                "\nCliente: " + cliente +
                "\nID: " + id + '\'' +
                "\nStatus: " + status +
                "\nData: " + data +
                "\nItens: " + itens ;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(ArrayList<ItemPedido> itens) {
        this.itens = itens;
    }


}
