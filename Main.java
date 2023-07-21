import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static Set<Laptop> filterLaptops(Set<Laptop> laptops, Map<String, String> criteria) {
        Set<Laptop> filteredLaptops = new HashSet<>();
        for (Laptop laptop : laptops) {
            boolean criteriaMatch = true;
            for (Map.Entry<String, String> entry : criteria.entrySet()) {
                switch (entry.getKey()) {
                    case "ram":
                        if (laptop.getRam() < Integer.parseInt(entry.getValue())) {
                            criteriaMatch = false;
                        }
                        break;
                    case "hdd":
                        if (laptop.getHdd() < Integer.parseInt(entry.getValue())) {
                            criteriaMatch = false;
                        }
                        break;
                    case "os":
                        if (!laptop.getOs().equals(entry.getValue())) {
                            criteriaMatch = false;
                        }
                        break;
                    case "color":
                        if (!laptop.getColor().equals(entry.getValue())) {
                            criteriaMatch = false;
                        }
                        break;
                }
                if (!criteriaMatch) {
                    break;
                }
            }
            if (criteriaMatch) {
                filteredLaptops.add(laptop);
            }
        }
        return filteredLaptops;
    }

    public static Map<String, String> queryCriteria() {
        Scanner scanner = new Scanner(System.in);

        Map<String, String> filterCriteria = new HashMap<>();

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ (4-16)");
        System.out.println("2 - Объем ЖД (128-1024)");
        System.out.println("3 - Операционная система (CentOS, Linux, MacOS, Windows, Ubuntu)");
        System.out.println("4 - Цвет (black, white, silver, gray, red, gold, pink, purple)");

        int criteria = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Введите минимальное значение:");
        String minValue = scanner.nextLine();
        scanner.close();

        switch (criteria) {
            case 1:
                filterCriteria.put("ram", minValue);
                break;
            case 2:
                filterCriteria.put("hdd", minValue);
                break;
            case 3:
                filterCriteria.put("os", minValue);
                break;
            case 4:
                filterCriteria.put("color", minValue);
                break;
        }

        return filterCriteria;
    }

    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();

        laptops.add(new Laptop(8, 256, "CentOS", "black"));
        laptops.add(new Laptop(4, 128, "Linux", "white"));
        laptops.add(new Laptop(16, 512, "MacOS", "silver"));
        laptops.add(new Laptop(8, 256, "Windows", "black"));
        laptops.add(new Laptop(16, 512, "Ubuntu", "silver"));
        laptops.add(new Laptop(6, 256, "Windows", "gray"));
        laptops.add(new Laptop(8, 512, "Linux", "red"));
        laptops.add(new Laptop(16, 1024, "MacOS", "gold"));
        laptops.add(new Laptop(4, 512, "Windows", "pink"));
        laptops.add(new Laptop(8, 256, "Linux", "purple"));

        System.out.println("Количество ноутбуков: " + laptops.size());

        var filterCriteria = queryCriteria();
        var filteredLaptops = filterLaptops(laptops, filterCriteria);
        if (filteredLaptops.size() == 0)
        {
            System.out.println("Нет ноутбуков, удовлетворяющих заданному критерию");
        }
        else for (Laptop laptop : filteredLaptops) {
            System.out.println(laptop);
        }    
    }
}
