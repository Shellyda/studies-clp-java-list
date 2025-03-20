
import java.util.ArrayList;
import java.util.List;

class Product {

    public int id;
    public String name;
    public double price;
    public int stock;

    public Product(int id, String name, double price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return String.format("Produto[ID: %d, Nome: %s, Preço: R$ %.2f, Estoque: %d]", id, name, price, stock);
    }
}

class Drink extends Product {

    double volume;

    public Drink(double volume, int id, String name, double price, int stock) {
        super(id, name, price, stock);
        this.volume = volume;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public String toString() {
        return String.format("Produto[ID: %d, Nome: %s, Preço: R$ %.2f, Estoque: %d, Volume: %.2f ml]", id, name, price, stock, volume);
    }
}

class Food extends Product {

    public List<String> ingredients;

    public Food(int id, String name, double price, int stock) {
        super(id, name, price, stock);
        this.ingredients = new ArrayList<>();
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients.addAll(ingredients);
    }

    public List<String> getIngredients() {
        return ingredients;
    }

    @Override
    public String toString() {
        return String.format("Produto[ID: %d, Nome: %s, Preço: R$ %.2f, Estoque: %d, Ingredientes: %s]", id, name, price, stock, ingredients);
    }
}

class ProductNotFoundException extends Exception {

    public ProductNotFoundException(String name) {
        super("Produto " + name + " não foi encontrado na lista.");
    }
}

class InvalidQuantityException extends Exception {

    public InvalidQuantityException(String name) {
        super("Quantidade informada inválida para a compra do produto " + name + ".");
    }
}

class OutOfStockException extends Exception {

    public OutOfStockException(String name) {
        super("Produto " + name + " fora de estoque.");
    }
}

class CoffePlace {

    List<Product> products;

    public CoffePlace() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public Product searchByProductName(String name) throws ProductNotFoundException {
        for (int i = 0; i < products.size(); i++) {
            if (this.products.get(i).getName().equals(name)) {
                return this.products.get(i);
            }
        }
        throw new ProductNotFoundException(name);
    }

    public String makeSell(String productName, int quantity) throws InvalidQuantityException, ProductNotFoundException, OutOfStockException {
        if (quantity <= 0) {
            throw new InvalidQuantityException(productName);
        }

        Product product = this.searchByProductName(productName);

        if (product.getStock() < quantity) {
            throw new OutOfStockException(productName);
        }

        product.setStock(product.getStock() - quantity);
        double totalValue = product.getPrice() * quantity;

        return String.format("Venda no valor total de %.2f do produto %s (qtd: %d) feita com sucesso!", totalValue, productName, quantity);
    }

}

public class CoffeShopSystem {

    public static void main(String[] args) {
        CoffePlace coffePlace = new CoffePlace();

        Drink coffee = new Drink(250, 1, "Café", 5.00, 10);
        Drink juice = new Drink(300, 2, "Suco de Laranja", 7.50, 5);
        Food sandwich = new Food(3, "Sanduíche", 12.00, 3);
        sandwich.setIngredients(List.of("Pão", "Queijo", "Presunto", "Tomate"));

        coffePlace.addProduct(coffee);
        coffePlace.addProduct(juice);
        coffePlace.addProduct(sandwich);

        // Teste 1: Venda válida
        try {
            System.out.println(coffePlace.makeSell("Café", 2));
        } catch (InvalidQuantityException | OutOfStockException | ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Teste 2: Tentativa de vender um produto inexistente
        try {
            System.out.println(coffePlace.makeSell("Chá Verde", 1));
        } catch (InvalidQuantityException | OutOfStockException | ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Teste 3: Tentativa de venda com quantidade inválida
        try {
            System.out.println(coffePlace.makeSell("Sanduíche", 0));
        } catch (InvalidQuantityException | OutOfStockException | ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }

        // Teste 4: Tentativa de venda com estoque insuficiente
        try {
            System.out.println(coffePlace.makeSell("Suco de Laranja", 10));
        } catch (InvalidQuantityException | OutOfStockException | ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
