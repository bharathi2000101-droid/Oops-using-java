import java.util.*;
class Ex2
{
ArrayList<String> a=new ArrayList<String>();
void append(String c)
{
a.add(c);
}
void insert(int p,String c)
{
a.add(p,c);
}
boolean search(String c)
{
return a.contains(c);
}
void display()
{
System.out.println(a.toString());
}
void searchLetter(String l)
{
for(int i=0;i<a.size();i++){
if(a.get(i).startsWith(l))
System.out.println(a.get(i));
}
}
public static void main(String arg[])
{
Ex2 obj=new Ex2();
Scanner s=new Scanner(System.in);
while (true) {
System.out.println("---STRING OPERATIONS---");
System.out.println("1.Append");
System.out.println("2.Insert");
System.out.println("3.Search");
System.out.println("4.Display with starting letter:");
System.out.println("5.Exit");
System.out.println("Enter your choice:");
int ch=s.nextInt();
if(ch==1){
System.out.println("Enter city name to be appended");
obj.append(s.next());
obj.display();
}
else if(ch==2){
System.out.println("Enter city name and position");
obj.insert(s.nextInt(),s.next());
obj.display();
}
else if(ch==3){
System.out.println("Enter city name to be Searched");
System.out.println(obj.search(s.next()));
}
else if(ch==4)
{
System.out.println("Enter the starting letter:");
String l=s.next();
obj.searchLetter(l);
}
else if(ch==5){
System.out.println("Program ended");
return;
}
else{
System.out.println("Invalid choice");
}
}
}
}
