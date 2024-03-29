package com.udacity.webcrawler.json;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * A static utility class that loads a JSON configuration file.
 */
public final class ConfigurationLoader {

  private final Path path;

  /**
   * Create a {@link ConfigurationLoader} that loads configuration from the given {@link Path}.
   */
  public ConfigurationLoader(Path path) {
    this.path = Objects.requireNonNull(path);
  }

  /**
   * Loads configuration from this {@link ConfigurationLoader}'s path
   *
   * @return the loaded {@link CrawlerConfiguration}.
   */
  public CrawlerConfiguration load() {
    // TODO: Fill in this method.

    try(Reader reader = Files.newBufferedReader(path)){

      return read(reader);

    }catch(IOException e){

      System.out.print("There was an issue with the loading process");
      e.printStackTrace();
    }

    return new CrawlerConfiguration.Builder().build();
  }

  /**
   * Loads crawler configuration from the given reader.
   *
   * @param reader a Reader pointing to a JSON string that contains crawler configuration.
   * @return a crawler configuration
   */
  @JsonDeserialize(builder = CrawlerConfiguration.Builder.class)
  public static CrawlerConfiguration read(Reader reader) throws IOException{

    // This is here to get rid of the unused variable warning.
    Objects.requireNonNull(reader);
    // TODO: Fill in this method
    ObjectMapper objectMapper = new ObjectMapper();

    objectMapper.disable(JsonParser.Feature.AUTO_CLOSE_SOURCE);
    return objectMapper.readValue(reader, CrawlerConfiguration.Builder.class).build();
//
//    try {
//
//      final CrawlerConfiguration.Builder crawlerConfigurationBuilder =  objectMapper.readValue(reader, CrawlerConfiguration.Builder.class);
//
//      return crawlerConfigurationBuilder.build();
//
//    }catch (IOException e){
//
//      System.out.println("There was an issue with the JSON parsing");
//      e.printStackTrace();




//      return null;
    }
  }

