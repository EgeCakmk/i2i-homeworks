package i2i;
import org.voltdb.client.Client;
import org.voltdb.client.ClientConfig;
import org.voltdb.client.ClientFactory;
import org.voltdb.client.ClientResponse;
import org.voltdb.VoltTable;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {

            ClientConfig clientConfig = new ClientConfig();
            Client client = ClientFactory.createClient(clientConfig);

            client.createConnection("localhost",32776);

            ClientResponse response = client.callProcedure("data");

            if (response.getStatus() == ClientResponse.SUCCESS) {
                VoltTable results = response.getResults()[0];
                while (results.advanceRow()) {
                    System.out.println("SUBSCRIBER ID: " + results.getLong("SUBSC_ID") +
                            ", SUBSCRIBER NAME: " + results.getString("SUBSC_NAME") +
                            ", SUBSCRIBER LASTNAME: " + results.getString("SUBSC_SURNAME") +
                            ", MSISDN: " + results.getString("MSISDN") +
                            ", TARIFF ID: " + results.getLong("TARIFF_ID") +
                            ", START DATE: " + results.getTimestampAsLong("START_DATE"));
                }
            } else {
                System.out.println("Procedure call failed: " + response.getStatusString());
            }

            client.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
