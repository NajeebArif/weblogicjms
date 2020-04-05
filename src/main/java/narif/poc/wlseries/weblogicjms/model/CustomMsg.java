package narif.poc.wlseries.weblogicjms.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomMsg {

    private Long id;
    private String message;

    @Override
    public String toString() {
        return "CustomMsg{id=" + id +", message='" + message + "'}";
    }
}
