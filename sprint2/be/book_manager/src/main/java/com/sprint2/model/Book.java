package com.sprint2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

@Entity
@JsonIgnoreProperties({"cartDetailList"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String bookName;
    private String bookImage;
    private Double bookPrice;
    private String bookTranslator;
    private String publishingCompany;
    private Long bookNumberOfPages;
    private Long discount;
    @Column(columnDefinition = "DATE")
    private String bookReleaseDate;
    @Column(columnDefinition = "TEXT")
    private String bookDescription;
    private String postBy;
    private String newChapter;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "authorId")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "categoryId")
    private Category category;

    @OneToMany(mappedBy = "book")
    private List<CartDetail> cartDetailList;

    public Book() {
    }

    public String getNewChapter() {
        return newChapter;
    }

    public void setNewChapter(String newChapter) {
        this.newChapter = newChapter;
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

    public List<CartDetail> getCartDetailList() {
        return cartDetailList;
    }

    public void setCartDetailList(List<CartDetail> cartDetailList) {
        this.cartDetailList = cartDetailList;
    }

    public String getPostBy() {
        return postBy;
    }

    public void setPostBy(String postBy) {
        this.postBy = postBy;
    }

    public Long getDiscount() {
        return discount;
    }

    public void setDiscount(Long discount) {
        this.discount = discount;
    }
}
