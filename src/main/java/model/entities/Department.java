package model.entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Department implements Serializable {

    private Integer id;
    private String name;
    private String email;
    private Date birthDate;
    private Integer departmentId;

    public Department() {
    }

    public Department(Integer id, String name, String email, Date birthDate, Integer departmentId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.departmentId = departmentId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Department that = (Department) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthDate=" + birthDate +
                ", departmentId=" + departmentId +
                '}';
    }
}
