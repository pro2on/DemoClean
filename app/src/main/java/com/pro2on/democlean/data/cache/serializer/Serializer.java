package com.pro2on.democlean.data.cache.serializer;

import com.google.gson.Gson;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Date: 23.03.17
 * Time: 10:31
 * Created by pro2on in project GithubClean
 */

@Singleton
public class Serializer {


    private final Gson gson = new Gson();


    @Inject
    public Serializer() {}



    /**
     * Serialize an object to Json.
     *
     * @param object to serialize.
     */
    public String serialize(Object object, Class clazz) {
        return gson.toJson(object, clazz);
    }

    /**
     * Deserialize a json representation of an object.
     *
     * @param string A json string to deserialize.
     */
    public <T> T deserialize(String string, Class<T> clazz) {
        return gson.fromJson(string, clazz);
    }


}
