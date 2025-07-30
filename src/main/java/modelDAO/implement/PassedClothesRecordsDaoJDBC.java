package modelDAO.implement;

import modelDAO.PassedClothesRecordsDAO;

import java.sql.Connection;

public class PassedClothesRecordsDaoJDBC implements PassedClothesRecordsDAO {

    private Connection conn;

    public PassedClothesRecordsDaoJDBC() {
    }

    public PassedClothesRecordsDaoJDBC(Connection conn) {
        this.conn = conn;
    }
}
