
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Main {

    public static void main(String[] args) throws SQLException, ParseException {

        DBConnection dbConnection = new DBConnection(
                "jdbc:mysql://localhost:3306/school?useSSL=false&characterEncoding=utf8",
                "root",
                "coderslab");

//        Aktualna data w Stringu:
//        Date curDate = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        String dateToStr = dateFormat.format(curDate);




    }
}


// delete from db
