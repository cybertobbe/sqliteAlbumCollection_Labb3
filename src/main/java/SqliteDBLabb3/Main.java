package SqliteDBLabb3;


import java.sql.*;
import java.util.Scanner;

public class Main {

      private static final Scanner scanner = new Scanner(System.in);


      public static void main(String[] args) {

            boolean quit = false;
            showMenu();
            while(!quit) {
                  System.out.println("\nChoose:(7 to show menu)");
                  int menyChoice = scanner.nextInt();
                  scanner.nextLine();

                  switch (menyChoice) {
                        case 0 -> {
                              System.out.println("\nShutting down..");
                              quit = true;
                        }
                        case 1 -> selectAllArtists();
                        case 2 -> insertArtist();
                        case 3 -> updateArtist();
                        case 4 -> deleteArtist();
                        case 5 -> showAllGenres();
                        case 6 -> joinTables();
                        case 7 -> showMenu();
                  }
            }

      }


      private static Connection connect() {
            String url = "jdbc:sqlite:C:\\Users\\cyber\\sqlite\\albumCollectionJDBC\\Labb3.db";
            Connection conn = null;
            try {
                  conn = DriverManager.getConnection(url);
            } catch (SQLException e) {
                  System.out.println(e.getMessage());
            }
            return conn;
      }

      private static void showMenu() {
            System.out.println("\nChoose:\n");
            System.out.println("""
                    0  - Shutdown
                    1  - Show all artists
                    2  - Add new artist
                    3  - Update artist with new genre
                    4  - Remove artist
                    5  - Show all genres
                    6  - Show artist and genre(Join)
                    7  - Show menu""");

      }

      private static void deleteArtist(){
            System.out.println("Artist to delete: ");
            String artistName = scanner.nextLine();
            deleteArtist(artistName);

      }

      private static void selectAllArtists(){
            String sql = "SELECT * FROM artist";

            try {
                  Connection conn = connect();
                  Statement stmt  = conn.createStatement();
                  ResultSet rs    = stmt.executeQuery(sql);

                  System.out.println(String.format("%-10s %-20s %-15s %-15s", "artistId", "artistName", "artistFounded", "artistGenreId"))
                  ;
                  while (rs.next()) {
                        System.out.println(String.format("%-10d %-20s %-15s %-15s",
                                rs.getInt("artistId"),
                                rs.getString("artistName"),
                                rs.getString("artistFounded"),
                                rs.getString("artistGenreId")));
                  }

            } catch (SQLException e) {
                  System.out.println(e.getMessage());
            }
      }

      private static void insertArtist() {

            System.out.println("New artist: ");
            String artist = scanner.nextLine();
            System.out.println("Artist founded: ");
            int artistFounded = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Genre: ");
            String artistGenre = scanner.nextLine();


            int artistGenreId = 0;

            //Call method to convert genre to genreId
            artistGenreId = genreConversion(artistGenre, artistGenreId);



            String sql = "INSERT INTO artist(artistName, artistFounded, artistGenreId) VALUES(?,?,?)";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                  // set the corresponding param
                  pstmt.setString(1, artist);
                  pstmt.setInt(2, artistFounded);
                  pstmt.setInt(3, artistGenreId);

                  // update
                  pstmt.executeUpdate();
                  System.out.println("You have added a new artist " + artist);
            } catch (SQLException e) {
                  System.out.println(e.getMessage());
            }
      }

      private static void updateArtist() {
            System.out.println("Artist to update: ");
            String artistName = scanner.nextLine();
            System.out.println("What genre: ");
            String artistGenre = scanner.nextLine();
            int artistGenreId = 0;

            //Call method to convert genre to genreId
            artistGenreId = genreConversion(artistGenre, artistGenreId);


            String sql = "UPDATE artist SET artistGenreId = ? WHERE artistName = ?";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                  // set the corresponding param
                  pstmt.setInt(1, artistGenreId);
                  pstmt.setString(2, artistName);
                  // update
                  pstmt.executeUpdate();
                  System.out.println("You have updated artist " + artistName + " with genre " + artistGenre);
            } catch (SQLException e) {
                  System.out.println(e.getMessage());
            }
      }


      private static int genreConversion(String artistGenre, int artistGenreId) {


            if(artistGenre.equals("Pop")) {
                  artistGenreId = 1;
            }
            else if(artistGenre.equals("Rock")){
                  artistGenreId = 2;
            }
            else if(artistGenre.equals("Jazz")){
                  artistGenreId = 3;
            }
            else if(artistGenre.equals("Blues")){
                  artistGenreId = 4;
            }

            return artistGenreId;
      }



      private static void deleteArtist(String artistName){

            String sql = "DELETE FROM artist WHERE artistName = ?";

            try (Connection conn = connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                  // set the corresponding param
                  pstmt.setString(1, artistName);
                  // execute the delete statement
                  pstmt.executeUpdate();
                  System.out.println("You have removed artist " + artistName);
            } catch (SQLException e) {
                  System.out.println(e.getMessage());
            }
      }
      private static void joinTables() {
            String sql = "SELECT artist.artistName, genre.genreName FROM artist INNER JOIN genre ON artist.artistGenreId = genre.genreId";

            try {
                  Connection conn = connect();
                  Statement stmt  = conn.createStatement();
                  ResultSet rs    = stmt.executeQuery(sql);

                  System.out.println(String.format("%-20s %-35s", "artistName", "genreName"));
                  while (rs.next()) {
                        System.out.println(String.format("%-20s %-35s",
                                rs.getString("artistName"),
                                rs.getString("genreName")));
                  }
            } catch (SQLException e) {
                  System.out.println(e.getMessage());
            }
      }

      private static void showAllGenres() {
            String sql = "SELECT * FROM genre";

            try {
                  Connection conn = connect();
                  Statement stmt  = conn.createStatement();
                  ResultSet rs    = stmt.executeQuery(sql);

                  System.out.println(String.format("%-10s %-20s", "genreId", "genreName"));
                  while (rs.next()) {
                        System.out.println(String.format("%-10s %-20s",
                                rs.getInt("genreId"),
                                rs.getString("genreName")));
                  }

            } catch (SQLException e) {
                  System.out.println(e.getMessage());
            }
      }

}
