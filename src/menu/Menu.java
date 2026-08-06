package menu;

import service.ClienteService;
import service.PedidoService;
import service.ProdutoService;
import service.RelatorioService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Menu {

    private static final  ClienteService clienteService = new ClienteService();
    private static final ProdutoService produtoService = new ProdutoService();
    private static final PedidoService pedidoService = new PedidoService(clienteService, produtoService);
    private static final RelatorioService relatorioService = new RelatorioService(pedidoService, produtoService);

    public Menu() {
    }

    public static  void menuPrincipal(Scanner scanner){
        boolean menuprincipal = true;
        while(menuprincipal) {

            System.out.println("\n========== InK Graphics ==========");
            System.out.println("1 - Clientes");
            System.out.println("2 - Pedidos");
            System.out.println("3 - Produtos");
            System.out.println("4 - Relatorios");
            System.out.println("0 - Sair");
            System.out.println("=====================================");

            String opcao = scanner.nextLine();
            switch (opcao){
                case "1":
                    menuCliente(scanner);
                    break;
                case "2":
                    menuPedidos(scanner);
                    break;
                case "3":
                    menuProdutos(scanner);
                    break;
                case "4":
                    menuRelatorio(scanner, relatorioService);
                    break;
                case "0":
                    System.out.println("Fechando sistema");
                    menuprincipal =  false;

                    break;
                default:
                    System.out.println("Opçãp Invalida!");
            }
        }
    }

    public static  void menuCliente(Scanner scanner){
        boolean menuClient = true;
        while (menuClient){
            System.out.println("\n========== GESTÃO DE CLIENTES ==========");
            System.out.println("1 - Cadastrar ");
            System.out.println("2 - Listar ");
            System.out.println("3 - Buscar ");
            System.out.println("4 - Editar ");
            System.out.println("5 - Excluir");
            System.out.println(" ");
            System.out.println("0 - Voltar ao menu");
            System.out.println("==========================================");

            String opcao = scanner.nextLine();
            switch (opcao){
                case "1":
                    clienteService.cadastrar(scanner);
                    break;
                case "2":
                    clienteService.listar();
                    break;
                case "3":
                    clienteService.buscar(scanner);
                    break;
                case "4":
                    clienteService.editar(scanner);
                    break;
                case "5":
                    clienteService.excluir(scanner);
                    break;
                case "0":
                    menuClient = false;
                    break;
                default:
                    System.out.println("Opcção invalida");
            }
        }

    }
    public static void menuProdutos(Scanner scanner){
        boolean menuProdutos = true;
        while (menuProdutos){
            System.out.println("\n========== GESTÃO DE PRODUTOS ==========");
            System.out.println("1 - Cadastrar ");
            System.out.println("2 - Listar ");
            System.out.println("3 - Buscar ");
            System.out.println("4 - Editar ");
            System.out.println("5 - Excluir");
            System.out.println(" ");
            System.out.println("0 - Voltar ao menu");
            System.out.println("==========================================");

            String opcao = scanner.nextLine();
            switch (opcao){
                case "1":
                    produtoService.cadastrar(scanner);
                    break;
                case "2":
                    produtoService.listar();
                    break;
                case "3":
                    produtoService.buscar(scanner);
                    break;
                case "4":
                    produtoService.editar(scanner);
                    break;
                case "5":
                    produtoService.excluir(scanner);
                    break;
                case "0":
                    menuProdutos = false;
                    break;
                default:
                    System.out.println("Opcção invalida");
            }
        }
    }
    public static void menuPedidos(Scanner scanner){
        boolean menuPedido = true;
        while (menuPedido){
            System.out.println("\n========== GESTÃO DE PEDIDOS ==========");
            System.out.println("1 - Criar ");
            System.out.println("2 - Listar ");
            System.out.println("3 - Cancelar ");
            System.out.println(" ");
            System.out.println("0 - Voltar ao menu");
            System.out.println("==========================================");

            String opcao = scanner.nextLine();
            switch (opcao){
                case "1":
                    pedidoService.criarPedido(scanner);
                    break;
                case "2":
                    pedidoService.listarPedidos();
                    break;
                case "3":
                    pedidoService.cancelarPedido(scanner);
                    break;

                    case "0":
                    menuPedido = false;
                    break;
                default:
                    System.out.println("Opção invalida");
            }
        }
    }
    public   static void menuRelatorio(Scanner scanner, RelatorioService relatorioService){
        boolean subMenu = true;
        while (subMenu){
            System.out.println("\n_-_-_- CENTRAL DE RELATÓRIOS _-_-_-");
            System.out.println("1 - Faturamento Total");
            System.out.println("2 - Faturamento por Mês");
            System.out.println("3 - Ticket Médio");
            System.out.println("4 - Produtos Mais Vendidos");
            System.out.println("5 - Top Compradores (Clientes)");
            System.out.println("6 - Alerta de Estoque Baixo / Zerado");
            System.out.println("7 - Produtos Sem Movimentação");
            System.out.println("8 - Pesquisar Pedidos por Período");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha um relatório: ");

            String opcao = scanner.nextLine();
            switch (opcao){
                case "1":
                    relatorioService.faturamentoTotal();
                    break;
                case "2":
                    relatorioService.faturamentoMensal();
                    break;
                case "3":
                    relatorioService.ticketMedio();
                    break;
                case "4":
                    relatorioService.maisVendidos();
                    break;
                case "5":
                    relatorioService.maioresCompradores();
                    break;
                case "6":
                    relatorioService.estoqueBaixo();
                    relatorioService.produtosZerados();
                    break;
                case "7":
                    break;
                case "8":
                    try{
                        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                        System.out.print("Digite a data inicial (dd/MM/yyyy): ");
                        LocalDate dataInicio = LocalDate.parse(scanner.nextLine(), fmt);

                        System.out.print("Digite a data final (dd/MM/yyyy): ");
                        LocalDate dataFim = LocalDate.parse(scanner.nextLine(), fmt);

                        relatorioService.pesquisarPeriodo(dataInicio, dataFim);
                    } catch (Exception e) {
                        System.out.println("Erro: Formato de data inválido! Use dd/MM/yyyy.");
                    }
                    break;
                case "0":
                    subMenu = false;
                    break;
                default:
                    System.out.println("Opção de relatório inválida!");
            }
        }
    }
}
