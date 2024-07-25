import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Timestamp;

public class OraJDBC{
    public static void main(String[] args) {
        // Veritabanı bağlantı bilgileri
        String url = "jdbc:oracle:thin:@localhost:1521:A"; // Veritabanı URL'si
        String user = "i2i_proje"; // Veritabanı kullanıcı adı
        String password = "123324"; // Veritabanı şifresi

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // JDBC driver'ını yükleyin
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Veritabanına bağlantı oluşturun
            connection = DriverManager.getConnection(url, user, password);

            // Rastgele sayıları eklemek için SQL sorgusu
            String sql = "INSERT INTO random_numbers2 (random_value) VALUES (?)";
            preparedStatement = connection.prepareStatement(sql);

            // Başlangıç zamanını kaydet
            Timestamp startTime = new Timestamp(System.currentTimeMillis());

            // 20000 rastgele sayıyı ekle
            for (int i = 0; i < 100000; i++) {
                preparedStatement.setDouble(1, Math.random()); // Math.random() ile rastgele sayı üretir
                preparedStatement.addBatch(); // Batch işlemine ekle

                // Eğer batch büyüklüğü belirli bir sınırı aşarsa, işlemi veritabanına gönder
                if (i % 1000 == 0) {
                    preparedStatement.executeBatch();
                }
            }
            
         // Son batch'i veritabanına gönder
            preparedStatement.executeBatch();

            // Bitiş zamanını kaydet
            Timestamp endTime = new Timestamp(System.currentTimeMillis());

            // İşlem süresini hesapla ve yazdır
            long duration = endTime.getTime() - startTime.getTime();
            System.out.println("İşlem Süresi: " + duration + " milisaniye");
        }catch(ClassNotFoundException | SQLException e) {
        	 e.printStackTrace();
        }  finally {
            // Kaynakları kapat
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
        	
        
    

