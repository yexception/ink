package repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.Pedido;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class PedidoRepository {

    private final ObjectMapper mapper = new ObjectMapper();
    private final File file = Arquivos.PEDIDOS;

     public PedidoRepository(){
         mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
         mapper.enable(SerializationFeature.INDENT_OUTPUT);
     }

    public boolean salvar(ArrayList<Pedido> pedidos) {
        try {
            mapper.writeValue(file, pedidos);
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao salvar os pedidos: " + e.getMessage());
            return false;
        }
    }
    public ArrayList<Pedido> carregar() {
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try {
            return mapper.readValue(file, new TypeReference<ArrayList<Pedido>>() {});
        } catch (IOException e) {
            System.out.println("Erro ao carregar os pedidos: " + e.getMessage());
            return new ArrayList<>();
        }
    }

}
