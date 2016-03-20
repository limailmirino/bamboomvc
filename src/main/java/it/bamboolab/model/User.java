package it.bamboolab.model;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String dn = "";
    private String role = "";
    private String company = "";
    private String sapCode = "";
    private String referencePerson;
    private String username = "";
    private String password = "";
    private String name = "";
    private String surname = "";
    private String cn;
    private String email = "";
    private List<Group> groups;
    private String goLiveDate = "";
    private String revisionDate = "";
    private String creationDate = "";
    private String selectedGroups = "";
    private int idRefPerson;    // N4N
    private String groupIdJoined = "";    // N4N

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public User() {
        this.groups = new ArrayList<Group>();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSapCode() {
        return sapCode;
    }

    public void setSapCode(String sapCode) {
        this.sapCode = sapCode;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getReferencePerson() {
        return referencePerson;
    }

    public void setReferencePerson(String referencePerson) {
        this.referencePerson = referencePerson;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        //String cleanPass = password.replace("{NONE}", "");
        //return cleanPass;
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    /**
     * @param group the group to set
     */
    public void addGroup(Group group) {
        this.groups.add(group);

    }

    public boolean hasGroup(String id) {
        String selGroups = this.getSelectedGroups();
        String[] groups = selGroups.split(";");
        for (String group : groups) {
            if (group.equals(id)) {
                return true;
            }
        }

        return false;
    }

    public String[] getGroups() {
        String selGroups = this.getSelectedGroups();
        String[] groups = selGroups.split(";");

        return groups;
    }

    public String getGoLiveDate() {
        return goLiveDate;
    }

    public void setGoLiveDate(String goLiveDate) {
        this.goLiveDate = goLiveDate;
    }

    public String getDN() {
        return "uid=" + this.getUsername() + ",ou=" + this.getCompany() + ",ou=Customers,dc=cellularline,dc=com";
    }

    /**
     * @return the revisionDate
     */
    public String getRevisionDate() {
        return revisionDate;
    }

    /**
     * @param revisionDate the revisionDate to set
     */
    public void setRevisionDate(String revisionDate) {
        this.revisionDate = revisionDate;
    }

    /**
     * @return the selectedGroups
     */
    public String getSelectedGroups() {
        return selectedGroups;
    }

    /**
     * @param selectedGroups the selectedGroups to set
     */
    public void setSelectedGroups(String selectedGroups) {
        this.selectedGroups = selectedGroups;
    }

    /**
     * @return
     * @author N4N
     */
    public int getIdRefPerson() {
        return this.idRefPerson;
    }

    /**
     * @param idRefPerson
     * @author N4N
     */
    public void setIdRefPerson(int idRefPerson) {
        this.idRefPerson = idRefPerson;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    /**
     * @return
     * @author N4N
     */
    public String getGroupIdJoined() {
        return groupIdJoined;
    }


    public List<Group> getGroupList() {
        return this.groups;
    }

    /**
     * Dato l'array dei groupId, ritorna nel getter una stringa concatenata dei groupId (join) separata da ",".
     *
     * @return
     * @author N4N
     */
    public void setGroupIdJoined(String[] groups) {
        String j = "";

        for (String g : groups) {
            j += g + ",";
        }

        if (j.length() > 2) j = j.substring(0, j.length() - 2);

        this.groupIdJoined = j;
    }

    /**
     * Ritorna nel getter la stringa groupsId.
     *
     * @param groupsId
     * @author N4N
     */
    public void setGroupIdJoined(String groupsId) {
        this.groupIdJoined = groupsId;
    }

    public String getPrefix() {

        if (this.role.equals("RETAILER")) {
            return "r";
        }

        if (this.role.equals("DIST_EST")) {

            if (this.hasGroup("Distributori Esteri GOCO")) {
                return "c";
            } else if (this.hasGroup("Distributori Esteri SIFU")) {
                return "f";
            } else if (this.hasGroup("Distributori Esteri SIPA")) {
                return "s";
            } else if (this.hasGroup("Distributori Esteri GOPA")) {
                return "p";
            } else if (this.hasGroup("Distributori Esteri BASIC")) {
                return "b";
            }
        }

        if (this.getRole().equals("DIST_ITA")) {
            return "c";
        }

        return null;
    }

    public String getCn() {
        return cn;
    }

    public void setCn(String cn) {
        this.cn = cn;
    }

    public String getDn() {
        return dn;
    }

    public void setDn(String dn) {
        this.dn = dn;
    }
}
