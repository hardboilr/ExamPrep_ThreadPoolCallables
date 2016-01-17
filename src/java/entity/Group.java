package entity;

public class Group {
    String authors;
    String cla;
    String group;        

    public Group(String authors, String cla, String group) {
        this.authors = authors;
        this.cla = cla;
        this.group = group;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getCla() {
        return cla;
    }

    public void setCla(String cla) {
        this.cla = cla;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
