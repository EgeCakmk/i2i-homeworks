
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;

public class oraJDBCSelect {
    public static void main(String[] args) {
    	
    	String url = "jdbc:oracle:thin:@localhost:1521:A"; // Veritabanı URL'si
        String user = "i2i_proje"; // Veritabanı kullanıcı adı
        String password = "123324"; // Veritabanı şifresi
    	
    	
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Double> randomNumbers2 = new ArrayList<>();

        try {
            // JDBC driver'ını yükleyin
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Veritabanına bağlantı oluşturun
            connection = DriverManager.getConnection(url, user, password);

            // Rastgele 20000 veriyi almak için SQL sorgusu
            String sql = "SELECT random_value FROM (SELECT random_value FROM random_numbers2 ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM <= 100000";
            preparedStatement = connection.prepareStatement(sql);

            // Başlangıç zamanını kaydet
            long startTime = System.currentTimeMillis();

            // Sorguyu çalıştır ve sonuçları al
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                randomNumbers2.add(resultSet.getDouble("random_value"));
            }

            // Bitiş zamanını kaydet
            long endTime = System.currentTimeMillis();

            // İşlem süresini hesapla ve yazdır
            long duration = endTime - startTime;
            System.out.println("Veri Çekme Süresi: " + duration + " milisaniye");

            // Çekilen verileri yazdır 
            System.out.println("Çekilen Veri Sayısı: " + randomNumbers2.size());

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // Kaynakları kapat
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
