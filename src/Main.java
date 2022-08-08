
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {


        FoodRandomizer database = new FoodRandomizer();
        System.out.println(">>>>>>>>>>>>>>> Welcome To Food Randomizer <<<<<<<<<<<<<<<");

        Scanner scan = new Scanner(System.in);

        System.out.println("Press A to add food  \n - V to view today's meal \n - Q to exit: \n");
        String choice = scan.next().trim().toUpperCase();


        while (!choice.equals("Q") ){

            if(choice.equals("A")){
                System.out.println("Enter the type of food you wish to add: \n - S for stew \n - M for main food \n");


                String typeOfFood = scan.next().trim().toUpperCase();

                if (typeOfFood.equals("S")){
                    System.out.println("Enter the stew name: \n");
                    String food = scan.next();


                    database.addData(food, "Stew");

                } else if (typeOfFood.equals("M")) {
                    System.out.println("Enter the main dish name: \n");
                    String food = scan.next();
                    database.addData(food, "Main Dish");


                }



            } else if (choice.equals("V")) {
//

                System.out.println( database.getFoodChoice());
            }
            System.out.println("Press A to add food  \n - V to view today's meal \n - Q to exit: \n");
           choice = scan.next().trim().toUpperCase();
        }
        scan.close();

        }


}