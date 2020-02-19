package io.esev.reactive.service.account.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class AccountEntity {

    @Id
    private String id;
    private String number;
    private String customerId;
    private long amount;
    private Date created;

    public AccountEntity() {
        super();
    }

    public AccountEntity(String number, String customerId, long amount) {
        super();
        this.setNumber(number);
        this.setCustomerId(customerId);
        this.setAmount(amount);
        this.setCreated(new Date());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountEntity{");
        sb.append("id='").append(id).append('\'');
        sb.append(", number='").append(number).append('\'');
        sb.append(", customerId='").append(customerId).append('\'');
        sb.append(", amount=").append(amount);
        sb.append(", created=").append(created);
        sb.append('}');
        return sb.toString();
    }
}
