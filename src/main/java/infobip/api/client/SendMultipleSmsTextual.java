package infobip.api.client;

import infobip.api.config.Configuration;
import retrofit.*;
import retrofit.http.*;
import com.google.gson.GsonBuilder;
import retrofit.converter.GsonConverter;
import infobip.api.config.TimeoutClientProvider;
import infobip.api.model.sms.mt.send.SMSResponse;
import infobip.api.model.sms.mt.send.textual.SMSMultiTextualRequest;

/**
 * This is a generated class and is not intended for modification!
 */
public class SendMultipleSmsTextual {
    private final Configuration configuration;

    public SendMultipleSmsTextual(Configuration configuration) {
        this.configuration = configuration;
    }

    interface SendMultipleSmsTextualService {
        @POST("/sms/1/text/multi")
        SMSResponse execute(@Body SMSMultiTextualRequest bodyObject);
    }
    public SMSResponse execute(SMSMultiTextualRequest bodyObject) {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(configuration.getBaseUrl())
                .setRequestInterceptor(getRequestInterceptor())
                .setConverter(new GsonConverter(new GsonBuilder()
                						.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ")
                						.create()))
                .setClient(new TimeoutClientProvider(configuration))
                .build();
        SendMultipleSmsTextualService service = restAdapter.create(SendMultipleSmsTextualService.class);
        return service.execute(bodyObject);
    }

    private RequestInterceptor getRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                if (configuration != null && configuration.getAuthorizationHeader() != null) {
                    request.addHeader("Authorization", configuration.getAuthorizationHeader());
                    request.addHeader("User-Agent", "Java-Client-Library");
                }
            }
        };
    }
}