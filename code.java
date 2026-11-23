import java.util.Scanner;

public class RequesterSession extends UserSession {

    public RequesterSession(InventoryManager manager, Scanner sc) {
        super(manager, sc);
    }

    @Override
    protected boolean authenticate() {
        System.out.println("\n===== GET SUPPLIES LOGIN =====");
        System.out.print("Username: ");
        String username = sc.nextLine().trim();
        System.out.print("Password: ");
        String password = sc.nextLine().trim();
        return username.equals("staff101") && password.equals("12345678");
    }

    private RequesterDetails collectCompanyDetails() {
        System.out.println("\n===== COMPANY EMPLOYEE DETAILS =====");
        System.out.print("Full Name: ");
        String fullName = sc.nextLine().trim();
        while (fullName.isEmpty()) {
            System.out.print("Full Name cannot be empty. Enter Full Name: ");
            fullName = sc.nextLine().trim();
        }

        System.out.print("Employee / Company ID: ");
        String employeeId = sc.nextLine().trim();
        while (employeeId.isEmpty()) {
            System.out.print("Employee ID cannot be empty. Enter Employee / Company ID: ");
            employeeId = sc.nextLine().trim();
        }

        System.out.print("Department (e.g., IT, HR, Finance, Operations): ");
        String department = sc.nextLine().trim();
        while (department.isEmpty()) {
            System.out.print("Department cannot be empty. Enter Department: ");
            department = sc.nextLine().trim();
        }

        return new RequesterDetails(fullName, employeeId, department);
    }

    @Override
    public void startMenu() {
        if (!authenticate()) {
            System.out.println("Invalid Username or Password!");
            return;
        }

        System.out.println("\nLogin Successful!");

        if (manager.getInventory().isEmpty()) {
            System.out.println("\nNo available supplies in inventory.");
            return;
        }

        String getMore;
        do {
            if (manager.getInventory().isEmpty()) {
                System.out.println("\nNo more available supplies in inventory.");
                break;
            }

            System.out.println("\n===== AVAILABLE SUPPLIES =====");
            for (int i = 0; i < manager.getInventory().size(); i++) {
                Item item = manager.getInventory().get(i);
                System.out.println((i + 1) + ". " + item.getName() + " [" + item.getCategory() + "] (Stock: " + item.getStock() + " " + item.getUnit() + ")");
            }

            int selected = readInt("Select Item Number: ");

            if (selected >= 1 && selected <= manager.getInventory().size()) {
                Item item = manager.getInventory().get(selected - 1);

                if (item.getStock() == 0) {
                    System.out.println("OUT OF STOCK!");
                } else {
                    RequesterDetails requester = collectCompanyDetails();
                    int qty = readInt("Quantity Needed: ");

                    if (qty <= 0) {
                        System.out.println("Invalid quantity!");
                    } else if (qty <= item.getStock()) {
                        if (manager.releaseSupply(selected - 1, qty, requester)) {
                            System.out.println("\n====================================");
                            System.out.println("        SUPPLY RELEASE SLIP         ");
                            System.out.println("====================================");
                            System.out.println("Item Released  : " + item.getName() + " (" + qty + " " + item.getUnit() + ")");
                            System.out.println("Category       : " + item.getCategory());
                            System.out.println("Requested By   : " + requester.getFullName());
                            System.out.println("Employee ID    : " + requester.getEmployeeId());
                            System.out.println("Department     : " + requester.getDepartment());
                            System.out.println("Remaining Stock: " + item.getStock() + " " + item.getUnit());
                            System.out.println("====================================");

                            if (item.getStock() <= 5 && item.getStock() > 0) {
                                System.out.println("LOW STOCK ALERT!");
                            } else if (item.getStock() == 0) {
                                System.out.println("ITEM IS NOW OUT OF STOCK!");
                            }
                        }
                    } else {
                        System.out.println("Insufficient Stock!");
                    }
                }
            } else {
                System.out.println("Invalid Item Number!");
            }

            System.out.print("\nDo you want to get more supplies? (yes/no): ");
            getMore = sc.nextLine().trim();
        } while (getMore.equalsIgnoreCase("yes") || getMore.equalsIgnoreCase("y"));

        System.out.println("Logged Out.");
    }
}
