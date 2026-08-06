package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.Cliente;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ClienteRepository {

    private final  ObjectMapper mapper = new ObjectMapper();
    private final File file = Arquivos.CLIENTES;

    public ClienteRepository(){
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    public boolean salvar(ArrayList<Cliente> clientes)  {
        try {
            mapper.writer().writeValue(file, clientes);
            return  true;
        } catch (IOException e) {
            System.out.println("Erro a salvar os clientes...");
            return false;
        }

    }

    public ArrayList<Cliente> carregar(){
        if(!file.exists()){
            return  new ArrayList<>();
        }
        try {
            return mapper.readValue(
                    file,
                    new TypeReference<ArrayList<Cliente>>() {}
            );
        }catch (IOException e){
            System.out.println("Erro a carregar clientes " + e.getMessage());
            return new ArrayList<>();
    }
    }

}
