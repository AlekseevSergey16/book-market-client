package com.salekseev.booksmarketclient.model;

public class Author {

    private Long id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String information;

    public Author() {
    }

    public Author(Long id, String firstName, String lastName,
                  String middleName, String information) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.information = information;
    }

    public Author(String lastName) {
        this.lastName = lastName;
    }

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

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public static AuthorBuilder builder() {
        return new AuthorBuilder();
    }

    public static final class AuthorBuilder {
        private Long id;
        private String firstName;
        private String lastName;
        private String middleName;
        private String information;

        private AuthorBuilder() {
        }

        public AuthorBuilder id(Long id) {
            this.id = id;
            return this;
        }

        public AuthorBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public AuthorBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public AuthorBuilder middleName(String middleName) {
            this.middleName = middleName;
            return this;
        }

        public AuthorBuilder information(String information) {
            this.information = information;
            return this;
        }

        public Author build() {
            Author author = new Author(lastName);
            author.setId(id);
            author.setFirstName(firstName);
            author.setMiddleName(middleName);
            author.setInformation(information);
            return author;
        }
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + (middleName != null ? " " + middleName : "");
    }
}
