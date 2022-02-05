import java.util.*;

public class Main
{
    public static void main(String[] args) {
        
        List<Integer> users = new ArrayList<>();
        users.add(1);
        users.add(2);
        users.add(3);
        List<Double> percentage = new ArrayList<>();
        percentage.add(25.0);
        percentage.add(25.0);
        percentage.add(50.0);
        
        ISplitwise splitwise = new Splitwise();
        splitwise.addUser( new User(1, "A"));
        splitwise.addUser( new User(2, "B"));
        splitwise.addUser( new User(3, "C"));
        Expense expense = new Expense(100, users, 1, new PercentageExpenseDivider(percentage));
        Expense expense2 = new Expense(200, users, 1, new PercentageExpenseDivider(percentage));
        splitwise.addExpense(expense);
        splitwise.addExpense(expense2);
        splitwise.printBalance(1);
        splitwise.printBalance(2);
        splitwise.printBalance(3);
    }
}

interface ISplitwise{
    
    void addExpense(Expense expense);
    
    void addUser(User user);
    
    void printBalance(Integer userId);
}

class Splitwise implements ISplitwise{
    
    Map<Integer, User> usersMap;
    
    Splitwise(){
        usersMap = new HashMap<>();
    }
    
    public void addUser(User user){
        usersMap.put(user.userId, user);
    }
    
    public void addExpense(Expense expense){
        if(expense.expenseDivider.validateExpenseDivision()){
           List<UserShare> userShareList = expense.expenseDivider.divideExpenses(expense.totalAmount, expense.users);
           User payee = usersMap.get(expense.payeeId);
           for(var userShare : userShareList){
               if(userShare.userId != expense.payeeId){
                   User user = usersMap.get(userShare.userId);
                   double newShare = user.balanceUsersList.getOrDefault(payee.userId, 0.0);
                    user.balanceUsersList.put(payee.userId, newShare + userShare.amount);
                    double newSharePayee = payee.balanceUsersList.getOrDefault(user.userId, 0.0);
                    payee.balanceUsersList.put(user.userId, newSharePayee - userShare.amount);
               }
           }
        }else
            System.out.println("Validation of percentage failed.");
    }
    
    public void printBalance(Integer userId){
        User user = usersMap.get(userId);
        user.printBalance();
    }
}

class Expense{
    double totalAmount;
    List<Integer> users;
    IExpenseDivider expenseDivider;
    Integer payeeId;
    
    Expense(double totalAmount, List<Integer> users, Integer payeeId, IExpenseDivider expenseDivider){
        this.totalAmount = totalAmount;
        this.users = users;
        this.payeeId = payeeId;
        this.expenseDivider = expenseDivider;
    }
}

interface IExpenseDivider{
    
    List<UserShare> divideExpenses(double totalAmount, List<Integer> users);
    
    boolean validateExpenseDivision();
}

class PercentageExpenseDivider implements IExpenseDivider{
    
    List<Double> percentage;
    
    PercentageExpenseDivider(List<Double> percentage){
        this.percentage = percentage;
    }
    
    public List<UserShare> divideExpenses(double totalAmount, List<Integer> users){
        List<UserShare> userShareList = new ArrayList<>();
        int i = 0;
        for(var userId : users){
            double share = totalAmount * (percentage.get(i++) / 100.0);
            UserShare userShare = new UserShare(share, userId);
            userShareList.add(userShare);
        }
        return userShareList;
    }
    
    public boolean validateExpenseDivision(){
        int sum = 0;
        for(Double val : percentage){
            if(val != null){
                sum += val;
            } 
        }
        return sum == 100.0;
    }
}

class UserShare{
    double amount;
    int userId;
    
    UserShare(double amount, int userId){
        this.userId = userId;
        this.amount = amount;
    }
}



class User{
    int userId;
    String firstName;
    Map<Integer, Double> balanceUsersList; 
    
    User(int userId, String firstName){
        this.userId = userId;
        this.firstName = firstName;
        this.balanceUsersList = new HashMap<>();
    }
    
    void printBalance(){
        for(var entry : balanceUsersList.entrySet()){
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
