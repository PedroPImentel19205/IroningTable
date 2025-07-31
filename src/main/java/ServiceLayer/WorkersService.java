package ServiceLayer;

import Db.IntegrityException;
import model.entities.Workers;
import modelDAO.DAOFactory;
import modelDAO.PassedClothesRecordsDAO;
import modelDAO.WorkersDAO;

import java.util.List;

public class WorkersService {

    // O serviço coordena as operações do DAO
    private WorkersDAO workersDAO = DAOFactory.createWorkersDao();
    private PassedClothesRecordsDAO passedClothesRecordsDAO = DAOFactory.createPassedDao();

    // Métodos CRUD

    public void insert (Workers workers){
        workersDAO.insert(workers);
    }

    public void update (Workers workers){
        workersDAO.update(workers);
    }

    public Workers findById(Integer id){
        return workersDAO.findById(id);
    }

    public List<Workers> findAll(){
        return workersDAO.findAll();
    }

    // Regra de Negócio: Impedir a exclusão se houver registros
    public void deleteById(Integer id){
        if (workersDAO.hasRecords(id)){
            throw new IntegrityException("Não é possível excluir o passador. Existem registros de produção associados.");
        }
        workersDAO.deleteById(id);
    }

    // Método que aplica a regra de negócio
    public Double calculateFinalSalary(Integer workerId, int year, int month) {
        // 1. Obter o faturamento mensal do passador usando o DAO
        Double monthlyEarnings = passedClothesRecordsDAO.findMonthlyEarningsByWorkerAndMonth(workerId,year,month);

        if (monthlyEarnings == null) {
            return 0.0; // Retorna 0 se não houver registros
        }

        return monthlyEarnings;
    }
