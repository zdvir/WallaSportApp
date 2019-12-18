package com.dvirz.rssfeedapp.Model;

public class Post
{
    private String title;
    private String pubDate;
    private String image;
    private String link;

    public Post(String title, String pubDate, String link, String image)
    {
        this.title = title;
        this.image = image;
        this.link = link;

        int index = pubDate.indexOf('+');
        this.pubDate = pubDate.substring(0,index-1);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPubDate()
    {
        return pubDate;
    }

    public void setPubDate(String pubDate)
    {
        this.pubDate = pubDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Post{" +
                "title='" + title + '\'' +
                ", pubDate='" + pubDate + '\'' +
                ", image='" + image + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
