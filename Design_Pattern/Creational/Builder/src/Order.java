public class Order {

    private final int id;
    private final String customerName;
    private final double amount;

    private final String address;
    private final String couponCode;
    private final String paymentMethod;

    private Order(Builder builder) {
        this.id = builder.id;
        this.customerName = builder.customerName;
        this.amount = builder.amount;
        this.address = builder.address;
        this.couponCode = builder.couponCode;
        this.paymentMethod = builder.paymentMethod;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customerName='" + customerName + '\'' +
                ", amount=" + amount +
                ", address='" + address + '\'' +
                ", couponCode='" + couponCode + '\'' +
                ", paymentMethod='" + paymentMethod + '\'' +
                '}';
    }

    public static class Builder {

        private int id;
        private String customerName;
        private double amount;

        private String address = "Not provided";
        private String couponCode = "No coupon";
        private String paymentMethod = "COD";

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setCustomerName(String customerName) {
            this.customerName = customerName;
            return this;
        }

        public Builder setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setCouponCode(String couponCode) {
            this.couponCode = couponCode;
            return this;
        }

        public Builder setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}