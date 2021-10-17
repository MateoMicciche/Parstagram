package com.example.parstagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Post.class);
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("SWhl5BJXXoPPh3gO822ewh1R13bCwFwLpuITqbi0")
                .clientKey("Ef443WXB6j8Z1qDRX8X8Layn3x6BA6UA6c92NICg")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
