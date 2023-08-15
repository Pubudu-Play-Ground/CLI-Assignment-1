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
                case withdrawal:
                case transfer:
                case accountBalance:
                case deleteAccount:  
                default:
                    System.exit(0);
            }
        
        } while(true);
        
    }
}
