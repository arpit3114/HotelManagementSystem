package Hotel;

import java.sql.*;
import java.util.Scanner;

public class HotelManagementSystem {
        private static final String url = "jdbc:mysql://localhost:3306/hotel_db";
        private static final String username = "root";
        private static final String password = "Admin@2004";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        try{
            Connection connection = DriverManager.getConnection(url, username, password);

            while(true){
                System.out.println();
                System.out.println("HOTEL MANAGEMENT SYSTEM");
                Scanner sc = new Scanner(System.in);
                System.out.println("1. Reserve a Room");
                System.out.println("2. View Reservations");
                System.out.println("3. Get Room Number");
                System.out.println("4. Update Reservations");
                System.out.println("5. Delete Reservations");
                System.out.println("0. Exit");
                System.out.println("Choose an Option: ");
                int choice = sc.nextInt();
                switch(choice){
                    case 1:
                        reserveRoom(connection, sc);
                        break;
                    case 2:
                        viewReservations(connection);
                        break;
                    case 3:
                        getRoomNumber(connection, sc);
                        break;
                    case 4:
                        UpdateReservation(connection, sc);
                        break;
                    case 5:
                        deleteReservation(connection, sc);
                        break;
                    case 0:
                        exit();
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Try again");
                }
            }
        }catch (SQLException e) {
            System.out.println(e.getMessage());
        }catch(InterruptedException e){
           throw new RuntimeException(e);
        }
    }

    private static void reserveRoom(Connection connection, Scanner sc) {
        try{
            System.out.println("Enter Guest Name: ");
            String guestName = sc.next();
            sc.nextLine();
            System.out.println("Enter room no. ");
            int roomNo = sc.nextInt();
            System.out.println("Enter contact no. ");
            String contactNo = sc.next();

            String sql = "insert into reservations (guest_name, room_no, contact_no)" +
                    "values ('" +guestName + "'," + roomNo + ",'" + contactNo +"')";

            try(Statement statement = connection.createStatement()){
                int affectRows = statement.executeUpdate(sql);
                if(affectRows > 0) System.out.println(" Reservation Successful. ");
                else System.out.println(" Reservation Failed. ");

            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void viewReservations(Connection connection) throws SQLException{
        String sql = "select * from reservations";

        try(Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql)){

            System.out.println("Current Reservations: ");
            System.out.println("+----------------+-----------------+---------------+----------------------+-----------------------+");
            System.out.println("| Reservation_ID |    Guest_name   |   Room_no     |       Contact_no     |   Reservation_date    |");
            System.out.println("+----------------+-----------------+---------------+----------------------+-----------------------+");

            while(rs.next()){
                int reservation_id = rs.getInt("reservation_id");
                String guestName = rs.getString("guest_name");
                int roomNo = rs.getInt("room_no");
                String contact = rs.getString("contact_no");
                String date = rs.getTimestamp("reservation_date").toString();

                System.out.printf("| %-14d | %-15s | %-13d | %-20s | %-19s |\n",
                        reservation_id, guestName, roomNo, contact, date);
            }
            System.out.println("+----------------+-----------------+---------------+----------------------+-----------------------+");;
        }
    }

    public static void getRoomNumber(Connection connection, Scanner sc){
        try{
            System.out.println("Enter Reservation ID: ");
            int reservation_id = sc.nextInt();
            System.out.println("Enter guest Name: ");
            String name = sc.next();

            String sql = "select room_no from reservations where reservation_id = "+reservation_id + "AND guest_name = '"+ name + "'";

            try(Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql)){

                if(rs.next()){
                    int roomNo = rs.getInt("room_no");
                    System.out.println("Room Number for Reservation ID " + reservation_id + "and Guest Name "+ name + "is: " +roomNo);
                }else System.out.println("Reservation not found for given details");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void UpdateReservation(Connection connection, Scanner sc){
        try{
            System.out.println("Enter Reservation ID to Update: ");
            int id = sc.nextInt();
            sc.nextLine();

            if(!reservationExists(connection, id)){
                System.out.println("Reservation Not Found for given ID");
                return;
            }

            System.out.println("Enter Guest name: ");
            String name = sc.next();
            System.out.println("Enter new room Number");
            int room = sc.nextInt();
            System.out.println("Enter new Phone Number: ");
            String no = sc.next();

            String sql = "UPDATE reservations set guest_name ='"+ name +"', room_no ="+room + ",contact_no ='"+no +"' where reservation_id = "+ id;

            try(Statement statement = connection.createStatement()){
                int affectRows = statement.executeUpdate(sql);
                if(affectRows > 0) System.out.println(" Updation Successful. ");
                else System.out.println(" Updation Failed. ");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void deleteReservation(Connection connection, Scanner sc){
        try{
            System.out.println("Enter Reservation ID to Delete: ");
            int reservation_id = sc.nextInt();

            if(!reservationExists(connection, reservation_id)){
                System.out.println("Reservation not found for given ID");
                return;
            }

            String sql = "delete from reservations where reservation_id =" +reservation_id;
            try(Statement statement = connection.createStatement()){
                int affectRows = statement.executeUpdate(sql);
                if(affectRows > 0) System.out.println(" Deletion Successful. ");
                else System.out.println(" Deletion Failed. ");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    private static boolean reservationExists(Connection connection, int id) {
        try{
            String sql = "select reservation_id from reservations where reservation_id ="+ id;

            try(Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql)){
                return rs.next();
            }
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }

    private static void exit() throws InterruptedException{
        System.out.print(" Exiting System ");
        int i =5;
        while(i!= 0){
            System.out.print(".");
            Thread.sleep(450);
            i--;
        }
        System.out.println();
        System.out.println(" ThankYou for Using Hotel Management System!!! ");
    }
}


