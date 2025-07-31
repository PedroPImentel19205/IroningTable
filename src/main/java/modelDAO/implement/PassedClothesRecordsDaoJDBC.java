package modelDAO.implement;

import model.entities.PassedClothesRecords;
import modelDAO.PassedClothesRecordsDAO;

import java.sql.Connection;
import java.time.LocalDate;
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

    }

    @Override
    public void update(PassedClothesRecords obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public PassedClothesRecords findById(Integer id) {
        return null;
    }

    @Override
    public List<PassedClothesRecords> findAll() {
        return List.of();
    }

    @Override
    public Double findDailyEarningsByWorkerAndData(Integer workerId, LocalDate date) {
        return 0.0;
    }

    @Override
    public Double findMonthlyEarningsByWorkerAndMonth(Integer workerId, int year, int month) {
        return 0.0;
    }

    @Override
    public Map<String, Double> findMonthlyEarningsForAllWorkers(int year, int month) {
        return Map.of();
    }

    @Override
    public boolean hasRecordForWorkerAndDate(Integer workerId, LocalDate date) {
        return false;
    }
}
