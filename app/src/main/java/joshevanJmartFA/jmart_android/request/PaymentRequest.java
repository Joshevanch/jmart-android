package joshevanJmartFA.jmart_android.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

import joshevanJmartFA.jmart_android.LoginActivity;
import joshevanJmartFA.jmart_android.MainActivity;

public class PaymentRequest extends StringRequest {
    private static final String URL = "http://10.0.2.2:8080/payment/create";
    private final Map<String, String> params;
    public PaymentRequest(String productCount, String shipmentAddress,
                          Response.Listener<String> listener, Response.ErrorListener errorListener)
    {
        super(Method.POST, URL, listener, errorListener);
        params = new HashMap<>();
        params.put("buyerId", String.valueOf(LoginActivity.getLoggedAccount().id));
        params.put("productId", String.valueOf(MainActivity.product.id));
        params.put("productCount", productCount);
        params.put("shipmentAddress", shipmentAddress);
        params.put("shipmentPlan", String.valueOf(MainActivity.product.shipmentPlans));
    }
    public Map<String, String> getParams(){
        return params;
    }

}
