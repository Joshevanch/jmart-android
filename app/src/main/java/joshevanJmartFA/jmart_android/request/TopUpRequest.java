package joshevanJmartFA.jmart_android.request;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * This class used to create top up account request to parse from back end
 */
public class TopUpRequest extends StringRequest {
    private static final String URL = "http://10.0.2.2:8080/account/%s/topUp";
    private final Map<String, String> params;
    public TopUpRequest(int id, String balance, Response.Listener<String> listener,
                                Response.ErrorListener errorListener)
    {
        super(Method.POST, String.format(URL,id), listener, errorListener);
        params = new HashMap<>();
        params.put("balance", balance);
    }
    public Map<String, String> getParams(){
        return params;
    }
}
