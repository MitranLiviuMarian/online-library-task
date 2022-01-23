package com.schwarz.onlinelibrary.model;

import com.schwarz.onlinelibrary.entities.BookEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class CustomerDto {
    private Long id;
    @NotBlank
    private String firstName;
    private String lastName;
    @Email(message = "Invalid email format.")
    private String email;
    @NotBlank
    private String password;
    @NotNull
    private Double deposit;
    private Set<BookEntity> purchasedBooks = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        CustomerDto that = (CustomerDto) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(email, that.email) && Objects.equals(password, that.password) && Objects.equals(deposit, that.deposit) && Objects.equals(purchasedBooks, that.purchasedBooks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, email, password, deposit, purchasedBooks);
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", budget=" + deposit +
                ", purchasedBooks=" + purchasedBooks +
                '}';
    }
}
