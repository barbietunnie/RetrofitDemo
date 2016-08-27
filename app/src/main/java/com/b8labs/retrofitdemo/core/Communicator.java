package com.b8labs.retrofitdemo.core;

import android.util.Log;

import com.b8labs.retrofitdemo.core.events.ErrorEvent;
import com.b8labs.retrofitdemo.core.events.ServerEvent;
import com.squareup.otto.Produce;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by badeyemi on 8/27/16.
 */

public class Communicator {
    public static final String TAG = Communicator.class.getSimpleName();
    public static final String SERVER_URL = "https://somesite.com/api/retrofit/";

    public void loginPost(String username, String password) {
        RestAdapter adapter = new RestAdapter.Builder()
                                    .setEndpoint(SERVER_URL)
                                    .setLogLevel(RestAdapter.LogLevel.FULL)
                                    .build();

        Interface communicatorInterface = adapter.create(Interface.class);
        Callback<ServerResponse> callback = new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse serverResponse, Response response) {
                if(serverResponse.getResponseCode() == 0) {
                    BusProvider.getInstance().post(produceServerEvent(serverResponse));
                } else {
                    BusProvider.getInstance().post(produceErrorEvent(
                            serverResponse.getResponseCode(),
                            serverResponse.getMessage()));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if(error != null) {
                    Log.e(TAG, error.getMessage());
                    error.printStackTrace();
                }

                BusProvider.getInstance().post(produceErrorEvent(-200, error.getMessage()));
            }
        };

        communicatorInterface.postData("login", username, password, callback);
    }

    public void loginGet(String username, String password) {
        RestAdapter adapter = new RestAdapter.Builder()
                                            .setEndpoint(SERVER_URL)
                                            .setLogLevel(RestAdapter.LogLevel.FULL)
                                            .build();
        Interface communicatorInterface = adapter.create(Interface.class);
        Callback<ServerResponse> callback = new Callback<ServerResponse>() {
            @Override
            public void success(ServerResponse response, Response response2) {
                if(response.getResponseCode() == 0) {
                    BusProvider.getInstance().post(produceServerEvent(response));
                } else {
                    BusProvider.getInstance().post(produceErrorEvent(
                                                                    response.getResponseCode(),
                                                                    response.getMessage()));
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if(error != null) {
                    Log.e(TAG, error.getMessage());
                    error.printStackTrace();
                }

                BusProvider.getInstance().post(produceErrorEvent(-200, error.getMessage()));
            }
        };
        communicatorInterface.getData("login", username, password, callback);
    }

    @Produce
    public ServerEvent produceServerEvent(ServerResponse response) {
        return new ServerEvent(response);
    }

    @Produce
    public ErrorEvent produceErrorEvent(int errorCode, String errorMsg) {
        return new ErrorEvent(errorCode, errorMsg);
    }
}
