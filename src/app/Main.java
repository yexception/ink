import menu.Menu;
import model.Cliente;
import service.ClienteService;
import service.PedidoService;
import service.ProdutoService;
import service.RelatorioService;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {

    Scanner scanner = new Scanner(System.in);

    System.out.println("Iniciando o sistema InK Graphics...");

    Menu.menuPrincipal(scanner);
    scanner.close();
    System.out.println("Sistema encerrado com sucesso. Até logo!");
}








