package net.woden.wdsit.model;

/**
 *
 * @author f.casallas
 */
public class ReportValidateLoadNameArchiveModel {

    private int numberCountry;
    private String abreviatureCountry;
    private String dateCreation;
    private String nameArchive;

    public int getNumberCountry() {
        return numberCountry;
    }

    public void setNumberCountry(int numberCountry) {
        this.numberCountry = numberCountry;
    }

    public String getAbreviatureCountry() {
        return abreviatureCountry;
    }

    public void setAbreviatureCountry(String abreviatureCountry) {
        this.abreviatureCountry = abreviatureCountry;
    }

    public String getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getNameArchive() {
        return nameArchive;
    }

    public void setNameArchive(String nameArchive) {
        this.nameArchive = nameArchive;
    }

}
