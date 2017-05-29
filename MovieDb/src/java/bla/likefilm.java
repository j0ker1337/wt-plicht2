
package bla;

public class likefilm {
    private int film;
    private int user;

    public int getFilm() {
        return film;
    }

    public void setFilm(int film) {
        this.film = film;
    }

    public int getUser() {
        return user;
    }

    @Override
    public String toString() {
        return "likefilm{" + "film=" + film + ", user=" + user + '}';
    }

    public void setUser(int user) {
        this.user = user;
    }
}
