import model.Cliente;
import service.ClienteService;
import service.PedidoService;
import service.ProdutoService;
import service.RelatorioService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    Scanner scanner = new Scanner(System.in);
    ClienteService clienteService = new ClienteService();
    ProdutoService produtoService = new ProdutoService();
    PedidoService pedidoService = new PedidoService(clienteService, produtoService);
    RelatorioService relatorioService = new RelatorioService(pedidoService, produtoService);
    boolean rodando = true;
    while (rodando){
        exibirMenu();
        System.out.println("Escolha um opcção");
        String opcao = scanner.nextLine();;
        switch (opcao){
            case "1":
                clienteService.cadastrar(scanner);
                break;
            case "2":
                produtoService.cadastrar(scanner);
                break;
            case "3":
                produtoService.listarProdutos();
                break;
            case "4":''
                pedidoService.criarPedido(scanner);
                break;
            case "5":
                break;
            case "6":
                pedidoService.cancelarPedido(scanner);
                break;
            case "7":
                menuRelatorio(scanner, relatorioService);
                break;
            case "0":
                rodando = false;
                System.out.println("\nSaindo do sistema... Até logo!");
                break;
            default:
                System.out.println("Opção inválida! Tente novamente.");
        }
    }


    scanner.close();





}

        private static void exibirMenu(){
            System.out.println("\n========== SISTEMA DE GESTÃO ==========");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Produto");
            System.out.println("3 - Listar Produtos / Estoque");
            System.out.println("4 - Criar Novo Pedido");
            System.out.println("5 - Avançar Status do Pedido");
            System.out.println("6 - Cancelar Pedido");
            System.out.println("7 - Central de Relatórios");
            System.out.println("0 - Sair");
            System.out.println("=======================================");
        }
        private  static void menuRelatorio(Scanner scanner, RelatorioService relatorioService){
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
