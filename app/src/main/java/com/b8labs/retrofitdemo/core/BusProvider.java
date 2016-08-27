package com.b8labs.retrofitdemo.core;

import com.squareup.otto.Bus;

/**
 * Created by badeyemi on 8/27/16.
 */

public class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }

    public BusProvider() {}
}
