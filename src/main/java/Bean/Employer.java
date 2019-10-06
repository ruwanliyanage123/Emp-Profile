package Bean;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="Employer")
public class Employer {
    private int employerId;
    private String employerName;
    private String position;

    public int getEmployerId() {
        return employerId;
    }

    public void setEmployerId(int employerId) {
        this.employerId = employerId;
    }

    public String getEmployerName() {
        return employerName;
    }

    public void setEmployerName(String employerName) {
        this.employerName = employerName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
