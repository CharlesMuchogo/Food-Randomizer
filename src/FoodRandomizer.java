import java.sql.*;
import java.util.ArrayList;

public class  FoodRandomizer {
    final String databaseUrl = "jdbc:mysql://localhost:3306/randomizer";
    final String username = "root";
    final  String password = "";

    ArrayList<String> maindish = new ArrayList<>();
    ArrayList<String> stew = new ArrayList<>() ;


    public  String retrieveData(String type){
        try {
            Connection connection = DriverManager.getConnection(databaseUrl, username,password);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM food  WHERE type = ?");
            statement.setString(1, type);

            ResultSet rs = statement.executeQuery();
            rs.next();

        while (rs.next()){
            if(type == "Main Dish"){
                maindish.add(rs.getString("food"));

            }else {
                stew.add(rs.getString("food"));

            }
        }

            rs.close();
        } catch (SQLException e){
            System.out.println("Caught error: " + e.toString());
        }
        String randomFood = "";
        for (int i = 0; i < maindish.size(); i++)
        {

            int index = (int)(Math.random() * maindish.size());
                if (type.equals("Main Dish") ){
                    randomFood = maindish.get(index);
                }else {
                    randomFood = stew.get(index);
                }

        }
        return randomFood;
    }

    public String getFoodChoice(){


        retrieveData("Main Dish");
        retrieveData("Stew");

        maindish.clear();
        stew.clear();

//        System.out.println();
       return  " Today you will be having " + retrieveData("Main Dish") + " and " +  retrieveData("Stew") + " \n";
    }

    public void addData(String name, String type){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(databaseUrl, username,password);

            PreparedStatement statement = connection.prepareStatement("Insert Into  food(food, type )values(?,?)");
            statement.setString(1, name);
            statement.setString(2, type);

            statement.executeUpdate();
            System.out.println("You have added " + name + " to " + type);

        } catch (SQLException | ClassNotFoundException e){
            System.out.println("Caught error: " + e.toString());
        }
    }
}
