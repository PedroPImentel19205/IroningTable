package modelDAO;

import DB.DB;
import modelDAO.implement.DepartmentDaoJDBC;
import modelDAO.implement.PassedClothesRecordsDaoJDBC;
import modelDAO.implement.WorkersDaoJDBC;

public class DAOFactory {

    public static DepartmentDAO createDepartmentDao(){
        return new DepartmentDaoJDBC(DB.getConnection());
    }

    public static PassedClothesRecordsDAO createPassedDao(){
        return new PassedClothesRecordsDaoJDBC(DB.getConnection());
    }

    public static WorkersDAO createWorkersDao(){
        return new WorkersDaoJDBC(DB.getConnection());
    }

}
