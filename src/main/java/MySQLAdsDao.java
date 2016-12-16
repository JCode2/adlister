import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.api.jdbc.Statement;
import com.mysql.cj.jdbc.Driver;


/**
 * Created by joshua on 12/15/16.
 */

public class MySQLAdsDao implements Ads {


    public MySQLAdsDao() throws SQLException {




    }

    @Override
    public List<Ad> all() throws SQLException {
        try {
            DriverManager.registerDriver(new Driver());
            Connection conn = DriverManager.getConnection(
                    Config.url,
                    Config.user,
                    Config.password
            );

            Statement stmt = (Statement) conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM ads");

           List <Ad> ads = new ArrayList<>();
            while (rs.next()){
                Ad ad = new Ad();
                ad.setId(rs.getLong("id"));
                ad.setUserId(rs.getLong("user_id"));
                ad.setTitle(rs.getString("title"));
                ad.setDescription(rs.getString("description"));
                 //ad object
            }
            return ads;
        }
        catch(SQLException e){
            throw new RuntimeException("Having Trouble Finding Ads");
        }

/*
        @Override
        public Long insert (Ad ad){
            return null;
        }
    }*/

    }

    @Override
    public long insert(Ad ad) {
        try {
            DriverManager.registerDriver(new Driver());
            Connection conn = DriverManager.getConnection(
                    Config.url,
                    Config.user,
                    Config.password
            );

            Statement stmt = (Statement) conn.createStatement();
            String insertQuery = "INSERT INTO ads(user_id, title, description) VALUES( ";
            insertQuery+="'" +ad.getTitle();
            insertQuery+= " " + ad.getDescription();
            insertQuery+= " " + ad.getUserId();
            stmt.executeUpdate(insertQuery, Statement.return_generated_keys);
            ResultSet rs = stmt.getGeneratedKeys();
            if(rs.next()){
                return rs.getLong(1);
            }

            List <Ad> ads = new ArrayList<>();
            while (rs.next()){
                Ad ad = new Ad();
                ad.setId(rs.getLong("id"));
                ad.setUserId(rs.getLong("user_id"));
                ad.setTitle(rs.getString("title"));
                ad.setDescription(rs.getString("description"));
                //ad object
            }
            return ad;
        }
        catch(SQLException e){
            throw new RuntimeException("Having Trouble Finding Ads");
        }

    }
}
