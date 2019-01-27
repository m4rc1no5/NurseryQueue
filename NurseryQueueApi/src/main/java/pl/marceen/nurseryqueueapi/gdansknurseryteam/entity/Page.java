package pl.marceen.nurseryqueueapi.gdansknurseryteam.entity;

/**
 * @author Marcin Zaremba
 */
public enum Page {
    LOGIN("konto/login"),
    DICTIONARY("slowniki?slowniki=%5B%22zlobek%22%5D"),
    ORDER("zgloszenia/");

    private static final String HOST = "https://kolejka.gzz.hekko24.pl/api/v1.0/";

    private String endpoint;

    Page(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getUrl() {
        return HOST + endpoint;
    }
}
