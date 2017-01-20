package a3.m1mo.mobjav.a816.myapplication.model;

/**
 * Created by Diagnostifo on 28/10/2016.
 */

public class Serie {
    private String name;
    private String original_name;
    private String poster_path;
    private String overview;
    private String backdrop_path;
    private String original_language;
    private Double popularity;
    private Integer id;
    private Integer vote_count;
    private Double vote_average;
    private Boolean serie_favorite;
    private String url_trailer;
    private Integer watch_later;
    private Integer favorite;

    public Integer getFavorite() {
        return favorite;
    }

    public void setFavorite(Integer favorite) {
        this.favorite = favorite;
    }

    public Integer getWatch_later() {
        return watch_later;
    }

    public void setWatch_later(Integer watch_later) {
        this.watch_later = watch_later;
    }

    public String getUrl_trailer() {
        return url_trailer;
    }

    public void setUrl_trailer(String url_trailer) {
        this.url_trailer = url_trailer;
    }

    public Boolean getSerie_favorite() {
        return serie_favorite;
    }

    public void setSerie_favorite(Boolean serie_favorite) {
        this.serie_favorite = serie_favorite;
    }

    public Serie(String name, String original_name, String poster_path, String overview, String backdrop_path, String original_language, Double popularity, Integer id, Integer vote_count, Double vote_average) {
        this.name = name;
        this.original_name = original_name;
        this.poster_path = poster_path;
        this.overview = overview;
        this.backdrop_path = backdrop_path;
        this.original_language = original_language;
        this.popularity = popularity;
        this.id = id;
        this.vote_count = vote_count;
        this.vote_average = vote_average;
    }

    public Serie() {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public class TrailerSerie {

        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public TrailerSerie(String key) {
            this.key = key;
        }
    }
}
