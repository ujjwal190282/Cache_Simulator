import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class T{
public static boolean ispowtwo(int p)
{while(p%2==0&&p>=0)p=p/2;
if(p==1)return(true);
else return(false);}
public static int powtwo(int p)
{int k=0;
	while(p%2==0&&p>=0) {p=p/2;k=k+1;}
	return k;
}
public static int dec(String x) {
	int k=0;
	int len=x.length()-1;
	for(int i=len;i>=0;i--) if(x.charAt(i)=='1')k=k+(int)Math.pow(2,len-i);
	return k;
}
	
public static void main(String[] args) throws IOException {
//Scanner scan= new Scanner(System.in);
	 File file =new File("C:\\Users\\Ujjwal\\Desktop\\input.txt");
	 BufferedReader scan =new BufferedReader(new FileReader(file));
	
Scanner p=new Scanner(System.in);
//if((str=scan.readLine())==null)System.out.println("File is empty");
Vector<Vector<String>> s = new Vector<Vector<String>>();

int S=Integer.parseInt(scan.readLine());
if(ispowtwo(S)==false){ System.out.println("Cache size should be power of two");System.exit(0);}

int B=Integer.parseInt(scan.readLine());
if(ispowtwo(B)==false){ System.out.println("Block size should be power of two");System.exit(0);}
int C=Integer.parseInt(scan.readLine());
if(ispowtwo(C)==false){ System.out.println("Number of lines should be power of two");System.exit(0);}
int s1=powtwo(S);
//System.out.println(s1);
int b1=powtwo(B);
//System.out.println(b1);
int c1=powtwo(C);
//System.out.println(c1);
if(S/B!=C) {System.out.println("Cache Size/Block size != No.of lines");System.exit(0);}
else if(B>S){System.out.println("Block Size greater than Cache size");System.exit(0);}
System.out.println("Press 1 for Direct, 2 for associative , 3 for n-way set associative ");
int choice= p.nextInt();
while(choice!=1&&choice!=2&&choice!=3) {System.out.println("Enter the correct choice");choice= p.nextInt();}
int n=0;
if(choice==3)
{
System.out.println("Enter the n for n-way associative mapping--");
n=p.nextInt();
int flag1=1;
while(flag1==1)
{
	if(n>C||n==0||(ispowtwo(n)==false)) {System.out.println("Enter the correct value of n");flag1=1;
	n=p.nextInt();
	}
	else flag1=0;
}
}
String[][] v = new String[C][B];
 for (int i = 0; i < C; i++) {
	 for(int j=0;j<B;j++) {
		 v[i][j]=""; 
	 }
 }
 String[] tag = new String[C];
 for(int i=0;i<C;i++)tag[i]="";
 String str;
 while((str=scan.readLine())!=null)
 {
	 
	 str = str.replaceAll("\\s", "");
	 String[] ary = str.split(",");
	 if(ary.length>2) {System.out.println("the input instruction includes more than two elements");continue;}
	 else
	 {   int flag=0;
		 if(ary[0].length()!=16) {System.out.println("Address in instruction is not of 16 bit");flag=1;continue;}
		 for(int i=0;i<16;i++) if(ary[0].charAt(i)!='0'&& ary[0].charAt(i)!='1') {System.out.println("Address is not in binary");flag=1;continue;}
		if(flag==0)
		{	if(choice==1)
		    {
			String tagarr=(ary[0].substring(0,16-b1-c1));
			int line=dec(ary[0].substring(16-b1-c1,16-b1));
			int index=dec(ary[0].substring(16-b1,16));
	
			if(ary.length==1)
			{
			if(tag[line].equals(tagarr)==false)System.out.println("Miss");
			else if(v[line][index].equals(""))System.out.println("Miss");
			else System.out.println(v[line][index]);
			}
			else
			{
				if(tag[line].equals("")) 
				{	tag[line]=tagarr;
				 	v[line][index]=ary[1];
				 }
			else if(tag[line].equals(tagarr)==false) {
					System.out.println("Replacement");
					for(int j=0;j<B;j++) {if( v[line][j]!="") {
						System.out.println(tag[line]+Integer.toBinaryString(line)+Integer.toBinaryString(j)+" , "+v[line][j]);
					}  v[line][j]="";
						};
						tag[line]=tagarr;
						v[line][index]=ary[1];	 
					 
				}
				else if(tag[line].equals(tagarr)) {
						 v[line][index]=ary[1];}	
			}
			}
	if(choice==2)
	{
		String tagarr=(ary[0].substring(0,16-b1));
		int index=dec(ary[0].substring(16-b1,16));
		if(ary.length==1)
		{
			int i;
		for(i=0;i<C;i++)
			{
				if(tag[i].equals(tagarr))
				{
					if(v[i][index].equals(""))
					{
						System.out.println("Miss");
						break;
					}
					else 
					{
						System.out.println(v[i][index]);
						break;
					}
				}
			}
		if(i==C)System.out.println("Miss");
		}
		else
		{
			int f=0;
			for(int i=0;i<C;i++)
			{
				if(tag[i].equals("")) 
				{
					tag[i]=tagarr;
					v[i][index]=ary[1];
					f=1;
					break;
				}
				else if(tag[i].equals(tagarr))
				{
					v[i][index]=ary[1];
					f=1;
					break;
				}
			}
			if(f==0)
			{
				int random= (int) (Math.random() * (C) + 0);
				System.out.println("Replacement  ");
				for(int i=0;i<B;i++)
				{
					if( v[random][i].equals("")==false) {
						System.out.println(tag[random]+Integer.toBinaryString(i)+" , "+v[random][i]);
						v[random][i]="";
				}
					
				}	tag[random]=tagarr;
					v[random][index]=ary[1];
					
			
		}
		
	}
	
		
	}
	if(choice==3)
	{
		
		int se=powtwo(C/n);
		String tagarr=(ary[0].substring(0,16-b1-se));
		int set =dec(ary[0].substring(16-b1-se,16-b1));
		
		int k=set*n;
		int index=dec(ary[0].substring(16-b1,16));
		if(ary.length==1)
		{
			int i;
			for(i=k;i<k+n;i++)
				{
					if(tag[i].equals(tagarr))
					{
						if(v[i][index].equals(""))
						{
							System.out.println("Miss");
							break;
						}
						else 
						{
							System.out.println(v[i][index]);
							break;
						}
					}
				}
			if(i==k+n)System.out.println("Miss");
		}
		else
		{
			int f=0;
			for(int i=k;i<k+n;i++)
			{ 
				if(tag[i].equals("")) 
				{
					tag[i]=tagarr;
					v[i][index]=ary[1];
					f=1;
					break;
				}
				else if(tag[i].equals(tagarr))
				{
					v[i][index]=ary[1];
					f=1;
					break;
				}
			}
			if(f==0)
			{
				int random= (int) ( Math.random() * (k+n - k ) + k);
				System.out.println("Replacement");
				for(int i=0;i<B;i++)
				{
					if( v[random][i].equals("")==false) {
						System.out.println(tag[random]+Integer.toBinaryString(i)+" , "+v[random][i]);
						v[random][i]="";
				}
				}	tag[random]=tagarr;
					v[random][index]=ary[1];
					
			
		}
	}
		
	}
	}
	 }}
	 
 System.out.println("Final Cache is:");
 int flag=0;
	for (int i=0;i<C;i++)
	{
		for(int j=0;j<B;j++)
		{
			if( v[i][j].equals("")==false) {flag=1;break;};
		}
	}
	
	if(flag==1)
	{
		for (int i=0;i<C;i++)
		{
			for(int j=0;j<B;j++)
			{
				if( v[i][j].equals("")==false)System.out.println(tag[i]+Integer.toBinaryString(i)+Integer.toBinaryString(j)+" , "+ v[i][j]);
				}
		}
	}
	else System.out.println("Cache is empty");
 
 

}

	
	
}