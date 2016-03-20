package it.bamboolab.model;

/**
 * Created by N4N on 20/04/2015.
 */
public class Reference {

    private int id;
    private String idSecurity;
    private String idRole;
    private String idUser;
    private String description;

    public String getIdRole() {
        return idRole;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setIdRole(String idRole) {
        this.idRole = idRole;
    }

    public String getIdUser() {

        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdSecurity() {
        return idSecurity;
    }

    public void setIdSecurity(String idSecurity) {
        this.idSecurity = idSecurity;
    }
}