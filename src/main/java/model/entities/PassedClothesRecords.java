package model.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class PassedClothesRecords implements Serializable {

    private Integer id;
    private Integer workerId;
    private Date recordDate;
    private Integer numberOfClothes;
    private Double earningPerCloth;

    public PassedClothesRecords() {
    }

    public PassedClothesRecords(Integer id, Integer workerId, Date recordDate, Integer numberOfClothes, Double earningPerCloth) {
        this.id = id;
        this.workerId = workerId;
        this.recordDate = recordDate;
        this.numberOfClothes = numberOfClothes;
        this.earningPerCloth = earningPerCloth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Integer workerId) {
        this.workerId = workerId;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Integer getNumberOfClothes() {
        return numberOfClothes;
    }

    public void setNumberOfClothes(Integer numberOfClothes) {
        this.numberOfClothes = numberOfClothes;
    }

    public Double getEarningPerCloth() {
        return earningPerCloth;
    }

    public void setEarningPerCloth(Double earningPerCloth) {
        this.earningPerCloth = earningPerCloth;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PassedClothesRecords that = (PassedClothesRecords) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PassedClothesRecords{" +
                "id=" + id +
                ", workerId=" + workerId +
                ", recordDate=" + recordDate +
                ", numberOfClothes=" + numberOfClothes +
                ", earningPerCloth=" + earningPerCloth +
                '}';
    }
}