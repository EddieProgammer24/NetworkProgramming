import java.util.ArrayList;
import java.util.List;

class HostEntry {
    private String domainName;
    private String ipAddress;

    public HostEntry(String domainName, String ipAddress) {
        this.domainName = domainName;
        this.ipAddress = ipAddress;
    }

    public String getDomainName() {
        return domainName;
    }

    public String getIpAddress() {
        return ipAddress;
    }
}

public class DNSDatabase {
    private List<HostEntry> database;

    public DNSDatabase() {
        this.database = new ArrayList<>();
    }

    public void addHostEntry(String domainName, String ipAddress) {
        database.add(new HostEntry(domainName, ipAddress));
    }

    public String findIpAddress(String domainName) {
        for (HostEntry entry : database) {
            if (entry.getDomainName().equals(domainName)) {
                return entry.getIpAddress();
            }
        }
        return null;
    }

    public static void main(String[] args) {
        DNSDatabase dnsDatabase = new DNSDatabase();

        // Adding some host entries
        dnsDatabase.addHostEntry("example.com", "192.0.2.1");
        dnsDatabase.addHostEntry("google.com", "172.217.7.14");

        // Finding IP address for a domain name
        String ipAddress = dnsDatabase.findIpAddress("google.com");
        if (ipAddress != null) {
            System.out.println("IP Address for google.com: " + ipAddress);
        } else {
            System.out.println("IP Address not found for google.com");
        }
    }
}
