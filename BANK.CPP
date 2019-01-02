#include<iostream.h>
#include<conio.h>
#include<stdio.h>
#include<fstream.h>
#include<string.h>
class BANK
{
  char name[20],mob[12],type[20];
  int acc;
  float balance;
  public:
  void input()
  {
     gotoxy(20,10);
     cout<<"Enter Account holder name"<<endl;
     gotoxy(20,11);
     gets(name);
     gotoxy(20,13);
     cout<<"Enter mobile number"<<endl;
     gotoxy(20,14);
     gets(mob);
     gotoxy(20,16);
     cout<<"Enter the type of account"<<endl;
     gotoxy(20,17);
     gets(type);
     clrscr();
  }
  void details()
  {
     clrscr();
     gotoxy(20,10);
     cout<<name<<" Your account details are:";
     gotoxy(20,12);
     cout<<"Account number="<<acc;
     gotoxy(20,13);
     cout<<"Account type="<<type;
     gotoxy(20,14);
     cout<<"Balance in your account="<<balance;
     gotoxy(20,15);
     cout<<"Mobile number="<<mob;
     gotoxy(20,18);
     cout<<"Press any key to get bank functions";
     getch();
     clrscr();
  }

  BANK()
  {
     static int x=20170;
     acc=x;
     x++;
     balance=500;
  }
  int acnt()
  {
      acc++;
     return acc;
  }
  int match()
  {
     return acc;
  }
  void display()
  {
     clrscr();
     gotoxy(20,10);
     cout<<"Name of Account holder="<<name;
     gotoxy(20,12);
     cout<<"Account number="<<acc;
     gotoxy(20,14);
     cout<<"Mobile number="<<mob;
     gotoxy(20,16);
     cout<<"Account type="<<type;
     gotoxy(20,18);
     cout<<"Account balance="<<balance;
  }
  void deposit()
    {
     int bal;
     cout<<endl<<"enter the amount to be deposit"<<endl;
     cin>>bal;
     balance+=bal;
     clrscr();
    }
   void withdrawal()
   {
     int bal;
     gotoxy(20,11);
     cout<<"enter amount to be withdrwal"<<endl;
     gotoxy(20,12);
     cin>>bal;
     if(balance>=bal)
     balance-=bal;
     else
       {
	 gotoxy(20,13);
	 cout<<"you have insufficient balance"<<endl;
       }
     clrscr();
   }

