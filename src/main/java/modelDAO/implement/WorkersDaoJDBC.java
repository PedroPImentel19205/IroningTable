package modelDAO.implement;

import Db.DB;
import Db.DBException;
import Db.IntegrityException;
import model.entities.Workers;
import modelDAO.WorkersDAO;

import java.sql.*;
import java.util.ArrayList;
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
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO workers (Name) VALUES (?)",
                    Statement.RETURN_GENERATED_KEYS
            );
            st.setString(1, obj.getName());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id); // Define o ID gerado no objeto Java
                }
                DB.closeResultSet(rs);
            } else {
                throw new DBException("Nenhuma linha afetada! A inserção pode ter falhado.");
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Workers obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE workers SET Name = ? WHERE Id = ?"
            );
            st.setString(1, obj.getName());
            st.setInt(2, obj.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM workers WHERE Id = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            // Captura o erro de integridade referencial,
            // que acontece se o Worker tiver registros em passed_clothes_records
            if (e.getSQLState().equals("23000")) {
                throw new IntegrityException("Não é possível excluir o passador. Existem registros associados.");
            }
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Workers findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM workers WHERE Id = ?"
            );
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Workers obj = new Workers();
                obj.setId(rs.getInt("Id"));
                obj.setName(rs.getString("Name"));
                return obj;
            }
            return null; // Retorna nulo se não encontrar o ID
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public List<Workers> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM workers ORDER BY Name"
            );
            rs = st.executeQuery();
            List<Workers> list = new ArrayList<>();
            while (rs.next()) {
                Workers obj = new Workers();
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

    @Override
    public boolean hasRecords(Integer workerId) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT COUNT(*) FROM passed_clothes_records WHERE WorkerId = ?"
            );
            st.setInt(1, workerId);
            rs = st.executeQuery();
            if (rs.next()) {
                // Se a contagem for maior que 0, significa que existem registros
                return rs.getInt(1) > 0;
            }
            return false;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }
}
