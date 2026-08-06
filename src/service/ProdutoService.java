package service;

import model.Pedido;
import model.Produto;
import repository.ProdutoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProdutoService {

    private ProdutoRepository repository = new ProdutoRepository();
    private ArrayList<Produto> produtos = new ArrayList<>();

    public ProdutoService(){
        this.produtos = repository.carregar();
    }
    public void cadastrar(Scanner scanner){
        System.out.println("=== Cadastro de produtos ===");
        System.out.println("Digite o nome do produto");
        String nome = scanner.nextLine();
        System.out.println("Qual o preço?");
        double preco = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Qual a categoria: " + "\nImpressao 3D " + "\n Camisas "+"\nPersonalizados");
        String categoria = scanner.nextLine();
        System.out.println("Fornecedor?");
        String fornecedor = scanner.nextLine();
        System.out.println("Quantidade de estoque?");
        int estoque = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Qual o custo de compra?");
        double custoDeCompra = scanner.nextDouble();
        System.out.println("Digite uma descrição para o produto: ");
        String descricao = scanner.nextLine();

        Produto produto = new Produto(nome,
                preco,
                categoria,
                fornecedor,
                estoque,
                custoDeCompra,descricao);
        produtos.add(produto);
        repository.salvar(produtos);
        System.out.println("Produto Cadastrado com sucessso!");

    }
    public void listar(){
        System.out.println("Lista de produtos: ");
        for (Produto produto : produtos){
            System.out.println(produto);
        }

        }
        public void buscar(Scanner scanner){
            System.out.println("Nome do produto?:");
            String nomeDoProduto = scanner.nextLine();;
            boolean encontrado = false;
            for (Produto produto: produtos){
                if(produto.getNome().equalsIgnoreCase(nomeDoProduto)){
                    System.out.println(produto);
                    encontrado = true;
                }
            }
            if(!encontrado){
                System.out.println("Produto nao cadastrado!");
            }
        }
    public void editar(Scanner scanner){
        System.out.println("Pagina de edição de produtos");
        System.out.println("Qual o nome do produto?");
        String produtoEditavel = scanner.nextLine();
        boolean encontrou2 = false;

        for (Produto produto : produtos){
            if(produto.getNome().equalsIgnoreCase(produtoEditavel)){
                menuEdicao(produto, scanner);
                repository.salvar(produtos);
                encontrou2 = true;
                break;
            }
        }
        if(!encontrou2){
            System.out.println("Produto inexistente!");
        }

        System.out.println("Produto atualizado com sucesso!");
    }

    private void menuEdicao(Produto produto, Scanner scanner){
        int option = -1;
        while(option  != 0){
            System.out.println("Qual opção você quer editar?");
            System.out.println("1 - Nome ");
            System.out.println("2 - Preço ");
            System.out.println("3 - Quantidade de no estoque");
            System.out.println("4 - Forcenedor");
            System.out.println("5 - Categoria");
            System.out.println("6 - Descrição");
            System.out.println("7 - Custo de compra");
            System.out.println("0 - Voltar");

            option = scanner.nextInt();;
            scanner.nextLine();

            switch (option){
                case 1:
                    System.out.println("Digite o novo nome do produto ");
                    String nome = scanner.nextLine();
                    produto.setNome(nome);
                    break;
                case 2:
                    System.out.println("Digite o novo preço: ");
                    double preco = scanner.nextDouble();;
                    scanner.nextLine();
                    produto.setPreco(preco);
                    break;
                case 3:
                    System.out.println("Qual a nova quantidade no estoque? ");
                    int estoque = scanner.nextInt();;
                    scanner.nextLine();
                    produto.setEstoque(estoque);
                    break;
                case 4:
                    System.out.println("Qual a nova quantidade no estoque? ");
                    String fornecedor = scanner.nextLine();;
                    produto.setForcenedor(fornecedor);
                    break;
                case 5:
                    System.out.println("QUal a nova categoria? ");
                    String categoria = scanner.nextLine();
                    produto.setCategoria(categoria);
                    break;
                case 6:
                    System.out.println("Qual a nova descrição? ");
                    String descricao = scanner.nextLine();
                    produto.setDescricao(descricao);
                    break;
                case 7 :
                    System.out.println("Qual o novo custo de compra? ");
                    double custoDeCompra = scanner.nextDouble();
                    scanner.nextLine();
                    produto.setCustoDeCompra(custoDeCompra);
                    break;
                case 0:
                    System.out.println("Voltando ao menu....");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    public void excluir(Scanner scanner){
        System.out.println("Selecione qual produto deseja excluir");
        Produto produtoRemover = null;
        String produtoExcluido = scanner.nextLine();;
        for (Produto produto : produtos){
            if(produto.getNome().equalsIgnoreCase(produtoExcluido)){
                produtoRemover = produto;

            }
        }
        if(produtoRemover != null){
            produtos.remove(produtoRemover);
            repository.salvar(produtos);
            System.out.println("Produto excluido com sucesso");
        }else{
            System.out.println("Produto Inexistente");
        }

    }

    public Produto buscaPorNome(String nome){
        if(nome == null){
            return null;

        }
        for(Produto produto: produtos){
            if(nome.equalsIgnoreCase(produto.getNome())){
                return produto;
            }
        }
        return null;
    }

    public boolean baixarEstoque(Produto produto, int quantidade){
        if(produto == null){
            return false;
        }
        if(produto.getEstoque() >= quantidade){
            int estoqueFinal = produto.getEstoque() - quantidade;
            produto.setEstoque(estoqueFinal);
            repository.salvar(produtos);
            return true;
        }else{
            return false;
        }

    }


    public boolean reporEstoque(Produto produto, int quantidade){
        if(produto == null){
            return false;
        }
        if(quantidade <= 0){
            return  false;
        }
        int estoqueFinal = produto.getEstoque() + quantidade;
        produto.setEstoque(estoqueFinal);
        repository.salvar(produtos);
        return  true;
    }

    public List<Produto> listarProdutos() {
        return produtos;
    }

}
