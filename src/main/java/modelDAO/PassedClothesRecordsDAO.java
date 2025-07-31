package modelDAO;

import model.entities.Department;
import model.entities.PassedClothesRecords;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public interface PassedClothesRecordsDAO {

    // --- Métodos CRUD Padrão ---
    void insert(PassedClothesRecords obj);    // Para inserir um novo registro
    void update(PassedClothesRecords obj);    // Para alterar um registro existente
    void deleteById(Integer id);    // Para deletar um registro pelo ID
    PassedClothesRecords findById(Integer id);// Para buscar um registro específico pelo ID
    List<PassedClothesRecords> findAll(); // Para buscar todos os registros

    // --- Métodos Específicos do Negócio ---
    Double findDailyEarningsByWorkerAndData (Integer workerId, LocalDate date); // Método para calcular o faturamento total de um único passador em um dia específico.
    Double findMonthlyEarningsByWorkerAndMonth (Integer workerId, int year, int month); // Método  calcular o faturamento total de um único passador em um mês e ano específicos.
    Map<String,Double> findMonthlyEarningsForAllWorkers (int year, int month); // Método para  para calcular o faturamento total de todos os passadores para um mês e ano específicos
    boolean hasRecordForWorkerAndDate(Integer workerId,LocalDate date); // Este método irá verificar a existência de um registro no banco de dados.



}
