package sw7.cornfield;

import android.os.AsyncTask;
import android.text.Html;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

class DownloadWebPageTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... urls) {

        //Create HttpClient and fetch data from url into InputStream
        String response = "";
        for (String url : urls) {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(url);
            try {
                HttpResponse execute = client.execute(httpGet);
                InputStream content = execute.getEntity().getContent();

                BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                //Ensure string is empty before attempted read
                String s = "";
                while ((s = buffer.readLine()) != null) {
                    //Add line to result string
                    response += s;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    private String _websiteData;

    @Override
    protected void onPostExecute(String result) {
        // todo: uncomment code and find a meaningful way to output the data
        //TextView six = (TextView) findViewById(R.id.six);
        _websiteData = Html.fromHtml(result).toString();
    }

    public String getWebsiteData()
    {
        return _websiteData;
    }
}
