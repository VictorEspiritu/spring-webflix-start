package io.esev.reactive.service.customer.model;

import java.util.ArrayList;
import java.util.List;

public class CustomerDTO {

    private String id;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private List<AccountDTO> accounts;

    public CustomerDTO() {
        super();
    }

    public CustomerDTO(String id, String firstName, String lastName, String documentNumber) {
        super();
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setDocumentNumber(documentNumber);
        this.accounts = new ArrayList<>();
    }

    public CustomerDTO(String documentNumber, List<AccountDTO> accounts) {
        super();
        this.setDocumentNumber(documentNumber);
        this.setAccounts(accounts);
    }

    public CustomerDTO(String id, String firstName, String lastName, String documentNumber, List<AccountDTO> accounts) {
        super();
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setDocumentNumber(documentNumber);
        this.setAccounts(accounts);
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

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDTO> accounts) {
        this.accounts = accounts;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CustomerDTO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", documentNumber='").append(documentNumber).append('\'');
        sb.append(", accounts=").append(accounts);
        sb.append('}');
        return sb.toString();
    }
}
