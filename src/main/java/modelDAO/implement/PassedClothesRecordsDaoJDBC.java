package modelDAO.implement;

import Db.DB;
import Db.DBException;
import model.entities.PassedClothesRecords;
import modelDAO.PassedClothesRecordsDAO;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PassedClothesRecordsDaoJDBC implements PassedClothesRecordsDAO {

    private Connection conn;

    public PassedClothesRecordsDaoJDBC() {
    }

    public PassedClothesRecordsDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(PassedClothesRecords obj) {

        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "INSERT INTO passed_clothes_records (WorkerId, RecordDate, NumberOfClothes, EarningPerCloth) VALUES (?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS
            );

            st.setInt(1, obj.getWorkerId());
            st.setString(2, obj.getRecordDate().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
            st.setInt(3, obj.getNumberOfClothes());
            st.setDouble(4, obj.getEarningPerCloth());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    obj.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DBException("Nenhuma linha afetada!");
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }

    }

    @Override
    public void update(PassedClothesRecords obj) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                    "UPDATE passed_clothes_records SET WorkerId = ?, RecordDate = ?, NumberOfClothes = ?, EarningPerCloth = ? WHERE Id = ?"
            );
            st.setInt(1, obj.getWorkerId());
            st.setString(2, obj.getRecordDate().toLocalDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
            st.setInt(3, obj.getNumberOfClothes());
            st.setDouble(4, obj.getEarningPerCloth());
            st.setInt(5, obj.getId());

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
            st = conn.prepareStatement("DELETE FROM passed_clothes_records WHERE Id = ?");
            st.setInt(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public PassedClothesRecords findById(Integer id) {

        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM passed_clothes_records WHERE Id = ?"
            );
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                PassedClothesRecords obj = new PassedClothesRecords();
                obj.setId(rs.getInt("Id"));
                obj.setWorkerId(rs.getInt("WorkerId"));
                obj.setRecordDate(Date.valueOf(LocalDate.parse(rs.getString("RecordDate")))); // Mapeia a string do banco para LocalDate
                obj.setNumberOfClothes(rs.getInt("NumberOfClothes"));
                obj.setEarningPerCloth(rs.getDouble("EarningPerCloth"));
                return obj;
            }
            return null;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

    }

    @Override
    public List<PassedClothesRecords> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT * FROM passed_clothes_records ORDER BY RecordDate DESC"
            );
            rs = st.executeQuery();
            List<PassedClothesRecords> list = new ArrayList<>();
            while (rs.next()) {
                PassedClothesRecords obj = new PassedClothesRecords();
                obj.setId(rs.getInt("Id"));
                obj.setWorkerId(rs.getInt("WorkerId"));
                obj.setRecordDate(Date.valueOf(LocalDate.parse(rs.getString("RecordDate"))));
                obj.setNumberOfClothes(rs.getInt("NumberOfClothes"));
                obj.setEarningPerCloth(rs.getDouble("EarningPerCloth"));
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
    public Double findDailyEarningsByWorkerAndData(Integer workerId, LocalDate date) {

        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT SUM(NumberOfClothes * EarningPerCloth) FROM passed_clothes_records WHERE WorkerId = ? AND RecordDate = ?"
            );
            st.setInt(1, workerId);
            st.setString(2, date.format(DateTimeFormatter.ISO_LOCAL_DATE));
            rs = st.executeQuery();
            if (rs.next()) {
                // A função SUM() pode retornar NULL se não houver registros.
                // Usamos rs.getObject para checar se é NULL e retornar 0.0 nesse caso.
                Object result = rs.getObject(1);
                return (result != null) ? rs.getDouble(1) : 0.0;
            }
            return 0.0;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }

    }

    @Override
    public Double findMonthlyEarningsByWorkerAndMonth(Integer workerId, int year, int month) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT SUM(NumberOfClothes * EarningPerCloth) FROM passed_clothes_records " +
                            "WHERE WorkerId = ? AND YEAR(RecordDate) = ? AND MONTH(RecordDate) = ?"
            );
            st.setInt(1, workerId);
            st.setInt(2, year);
            st.setInt(3, month);
            rs = st.executeQuery();
            if (rs.next()) {
                Object result = rs.getObject(1);
                return (result != null) ? rs.getDouble(1) : 0.0;
            }
            return 0.0;
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
    }

    @Override
    public Map<String, Double> findMonthlyEarningsForAllWorkers(int year, int month) {
        PreparedStatement st = null;
        ResultSet rs = null;
        Map<String, Double> monthlyEarnings = new LinkedHashMap<>();
        try {
            st = conn.prepareStatement(
                    "SELECT w.Name, SUM(pcr.NumberOfClothes * pcr.EarningPerCloth) AS TotalFaturado " +
                            "FROM workers AS w " +
                            "JOIN passed_clothes_records AS pcr ON w.Id = pcr.WorkerId " +
                            "WHERE YEAR(pcr.RecordDate) = ? AND MONTH(pcr.RecordDate) = ? " +
                            "GROUP BY w.Id, w.Name " +
                            "ORDER BY TotalFaturado DESC"
            );
            st.setInt(1, year);
            st.setInt(2, month);
            rs = st.executeQuery();
            while (rs.next()) {
                String workerName = rs.getString("Name");
                Double totalEarnings = rs.getDouble("TotalFaturado");
                monthlyEarnings.put(workerName, totalEarnings);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
        }
        return monthlyEarnings;
    }

    @Override
    public boolean hasRecordForWorkerAndDate(Integer workerId, LocalDate date) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement(
                    "SELECT COUNT(*) FROM passed_clothes_records WHERE WorkerId = ? AND RecordDate = ?"
            );
            st.setInt(1, workerId);
            st.setString(2, date.format(DateTimeFormatter.ISO_LOCAL_DATE));
            rs = st.executeQuery();
            if (rs.next()) {
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
