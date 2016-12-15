import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;


/**
 * Created by joshua on 12/15/16.
 */
public class MySQLAdsDao implements Ads {

    Connection connection = DriverManager.getConnection(
            "jdbc:mysql://localhost/adslister_db",
            "root",
            "codeup"
    );

    public MySQLAdsDao() throws SQLException {
    }

    @Override
    public List<Ad> all() {
        return null;
    }

    @Override
    public Long insert(Ad ad) {
        return null;
    }
}
