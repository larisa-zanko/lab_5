package Companies;

public class Company {
    String name;
    String shortTitle;
    String dateUpdate;
    String address;
    String dateFoundation;
    int countEmployees;
    String auditor;
    String phone;
    String email;
    String branch;
    String activity;
    String internetAddress;

    public Company(String csvLine) {
        String[] fields = csvLine.split(";");
        this.name = fields[0];
        this.shortTitle = fields[1];
        this.dateUpdate = fields.length > 2 ? fields[2] : "";
        this.address = fields.length > 3 ? fields[3] : "";
        this.dateFoundation = fields.length > 4 ? fields[4] : "";
        this.countEmployees = fields.length > 5 && !fields[5].isEmpty() ? Integer.parseInt(fields[5]) : 0;
        this.auditor = fields.length > 6 ? fields[6] : "";
        this.phone = fields.length > 7 ? fields[7] : "";
        this.email = fields.length > 8 ? fields[8] : "";
        this.branch = fields.length > 9 ? fields[9] : "";
        this.activity = fields.length > 10 ? fields[10] : "";
        this.internetAddress = fields.length > 11 ? fields[11] : "";
    }

    public String toXml() {
        return String.format("<company><name>%s</name><shortTitle>%s</shortTitle><branch>%s</branch><activity>%s</activity></company>",
                name, shortTitle, branch, activity);
    }

    public String toJson() {
        return String.format("{\"name\":\"%s\",\"shortTitle\":\"%s\",\"branch\":\"%s\",\"activity\":\"%s\"}",
                name, shortTitle, branch, activity);
    }
}