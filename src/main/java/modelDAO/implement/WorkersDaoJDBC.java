package modelDAO.implement;

import model.entities.Workers;
import modelDAO.WorkersDAO;

import java.sql.Connection;
import java.util.List;

public class WorkersDaoJDBC implements WorkersDAO {

    private Connection conn;

    public WorkersDaoJDBC() {
    }

    public WorkersDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Workers obj) {

    }

    @Override
    public void update(Workers obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Workers findById(Integer id) {
        return null;
    }

    @Override
    public List<Workers> findAll() {
        return List.of();
    }
}
