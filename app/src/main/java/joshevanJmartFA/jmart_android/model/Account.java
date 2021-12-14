package joshevanJmartFA.jmart_android.model;

/**
 * This class stores user account information, such as email, password, name, balance
 * and extends serializable so that the account will have id that is serialized
 * @author Joshevan
 * @version 1.0
 */
public class Account extends Serializable {
    /**
     * Account balance
     */
    public double balance;
    /**
     * Account email
     */
    public String email;
    /**
     * Account name
     */
    public String name;
    /**
     * Account password
     */
    public String password;
    /**
     * Account store
     */
    public Store store;
}
