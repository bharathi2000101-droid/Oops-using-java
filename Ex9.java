import java.util.Scanner;  
public class GenericDemo {
public static <E extends Comparable<E> > E max(E[] list) {
E max = list[0]; 
for(int i = 1; i < list.length; i++) {
if(list[i].compareTo(max) > 0 ) {
max = list[i];
}
}
return max;
}
public static <E extends Comparable<E> > E min(E[] list) {
E min  = list[0]; 
for(int i = 1; i < list.length; i++) {
if(list[i].compareTo(min) < 0 ) {
min = list[i];
}
}
return min;
}
public static void main(String arg[]){
Integer []a={1,5,3,15};
Double []b={.5,67.8,3.8,0.01};
GenericDemo g=new GenericDemo();
System.out.println("Find the max number in Integer list is:"+g.max(a));
System.out.println("Find the max number in Double list is:"+g.max(b));
System.out.println("Find the min number in Integer list is:"+g.min(a));
System.out.println("Find the min number in Double list is:"+g.min(b));
}
}
