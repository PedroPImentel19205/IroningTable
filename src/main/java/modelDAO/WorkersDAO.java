package modelDAO;

import model.entities.Department;
import model.entities.Workers;

import java.util.List;

public interface WorkersDAO {

    // --- Métodos CRUD Padrão ---
    void insert(Workers obj);    // Para inserir um novo registro
    void update(Workers obj);    // Para alterar um registro existente
    void deleteById(Integer id);    // Para deletar um registro pelo ID
    Workers findById(Integer id);// Para buscar um registro específico pelo ID
    List<Workers> findAll(); // Para buscar todos os registros

}
