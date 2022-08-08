package com.sprint2.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CartDetail {
    @Id
    private Long cartDetailId;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "cartId")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "bookId")
    private Book book;

    public CartDetail() {
    }

    public Long getCartDetailId() {
        return cartDetailId;
    }

    public void setCartDetailId(Long cartDetailId) {
        this.cartDetailId = cartDetailId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

}
