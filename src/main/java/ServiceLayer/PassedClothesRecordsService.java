package ServiceLayer;

import Db.IntegrityException;
import Db.ValidationException;
import model.entities.PassedClothesRecords;
import modelDAO.DAOFactory;
import modelDAO.PassedClothesRecordsDAO;

public class PassedClothesRecordsService {

    private PassedClothesRecordsDAO recordsDAO = DAOFactory.createPassedDao();

    /**
     * Tenta registrar a produção diária.
     * Lança IntegrityException se já existir um registro para o mesmo passador e data.
     */

    public void registerDailyProduction(PassedClothesRecords record){

        // --- Válidação básica do objeto ---
        // Se o objeto for nulo ou faltar informações cruciais
        if (record == null || record.getWorkerId() == null || record.getRecordDate() == null){
            throw new ValidationException("Objeto PassedClothesRecord inválido. Campos essenciais não podem ser nulos.");
        }

        // --- A LÓGICA DE VERIFICAÇÃO ---
        // 1. Chamar o método do DAO para verificar a existência
        if(recordsDAO.hasRecordForWorkerAndDate(record.getWorkerId(), record.getRecordDate().toLocalDate()))

        // 2. Se a verificação retornar true, lançar uma exceção
        throw new IntegrityException("Já existe um registro de produção para o passador com ID " +
                record.getWorkerId() + " na data " + record.getRecordDate() + ".");

        // Se a condição acima for falsa (ou seja, se o registro não existe),
        // o código continua e realiza a inserção.

        recordsDAO.insert(record);
    }

}
