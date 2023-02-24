package platform.controllers;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import platform.entries.CodeSnippet;

import java.util.Map;

//Utility class for parsing JSON data into CodeSnippet objects.
public class ApiUtils {

    // Parses a Map object representing a JSON object into a CodeSnippet object.
    //@param map the input Map object representing the JSON data
    //@return a CodeSnippet object created from the JSON data
    //@throws ResponseStatusException if the required data is missing or invalid
    public static CodeSnippet parseSnippetFromJSON(@NotNull Map<String, Object> map) {
        CodeSnippet snippet = new CodeSnippet(getStringByKey(map, "code"));
        snippet.setViewsLimit(getIntegerOrNullByKey(map, "viewsLimit"));
        Integer minutesLimit = getIntegerOrNullByKey(map, "minutesLimit");
        String header = getStringOrNullByKey(map, "header");
        if (minutesLimit != null) {
            snippet.setDeleteDate(snippet.getCreateDate().plusMinutes(minutesLimit));
        }
        if (header != null) {
            snippet.setHeader(header);
        }
        return snippet;
    }

    //Helper method to get a String value from a Map object by key.
    private static String getStringByKey(@NotNull Map<String, Object> map, @NotNull String key) {
        return getClassOrNullOrThrowByKey(map, key, String.class, false);
    }

    //Helper method to get a String value from a Map object by key
    // returns null if the value is missing
    private static String getStringOrNullByKey(@NotNull Map<String, Object> map, @NotNull String key) {
        return getClassOrNullOrThrowByKey(map, key, String.class, true);
    }

    //Helper method to get an Integer value from a Map object by key
    // returns null if the value is missing
    private static Integer getIntegerOrNullByKey(@NotNull Map<String, Object> map, @NotNull String key) {
        return getClassOrNullOrThrowByKey(map, key, Integer.class, true);
    }

    //Helper method to get a value of a given class from a Map object by key
    // returns null if the value is missing
    private static <T> T getClassOrNullOrThrowByKey(@NotNull Map<String, Object> map, @NotNull String key,
                                                    Class<T> type, boolean nullable) {
        Object value = map.get(key);
        if (value == null) {
            if (nullable) {
                return null;
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, key + " value can't be empty");
            }
        }
        if (value.getClass() == type) {
            return type.cast(value);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, key + " value should be " + type.toString());
    }
}