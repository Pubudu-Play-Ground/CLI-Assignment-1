import java.util.Scanner;

public class CLI_APP1 {
    private static final Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {

        final String clear = "\033[H\033[2J";

        final String Dashboard = "WELCOME TO SMART BANKING";
        final String newAccount = "CREATE NEW ACCOUNT";
        final String deposits = "DEPOSITS";
        final String withdrawal= "WITHDRAWAL";
        final String transfer= "TRANSFER";
        final String accountBalance= "CHECK ACCOUNT BALANCE";
        final String deleteAccount= "DELETE ACCOUNT";
        String screen = Dashboard; 

        String [] CustomerAccount=new String [0];
        String [] CustomerName = new String [0];
        double [] amount = new double[0];

        do{
            System.out.println(clear);
            System.out.println("-".repeat(100));
            System.out.printf("|%40s%30s%30s|\n"," ","\033[34:1m"+screen+"\033[0m"," ");
            System.out.println("-".repeat(100));

            switch (screen){
                case Dashboard:

                    System.out.println("\n[1] Create New Account");
                    System.out.println("[2] Deposit");
                    System.out.println("[3] Withdrawal");
                    System.out.println("[4] Transfer");
                    System.out.println("[5] Check Account Balance");
                    System.out.println("[6] Delete Account");
                    System.out.println("[7] Exit\n");
                    System.out.print("Enter your Option : ");
                    int input = scan.nextInt();
                    scan.nextLine();

                    switch (input){
                        case 1: screen=newAccount; break;
                        case 2: screen=deposits;break;
                        case 3: screen=withdrawal; break;
                        case 4: screen=transfer; break;
                        case 5: screen=accountBalance; break;
                        case 6: screen=deleteAccount; break;
                        case 7: System.exit(0);break;
                        default: continue;
                        
                    }
                    break;
                case newAccount:

                    String account = String.format("SDB-%05d", CustomerAccount.length+1);
                    System.out.println("Bank Account Number : "+account);
                    
                    String name;
                    loop1:
                    do{
                        System.out.print("Name : ");
                        name = scan.nextLine().strip();
                        if(name.isEmpty()){
                            System.out.println("Name can't be Empty");
                            continue;
                        }
                        for (int i = 0; i < name.length(); i++) {
                            if(!(Character.isLetter(name.charAt(i))||Character.isSpaceChar(name.charAt(i)))){
                                System.out.println("Invalid input");
                                continue loop1;
                            }
                        }
                        break;

                    }while(true);

                    String initialamount;
                    loop2:
                    do{
                        System.out.print("Initial Deposit : ");
                        initialamount = scan.nextLine().strip();
                        if(initialamount.isEmpty()){
                            System.out.println("Initial Deposit can't be empty");
                            continue;
                        }
                        for (int i = 0; i < initialamount.length(); i++) {
                            if(!(Character.isDigit(initialamount.charAt(i))||initialamount.charAt(i)=='.')){
                                System.out.println("Invalid input");
                                continue loop2;
                            }
                        }
                        if(Double.valueOf(initialamount)<=500.00){
                            System.out.println("Initial ammount should be greater than Rs. 500.00");
                            continue;
                        }
                        break;


                    }while(true);

                    String [] newCustomerAccount = new String[CustomerAccount.length+1];
                    String [] newCustomerName = new String[CustomerName.length+1];
                    double [] newAmount = new double[amount.length+1];

                    for (int i = 0; i < CustomerAccount.length; i++) {
                        newCustomerAccount[i]=CustomerAccount[i];
                        newCustomerName[i]=CustomerName[i];
                        newAmount[i]=amount[i];
                    }
                    newCustomerAccount[newCustomerAccount.length-1]=account;
                    newCustomerName[newCustomerName.length-1]=name;
                    newAmount[newAmount.length-1]=Double.valueOf(initialamount);

                    CustomerAccount=newCustomerAccount;
                    CustomerName=newCustomerName;
                    amount=newAmount;
                    System.out.println();

                    System.out.println(account+" : "+name +" has been created Successfully\n");

                    System.out.print("Do you want to add another Account [Y/N] : ");
                   
                    if (scan.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = Dashboard;
                    break;



                case deposits:  

                    String depositaccount;
                    boolean found;
                    int index=0;
                    loop3:
                    do{
                        found=true;
                        System.out.print("Enter Account number : ");
                        depositaccount = scan.nextLine().strip();
                        if(depositaccount.isEmpty()){
                            System.out.println("Account number can't be Empty");
                            continue;
                        } else if(!depositaccount.startsWith("SDB-")||depositaccount.length()!=9){
                            System.out.println("Invalid input");
                            continue;
                        }
                        for (int i = 4; i < depositaccount.length(); i++) {
                            if(!(Character.isDigit(depositaccount.charAt(i)))){
                                System.out.println("Invalid input");
                                continue loop3;
                            }
                        }
                        for (int i = 0; i < CustomerAccount.length; i++) {
                            if(depositaccount.equals(CustomerAccount[i])){
                                index=i;
                                found=false;
                                continue loop3;
                            }
                        }
                        System.out.println("Account not Found");
                        continue;
                        
                    }while(found);
                    
                    System.out.printf("Name : %s\n",CustomerName[index]);
                    System.out.printf("Current balance : Rs %.2f\n\n",amount[index]);
                    String depositAmmount;
                    loop4:
                    do{
                        System.out.print("Deposit Amount : ");
                        depositAmmount = scan.nextLine().strip();
                        if(depositAmmount.isEmpty()){
                            System.out.println("Deposit can't be empty");
                            continue;
                        }
                        for (int i = 0; i < depositAmmount.length(); i++) {
                            if(!(Character.isDigit(depositAmmount.charAt(i))||depositAmmount.charAt(i)=='.')){
                                System.out.println("Invalid input");
                                continue loop4;
                            }
                        }
                        if(Double.valueOf(depositAmmount)<=500.00){
                            System.out.println("Deposit should be greater than Rs. 500.00");
                            continue;
                        }
                        break;

                    }while(true);
                    amount[index]+=Double.valueOf(depositAmmount);
                    System.out.printf("\nNew Balance : Rs. %.2f\n",(amount[index]));

                    System.out.print("Do you want to continue [Y/N] : ");
                   
                    if (scan.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = Dashboard;
                    break;
                case withdrawal:
                
                    String withdrawalaccount;
                    boolean found2;
                    int index2=0;
                    loop5:
                    do{
                        found2=true;
                        System.out.print("Enter Account number : ");
                        withdrawalaccount = scan.nextLine().strip();
                        if(withdrawalaccount.isEmpty()){
                            System.out.println("Account number can't be Empty");
                            continue;
                        } else if(!withdrawalaccount.startsWith("SDB-")||withdrawalaccount.length()!=9){
                            System.out.println("Invalid input");
                            continue;
                        }
                        for (int i = 4; i < withdrawalaccount.length(); i++) {
                            if(!(Character.isDigit(withdrawalaccount.charAt(i)))){
                                System.out.println("Invalid input");
                                continue loop5;
                            }
                        }
                        for (int i = 0; i < CustomerAccount.length; i++) {
                            if(withdrawalaccount.equals(CustomerAccount[i])){
                                index2=i;
                                found2=false;
                                continue loop5;
                            }
                        }
                        System.out.println("Account not Found");
                        continue;
                        
                    }while(found2);

                    System.out.printf("Name : %s\n",CustomerName[index2]);
                    System.out.printf("Current balance : Rs %.2f\n\n",amount[index2]);

                    String withdrawalAmmount;
                    loop6:
                    do{
                        System.out.print("Withdrawal Amount : ");
                        withdrawalAmmount = scan.nextLine().strip();
                        if(withdrawalAmmount.isEmpty()){
                            System.out.println("withdrawal can't be empty");
                            continue;
                        }
                        for (int i = 0; i < withdrawalAmmount.length(); i++) {
                            if(!(Character.isDigit(withdrawalAmmount.charAt(i))||withdrawalAmmount.charAt(i)=='.')){
                                System.out.println("Invalid input");
                                continue loop6;
                            }
                        }
                        if(Double.valueOf(withdrawalAmmount)<=500.00){
                            System.out.println("Withdrawal should be greater than Rs. 500.00");
                            continue;
                        }
                        break;


                    }while(true);
                    amount[index2]-= Double.valueOf(withdrawalAmmount);
                    System.out.printf("New Balance : Rs. %.2f\n\n",amount[index2]);


                    System.out.print("Do you want to continue [Y/N] : ");
                   
                    if (scan.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = Dashboard;
                    break;

                case transfer:
                case accountBalance:
                case deleteAccount:  
                default:
                    System.exit(0);
            }
        
        } while(true);
        
    }
}
