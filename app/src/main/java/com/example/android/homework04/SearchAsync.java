package com.example.android.homework04;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class SearchAsync extends AsyncTask<String, Integer, ArrayList<Recipe>> {

    ArrayList<String>arrayList;
    String dishName;
    static String DISH_KEY = "q";
    static String INGREDIANT_KEY = "i";
    String url = "http://www.recipepuppy.com/api/";
    SearchInterface searchInterface;

    public SearchAsync(Fragment searchInterface, ArrayList<String> arrayList, String dishName) {
        this.searchInterface = (SearchInterface) searchInterface;
        this.arrayList = arrayList;
        arrayList.remove(arrayList.size()-1);
        this.dishName = dishName;
    }

    @Override
    protected ArrayList<Recipe>  doInBackground(String... strings) {
        RequestParameter requestParameter = new RequestParameter();
        requestParameter.addParamters(DISH_KEY, dishName);

        String arrayString = arrayList.toString().replace("[", "")
                .replace("]", "").replace(", ", ",").trim();
        requestParameter.addParamters(INGREDIANT_KEY, arrayString);

        String encodedURL = requestParameter.getEncordedURL(url);

        Log.d("hi " , "hellooooooooo" +encodedURL);

        HttpURLConnection connection = null;
        ArrayList<Recipe> result = new ArrayList<>();
        String urlString = null;
        try {
            URL url = new URL(encodedURL);
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
        searchInterface.handleRecipeResult(recipes);
    }



    public  interface SearchInterface{
        void handleRecipeResult(ArrayList<Recipe> music);
    }
}
