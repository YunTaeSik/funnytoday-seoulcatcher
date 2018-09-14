package todday.funny.seoulcatcher.server;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import todday.funny.seoulcatcher.model.messageModel.FcmError;
import todday.funny.seoulcatcher.model.messageModel.Message;
import todday.funny.seoulcatcher.model.messageModel.SendCallData;
import todday.funny.seoulcatcher.util.Keys;

public interface SeoulCatcherService {

    //FCM 보내기
    @POST("projects/funnytoday-seoulcatcher/messages:send")
    Observable<Message> call(
            @Header("Authorization") String serverAuth,
            @Header("Content-Type") String contentType,
            @Body SendCallData sendCallData
    );

    class Creator {
        public static SeoulCatcherService create() {
            Retrofit retrofit = new Retrofit.Builder()
                    .addCallAdapterFactory(
                            RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Keys.FCM_URL_API)
                    .build();
            return retrofit.create(SeoulCatcherService.class);
        }
    }
}
