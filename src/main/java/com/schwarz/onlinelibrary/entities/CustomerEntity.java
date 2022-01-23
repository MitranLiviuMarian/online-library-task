package com.schwarz.onlinelibrary.entities;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CustomerEntity extends Entity {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Double deposit;
    private Set<BookEntity> purchasedBooks = new HashSet<>();

    public void buyBook(BookEntity bookEntity) {
        deposit -= bookEntity.getPrice();
        purchasedBooks.add(bookEntity);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Set<BookEntity> getPurchasedBooks() {
        return purchasedBooks;
    }

    public void setPurchasedBooks(Set<BookEntity> purchasedBooks) {
        this.purchasedBooks = purchasedBooks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        CustomerEntity that = (CustomerEntity) o;
        return Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(deposit, that.deposit) && Objects.equals(purchasedBooks, that.purchasedBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), firstName, lastName, email, password, deposit, purchasedBooks);
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", budget=" + deposit +
                ", purchasedBooks=" + purchasedBooks +
                "} " + super.toString();
    }

    public void removeBook(BookEntity removedBook) {
        boolean removed = purchasedBooks.removeIf(book -> Objects.equals(book, removedBook));
        if (removed) {
            deposit += removedBook.getPrice();
        }
    }

    public void updateBook(BookEntity newBook) {
        boolean removed = purchasedBooks.removeIf(book ->  Objects.equals(book.getId(), newBook.getId()));
        purchasedBooks.add(newBook);
    }
}
