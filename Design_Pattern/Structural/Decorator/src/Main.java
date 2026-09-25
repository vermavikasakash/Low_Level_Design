class Main {
    static void main(String[] args) {

//        Coffee coffee = new SimpleCoffee();
//        System.out.println(coffee.getDescription() + " price is" + coffee.getCost());
//        System.out.println(coffee.getCost());
        Coffee coffee = new SimpleCoffee();
        coffee = new MilkDecorator(coffee);
        coffee = new SugarDecorator(coffee);


        System.out.println(coffee.getDescription());
        System.out.println(coffee.getCost());
    }
}

