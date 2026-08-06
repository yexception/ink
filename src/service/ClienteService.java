package service;

import model.Cliente;
import repository.ClienteRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class ClienteService {
    private ClienteRepository repository = new ClienteRepository();
    private ArrayList<Cliente> clientes = new ArrayList<>();

    public void cadastrar(Scanner scanner){
        System.out.println("=== Cadastro de Cliente =====");
        System.out.println("Digite o nome: ");
       String nome = scanner.nextLine();
        System.out.println("Digite o cpf");
        String cpf = scanner.nextLine();
        System.out.println("Digite o Telefone");
        String telefone = scanner.nextLine();
        System.out.println("Digite o endereço");
        String endereco = scanner.nextLine();
        System.out.println("Digite o email");
        String email = scanner.nextLine();
        Cliente cliente = new Cliente(nome,telefone,endereco,cpf,
        email);

        clientes.add(cliente);
        repository.salvar(clientes);
        System.out.println("Cliente novo cadastrado com sucesso!");
    }

    public void listar(){
        System.out.println("Listando Clientes.....");
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }
    public void buscar(Scanner scanner){
        System.out.println("Digite o nome do cliente: ");
        String nomeDigitado = scanner.nextLine();
        boolean encontrado = false;
        for(Cliente cliente : clientes){
            if(cliente.getNome().equalsIgnoreCase(nomeDigitado)){
                System.out.println(cliente);
                encontrado = true;
            }
        }
        if(!encontrado){
            System.out.println("Cliente não encontrado!");
        }
    }
    public void editar(Scanner scanner){
        System.out.println("Pagina de edição de clientes");
        System.out.println("Escolha qual dado você quer alterar: "
                + "\n  1 + Nome "
                + "\n  2 + Cpf "
                + "\n  3 + Telefone "
                + "\n  4 + Endereço "
                + "\n  5 + Email ");
        int clienteEditavel = scanner.nextInt();
        scanner.nextLine();
        boolean encontrou2 = false;
        for (Cliente cliente : clientes){

            switch (clienteEditavel){
                case 1: System.out.println("Digite o novo nome");
                   String nomeNovo = scanner.nextLine();
                   cliente.setNome(nomeNovo);
                   encontrou2 = true;
                    break;
                case 2:
                    System.out.println("Digite o novo cpf");
                    String cpfNovo = scanner.nextLine();
                    cliente.setCpf(cpfNovo);
                    encontrou2 = true;
                    break;
                case 3:
                    System.out.println("Digite o novo telefone");
                    String telefoneNovo = scanner.nextLine();
                    cliente.setTelefone(telefoneNovo);
                    encontrou2 = true;
                    break;
                case 4:
                    System.out.println("Digite o novo endereço");
                    String enderecoNovo = scanner.nextLine();
                    cliente.setEndereco(enderecoNovo);
                    encontrou2 = true;
                    break;
                case 5: System.out.println("Digite o novo email");
                    String emailNovo = scanner.nextLine();
                    cliente.setEmail(emailNovo);
                    encontrou2 = true;
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        if(!encontrou2){
            System.out.println("Cliente inexistente!");
        }

        System.out.println("Cliente atualizado com sucesso!");
    }

    public void excluir(Scanner scanner){
        System.out.println("Selecione qual cliente deseja excluir");
        Cliente clienteRemover = null;
        String clienteExcluido = scanner.nextLine();;
        for (Cliente cliente : clientes){
            if(cliente.getNome().equalsIgnoreCase(clienteExcluido)){
                clienteRemover = cliente;
            }
        }
        if(clienteRemover != null){
            clientes.remove(clienteRemover);
            System.out.println("Cliente excluido com sucesso");
        }else{
            System.out.println("Cliente Inexistente");
        }

    }
    public Cliente buscarCpf(String cpf){
        if(cpf == null){
            return null;
        }
        for (Cliente cliente : clientes){
            if(cliente.getCpf().equals(cpf)){
                return cliente;
            }
        }
        return null;
    }

}
