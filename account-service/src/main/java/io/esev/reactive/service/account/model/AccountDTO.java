package io.esev.reactive.service.account.model;

public class AccountDTO {

    private String id;
    private String number;
    private String customerId;
    private long amount;

    public AccountDTO() {
        super();
    }

    public AccountDTO(String id, String number, String customerId, long amount) {
        this.setId(id);
        this.setNumber(number);
        this.setCustomerId(customerId);
        this.setAmount(amount);
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("AccountDTO{");
        sb.append("id='").append(id).append('\'');
        sb.append(", number='").append(number).append('\'');
        sb.append(", customerId='").append(customerId).append('\'');
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
