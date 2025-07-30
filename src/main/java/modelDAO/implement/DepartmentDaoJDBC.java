package modelDAO.implement;

import modelDAO.DepartmentDAO;

import java.sql.Connection;

public class DepartmentDaoJDBC implements DepartmentDAO {

        private Connection conn;

        public DepartmentDaoJDBC() {
        }

        public DepartmentDaoJDBC(Connection conn) {
                this.conn = conn;
        }
}
