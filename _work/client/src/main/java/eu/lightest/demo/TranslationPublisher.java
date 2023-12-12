package eu.lightest.demo;

import eu.lightest.verifier.wrapper.HTTPSHelper;
import okhttp3.*;
import org.apache.log4j.Logger;

import java.io.IOException;

public class TranslationPublisher {
    private static Logger logger = Logger.getLogger(TranslationPublisher.class);
    private final String TTA;

    public TranslationPublisher(String tta) {
        TTA = tta;
    }

    public boolean publish(TRANSLATION translation) {
        logger.info("Publishing Translation " + translation.TRANSLATION + " ...");

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(TTA + "rsc/translation/")
                .post(RequestBody.create(translation.DATA, MediaType.parse("application/json")))
                .build();

        try (Response response = client.newCall(request).execute()) {
            if(response.code() == 200) {
                logger.info("Translation published: " + translation.TRANSLATION);
                logger.info(" holding data: " + translation.DATA);
                return true;
            } else {
                logger.error("Publishing failed for Translation " + translation.TRANSLATION);
                logger.error(response.body().string());
                logger.error(response.code());
            }
        } catch(IOException e) {
            logger.error("", e);
        }

        return false;
    }

    public  static abstract class TRANSLATION {
        public final String TRANSLATION;

        public final String DATA;

        public TRANSLATION(String translation, String data) {
            TRANSLATION = translation;
            DATA = data;
        }
    }
}
