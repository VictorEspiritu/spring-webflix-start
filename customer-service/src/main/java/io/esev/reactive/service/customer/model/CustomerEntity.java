package io.esev.reactive.service.customer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CustomerEntity {

    @Id
    private String id;
    private String firstName;
    private String lastName;
    private String documentNumber;

    public CustomerEntity() {
        super();
    }

    public CustomerEntity(String firstName, String lastName, String documentNumber) {
        super();
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setDocumentNumber(documentNumber);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomerEntity{");
        sb.append("id='").append(id).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", documentNumber='").append(documentNumber).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
