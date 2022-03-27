package spinoza.api;

/*******************************************
 * File Name: PixabayAPI.java
 * Purpose: get images using PixabayApi
 * Programmer: Gabriel.Espinoza
 * Last Update: 12/03/2020
 *
*******************************************/

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class PixabayAPI {
	private static ArrayList<String> thumbnailsArray;
	private static String selectedImage;

	public static void searchImages(String search) throws IOException {

		String API_KEY = "enter_your_pixabay_api_key";

		String URL = "https://pixabay.com/api/?key=" + API_KEY + "&q=" + search + "&image_type=all&min_height=300&per_page=10"; //URLEncoder.encode(Search);

	    // Connect to the URL using java's native library
	    URL url = new URL(URL);
	    HttpURLConnection request = (HttpURLConnection) url.openConnection();
	    request.connect();

	    // Convert to a JSON object to print data
	    JsonParser jparser = new JsonParser(); //from gson
	    try{
	    JsonElement jelement = jparser.parse(new InputStreamReader((InputStream) request.getContent())); //Convert the input stream to a json element
	    JsonObject jobject = jelement.getAsJsonObject();
	    System.out.println(jobject);
        JsonArray jsonArray = (JsonArray)jobject.get("hits");
		// prints all values and keys from array RAW
        //System.out.println("ARRAY"+tsmresponse.get(1));

        // ArrayList to store values
        thumbnailsArray = new ArrayList<String>();
        ArrayList<String> imageArray = new ArrayList<String>();

        // iterate each
        for(int i=0; i<jsonArray.size(); i++){
        	// convert elements of array into JsonObject
        	JsonObject jsson = jsonArray.get(i).getAsJsonObject();
			// get any value of new JsonObject
        	// System.out.println(jsson.get("contentUrl"));
        	// Add values to Array
        	thumbnailsArray.add(jsson.get("previewURL").toString());
        	imageArray.add(jsson.get("webformatURL").toString());
        }
	    } catch(IOException e){
	    	JOptionPane.showMessageDialog(null, "No images found! try another word");
	    }
	} // end main method

	// get images from thumbnailsArray
	public static String getImages(int index){
		selectedImage = thumbnailsArray.get(index).replaceAll("\"", "");
		return selectedImage;
	}

	// set selected image
	public static void setSelectedImage(String selectedImage){
		PixabayAPI.selectedImage = selectedImage;
	}

	// return selected image
	public static String getSelectedImage(){
		return selectedImage;
	}

	public static boolean isEmpty() {
		return thumbnailsArray.isEmpty();
	}
} // end class
