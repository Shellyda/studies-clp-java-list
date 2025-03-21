# Coffee Shop Product Management System

## System Requirements

### Base Class: Product
- **Attributes:**
  - `id` (int): Unique product identifier.
  - `name` (String): Product name.
  - `price` (double): Unit price of the product.
  - `stock` (int): Quantity of the product in stock.
- **Constructor:**
  - Initializes all attributes.
- **Methods:**
  - Getters and setters for each attribute.
  - `toString()`: Returns a textual representation of the product.

### Subclass: Beverage (inherits from Product)
- **Attributes:**
  - `volume` (double): Volume of the beverage in milliliters.
- **Constructor:**
  - Initializes inherited attributes and the specific `volume` attribute.
- **Methods:**
  - Getters and setters.
  - `toString()`: Overrides the method to include volume information.

### Subclass: Food (inherits from Product)
- **Attributes:**
  - `ingredients` (List<String>): List containing the ingredients of the food item.
- **Constructor:**
  - Initializes inherited attributes and the ingredient list.
- **Methods:**
  - Getters and setters.
  - `toString()`: Overrides the method to include ingredients.

## Custom Exceptions
- **ProductNotFoundException**: Extends the `Exception` class. Thrown when a search for a product (by name) in the coffee shop does not find the desired product.
- **InvalidQuantityException**: Extends the `Exception` class. Thrown when the quantity provided for a sale is less than or equal to zero.

## Class: CoffeeShop
- **Attributes:**
  - `products` (List<Product>): List containing all available products in the coffee shop.
- **Methods:**
  - `addProduct(Product product)`: Adds a new product to the list.
  - `searchProductByName(String name)`: Returns the product whose name matches the parameter. If not found, throws a `ProductNotFoundException`.
  - `processSale(String productName, int quantity)`: Simulates a product sale.
    - Checks if the provided quantity is valid (greater than zero); otherwise, throws an `InvalidQuantityException`.
    - Searches for the product by name; if not found, throws or propagates a `ProductNotFoundException`.
    - If the sale is valid, displays a message showing the sold product, quantity, and total sale price.

## Class: Main
### Functionality:
- Create a test scenario where:
  - `Beverage` and `Food` objects are instantiated and added to the coffee shop.
  - Sales tests are performed, including:
    - A valid sale.
    - An attempt to sell a non-existent product.
    - An attempt to sell with an invalid quantity.
    - An attempt to sell with insufficient stock.
- Use try-catch blocks to capture and handle exceptions, displaying appropriate messages for the user.

