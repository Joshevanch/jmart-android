package joshevanJmartFA.jmart_android.model;

public class Product extends Serializable{
    public int accountId;
    public ProductCategory category;
    public boolean conditionUsed;
    public double discount;
    public String name;
    public double price;
    public byte shipmentPlans;
    public int weight;
    public String toString(){
        return "Account Id: "+this.accountId+"Name: " +this.name+ "Weight: " +this.weight+ "Condition used: "+this.conditionUsed+"Price: "+this.price+"Discount: "+this.discount+"Category: "+this.category;
    }
}
