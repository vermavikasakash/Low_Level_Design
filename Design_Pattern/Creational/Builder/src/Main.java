public class Main {

  public static void main(String[] args) {

    Order order = new Order.Builder()
            .setId(1)
            .setCustomerName("Vikas")
            .setAmount(5000)
            .setAddress("Bangalore")
            .setCouponCode("SAVE20")
            .setPaymentMethod("UPI")
            .build();

    System.out.println(order);
  }
}