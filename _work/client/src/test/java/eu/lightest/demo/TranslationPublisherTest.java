package eu.lightest.demo;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TranslationPublisherTest {
    private static final String TTA = "http://localhost:8081/ttaFM/mng/";
    private static TranslationPublisher publisher;

    @BeforeClass
    public static void init() {
        publisher = new TranslationPublisher(TTA);
    }

    @Test
    public void publishEIDAS_POF() {

        boolean status = publisher.publish(new TRANSLATION_EIDAS_POF());

        assertTrue("Publishing failed", status);
    }

    @Test
    public void publishPOF_EIDAS() {

        boolean status = publisher.publish(new TRANSLATION_POF_EIDAS());

        assertTrue("Publishing failed", status);
    }

    public static class TRANSLATION_EIDAS_POF extends TranslationPublisher.TRANSLATION {

        public TRANSLATION_EIDAS_POF() {
            super("EIDAS to POF", "{\n" +
                    "    \"agreement\": {\n" +
                    "        \"name\": \"pof-to-tr-esig\",\n" +
                    "        \"status\": \"active\",\n" +
                    "        \"creation-date\": \"2019-08-10\",\n" +
                    "        \"leaving-date\": \"2020-08-10\",\n" +
                    "        \"activation-date\": \"2019-08-10\",\n" +
                    "        \"source\": {\n" +
                    "            \"name\": \"federation.pof-demo.lightest.nlnetlabs.nl\",\n" +
                    "            \"level\": \"advanced\",\n" +
                    "            \"provider\": \"pof\",\n" +
                    "            \"params\": [{\n" +
                    "\t\t\t\t\t\"name\": \"serviceAdditionalServiceInfo\",\n" +
                    "\t\t\t\t\t\"value\": \"for_esignatures\"\n" +
                    "\t\t\t\t}\n" +
                    "\t\t\t]\n" +
                    "        },\n" +
                    "        \"target\": {\n" +
                    "            \"name\": \"tr-eidas-esignature.lightest.nlnetlabs.nl\",\n" +
                    "            \"provider\": \"tr\"\n" +
                    "        }\n" +
                    "    }\n" +
                    "}");
        }
    }

    public static class TRANSLATION_POF_EIDAS extends TranslationPublisher.TRANSLATION {

        public TRANSLATION_POF_EIDAS() {
            super("POF to EIDAS", "{\n" +
                    "    \"agreement\": {\n" +
                    "        \"name\": \"pof-to-eidas\",\n" +
                    "        \"status\": \"active\",\n" +
                    "        \"creation-date\": \"2019-08-10\",\n" +
                    "        \"leaving-date\": \"2020-08-10\",\n" +
                    "        \"activation-date\": \"2019-08-10\",\n" +
                    "        \"source\": {\n" +
                    "            \"name\": \"federation.pof-demo.lightest.nlnetlabs.nl\",\n" +
                    "            \"level\": \"advanced\",\n" +
                    "            \"provider\": \"pof\"\n" +
                    "        },\n" +
                    "        \"target\": {\n" +
                    "            \"name\": \"eidas.pof-demo.lightest.nlnetlabs.nl\",\n" +
                    "            \"provider\": \"eu\"\n" +
                    "        }\n" +
                    "    }\n" +
                    "}");
        }
    }
}
