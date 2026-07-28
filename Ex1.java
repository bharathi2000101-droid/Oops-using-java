import java.util.Scanner;
class EB
{
    int consumerNo;
    String consumerName;
    int previousReading, currentReading, units;
    String connectionType;
    double amount = 0;
    void getInput()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Consumer Number: ");
        consumerNo = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Consumer Name: ");
        consumerName = sc.nextLine();
        System.out.print("Enter Previous Reading: ");
        previousReading = sc.nextInt();
        System.out.print("Enter Current Reading: ");
        currentReading = sc.nextInt();
        units = currentReading - previousReading;
        sc.nextLine();
        System.out.print("Enter Connection Type (Domestic/Commercial): ");
        connectionType = sc.nextLine();
    }

    void calculateBill()
    {
        if(connectionType.equalsIgnoreCase("Domestic"))
        {
            if(units <= 100)
            {
                amount=0;
            }
            else if(units <= 200)
            {
                amount=units*2;
            }
            else if(units <= 500)
            {
                amount=units*4;
            }
            else
            {
                amount=units*6;
            }
        }
        else if(connectionType.equalsIgnoreCase("Commercial"))
        {
            if(units <= 100)
            {
                amount = units * 2;
            }
            else if(units <= 200)
            {
                amount = units * 4;
            }
            else if(units <= 500)
            {
                amount = units * 6;
            }
            else
            {
                amount = units * 7;
            }
        }
        else
        {
            System.out.println("Invalid Connection Type");
            return;
        }

        displayBill();
    }

    void displayBill()
    {
        System.out.println("\n------ EB BILL ------");
        System.out.println("Consumer No : " + consumerNo);
        System.out.println("Consumer Name : " + consumerName);
        System.out.println("Units Consumed: " + units);
        System.out.println("Connection : " + connectionType);
        System.out.println("Bill Amount : Rs." + amount);
    }

    public static void main(String args[])
    {
        EB obj = new EB();
        obj.getInput();
        obj.calculateBill();
    }
}
