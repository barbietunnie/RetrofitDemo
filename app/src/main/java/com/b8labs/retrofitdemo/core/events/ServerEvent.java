package com.b8labs.retrofitdemo.core.events;

import com.b8labs.retrofitdemo.core.ServerResponse;

/**
 * Created by badeyemi on 8/27/16.
 */

public class ServerEvent {
    private ServerResponse serverResponse;

    public ServerEvent(ServerResponse serverResponse) {
        this.serverResponse = serverResponse;
    }

    public ServerResponse getServerResponse() {
        return serverResponse;
    }

    public void setServerResponse(ServerResponse serverResponse) {
        this.serverResponse = serverResponse;
    }
}
