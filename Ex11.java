import java.sql.*;
import java.util.Scanner;

public class Main {

    static final String URL =
        "jdbc:mysql://localhost:3306/medical_records";
    static final String USER = "root";

    // Replace this with your MySQL root password
    static final String PASSWORD = "YOUR_MYSQL_PASSWORD";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try (Connection con =
                 DriverManager.getConnection(URL, USER, PASSWORD)) {

            System.out.println("MySQL Connected Successfully!");

            int choice = -1;

            do {
                System.out.println("\n===== MEDICAL RECORDS MANAGEMENT SYSTEM =====");
                System.out.println("1. Add Patient");
                System.out.println("2. View Patients");
                System.out.println("3. Add Doctor");
                System.out.println("4. View Doctors");
                System.out.println("5. Schedule Appointment");
                System.out.println("6. View Appointments");
                System.out.println("0. Exit");
                System.out.print("Enter choice: ");

                try {
                    choice = Integer.parseInt(sc.nextLine().trim());

                    switch (choice) {
                        case 1:
                            addPatient(con);
                            break;
                        case 2:
                            viewPatients(con);
                            break;
                        case 3:
                            addDoctor(con);
                            break;
                        case 4:
                            viewDoctors(con);
                            break;
                        case 5:
                            scheduleAppointment(con);
                            break;
                        case 6:
                            viewAppointments(con);
                            break;
                        case 0:
                            System.out.println("Thank you!");
                            break;
                        default:
                            System.out.println("Choose a valid option.");
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Please enter a valid number.");
                    choice = -1;
                } catch (SQLException e) {
                    System.out.println("Database error: " + e.getMessage());
                }

            } while (choice != 0);

        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            System.out.println(
                "Check your MySQL service, password and JDBC driver."
            );
        }

        sc.close();
    }

    static void addPatient(Connection con) throws SQLException {
        System.out.print("Patient ID: ");
        String id = sc.nextLine().trim();

        System.out.print("Patient name: ");
        String name = sc.nextLine().trim();

        System.out.print("Age: ");
        int age = Integer.parseInt(sc.nextLine().trim());

        System.out.print("Gender: ");
        String gender = sc.nextLine().trim();

        System.out.print("Contact number: ");
        String contact = sc.nextLine().trim();

        if (id.isEmpty() || name.isEmpty()
                || age < 0 || age > 120) {
            System.out.println("Invalid patient details.");
            return;
        }

        String sql = "INSERT INTO patients VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setInt(3, age);
            ps.setString(4, gender);
            ps.setString(5, contact);

            ps.executeUpdate();
            System.out.println("Patient added successfully!");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Patient ID already exists.");
        }
    }

    static void viewPatients(Connection con) throws SQLException {
        String sql = "SELECT * FROM patients";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                    rs.getString("patient_id") + " | "
                    + rs.getString("name") + " | Age: "
                    + rs.getInt("age") + " | "
                    + rs.getString("gender") + " | Contact: "
                    + rs.getString("contact")
                );
            }

            if (!found) {
                System.out.println("No patients found.");
            }
        }
    }

    static void addDoctor(Connection con) throws SQLException {
        System.out.print("Doctor ID: ");
        String id = sc.nextLine().trim();

        System.out.print("Doctor name: ");
        String name = sc.nextLine().trim();

        System.out.print("Specialization: ");
        String specialization = sc.nextLine().trim();

        if (id.isEmpty() || name.isEmpty()
                || specialization.isEmpty()) {
            System.out.println("Doctor details cannot be empty.");
            return;
        }

        String sql = "INSERT INTO doctors VALUES (?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, specialization);

            ps.executeUpdate();
            System.out.println("Doctor added successfully!");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Doctor ID already exists.");
        }
    }

    static void viewDoctors(Connection con) throws SQLException {
        String sql = "SELECT * FROM doctors";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                    rs.getString("doctor_id") + " | "
                    + rs.getString("name") + " | "
                    + rs.getString("specialization")
                );
            }

            if (!found) {
                System.out.println("No doctors found.");
            }
        }
    }

    static void scheduleAppointment(Connection con)
            throws SQLException {

        System.out.print("Appointment ID: ");
        String id = sc.nextLine().trim();

        System.out.print("Patient ID: ");
        String patientId = sc.nextLine().trim();

        System.out.print("Doctor ID: ");
        String doctorId = sc.nextLine().trim();

        System.out.print("Date (DD-MM-YYYY): ");
        String date = sc.nextLine().trim();

        System.out.print("Time: ");
        String time = sc.nextLine().trim();

        if (id.isEmpty() || date.isEmpty() || time.isEmpty()) {
            System.out.println("Appointment details cannot be empty.");
            return;
        }

        if (!exists(con, "patients", "patient_id", patientId)) {
            System.out.println("Patient ID not found.");
            return;
        }

        if (!exists(con, "doctors", "doctor_id", doctorId)) {
            System.out.println("Doctor ID not found.");
            return;
        }

        String sql = "INSERT INTO appointments VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.setString(2, patientId);
            ps.setString(3, doctorId);
            ps.setString(4, date);
            ps.setString(5, time);

            ps.executeUpdate();
            System.out.println("Appointment scheduled successfully!");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println(
                "Appointment ID already exists or a related record is invalid."
            );
        }
    }

    static boolean exists(Connection con, String table,
                          String column, String id)
            throws SQLException {

        // Table and column names are fixed internal values.
        String sql = "SELECT 1 FROM " + table
                   + " WHERE " + column + " = ?";

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    static void viewAppointments(Connection con)
            throws SQLException {

        String sql =
            "SELECT a.appointment_id, a.patient_id, p.name AS patient_name, "
          + "a.doctor_id, d.name AS doctor_name, "
          + "a.appointment_date, a.appointment_time "
          + "FROM appointments a "
          + "JOIN patients p ON a.patient_id = p.patient_id "
          + "JOIN doctors d ON a.doctor_id = d.doctor_id";

        try (Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            boolean found = false;

            while (rs.next()) {
                found = true;
                System.out.println(
                    rs.getString("appointment_id")
                    + " | Patient: " + rs.getString("patient_name")
                    + " (" + rs.getString("patient_id") + ")"
                    + " | Doctor: " + rs.getString("doctor_name")
                    + " (" + rs.getString("doctor_id") + ")"
                    + " | Date: " + rs.getString("appointment_date")
                    + " | Time: " + rs.getString("appointment_time")
                );
            }

            if (!found) {
                System.out.println("No appointments found.");
            }
        }
    }
}
