package eu.lightest.demo;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SchemePublisherTest {
    
    private static final String TSPA = "http://localhost:8080/tspa/api/v1/";
    private static SchemePublisher publisher;
    
    @BeforeClass
    public static void init() {
        publisher = new SchemePublisher(TSPA);
    }
    
    @Test
    public void publishEidas() {
        
        boolean statusEidas = publisher.publish(new SCHEME_EIDAS());
        
        assertTrue("Publishing failed", statusEidas);
    }
    
    @Test
    public void publishTrEsig() {
        
        boolean statusTrEsig = publisher.publish(new SCHEME_TUBITAK_ESIG());
        
        assertTrue("Publishing failed", statusTrEsig);
    }
    
    @Test
    public void publishPOF() {
        
        boolean statusPOF = publisher.publish(new SCHEME_POF());
        
        assertTrue("Publishing failed", statusPOF);
    }
    
    @Test
    public void xPrint() {
        SchemePublisher.printTable();
    }
    
    public static class SCHEME_TUBITAK_ESIG extends SchemePublisher.SCHEME {
        
        public SCHEME_TUBITAK_ESIG() {
            super("https://mindertestbed.org:8443/tta/TR_eIDAS_eSignature_2019-12-05.xml",
                    "TR-eSignature.lightest.nlnetlabs.nl",
                    "tr-eidas-esignature.lightest.nlnetlabs.nl");
        }
    }
    
    public static class SCHEME_POF extends SchemePublisher.SCHEME {
        
        public SCHEME_POF() {
            super("https://lightest.iaik.tugraz.at/testschemes/Pumpkin_Demo_TS_v0.3-signed.xml",
                    "company-ca.pof-demo.lightest.nlnetlabs.nl",
                    "federation.pof-demo.lightest.nlnetlabs.nl");
        }
    }
    
    public static class SCHEME_EIDAS extends SchemePublisher.SCHEME {
        
        public SCHEME_EIDAS() {
            super("https://ec.europa.eu/tools/lotl/eu-lotl.xml",
                    "eidas-ca.pof-demo.lightest.nlnetlabs.nl",
                    "eidas.pof-demo.lightest.nlnetlabs.nl");
        }
    }
}