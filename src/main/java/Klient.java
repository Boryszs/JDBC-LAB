public class Klient {
    private int idKlient;
    private String login;
    private String haslo;
    private int idOsoby;

    public Klient() {
    }

    public Klient(int idKlient, String login, String haslo, int idOsoby) {
        this.idKlient = idKlient;
        this.login = login;
        this.haslo = haslo;
        this.idOsoby = idOsoby;
    }

    public int getIdKlient() {
        return idKlient;
    }

    public String getLogin() {
        return login;
    }

    public String getHaslo() {
        return haslo;
    }

    public int getIdOsoby() {
        return idOsoby;
    }

    public void setIdKlient(int idKlient) {
        this.idKlient = idKlient;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public void setIdOsoby(int idOsoby) {
        this.idOsoby = idOsoby;
    }

    @Override
    public String toString() {
        return "Klient{" +
                "idKlient=" + idKlient +
                ", login='" + login + '\'' +
                ", haslo='" + haslo + '\'' +
                ", idOsoby=" + idOsoby +
                '}';
    }

}
