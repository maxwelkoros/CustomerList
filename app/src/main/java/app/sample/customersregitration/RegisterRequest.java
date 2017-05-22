package app.sample.customersregitration;

/**
 * Created by Geoffrey Koros on 4/7/2017.
 */

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "https://komaxwel.000webhostapp.com/Registers.php";
    private Map<String, String> params;

    public RegisterRequest(String fname, String oname, String adres, int cid, int nid, int mob, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("fname", fname);
        params.put("oname", oname);
        params.put("adres", adres);
        params.put("cid", cid + "");
        params.put("nid", nid + "");
        params.put("mob", mob + "");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
