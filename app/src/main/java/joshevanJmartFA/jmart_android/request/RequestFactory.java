package joshevanJmartFA.jmart_android.request;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * This class used to create get by id, get by page, filter, payment request to parse from back end
 */
public class RequestFactory {
    private static final String URL_FORMAT_ID = "http://10.0.2.2:8080/%s/%d";
    private static final String URL_FORMAT_PAGE = "http://10.0.2.2:8080/%s/page?page=%s&pageSize=%s";
    private static final String URL_FORMAT_FILTER = "http://10.0.2.2:8080/product/getFiltered?page=%s&pageSize=%s&search=%s&minPrice=%s&maxPrice=%s&category=%s&conditionUsed=%s";
    private static final String URL_FORMAT_PAYMENT = "http://10.0.2.2:8080/payment/%d/%s?page=%s&pageSize=%s";
    public static StringRequest getById
            (
                    String parentURI,
                    int id,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_ID, parentURI, id);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }
    public static StringRequest getPage
            (
                    String parentURI,
                    int page,
                    int pageSize,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_PAGE, parentURI, page, pageSize);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }
    public static StringRequest getFiltered
            (
                    int page,
                    int pageSize,
                    String search,
                    int minPrice,
                    int maxPrice,
                    String category,
                    Boolean conditionUsed,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_FILTER, page, pageSize, search, minPrice,
        maxPrice, category, conditionUsed);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }
    public static StringRequest getPayment
            (
                    int id,
                    String paymentType,
                    int page,
                    int pageSize,
                    Response.Listener<String> listener,
                    Response.ErrorListener errorListener
            )
    {
        String url = String.format(URL_FORMAT_PAYMENT, id, paymentType, page, pageSize);
        return new StringRequest(Request.Method.GET, url, listener, errorListener);
    }
}
