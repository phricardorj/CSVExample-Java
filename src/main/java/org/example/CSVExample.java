import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.example.Employee;

public class CSVExample {

    private static final String FILE_NAME = "employees.csv";

    public static void main(String[] args) {
        // instanciando o scanner
        Scanner scanner = new Scanner(System.in);

        // Verifica se o arquivo já existe
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            // Cria o arquivo e escreve alguns dados de exemplo
            try (CSVWriter writer = new CSVWriter(new FileWriter(FILE_NAME))) {
                String[] data = "Pedro Ricardo,22,Developer".split(",");
                writer.writeNext(data);
                data = "Gabriel,21,Student".split(",");
                writer.writeNext(data);
                data = "João,21,Student".split(",");
                writer.writeNext(data);
                data = "Alice,22,Developer".split(",");
                writer.writeNext(data);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Lê os dados do arquivo CSV e armazena em uma lista de objetos Employee
        List<Employee> employees = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(FILE_NAME))) {
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                String name = nextLine[0];
                int age = Integer.parseInt(nextLine[1]);
                String profession = nextLine[2];
                employees.add(new Employee(name, age, profession));
            }
        } catch (CsvValidationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Pede ao usuário para informar uma profissão
        System.out.print("Informe uma profissão: ");
        String profession = scanner.nextLine();

        // Pede ao usuário para informar uma idade
        System.out.print("Informe uma idade: ");
        int age = Integer.parseInt(scanner.nextLine());

        // Imprime os nomes dos funcionários com a profissão informada
        System.out.println("Funcionários com a profissão " + profession + ":");
        for (Employee employee : employees) {
            if (employee.getProfession().equalsIgnoreCase(profession)) {
                System.out.println(employee.getName());
            }
        }

        // Imprime os nomes dos funcionários com a idade informada
        System.out.println("Funcionários com " + age + " anos:");
        for (Employee employee : employees) {
            if (employee.getAge() == age) {
                System.out.println(employee.getName());
            }
        }
    }
}
