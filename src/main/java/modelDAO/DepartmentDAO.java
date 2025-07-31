package modelDAO;

import model.entities.Department;

import java.util.List;

public interface DepartmentDAO {

    // --- Métodos CRUD Padrão ---
    void insert(Department obj);    // Para inserir um novo registro
    void update(Department obj);    // Para alterar um registro existente
    void deleteById(Integer id);    // Para deletar um registro pelo ID
    Department findById(Integer id);// Para buscar um registro específico pelo ID
    List<Department> findAll(); // Para buscar todos os registros

}
