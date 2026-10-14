import java.sql.*;
public class Test
{
public static void main(String s[])throws Exception
{
Connection con;
PreparedStatement ps;
Class.forName("com.mysql.jdbc.Driver");
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bharathi","root","");
ps=con.prepareStatement("insert into students values(101,'bharathi','Msc(CS)',7.35)");
ps.executeUpdate();
System.out.println("Insert Successfully");
ps=con.prepareStatement("UPDATE `students` SET `department`='ECE' WHERE `rollno`=101");
ps.executeUpdate();
System.out.println("Updated Successfully ");
ps=con.prepareStatement("SELECT * FROM `students` ");
System.out.println("Student Records");
ResultSet rs=ps.executeQuery();
while(rs.next())
{System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getDouble(4));}
ps=con.prepareStatement("DELETE FROM `students` WHERE `rollno`=101 ");
ps.executeUpdate();
System.out.println("Deleted Successfully ");
}
}
