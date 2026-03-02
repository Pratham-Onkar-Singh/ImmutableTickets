import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t1 = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created:    " + t1);

        IncidentTicket t2 = service.assign(t1, "agent@example.com");
        System.out.println("\nAssigned (new instance):    " + t2);
        System.out.println("Original unchanged:         " + t1);

        IncidentTicket t3 = service.escalateToCritical(t2);
        System.out.println("\nEscalated (new instance):   " + t3);
        System.out.println("Previous unchanged:         " + t2);

        List<String> tags = t3.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE");
            System.out.println("\nTag mutation succeeded (should not happen)");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nTag mutation blocked — list is unmodifiable (immutability works)");
        }

        IncidentTicket manual = IncidentTicket.builder()
                .id("TCK-2000")
                .reporterEmail("alice@example.com")
                .title("Cannot log in to portal")
                .priority("HIGH")
                .slaMinutes(120)
                .source("EMAIL")
                .customerVisible(true)
                .addTag("AUTH")
                .addTag("PORTAL")
                .build();
        System.out.println("\nManually built: " + manual);
    }
}
