package modelDAO.implement;

import modelDAO.WorkersDAO;

import java.sql.Connection;

public class WorkersDaoJDBC implements WorkersDAO {

    private Connection conn;

    public WorkersDaoJDBC() {
    }

    public WorkersDaoJDBC(Connection conn) {
        this.conn = conn;
    }
}
