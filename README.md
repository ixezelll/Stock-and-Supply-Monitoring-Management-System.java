# Stock-and-Supply-Monitoring-Management-System.java
# Stock and Supply Monitoring System

**Description:** "Stock and Supply Monitoring System" is a console-based inventory and supply-release management program that allows authenticated staff to log in, view inventory with stock status indicators, restock items, add or remove items, and review transaction history, while enabling users to request and release available supplies. The program demonstrates how classes, collections, and conditional logic organize inventory operations and stock tracking in Java.

## Object-Oriented Programming Concepts Applied

The following are the detailed documentation of how the OOP principles are applied throughout the program:

### Encapsulation

```java
class Item {
    String name;
    int stock;

    public Item(String name, int stock) {
        this.name = name;
        this.stock = stock;
    }
}
```
The `Item` class groups an item's `name` and `stock` count into a single object and initializes them through its constructor. Currently, `name` and `stock` use default (package-private) access without `private` modifiers or getter/setter methods, so fields are accessed directly from `StockSupplyMonitoringSystem`. Declaring `name` and `stock` as `private` and exposing controlled methods would provide stronger encapsulation.

### Inheritance

The current program does not directly implement inheritance because `Item` and `StockSupplyMonitoringSystem` do not extend another user-defined class or implement a custom interface. Instead, `StockSupplyMonitoringSystem` uses object composition by holding collections of `Item` objects (`ArrayList<Item> inventory`) to manage supply records.

### Abstraction

```java
ArrayList<Item> inventory = new ArrayList<>();
ArrayList<String> history = new ArrayList<>();
```
In `StockSupplyMonitoringSystem`, `ArrayList` abstracts the underlying dynamic array management used to store `Item` records and log transaction history entries. Menu actions and stock status evaluations (`OUT OF STOCK`, `LOW STOCK`, `AVAILABLE`) are separated into clear branches within the program loop, though extracting repeated menu/inventory display and validation steps into dedicated helper methods would further improve abstraction.

### Polymorphism

The current program does not explicitly implement runtime polymorphism through method overriding or method overloading. Instead, it uses `switch` statements and `if-else` conditions to handle different menu selections, stock thresholds (`stock == 0`, `stock <= 5`, otherwise `AVAILABLE`), and supply release checks. Future enhancements could introduce polymorphic item or transaction types if specialized items or logging behaviors are added.

## Program Structure

### Item Class
**ROLE:**
- Represents a single supply or inventory item and its current stock level

**DATA:**
- `name` — Name of the item
- `stock` — Current available quantity in stock

**RESPONSIBILITIES:**
- Initialize item name and initial stock count via `Item(String name, int stock)`

**RELATIONSHIPS:**
- Stored inside `ArrayList<Item> inventory` within `StockSupplyMonitoringSystem`

### StockSupplyMonitoringSystem Class
**ROLE:**
- Serves as the main entry point and console controller for staff inventory management and user supply requests

**DATA STRUCTURES:**
- `ArrayList<Item> inventory` — Stores all tracked `Item` objects in memory
- `ArrayList<String> history` — Records stock additions, new items, item removals, and released supplies

**RESPONSIBILITIES:**
- Handle main menu navigation (`Staff Login`, `Get Supplies`, `Exit`)
- Authenticate staff credentials (`staff101` / `12345678`) and run the Staff Menu
- Display inventory with status indicators (`OUT OF STOCK` when `0`, `LOW STOCK` when `<= 5`, `AVAILABLE` otherwise)
- Add stock, add new items, remove items, and view transaction history
- Process supply requests (`Get Supplies`), deduct stock, log releases, and trigger low/out-of-stock alerts

**RELATIONSHIPS:**
- Creates, updates, and removes `Item` objects in `inventory`
- Appends activity logs to `history`

## How to Run the Program

### PREREQUISITES
1. Before running the program, make sure Java Development Kit (JDK) is installed. Open your terminal and check the installation:

```bash
java -version
javac -version
```
   - If you see an error like `java is not recognized`, Java is not installed or not added to your system's `PATH`. Download the JDK from: https://www.oracle.com/java/technologies/downloads/
2. Go to the GitHub repository.
3. Click the green **Code** button and select **Download ZIP**.
4. Extract the ZIP file to a folder on your computer.

### AFTERWARDS, COMPILE THE PROGRAM
5. Open VS Code or another Java-supported IDE.
6. Select **File**, then choose **Open Folder**, and select the project folder.
7. Open `StockSupplyMonitoringSystem.java` and run the program or open a terminal in the project folder and compile/run:

```bash
javac StockSupplyMonitoringSystem.java
java StockSupplyMonitoringSystem
```

### TO NAVIGATE THE PROGRAM

1. **Main Menu**
   - `1. Staff Login` — Prompts for username and password (`staff101` / `12345678`) to open the Staff Menu
   - `2. Get Supplies` — Allows selecting an available item and entering the quantity needed
   - `3. Exit` — Exits the program

2. **Staff Menu** (after successful login)
   - `1. View Inventory` — Displays each item's number, name, stock count, and status (`OUT OF STOCK`, `LOW STOCK`, or `AVAILABLE`)
   - `2. Add Stock` — Increases the stock count of an existing item and logs the transaction
   - `3. Add New Item` — Creates a new `Item` with a name and initial stock and logs it in history
   - `4. Remove Item` — Deletes a selected item from inventory and logs the removal
   - `5. View Transaction History` — Displays all recorded inventory and supply-release entries
   - `6. Logout` — Returns to the Main Menu

3. **Get Supplies**
   - Displays current items and available stock counts.
   - Prompts for an item number and quantity needed, deducts stock if sufficient, logs the release in history, and alerts if stock is `<= 5` or reaches `0`.

## Sample Output

1. **Main Menu**
   Displays the main options for the Stock and Supply Monitoring System.
   `[Insert screenshot of the Main Menu here]`

2. **Staff Login & Staff Menu**
   Shows staff authentication and the six management options available after logging in.
   `[Insert screenshot of the Staff Login & Staff Menu here]`

3. **View Inventory**
   Displays item details along with `OUT OF STOCK`, `LOW STOCK`, or `AVAILABLE` status.
   `[Insert screenshot of View Inventory here]`

4. **Add Stock, Add New Item & Remove Item**
   Shows inventory updates made by staff and confirmation messages.
   `[Insert screenshot of Add Stock / Add New Item / Remove Item here]`

5. **Get Supplies**
   Shows supply selection, stock deduction, remaining stock, and low/out-of-stock alerts.
   `[Insert screenshot of Get Supplies here]`

6. **View Transaction History**
   Displays the recorded log of added stock, new/removed items, and released supplies.
   `[Insert screenshot of View Transaction History here]`

## Author and Acknowledgement

This program is created by `[Insert Group Name]` from `[Insert Section]` in compliance with the final project requirement in the course Object-Oriented Programming.

Lead Programmer:
`[Insert Name]`

Members:
`[Insert Member 1]`
`[Insert Member 2]`
`[Insert Member 3]`
`[Insert Member 4]`

Special thanks to `[Insert Instructor's Name]` for guidance and introducing the OOP concepts used in this project.

## Future Enhancements

Future improvements may include saving inventory and transaction history to a file or database so data persists after the program exits, adding input validation for non-integer or negative inputs, supporting timestamps and user details in transaction history, encapsulating `Item` fields with private modifiers and getters/setters, and building a graphical user interface (GUI).

