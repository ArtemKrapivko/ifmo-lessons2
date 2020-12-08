package polytech.source.models;

public enum OrderStatus {
    READY("готовится"),                   // "готовится"
    DELIVERED("отгружен"),               //"отгружен"
    CANCEL("отменён");                   // "отменён"

    private String OrderStatus;

    OrderStatus(String OrderStatus) {
        this.OrderStatus = OrderStatus;
    }
}