import java.util.Scanner;

public class Main {

    public static String[] products = {"Хлеб", "Пачка гречки", "Упаковка яиц", "Мороженка"};
    public static int[] prices = {50, 135, 65, 53};
    public static int MIN_COST_FOR_BONUS = 1000;

    // В стоимости этих товаров каждые три товара должны стоить как два:
    public static String[] productsOnSale = {"Хлеб", "Мороженка"};

    public static void main(String[] args) {
        System.out.println("Добро пожаловать в магазин!");
        System.out.println("Наш ассортимент:");
        for (int i = 0; i < products.length; i++) {
            System.out.println("\t" + (i + 1) + ". " + products[i] + " за " + prices[i] + " за шт. ");
        }
        System.out.println();

        Scanner scanner = new Scanner(System.in);

        int[] counts = new int[products.length];

        while (true) {
            System.out.print("Введите номер товара и количество через пробел или end: ");
            String line = scanner.nextLine();

            if ("end".equals(line)) {
                break;
            }

            String[] parts = line.split(" ");
            int productNum = Integer.parseInt(parts[0]) - 1;
            int productCount = Integer.parseInt(parts[1]);

            counts[productNum] += productCount;
        }

        System.out.println("Ваша корзина покупок:");
        int sum = 0;
        for (int i = 0; i < products.length; i++) {
            if (counts[i] != 0) {
                boolean isOnSale = false;
                for (String saleProduct : productsOnSale) {
                    if (products[i].equals(saleProduct)) {
                        isOnSale = true;
                    }
                }

                if (isOnSale) {
                    int itemsSumCurrent = prices[i] * (counts[i] / 3 * 2 + counts[i] % 3);
                    sum += itemsSumCurrent;
                    System.out.println("\t" + products[i] + " " + (itemsSumCurrent >= MIN_COST_FOR_BONUS ? counts[i] + 1 : counts[i]) + " шт. за " + itemsSumCurrent + " руб. (распродажа!)");
                } else {
                    int itemsSumCurrent = prices[i] * counts[i];
                    sum += itemsSumCurrent;
                    System.out.println("\t" + products[i] + " " + (itemsSumCurrent >= MIN_COST_FOR_BONUS ? counts[i] + 1 : counts[i]) + " шт. за " + itemsSumCurrent + " руб.");
                }
            }
        }
        System.out.println("Итого: " + sum + " руб.");
    }

}
