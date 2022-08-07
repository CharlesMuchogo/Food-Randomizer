import java.sql.*;
import java.util.ArrayList;

public class  FoodRandomizer {
    final String databaseUrl = "jdbc:mysql://localhost:3306/randomizer";
    final String username = "root";
    final  String password = "";

    ArrayList<String> maindish = new ArrayList<>();
    ArrayList<String> stew = new ArrayList<>() ;

    public  void retrieveData(String type){
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
    }

    public void getFoodChoice(){
        String randomStew = "";
        String randomDish = "";

        retrieveData("Main Dish");
        retrieveData("Stew");
        for (int i = 0; i < maindish.size(); i++)
        {

            int index = (int)(Math.random() * maindish.size());

        randomDish = maindish.get(index);
        }
        for (int i = 0; i < stew.size(); i++)
        {

            int index = (int)(Math.random() * stew.size());

            randomStew = stew.get(index);
        }

        maindish.clear();
        stew.clear();

        System.out.println(" Today you will be having " + randomDish + " and " + randomStew + " \n");
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

        } catch (SQLException e){
            System.out.println("Caught error: " + e.toString());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
