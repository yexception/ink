package service;

import model.Cliente;
import model.ItemPedido;
import model.Pedido;
import model.Produto;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RelatorioService {

    private PedidoService pedidoService;
    private ProdutoService produtoService;


    public RelatorioService(PedidoService pedidoService, ProdutoService produtoService) {
        this.pedidoService = pedidoService;
        this.produtoService = produtoService;
    }

    public void faturamentoTotal(){
        double faturamento = 0;
        for (Pedido pedido : pedidoService.listarPedidos()){
            if(pedido.getStatus() == Pedido.Status.FINALIZADO){
                faturamento += pedido.getValorTotal();
            }


        }
        System.out.println("_-_-_-RELATORIO_-_-_");
        System.out.println("Faturamento total");
        System.out.println("R$ " + faturamento);
    }
    public void estoqueBaixo(){
        System.out.println("ESTOQUE BAIXO ->");
        for (Produto produto : produtoService.listarProdutos() ){
            int quantidade = produto.getEstoque();
            if(produto.getEstoque() <= 5){

                System.out.println(produto.getNome() + " -> " + quantidade );
            }

        }
    }
    public Map<String, Integer> maisVendidos(){
        HashMap<String, Integer> produtosVendidos = new HashMap<>();
        for (Pedido pedido : pedidoService.listarPedidos()){

            if(pedido.getStatus() == Pedido.Status.FINALIZADO){
                for (ItemPedido item : pedido.getItens()){
                    String nome = item.getProduto().getNome();
                    int quantidade = item.getQuantidade();
                    if (produtosVendidos.containsKey(nome)){
                        int qntdAtual = produtosVendidos.get(nome);
                        produtosVendidos.put(nome, qntdAtual + quantidade);
                    }else{
                        produtosVendidos.put(nome, quantidade);
                    }
                }
            }
        }
        System.out.println("\n Relatorio de MAIS VENDIDOS");
        if(produtosVendidos.isEmpty()){
            System.out.println("Nenhuma venda finalizada até o momento");
        }else{
            List<Map.Entry<String, Integer>> lista = new ArrayList<>(produtosVendidos.entrySet());
            Collections.sort(lista, (a,b) -> Integer.compare(b.getValue(), a.getValue()));
                for(int i = 0; i < Math.min(5, lista.size()); i++){
                    Map.Entry<String, Integer> item = lista.get(i);
                    System.out.println(item.getKey() + " ->" + item.getValue() + " de unidades!");
                }



        }


        return produtosVendidos;
    }

    public Map<Cliente, Double> maioresCompradores(){
        HashMap<Cliente, Double> clientesCompradores = new HashMap<>();
        for (Pedido pedido : pedidoService.listarPedidos()){
            if(pedido.getStatus() == Pedido.Status.FINALIZADO){
                Cliente nome = pedido.getCliente();
                double valor = pedido.getValorTotal();
                double total = clientesCompradores.getOrDefault(nome, 0.0);
                clientesCompradores.put(nome, total+valor);
            }
            System.out.println("\n Cliente que mais efetuou compras: ");
            if (clientesCompradores.isEmpty()){
                System.out.println("Nenhum pedidp finalizado");
            }else{
                Cliente clienteNum1 = null;
                double maiorGasto = 0.0;
                for (Map.Entry<Cliente, Double> entry : clientesCompradores.entrySet()){
                    if(entry.getValue() > maiorGasto){
                        maiorGasto = entry.getValue();;
                        clienteNum1 = entry.getKey();
                    }
                    System.out.println("🏆 Cliente Top 1: " + clienteNum1);
                    System.out.println("Total investido: R$ " + String.format("%.2f", maiorGasto));
                }
            }


        }
        return clientesCompradores;
    }

    public void rankingCompradores(int limite){
        HashMap<Cliente, Double> gastosPorCliente = new HashMap<>();
        for (Pedido pedido : pedidoService.listarPedidos()){
            if(pedido.getStatus() == Pedido.Status.FINALIZADO){
                Cliente cliente = pedido.getCliente();
                gastosPorCliente.put(cliente, gastosPorCliente.getOrDefault(cliente,0.0) + pedido.getValorTotal());
            }


        }
        if (gastosPorCliente.isEmpty()) {
            System.out.println("Nenhum pedido finalizado encontrado.");
            return;
        }
        List<Map.Entry<Cliente, Double>> lista = new ArrayList<>(gastosPorCliente.entrySet());
        Collections.sort(lista, (a,b) -> Double.compare(b.getValue(), a.getValue()));
        System.out.println("TOP RANKING");
        for(int i = 0; i < Math.min(5, lista.size()); i++){
            Map.Entry<Cliente, Double> entry = lista.get(i);
            System.out.println((i +1) + entry.getKey().getNome() +  " -> R$ " + String.format("%.2f", entry.getValue()));
        }
    }
    public void faturamentoMensal(){
        HashMap<YearMonth, Double> faturamentoMes = new HashMap<>();
        for (Pedido pedido : pedidoService.listarPedidos()){
            if(pedido.getStatus() == Pedido.Status.FINALIZADO){
                YearMonth mesAno = YearMonth.from(pedido.getData());
                double valorPedido = pedido.getValorTotal();;


                double total = faturamentoMes.getOrDefault(mesAno, 0.0);
                faturamentoMes.put(mesAno , total+valorPedido);
            }
        }
        System.out.println("FATURAMENTO MENSAL ");
        if(faturamentoMes.isEmpty()){
            System.out.println("Nenhum pedido finalizado");
            return;
        }
        Map<YearMonth, Double> faturamentoOrdem = new TreeMap<>(faturamentoMes);
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("MM/yyyy");
        for (Map.Entry<YearMonth, Double> entry : faturamentoOrdem.entrySet()) {
            String mesFormatado = entry.getKey().format(formatador);
            double total = entry.getValue();

            System.out.println("Mês " + mesFormatado + " -> R$ " + String.format("%.2f", total));
        }
    }

    public void ticketMedio() {
        double faturamentoTotal = 0;
        int qtdPedidosFinalizados = 0;

        for (Pedido pedido : pedidoService.listarPedidos()) {
            if (pedido.getStatus() == Pedido.Status.FINALIZADO) {
                faturamentoTotal += pedido.getValorTotal();
                qtdPedidosFinalizados++;
            }
        }

        System.out.println("\n TICKET MÉDIO ");
        if (qtdPedidosFinalizados == 0) {
            System.out.println("Nenhum pedido finalizado para calcular o ticket médio.");
            return;
        }

        double ticket = faturamentoTotal / qtdPedidosFinalizados;
        System.out.println("Pedidos finalizados: " + qtdPedidosFinalizados);
        System.out.println("Faturamento total: R$ " + String.format("%.2f", faturamentoTotal));
        System.out.println("Ticket Médio por pedido: R$ " + String.format("%.2f", ticket));
    }

    public void produtosZerados() {
        System.out.println("\nPRODUTOS COM ESTOQUE ZERADO");
        boolean zerado = false;

        for (Produto produto : produtoService.listarProdutos()) {
            if (produto.getEstoque() <= 0) {
                System.out.println(" " + produto.getNome() + " | Preço: R$ " + String.format("%.2f", produto.getPreco()));
                zerado = true;
            }
        }

        if (!zerado) {
            System.out.println("Nenhum produto está zerado no momento. Estoque OK!");
        }
    }

    public void pesquisarPeriodo(LocalDate dataInicio, LocalDate dataFim){
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Pedidos entre: " + dataInicio + " E " + dataFim);
        boolean encontrou = false;
        for(Pedido pedido : pedidoService.listarPedidos()){
            LocalDate dataPedido = pedido.getData();

            if((dataPedido.isEqual(dataInicio) || dataPedido.isAfter(dataFim) ) ){
                System.out.println("ID: " + pedido.getId()+
                        " | Data: " + dataPedido.format(fmt) +
                        " | Cliente: " + pedido.getCliente().getNome() +
                        " | Status: " + pedido.getStatus() +
                        " | Total: R$ " + String.format("%.2f", pedido.getValorTotal()));

                encontrou = true;
            }

        }
        if (!encontrou) {
            System.out.println("Nenhum pedido encontrado neste período.");
        }
    }
}
