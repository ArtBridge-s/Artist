package com.artbridge.artist;

//import com.artbridge.artist.ArtistApp;
//import com.artbridge.artist.config.AsyncSyncConfiguration;
//import com.artbridge.artist.config.EmbeddedKafka;
//import com.artbridge.artist.config.EmbeddedSQL;
//import java.lang.annotation.ElementType;
//import java.lang.annotation.Retention;
//import java.lang.annotation.RetentionPolicy;
//import java.lang.annotation.Target;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;

/**
 * Base composite annotation for integration tests.
 */
//@Target(ElementType.TYPE)
//@Retention(RetentionPolicy.RUNTIME)
//@SpringBootTest(classes = { ArtistApp.class, AsyncSyncConfiguration.class })
//@EmbeddedKafka
//@EmbeddedSQL
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public @interface IntegrationTest {
}
