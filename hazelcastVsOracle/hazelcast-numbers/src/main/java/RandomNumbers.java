import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import java.util.Random;
import com.hazelcast.collection.IList;

public class RandomNumbers {

    public static void main(String[] args) {
        //instance oluştur
        Config config = new Config();
        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);

        // Rastgele sayılar oluşturma ve ekleme süresini ölç
        long startTime = System.currentTimeMillis();
        addRandomNumbers(hazelcastInstance);
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        System.out.println("Program ekleme süresi: " + duration + " milisaniye");

        // Rastgele sayıları çekme süresini ölç
        long startTime2 = System.currentTimeMillis();
        getRandomNumbers(hazelcastInstance);
        long endTime2 = System.currentTimeMillis();
        long duration2 = endTime2 - startTime2;
        System.out.println("Program çekme süresi: " + duration2 + " milisaniye");

        // instance kapat
        hazelcastInstance.shutdown();
    }

    // Rastgele sayılar oluşturup listeye ekler
    private static void addRandomNumbers(HazelcastInstance hazelcastInstance) {
        Random random = new Random();
        IList<Integer> randomNumbersList = hazelcastInstance.getList("randomNumbers");
        
        for (int i = 1; i <= 100000; i++) {
            int value = random.nextInt(100); 
            randomNumbersList.add(value);
            
            if (i % 20000 == 0) {
                System.out.println("Added Value: " + value);
            }
        }
    }

    // Rastgele sayıları listeden çeker
    private static void getRandomNumbers(HazelcastInstance hazelcastInstance) {
        Random random = new Random();
        IList<Integer> randomNumbersList = hazelcastInstance.getList("randomNumbers");
        
        for (int i = 1; i <= 100000; i++) {
            int randomIndex = random.nextInt(randomNumbersList.size());
            int randomValue = randomNumbersList.get(randomIndex);
            
            if (i % 20000 == 0) {
                System.out.println("Retrieved Value: " + randomValue);
            }
        }
    }
}
