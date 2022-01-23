package com.schwarz.onlinelibrary.entities;

import java.util.Objects;

public class BookEntity extends Entity {
    private String title;
    private Double price;
    private String author;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        BookEntity that = (BookEntity) o;
        return Objects.equals(title, that.title) && Objects.equals(price, that.price) && Objects.equals(author, that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), title, price, author);
    }

    @Override
    public String toString() {
        return "BookEntity{" +
                "title='" + title + '\'' +
                ", price=" + price +
                ", author='" + author + '\'' +
                "} " + super.toString();
    }
}
