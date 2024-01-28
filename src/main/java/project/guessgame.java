package project;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class guessgame {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        int chances = 3;
        int[] system_generated_numbers = new int[chances];
        int[] user_entered_numbers = new int[chances];

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter id");
        int id = sc.nextInt();

        System.out.println("Enter name");
        String name = sc.next();

        Timestamp startdate = new Timestamp(System.currentTimeMillis());
        int score = 0;

        System.out.println("Enter number between 0-9");

        for (int i = 0; i < 3; i++) {
            Random r = new Random();
            int gen_num = r.nextInt(10);

            System.out.println("Guess the Number");
            int guess_num = sc.nextInt();

            system_generated_numbers[i] = gen_num;
            user_entered_numbers[i] = guess_num;

            if (gen_num == guess_num) {
                score += 10;
            }
        }

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/school", "root", "Soumya@123");
        PreparedStatement ps = con.prepareStatement("insert into guessgame(id,name,system_generated_numbers, user_entered_numbers,score,startdate) values(?,?,?,?,?,?)");

        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setString(3, Arrays.toString(system_generated_numbers));
        ps.setString(4, Arrays.toString(user_entered_numbers));
        ps.setInt(5, score);
        ps.setTimestamp(6, startdate);

        ps.execute();
        System.out.println("Values Inserted");

        
        ps.close();
        con.close();
        sc.close();
    }
}