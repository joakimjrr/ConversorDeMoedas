import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConversorDeMoedas {

    public static void main(String[] args) {
        exibirMenu();
    }

    public static void exibirMenu() {
        System.out.println("Seja bem-vindo/a ao Conversor de moedas =]");
        System.out.println("1) Dólar =>> Peso argentino");
        System.out.println("2) Peso argentino =>> Dólar");
        System.out.println("3) Dólar =>> Real Brasileiro");
        System.out.println("4) Real Brasileiro =>> Dólar");
        System.out.println("5) Dólar =>> Peso Colombiano");
        System.out.println("6) Peso Colombiano =>> Dólar");
        System.out.println("7) Sair\n");

        System.out.println("Escolha uma opção válida:");

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String escolha = reader.readLine();

            switch (escolha) {
                case "1":
                    converterMoeda("USD", "ARS");
                    break;
                case "2":
                    converterMoeda("ARS", "USD");
                    break;
                case "3":
                    converterMoeda("USD", "BRL");
                    break;
                case "4":
                    converterMoeda("BRL", "USD");
                    break;
                case "5":
                    converterMoeda("USD", "COP");
                    break;
                case "6":
                    converterMoeda("COP", "USD");
                    break;
                case "7":
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    exibirMenu();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void converterMoeda(String from, String to) {
        try {
            String apiKey = "8290b63075fd14bb08d1a926";
            String urlStr = "https://v6.exchangeratesapi.io/latest?base=" + from + "&symbols=" + to;

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            System.out.println("Taxa de câmbio de " + from + " para " + to + ": " + response.toString());
            exibirMenu();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
