package a3.m1mo.mobjav.a816.myapplication.model;

/**
 * Created by digitalhouse on 20/10/16.
 */

public class Pelicula {

    private String title;
    private String overview;
    private String poster_path;
    private String release_date;
    private String original_title;
    private String original_language;
    private String backdrop_path;
    private Integer id;
    private Integer vote_count;
    private Double popularity;
    private Double vote_average;
    private Boolean adult;
    private Boolean video;
    private Boolean video_favorito;
    private String urlTrailer;
    private Integer watchLater;
    private Integer favorite;

    public Boolean getVideo_favorito() {
        return video_favorito;
    }

    public void setVideo_favorito(Boolean video_favorito) {
        this.video_favorito = video_favorito;
    }

    public Integer getWatchLater() {
        return watchLater;
    }

    public void setWatchLater(Integer watchLater) {
        this.watchLater = watchLater;
    }

    public Integer getFavorite() {
        return favorite;
    }

    public void setFavorite(Integer favorite) {
        this.favorite = favorite;
    }

    public Boolean getVideoFavorito() {
        return video_favorito;
    }

    public void setVideoFavorito(Boolean videoFavorito) {
        this.video_favorito = videoFavorito;
    }

    public String getUrlTrailer() {
        return urlTrailer;
    }

    public void setUrlTrailer(String urlTrailer) {
        this.urlTrailer = urlTrailer;
    }

    public Pelicula(String title, String overview, String poster_path, String release_date, String original_title, String original_language, String backdrop_path, Integer id, Integer vote_count, Double popularity, Double vote_average, Boolean adult, Boolean video) {
        this.title = title;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.original_title = original_title;
        this.original_language = original_language;
        this.backdrop_path = backdrop_path;
        this.id = id;
        this.vote_count = vote_count;
        this.popularity = popularity;
        this.vote_average = vote_average;
        this.adult = adult;
        this.video = video;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }


    public Pelicula() {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Boolean getAdult() {
        return adult;
    }

    public void setAdult(Boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }



    public Integer getVote_count() {
        return vote_count;
    }

    public void setVote_count(Integer vote_count) {
        this.vote_count = vote_count;
    }

    public Boolean getVideo() {
        return video;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public Double getVote_average() {
        return vote_average;
    }

    public void setVote_average(Double vote_average) {
        this.vote_average = vote_average;
    }

    public class TrailerPelicula {

        private String key;

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public TrailerPelicula(String key) {
            this.key = key;
        }
    }
}
