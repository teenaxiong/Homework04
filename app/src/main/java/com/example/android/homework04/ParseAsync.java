package com.example.android.homework04;

import android.content.Context;
import android.os.AsyncTask;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ParseAsync extends AsyncTask<String, Integer, ArrayList<Recipe>> {

    ParseInterface parseInterface;
    public ParseAsync(ParseInterface parseInterface){
        this.parseInterface = parseInterface;
    }
    @Override
    protected ArrayList<Recipe> doInBackground(String... strings) {
        HttpURLConnection connection = null;
        ArrayList<Recipe> result = new ArrayList<>();
        String urlString = null;
        try {
            URL url = new URL(strings[0]);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                urlString = IOUtils.toString(connection.getInputStream(), "UTF8");
                JSONObject root = new JSONObject(urlString);
                JSONArray rootArray = root.getJSONArray("results");
                for(int x = 0; x<rootArray.length(); x++){
                    JSONObject resultObject = rootArray.getJSONObject(x);

                    Recipe recipe = new Recipe();
                    if(resultObject.has("title")){
                        recipe.setTitle(resultObject.getString("title"));
                    }else recipe.setTitle("Unknown Title Name");

                    if(resultObject.has("href")){
                        recipe.setHref(resultObject.getString("href"));
                    }else     recipe.setHref(resultObject.getString(null));

                    if(resultObject.has("ingredients")){
                        recipe.setIngredients(resultObject.getString("ingredients"));
                    }else recipe.setIngredients(resultObject.getString("No Ingredients"));


                    if(resultObject.has("thumbnail")){
                        recipe.setThumbnail(resultObject.getString("thumbnail"));
                    }else  recipe.setThumbnail(resultObject.getString("n/a"));

                    result.add(recipe);
                    publishProgress(x);
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            connection.disconnect();
        }
        return result;
    }

    @Override
    protected void onPostExecute(ArrayList<Recipe> recipes) {
         //getSupportFragmentManager().beginTransaction().add(R.id.container, new SearchFragment(), "SearchFragment").commit();
        parseInterface.handleRecipeResult(recipes);


    }


    public  interface ParseInterface{
         void handleRecipeResult(ArrayList<Recipe> music);
    }

}
