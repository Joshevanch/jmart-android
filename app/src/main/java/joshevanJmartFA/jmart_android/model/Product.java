package joshevanJmartFA.jmart_android.model;

/**
 * This class contains product information, such as account id, product category, condition used
 * discount, name, price, shipment plans, weight and extends serializable so that the product
 * will have id that is serialized
 * @author Joshevan
 *
 */
public class Product extends Serializable{
    /**
     * Account id
     */
    public int accountId;
    /**
     * Product category
     */
    public ProductCategory category;
    /**
     * Product condition used
     */
    public boolean conditionUsed;
    /**
     * Product discount
     */
    public double discount;
    /**
     * Product name
     */
    public String name;
    /**
     * Product price
     */
    public double price;
    /**
     * Product shipment plan
     */
    public byte shipmentPlans;
    /**
     * Product weight
     */
    public int weight;
    /**
     * This method override toString
     */
    @Override
    public String toString(){
        return this.name;
    }
}
