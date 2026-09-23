public class Main {

    public static void main(String[] args) {



        UserService service = new UserService();

        service.createUser(1, "Vikas", "vikas@gmail.com");

        service.createUser(2, "Rahul", "rahul@gmail.com");
        System.out.println(service.getUser(1));


        service.updateUser(1, "Vikas Verma", "vikas.verma@gmail.com");

        System.out.println(service.getUser(1));

        service.deleteUser(2);

    }
}