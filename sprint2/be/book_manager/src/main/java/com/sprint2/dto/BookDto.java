package com.sprint2.dto;

import com.sprint2.model.Author;
import com.sprint2.model.CartDetail;
import com.sprint2.model.Category;


public class BookDto {

    private Long bookId;
    private String bookName;
    private String bookImage;
    private Double bookPrice;
    private String bookTranslator;
    private String publishingCompany;
    private Long bookNumberOfPages;
    private String bookReleaseDate;
    private String bookDescription;
    private String postBy;
    private Author author;
    private Category category;
    private Long discount;
    private String newChapter;


    public String getNewChapter() {
        return newChapter;
    }

    public void setNewChapter(String newChapter) {
        this.newChapter = newChapter;
    }

    public BookDto() {
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookImage() {
        return bookImage;
    }

    public void setBookImage(String bookImage) {
        this.bookImage = bookImage;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }

    public String getBookTranslator() {
        return bookTranslator;
    }

    public void setBookTranslator(String bookTranslator) {
        this.bookTranslator = bookTranslator;
    }

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }

    public Long getBookNumberOfPages() {
        return bookNumberOfPages;
    }

    public void setBookNumberOfPages(Long bookNumberOfPages) {
        this.bookNumberOfPages = bookNumberOfPages;
    }

    public String getBookReleaseDate() {
        return bookReleaseDate;
    }

    public void setBookReleaseDate(String bookReleaseDate) {
        this.bookReleaseDate = bookReleaseDate;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getPostBy() {
        return postBy;
    }

    public void setPostBy(String postBy) {
        this.postBy = postBy;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "bookId=" + bookId +
                ", bookName='" + bookName + '\'' +
                ", bookImage='" + bookImage + '\'' +
                ", bookPrice=" + bookPrice +
                ", bookTranslator='" + bookTranslator + '\'' +
                ", publishingCompany='" + publishingCompany + '\'' +
                ", bookNumberOfPages=" + bookNumberOfPages +
                ", bookReleaseDate='" + bookReleaseDate + '\'' +
                ", bookDescription='" + bookDescription + '\'' +
                ", postBy='" + postBy + '\'' +
                ", author=" + author +
                ", category=" + category +
                ", discount=" + discount +
                ", newChapter='" + newChapter + '\'' +
                '}';
    }
}
