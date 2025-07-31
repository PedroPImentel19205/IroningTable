package modelDAO.implement;

import model.entities.Department;
import modelDAO.DepartmentDAO;

import java.sql.Connection;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDAO {

        private Connection conn;

        public DepartmentDaoJDBC() {
        }

        public DepartmentDaoJDBC(Connection conn) {
                this.conn = conn;
        }

        @Override
        public void insert(Department obj) {

        }

        @Override
        public void update(Department obj) {

        }

        @Override
        public void deleteById(Integer id) {

        }

        @Override
        public Department findById(Integer id) {
                return null;
        }

        @Override
        public List<Department> findAll() {
                return List.of();
        }
}
