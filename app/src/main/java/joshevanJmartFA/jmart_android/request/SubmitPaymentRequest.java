package joshevanJmartFA.jmart_android.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SubmitPaymentRequest extends StringRequest {
    private static final String URL = "http://10.0.2.2:8080/payment/%s/submit";
    private final Map<String, String> params;
    public SubmitPaymentRequest(int id, Response.Listener<String> listener,
                                Response.ErrorListener errorListener)
    {
        super(Method.POST, String.format(URL,id), listener, errorListener);
        params = new HashMap<>();
    }
    public Map<String, String> getParams(){
        return params;
    }
}