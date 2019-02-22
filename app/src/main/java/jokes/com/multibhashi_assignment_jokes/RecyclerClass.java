package jokes.com.multibhashi_assignment_jokes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerClass extends AppCompatActivity {

    ArrayList<String> jokes_list = new ArrayList<String>();
    static boolean  lock  = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recycler_activity);

        final String url = getIntent().getStringExtra("url");
//        Toast.makeText(getApplicationContext(), url.toString(), Toast.LENGTH_SHORT).show();

        synchronized (this){
            get_data_from_volley(url);
        }




    }

    private void set_adapter_recycler() {
//        Toast.makeText(getApplicationContext(), Integer.toString(jokes_list.size()), Toast.LENGTH_SHORT).show();
        RecyclerView p_recycler = (RecyclerView)findViewById(R.id.recycler_view);
        p_recycler.setLayoutManager(new LinearLayoutManager(this));
        p_recycler.setAdapter(new Padapter(jokes_list, this));
    }

    private void get_data_from_volley(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, String.format(url), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                JSONObject jsonObject = null;
                JSONArray jokes_array = null;
                try {
                    jsonObject = new JSONObject(response);
//                    Toast.makeText(getApplicationContext(), jsonObject.toString(),Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "json error"+ e.toString(), Toast.LENGTH_SHORT).show();
                }

                try {
                    jokes_array = jsonObject.getJSONArray("value");
//                      Toast.makeText(getApplicationContext(), jokes_array.getString(5), Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), "json error"+ e.toString(), Toast.LENGTH_SHORT).show();
                }
                for(int j = 0; j< jokes_array.length(); j++){
                    String joke;
                    JSONObject obj = null;

                    try {
                        obj = jokes_array.getJSONObject(j);
                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "json error"+ e.toString(), Toast.LENGTH_SHORT).show();

                    }
                    try {
                        joke = obj.getString("joke");
                        joke = joke.replaceAll("&quot;", "\"");
                        jokes_list.add(joke);


                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), "json error"+ e.toString(), Toast.LENGTH_SHORT).show();
                    }

                }
                lock = true;
                set_adapter_recycler();




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"volleyError"+ error.toString(), Toast.LENGTH_SHORT).show();
                lock=true;
            }
        });

        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }


}
