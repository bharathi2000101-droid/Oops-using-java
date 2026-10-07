import java.io.*;
class Ex7
{
public static void main(String arg[])
{
try{
FileInputStream fin=new FileInputStream("src.txt");
FileOutputStream fout=new FileOutputStream("dst.txt");
int data;
while((data=fin.read())!=-1)
{
fout.write(data);
}
}
catch(Exception e)
{
System.out.println(e);
}
System.out.println("File created successfully");
}
}