   void name_fun()
   {
      char nam[20];
      gotoxy(20,10);
      cout<<"Enter your correct name";
      gotoxy(20,11);
      gets(nam);
      strcpy(name,nam);
      clrscr();
   }
};
int functions();
int main()
{
   clrscr();
   fstream file;
   ifstream file2;
   BANK obj;
   int y,r,i,test=0;
   int acc_n;
  do{
	test=0;
	r=functions();
	up:
       switch(r)
       {
	 case 32:
	 obj.input();
	 file>>obj.acnt();
	 obj.details();
	 file.open("bank.txt",ios::ate|ios::in|ios::out);
	 file.write((char*)&obj,sizeof(BANK));
	 file.close();
	 break;
	 case 36:
	 gotoxy(20,12);
	 cout<<"Enter your account number";
	 gotoxy(20,13);
	 cin>>acc_n;
	 file.open("bank.txt",ios::ate|ios::in|ios::out);
	 file.seekg(0,ios::beg);
	 while(file)
	 {
	    BANK obj2;
	    file.read((char*)&obj2,sizeof(BANK));
	    if(acc_n==obj2.match())
	    {
	       ++test;
	       obj2.display();
	    }
	}
	if(test==0)
	  {
	    gotoxy(20,15);
	    cout<<"You have entered wrong Account number"<<endl;
	  }
	file.close();
	cout<<endl<<"Enter 1 for BANK funtion"<<endl<<"Enter 2 for Exit"<<endl;
	cin>>y;
	if(y==1)
	{
	  clrscr();
	  r=functions();
	  goto up;        //go up for men
	}
	break;
	case 33:
	gotoxy(20,12);
	cout<<"Enter your account number";
	gotoxy(20,13);
	cin>>acc_n;
	file.open("bank.txt",ios::in|ios::out|ios::ate);
	file.seekg(0,ios::beg);
	while(file)
	{
	   BANK obj2;
	   file.read((char*)&obj2,sizeof(BANK));
	   if(acc_n==obj2.match())
	   {
	      ++test;
	      obj2.deposit();
	      long int pos=file.tellg();
	      file.seekp(pos-sizeof(BANK));
	      file.write((char*)&obj2,sizeof(BANK));
	      break;
	   }
	}
	if(test==0)
	  {
	   gotoxy(20,15);
	   cout<<"You have entered wrong Account number"<<endl;
	  }
	file.close();
	break;
	case 34:
	gotoxy(20,10);
	cout<<"Enter tour account number"<<endl;
	gotoxy(20,11);
	cin>>acc_n;
	file.open("bank.txt",ios::in|ios::out|ios::ate);
	file.seekg(0,ios::beg);
	while(file)
	{
	   BANK obj2;
	   file.read((char*)&obj2,sizeof(BANK));
	   if(acc_n==obj2.match())
	   {
	      ++test;
	      obj2.withdrawal();
	      long int pos=file.tellg();
	      file.seekp(pos-sizeof(BANK));
	      file.write((char*)&obj2,sizeof(BANK));
	      break;
	   }
	}
	if(test==0)
	  {
	    gotoxy(20,15);
	   cout<<"You have entered wrong Account number";
	  }
	file.close();
	break;
	case 35:
	cout<<endl<<"option available to correct your name"<<endl;
	cout<<endl<<"Enter your account number"<<endl;
	cin>>acc_n;
	file.open("bank.txt",ios::in|ios::out|ios::ate);
	file.seekg(0,ios::beg);
	while(file)
	{
	   BANK obj2;
	   file.read((char*)&obj2,sizeof(BANK));
	   if(acc_n==obj2.match())
	   {
	      ++test;
	      obj2.name_fun();
	      long int pos=file.tellg();
	      file.seekp(pos-sizeof(BANK));
	      file.write((char*)&obj2,sizeof(BANK));
	      break;
	   }
	}
	if(test==0)
	  {
	   cout<<endl<<"You have entered wrong Account number"<<endl;

	  }
	file.close();
	break;
     }     //switch close }
     if(y==2)
       {
	 break;            //loop break statement
       }
   }while(r!=37);  //loop close
   getch();
   return 0;
 }
  int functions()
   {
      int i,x=20,y=20;
       char s;
      gotoxy(x,y);
      gotoxy(19,10);
      cout<<"WELCOME TO THE BANK OF JMI"<<endl;
      gotoxy(20,12);
      cout<<"1.OPEN ACCOUNT";
      gotoxy(20,13);
      cout<<"2.DEPOSIT";
      gotoxy(20,14);
      cout<<"3.WITHDRAWAL";
      gotoxy(20,15);
      cout<<"4.UPDATE";
      gotoxy(20,16);
      cout<<"5.GET DETAILS";
      gotoxy(20,17);
      cout<<"6.EXIT"<<endl;
       for(i=0;i<200;i++)
       {  s=getch();
	  if(75==s)
	  {
	     gotoxy(x-1,y);
	     x--;
	  }
	  if(77==s)
	  {
	    gotoxy(x+1,y);
	     x++;
	  }
       if(80==s)
       {
	  gotoxy(x,y+1);
	  y++;
       }
      if(72==s)
      {
	gotoxy(x,y-1);
	y--;
      }
	if(13==s)
	{
	  clrscr();
	  break;
	}
     }
       //cout<<x<<y;
       return x+y;
   }