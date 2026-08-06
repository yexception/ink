package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.Pedido;
import model.Produto;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = Arquivos.PRODUTOS;
    private ArrayList<Produto> produtos = new ArrayList<>();

    public ProdutoRepository(){
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }
    public boolean salvar(ArrayList<Produto> produtos){
        try {
            mapper.writeValue(file, produtos);
            return true;
        }catch (IOException e){
            System.out.println("Produto nao encontrado");
            return false;
        }
    }

    public ArrayList<Produto> carregar(){
        if(!file.exists()){

            return new ArrayList<>();
        }
        try {
            return mapper.readValue(
                    file,
                    new TypeReference<ArrayList<Produto>>() {}
            );
        }catch (IOException e){
            System.out.println("Erro ao carregar produtos " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<Produto> listarProdutos() {
        return produtos;
    }


}
