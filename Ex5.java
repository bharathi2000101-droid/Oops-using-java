import java.util.*;

class Employee {
    String ename, address, mailid;
    int eid;
    long mobile;
    double bp, da, hra, pf, sc, gs, ns;

    Scanner s = new Scanner(System.in);

    void setInput() {
        System.out.println("Enter name:");
        ename = s.nextLine();

        System.out.println("Enter address:");
        address = s.nextLine();

        System.out.println("Enter email id:");
        mailid = s.nextLine();

        System.out.println("Enter employee id:");
        eid = s.nextInt();

        System.out.println("Enter mobile number:");
        mobile = s.nextLong();
    }

    void display() {
        System.out.println("Name: " + ename);
        System.out.println("Emp id: " + eid);
        System.out.println("Mail id: " + mailid);
        System.out.println("Address: " + address);
        System.out.println("Mobile no: " + mobile);
    }
}

class programmer extends Employee {

    void setBasicpay() {
        Scanner s1 = new Scanner(System.in);

        System.out.println("Enter Basic Pay:");
        bp = s1.nextDouble();
    }

    void cal() {
        da = bp * 97 / 100.0;
        hra = bp * 10 / 100.0;
        pf = bp * 12 / 100.0;
        sc = bp * 1 / 100.0;
        gs = bp + da + hra;
        ns = gs - (pf + sc);
    }

    void display() {
        super.display();

        System.out.println("DA: " + da);
        System.out.println("HRA: " + hra);
        System.out.println("PF: " + pf);
        System.out.println("Staff Club Fund: " + sc);
        System.out.println("Gross Salary: " + gs);
        System.out.println("Net Salary: " + ns);
    }
}

class Ex3 {
    public static void main(String args[]) {

        programmer p = new programmer();

        p.setInput();
        p.setBasicpay();
        p.cal();
        p.display();
    }
}
