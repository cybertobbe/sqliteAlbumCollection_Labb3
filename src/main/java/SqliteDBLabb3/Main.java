package SqliteDBLabb3;


import java.sql.*;
import java.util.Scanner;

public class Main {

      private static Scanner scanner = new Scanner(System.in);

      private static Connection connect() {
            // SQLite connection string
            String url = "jdbc:sqlite:C:\\Users\\cyber\\sqlite\\albumCollectionJDBC\\Labb3.db";
            Connection conn = null;
            try {
                  conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                  System.out.println(e.getMessage());
            }
            return conn;
      }

      private static void printActions() {
            System.out.println("\nVälj:\n");
            System.out.println("0  - Stäng av\n" +
                    "1  - Visa alla böcker\n" +
                    "2  - Lägga till en ny bok\n" +
                    "3  - Uppdatera en bok\n" +
                    "4  - Ta bort en bok\n" +
                    "5  - Visa en lista över alla val.");
      }


      private static void deleteBook(){
            System.out.println("Skriv in id:t på boken som ska tas bort: ");
            int inputId = scanner.nextInt();
            delete(inputId);
            scanner.nextLine();
      }

      private static void selectAll(){
            String sql = "SELECT * FROM artist";

            try {
                  Connection conn = connect();
                  Statement stmt  = conn.createStatement();
                  ResultSet rs    = stmt.executeQuery(sql);

                  // loop through the result set
                  while (rs.next()) {
                        System.out.println(rs.getInt("artistId") +  "\t\t" +
                                rs.getString("artistName") + "\t\t" +
                                rs.getString("artistFounded") + "\t\t" +
                                rs.getString("artistGenreId"));
                  }
            } catch (SQLException e) {
                  System.out.println(e.getMessage());
            }
      }

      private static void insert() {

            //ArtistGenreID convert from text input  to  int
            //int artistGenre = 0;

            System.out.println("Ny artist: ");
            String artist = scanner.nextLine();
            System.out.println("Artist grundades: ");
            int artistFounded = scanner.nextInt();
            System.out.println("Genre: ");
            int artistGenreId = scanner.nextInt();
            scanner.nextLine();



            String sql = "INSERT INTO artist(artistName, artistFounded, artistGenreId) VALUES(?,?,?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                  // set the corresponding param
                  pstmt.setString(1, artist);
                  pstmt.setInt(2, artistFounded);
                  pstmt.setInt(3, artistGenreId);

                  // update
                  pstmt.executeUpdate();
                  System.out.println("Du har lagt till en ny artist");
            } catch (SQLException e) {
                  System.out.println(e.getMessage());
            }
      }

      private static void update() {
            System.out.println("Artist att uppdatera: ");
            String artistName = scanner.nextLine();
            System.out.println("Vilken genre: ");
            String artistGenre = scanner.nextLine();
            int artistGenreId = 0;//anrop till if metod

            artistGenreId = genreCheck(artistGenre, artistGenreId);

            String sql = "UPDATE artist SET artistGenreId = ? WHERE artistName = ?";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                  // set the corresponding param
                  pstmt.setInt(1, artistGenreId);
                  pstmt.setString(2, artistName);
                  // update
                  pstmt.executeUpdate();
                  System.out.println("Du har uppdaterat vald artist");
            } catch (SQLException e) {
                  System.out.println(e.getMessage());
            }
      }

      private static int genreCheck(String artistGenre, int artistGenreId) {
            if(artistGenre.equals("Pop")) {
                  artistGenreId = 1;
            }
            else if(artistGenre.equals("Rock")){
                  artistGenreId = 2;
            }
            return artistGenreId;
      }

      private static void delete(int id) {
            String sql = "DELETE FROM artist WHERE artistId = ?";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                  // set the corresponding param
                  pstmt.setInt(1, id);
                  // execute the delete statement
                  pstmt.executeUpdate();
                  System.out.println("Du har tagit bort artist");
            } catch (SQLException e) {
                  System.out.println(e.getMessage());
            }
      }

      public static void main(String[] args) {

            boolean quit = false;
            printActions();
            while(!quit) {
                  System.out.println("\nVälj (5 för att visa val):");
                  int action = scanner.nextInt();
                  scanner.nextLine();

                  switch (action) {
                        case 0:
                              System.out.println("\nStänger ner...");
                              quit = true;
                              break;

                        case 1:
                              selectAll();
                              break;

                        case 2:
                              insert();
                              break;

                        case 3:
                              update();
                              break;

                        case 4:
                              //delete(1);
                              deleteBook();
                              break;

                        case 5:
                              printActions();
                              break;
                  }
            }

      }

}
