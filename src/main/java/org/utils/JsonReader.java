package org.utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonReader {

    public static Object[][] getJSONData(String filePath, String dataSet) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        JSONObject jsonObject = new JSONObject(content);
        JSONArray jsonArray = jsonObject.getJSONArray(dataSet);

        Object[][] data = new Object[jsonArray.length()][3];  // Adjusted for 3 columns: phoneNumber, pin, and expectedResult

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            data[i][0] = obj.getString("phoneNumber");      // Phone number
            data[i][1] = obj.getString("pin");              // PIN
            data[i][2] = obj.getString("expectedResult");   // Expected result
        }

        return data;
    }
}