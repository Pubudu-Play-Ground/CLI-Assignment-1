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
                    String transferfrom;
                    boolean found3;
                    int index3=0;
                    loop7:
                    do{
                        found3=true;
                        System.out.print("Transfer from Account number : ");
                        transferfrom = scan.nextLine().strip();
                        if(transferfrom.isEmpty()){
                            System.out.println("Account Number can't be Empty");
                            continue;
                        } else if(!transferfrom.startsWith("SDB-")||transferfrom.length()!=9){
                            System.out.println("Invalid input");
                            continue;
                        }
                        for (int i = 4; i < transferfrom.length(); i++) {
                            if(!(Character.isDigit(transferfrom.charAt(i)))){
                                System.out.println("Invalid input");
                                continue loop7;
                            }
                        }
                        for (int i = 0; i < CustomerAccount.length; i++) {
                            if(transferfrom.equals(CustomerAccount[i])){
                                index3=i;
                                found3=false;
                                continue loop7;
                            }
                        }
                        System.out.println("Account not Found");
                        continue;
                        
                    }while(found3);
                    System.out.println("Account name : "+CustomerName[index3]);
                    System.out.println();

                    String transferto;
                    boolean found4;
                    int index4=0;
                    loop8:
                    do{
                        found4=true;
                        System.out.print("Transfer to Account number : ");
                        transferto = scan.nextLine().strip();
                        if(transferto.isEmpty()){
                            System.out.println("Account Number can't be Empty");
                            continue;
                        } else if(!transferto.startsWith("SDB-")||transferto.length()!=9){
                            System.out.println("Invalid input");
                            continue;
                        }
                        for (int i = 4; i < transferto.length(); i++) {
                            if(!(Character.isDigit(transferto.charAt(i)))){
                                System.out.println("Invalid input");
                                continue loop8;
                            }
                        }
                        for (int i = 0; i < CustomerAccount.length; i++) {
                            if(transferto.equals(CustomerAccount[i])){
                                index4=i;
                                found4=false;
                                continue loop8;
                            }
                        }
                        System.out.println("Account not Found");
                        continue;
                        
                    }while(found4);
                    System.out.println("Account name : "+CustomerName[index4]);
                    System.out.println();
                    System.out.printf("From A/C balance : %.2f\n",amount[index3]);
                    System.out.printf("To A/C balance : %.2f\n\n",amount[index4]);

                    String transferAmmount;
                    loop9:
                    do{
                        System.out.print("Transfer Amount : ");
                        transferAmmount = scan.nextLine().strip();
                        if(transferAmmount.isEmpty()){
                            System.out.println("transfer amount can't be empty");
                            continue;
                        }
                        for (int i = 0; i < transferAmmount.length(); i++) {
                            if(!(Character.isDigit(transferAmmount.charAt(i))||transferAmmount.charAt(i)=='.')){
                                System.out.println("Invalid input");
                                continue loop9;
                            }
                        }
                        if(Double.valueOf(transferAmmount)<=100.00){
                            System.out.println("Transfer amount should be greater than Rs. 100.00");
                            continue;
                        }
                        break;


                    }while(true);

                    amount[index3]-=(Double.valueOf(transferAmmount))*(1.02);
                    amount[index4]+=Double.valueOf(transferAmmount);

                    System.out.printf("\nAccount %s new balance : %.2f\n",transferfrom,amount[index3]);
                    System.out.printf("Account %s new balance : %.2f\n\n",transferto,amount[index4]);

                    System.out.print("Do you want to continue [Y/N] : ");
                
                    if (scan.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = Dashboard;
                    break;
                    
                case accountBalance:
                    String detailsacc;
                    boolean found5;
                    int index5=0;
                    loop10:
                    do{
                        found5=true;
                        System.out.print("Account number : ");
                        detailsacc = scan.nextLine().strip();
                        if(detailsacc.isEmpty()){
                            System.out.println("Account Number can't be Empty");
                            continue;
                        } else if(!detailsacc.startsWith("SDB-")||detailsacc.length()!=9){
                            System.out.println("Invalid input");
                            continue;
                        }
                        for (int i = 4; i < detailsacc.length(); i++) {
                            if(!(Character.isDigit(detailsacc.charAt(i)))){
                                System.out.println("Invalid input");
                                continue loop10;
                            }
                        }
                        for (int i = 0; i < CustomerAccount.length; i++) {
                            if(detailsacc.equals(CustomerAccount[i])){
                                index5=i;
                                found5=false;
                                continue loop10;
                            }
                        }
                        System.out.println("Account not Found");
                        continue;
                        
                    }while(found5);
                    System.out.printf("Name : %s\n",CustomerName[index5]);
                    System.out.printf("Current Account balance : %.2f\n\n",amount[index5]);

                    System.out.printf("Available balance for withdrawal : %s\n\n",amount[index5]-500.00);

                    System.out.print("Do you want to continue [Y/N] : ");
                
                    if (scan.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = Dashboard;
                    break;

                    
                case deleteAccount:  
                    String deleteacc;
                    boolean found6;
                    int index6=0;
                    loop11:
                    do{
                        found6=true;
                        System.out.print("Account number : ");
                        deleteacc = scan.nextLine().strip();
                        if(deleteacc.isEmpty()){
                            System.out.println("Account Number can't be Empty");
                            continue;
                        } else if(!deleteacc.startsWith("SDB-")||deleteacc.length()!=9){
                            System.out.println("Invalid input");
                            continue;
                        }
                        for (int i = 4; i < deleteacc.length(); i++) {
                            if(!(Character.isDigit(deleteacc.charAt(i)))){
                                System.out.println("Invalid input");
                                continue loop11;
                            }
                        }
                        for (int i = 0; i < CustomerAccount.length; i++) {
                            if(deleteacc.equals(CustomerAccount[i])){
                                index6=i;
                                found6=false;
                                continue loop11;
                            }
                        }
                        System.out.println("Account not Found");
                        continue;
                        
                    }while(found6);
                    System.out.printf("Name : %s\n",CustomerName[index6]);
                    System.out.printf("Current Account balance : %.2f\n\n",amount[index6]);

                    System.out.print("Are you sure to Delete [Y/N] : ");
                
                    if (scan.nextLine().strip().toUpperCase().equals("Y")){
                        String [] deleteAcc = new String[CustomerAccount.length-1];
                        String [] deletename = new String[CustomerName.length-1];
                        double [] deleteamonut = new double[amount.length-1];

                        for (int i = 0; i < CustomerAccount.length; i++) {
                            if(i<index6){
                                deleteAcc[i]=CustomerAccount[i];
                                deletename[i]=CustomerName[i];
                                deleteamonut[i]=amount[i];
                            } else if(i>index6){
                                deleteAcc[i-1]=CustomerAccount[i];
                                deletename[i-1]=CustomerName[i];
                                deleteamonut[i-1]=amount[i];
                            }
                        }
                        CustomerAccount=deleteAcc;
                        CustomerName=deletename;
                        amount=deleteamonut;


                    } else{
                        screen = Dashboard;
                        break;
                    }
                    System.out.println("Deleted Successfully.. \n");
                    System.out.print("Do you want to continue [Y/N] : ");
                
                    if (scan.nextLine().strip().toUpperCase().equals("Y")) continue;
                    screen = Dashboard;
                    break;
                    
                default:
                    System.exit(0);
            }
        
        } while(true);
        
    }
}
