package edu.training.web.bean;

import java.io.Serializable;
import java.util.Objects;

public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;
    private String date;
    private String title;
    private String brief;
    private String category;
    private String image;
    private String authorName;
    private String video;

    public News() {
    }

    public News(long id, String date, String title, String brief, String category, String image, String authorName, String video) {
        this.id = id;
        this.date = date;
        this.title = title;
        this.brief = brief;
        this.category = category;
        this.image = image;
        this.authorName = authorName;
        this.video = video;
    }

    public long getIdNews() {
        return id;
    }

    public void setIdNews(long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorName, brief, category, date, id, image, title, video);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        News news = (News) obj;
        return id == news.id &&
                Objects.equals(date, news.date) &&
                Objects.equals(title, news.title) &&
                Objects.equals(brief, news.brief) &&
                Objects.equals(category, news.category) &&
                Objects.equals(image, news.image) &&
                Objects.equals(authorName, news.authorName) &&
                Objects.equals(video, news.video);
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", title='" + title + '\'' +
                ", brief='" + brief + '\'' +
                ", category='" + category + '\'' +
                ", image='" + image + '\'' +
                ", authorName='" + authorName + '\'' +
                ", video='" + video + '\'' +
                '}';
    }
}