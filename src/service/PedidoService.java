package service;

import model.Cliente;
import model.ItemPedido;
import model.Pedido;
import model.Produto;
import repository.PedidoRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PedidoService {

    private ProdutoService produtoService;
    private ClienteService clienteService;
    private PedidoRepository repository = new PedidoRepository();
    private ArrayList<Pedido> pedidos = new ArrayList<>();



     public PedidoService(ClienteService clienteService, ProdutoService produtoService){
         this.clienteService = clienteService;
         this.produtoService = produtoService;
         this.pedidos = repository.carregar();
     }

    private Pedido buscarPeloID(String id){
         for (Pedido pedido : pedidos){
             if(pedido.getId().equalsIgnoreCase(id)){
                 return pedido;
             }
         }
         return null;
    }
     public void criarPedido(Scanner scanner){
         System.out.println("=====InK Pedidos=====");
         System.out.println("Digite o cpf do cliente: ");
         String cpf = scanner.nextLine();
         Cliente cliente = clienteService.buscarCpf(cpf);

        if(cliente == null){
            System.out.println("Cliente nao cadastrado, deseja cadastrar?");
            return;
        }

         System.out.println("Cliente " + cliente.getNome() + " encontrado");
         System.out.println("Qual o id do pedido? (3 primeiros numero do Cpf com as 3 primeiras letras do nome) " );
         String id = scanner.nextLine();
         LocalDate data = LocalDate.now();
         int adicionarMais = 1;
         ArrayList<ItemPedido> itens = new ArrayList<>();
         do {
             System.out.println("Quais item do pedido? ");
             String item = scanner.nextLine();
             Produto produto = produtoService.buscaPorNome(item);

             if (produto == null) {
                 System.out.println("Produto não encontrado!");
                 return;
             }
             System.out.println("Qual a quantidade? ");
             int quantidade = scanner.nextInt();
             scanner.nextLine();

             ItemPedido itemPedido = new ItemPedido(produto, quantidade);

             itens.add(itemPedido);

             System.out.println("Deseja adicionar mais algum produto? ");
             System.out.println("1 - Sim");
             System.out.println("0 - Não");
             adicionarMais = scanner.nextInt();
             scanner.nextLine();


         }while (adicionarMais == 1) ;

         for(ItemPedido item : itens){
             if(item.getProduto().getEstoque() < item.getQuantidade()){
                 System.out.println("Estoque insuficiente para o produto: " + item.getProduto().getNome());
                 return;
             }
         }
         for (ItemPedido item : itens){
             produtoService.baixarEstoque(item.getProduto(), item.getQuantidade());
         }
          System.out.println("Finalizando pedido!");
         Pedido pedido = new Pedido(cliente, id, data, itens);
         pedidos.add(pedido);
         repository.salvar(pedidos);

         }

         public void cancelarPedido(Scanner scanner){
             System.out.println("InK Cancelamento");
             System.out.println("Digite o id do pedido que deseja cancelar: ");
             String id = scanner.nextLine();
             Pedido pedido = buscarPeloID(id);
             if(pedido == null){
                 System.out.println("Pedido não encontrado!");
                 return;
             }
             Pedido.Status cancelado = Pedido.Status.CANCELADO;
             if(pedido.getStatus() == cancelado){
                 System.out.println("Pedido ja esta cancelado");
                 return;
             }
             for(ItemPedido item : pedido.getItens()){
                 produtoService.reporEstoque(item.getProduto(), item.getQuantidade());

             }
             pedido.setStatus(cancelado);
             System.out.println("Pedido " + id + "cancelado!" );
             repository.salvar(pedidos);
         }
        public List<Pedido> listarPedidos() {
        return pedidos;
         }


     }

