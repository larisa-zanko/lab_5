package Companies;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CompaniesServise companyService = new  CompaniesServise();
        String logFilePath = "logfile.txt";

        try {
            companyService.loadCompanies("input.csv");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Выберите запрос:");
                System.out.println("1. Найти компанию по краткому наименованию");
                System.out.println("2. Выбрать компании по отрасли");
                System.out.println("3. Выбрать компании по виду деятельности");
                System.out.println("4. Выбрать компании по дате основания");
                System.out.println("5. Выбрать компании по численности сотрудников");
                System.out.println("6. Выход");

                int choice = scanner.nextInt();
                scanner.nextLine();

                List<Company> foundCompanies = null;

                switch (choice) {
                    case 1:
                        System.out.print("Введите краткое наименование: ");
                        String shortTitleQuery = scanner.nextLine();
                        foundCompanies = companyService.findByShortTitle(shortTitleQuery);
                        break;
                    case 2:
                        System.out.print("Введите отрасль: ");
                        String branchQuery = scanner.nextLine();
                        foundCompanies = companyService.findByBranch(branchQuery);
                        break;
                    case 3:
                        System.out.print("Введите вид деятельности: ");
                        String activityQuery = scanner.nextLine();
                        foundCompanies = companyService.findByActivity(activityQuery);
                        break;
                    case 4:
                        System.out.print("Введите год основания (с): ");
                        int startYear = scanner.nextInt();
                        System.out.print("Введите год основания (по): ");
                        int endYear = scanner.nextInt();
                        foundCompanies = companyService.findByFoundationDate(startYear, endYear);
                        break;
                    case 5:
                        System.out.print("Введите минимальное количество сотрудников: ");
                        int minCount = scanner.nextInt();
                        System.out.print("Введите максимальное количество сотрудников: ");
                        int maxCount = scanner.nextInt();
                        foundCompanies = companyService.findByEmployeeCount(minCount, maxCount);
                        break;
                    case 6:
                        System.out.println("Выход из программы.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Неверный выбор. Попробуйте снова.");
                        continue;
                }

                String logMessage = String.format("%s: Запрос: %s, Найдено: %d",
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),
                        choice, foundCompanies != null ? foundCompanies.size() : 0);
                companyService.logAction(logFilePath, logMessage);

                // Запись результатов в XML и JSON
                if (foundCompanies != null && !foundCompanies.isEmpty()) {
                    companyService.writeToXml("out.xml", foundCompanies);
                    companyService.writeToJson("out.json", foundCompanies);
                    System.out.println("Результаты сохранены в out.xml и out.json.");
                } else {
                    System.out.println("Ничего не найдено.");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}