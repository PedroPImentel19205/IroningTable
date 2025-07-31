package modelDAO.implement;

import Db.DB;
import Db.DBException;
import model.entities.Department;
import modelDAO.DepartmentDAO;

import java.sql.*;
import java.util.ArrayList;
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
                PreparedStatement st = null;
                try {
                      st = conn.prepareStatement(
                              "INSERT INTO workers (Name) VALUES (?)",
                              Statement.RETURN_GENERATED_KEYS
                      );
                      st.setString(1,obj.getName());

                      int rowsAffected = st.executeUpdate();

                      if (rowsAffected > 0){
                              ResultSet rs = st.getGeneratedKeys();
                              if (rs.next()){
                                      int id = rs.getInt(1);
                                      obj.setId(id); // Define o ID gerado no objeto
                              }
                              DB.closeResultSet(rs);
                      }
                      else {
                              throw new DBException("Nenhuma linha afetada!");
                      }
                }
                catch (SQLException e){
                        throw new DBException(e.getMessage());
                }
                finally {
                        DB.closeStatement(st);
                }

        }

        @Override
        public void update(Department obj) {
                PreparedStatement st = null;
                try {
                        st = conn.prepareStatement(
                                "UPDATE workers SET Name = ? WHERE Id = ?"
                        );
                        st.setString(1, obj.getName());
                        st.setInt(2, obj.getId());

                        st.executeUpdate();
                }
                catch (SQLException e){
                        throw new DBException(e.getMessage());
                }
                finally {
                        DB.closeStatement(st);
                }

        }

        @Override
        public void deleteById(Integer id) {
                PreparedStatement st = null;
                try {
                        st = conn.prepareStatement("DELETE FROM departments WHERE Id = ?");
                        st.setInt(1, id);
                        st.executeUpdate();
                } catch (SQLException e) {
                        // Lógica para tratar a falha de integridade referencial.
                        // Isso acontece se houver um Worker associado a este Department.
                        if (e.getSQLState().equals("23000")) {
                                throw new DBException("Não é possível excluir o departamento. Existem trabalhadores associados.");
                        }
                        throw new DBException(e.getMessage());
                } finally {
                        DB.closeStatement(st);
                }

        }

        @Override
        public Department findById(Integer id) {
                PreparedStatement st = null;
                ResultSet rs = null;
                try {
                        st = conn.prepareStatement("SELECT * FROM departments WHERE Id = ?");
                        st.setInt(1, id);
                        rs = st.executeQuery();
                        if (rs.next()) {
                                Department obj = new Department();
                                obj.setId(rs.getInt("Id"));
                                obj.setName(rs.getString("Name"));
                                return obj;
                        }
                        return null; // Retorna nulo se não encontrar o departamento
                } catch (SQLException e) {
                        throw new DBException(e.getMessage());
                } finally {
                        DB.closeResultSet(rs);
                        DB.closeStatement(st);
                }
        }

        @Override
        public List<Department> findAll() {
                PreparedStatement st = null;
                ResultSet rs = null;
                try {
                        st = conn.prepareStatement("SELECT * FROM departments ORDER BY Name");
                        rs = st.executeQuery();
                        List<Department> list = new ArrayList<>();
                        while (rs.next()) {
                                Department obj = new Department();
                                obj.setId(rs.getInt("Id"));
                                obj.setName(rs.getString("Name"));
                                list.add(obj);
                        }
                        return list;
                } catch (SQLException e) {
                        throw new DBException(e.getMessage());
                } finally {
                        DB.closeResultSet(rs);
                        DB.closeStatement(st);
                }
        }
}
