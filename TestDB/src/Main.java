import java.sql.*;

public class Main {

    public static final String DB_NAME= "testJava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:C:\\Users\\Welcome\\Java Programmes\\TestDB\\"+ DB_NAME;

    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";


    public static void main(String[] args) {

        try{
            Connection conn = DriverManager.getConnection(CONNECTION_STRING);
            Statement statement = conn.createStatement();

            statement.execute("DROP TABLE IF EXISTS "+ TABLE_CONTACTS);

            statement.execute("CREATE TABLE IF NOT EXISTS " +TABLE_CONTACTS +
                    " ( "+ COLUMN_NAME +" Text, "
                    + COLUMN_PHONE + " INTEGER, "
                    + COLUMN_EMAIL + " TEXT)");

            insertContact(statement,"Shyam", 123456, "sm@email.com");
            insertContact(statement,"Ram", 16756, "Qram@email.com");
            insertContact(statement,"Karan", 120986, "kr@email.com");
            insertContact(statement,"Suresh", 193728, "smistri@trialemail.com");

            statement.execute("UPDATE "+ TABLE_CONTACTS + " SET "
                    + COLUMN_PHONE + " = 990033 WHERE "
                    + COLUMN_NAME + " = 'Karz'");

            statement.execute("DELETE FROM "+ TABLE_CONTACTS +
                    " WHERE " + COLUMN_NAME + "='Ram'");


            ResultSet result = statement.executeQuery("SELECT * FROM " + TABLE_CONTACTS );
            while(result.next()){
                System.out.println(result.getString(COLUMN_NAME)+" "+
                                    result.getInt(COLUMN_PHONE)+" "+
                                    result.getString(COLUMN_EMAIL));
            }

            result.close();

            statement.close();
            conn.close();

        }catch(SQLException e){
            System.out.println("Something went wayward:"+ e.getMessage() );
            e.printStackTrace();
        }

    }
    private static void insertContact( Statement statement, String name, int phone, String email) throws SQLException{
        statement.execute("INSERT INTO " + TABLE_CONTACTS + " ( "
                + COLUMN_NAME + ", "
                + COLUMN_PHONE + ", "
                + COLUMN_EMAIL + " ) "
                + "VALUES ('"+ name +"', "+ phone +", '"+ email +"' )");
    }

}