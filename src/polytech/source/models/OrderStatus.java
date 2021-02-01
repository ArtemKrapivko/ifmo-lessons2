package polytech.source.models;

public enum OrderStatus {
    READY("готовится"),                   // "готовится"
    DELIVERED("отгружен"),               //"отгружен"
    CANCEL("отменён");                   // "отменён"

    private String orderStatus;

    OrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String toString() {
        return orderStatus;
    }

    public static OrderStatus getStatusFromString(String s){
        switch (s){
            case "готовится": return READY;
            case "отгружен": return DELIVERED;
            case "отменён": return CANCEL;
            default: return null;
        }
    }
}