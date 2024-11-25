package Companies;

import java.io.*;
        import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompaniesServise {
    private List<Company> companies = new ArrayList<>();

    public void loadCompanies(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                companies.add(new Company(line));
            }
        }
    }

    public List<Company> findByShortTitle(String shortTitle) {
        return companies.stream()
                .filter(c -> c.shortTitle.equalsIgnoreCase(shortTitle))
                .collect(Collectors.toList());
    }

    public List<Company> findByBranch(String branch) {
        return companies.stream()
                .filter(c -> c.branch.equalsIgnoreCase(branch))
                .collect(Collectors.toList());
    }

    public List<Company> findByActivity(String activity) {
        return companies.stream()
                .filter(c -> c.activity.equalsIgnoreCase(activity))
                .collect(Collectors.toList());
    }

    public List<Company> findByFoundationDate(int startYear, int endYear) {
        return companies.stream()
                .filter(c -> {
                    int year = Integer.parseInt(c.dateFoundation.split("-")[0]);
                    return year >= startYear && year <= endYear;
                })
                .collect(Collectors.toList());
    }

    public List<Company> findByEmployeeCount(int min, int max) {
        return companies.stream()
                .filter(c -> c.countEmployees >= min && c.countEmployees <= max)
                .collect(Collectors.toList());
    }

    public void logAction(String logFilePath, String message) {
        try (BufferedWriter logger = new BufferedWriter(new FileWriter(logFilePath, true))) {
            logger.write(message);
            logger.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToXml(String filePath, List<Company> companies) throws IOException {
        try (BufferedWriter xmlWriter = new BufferedWriter(new FileWriter(filePath))) {
            xmlWriter.write("<companies>");
            xmlWriter.newLine();
            for (Company company : companies) {
                xmlWriter.write(company.toXml());
                xmlWriter.newLine();
            }
            xmlWriter.write("</companies>");
        }
    }

    public void writeToJson(String filePath, List<Company> companies) throws IOException {
        try (BufferedWriter jsonWriter = new BufferedWriter(new FileWriter(filePath))) {
            jsonWriter.write("[");
            boolean first = true;
            for (Company company : companies) {
                if (!first) {
                    jsonWriter.write(",");
                }
                jsonWriter.write(company.toJson());
                first = false;
            }
            jsonWriter.write("]");
        }
    }
}