package com.uber.service.bookingservice.configs;

import com.netflix.discovery.EurekaClient;
import com.uber.service.bookingservice.apis.LocationServiceApi;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfig {

    private final EurekaClient eurekaClient;

    public RetrofitConfig(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    private String getServiceUrl(String serviceName){
        return eurekaClient.getNextServerFromEureka(serviceName, false).getHomePageUrl();
    }

    @Bean
    public LocationServiceApi locationServiceApi() {
        return new Retrofit.Builder()
                .baseUrl(getServiceUrl("LOCATIONSERVICE"))
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build()
                .create(LocationServiceApi.class);
    }
}
