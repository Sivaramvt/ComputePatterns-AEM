package com.computepatterns.apppatterns.osgi;

import java.io.IOException;
import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Demonstration of osgi Component Vs Service. More details: http://www.computepatterns.com/76/osgi-component-vs-service-in-aem/
 **/
@Component(
    label = "Compute Patterns - Weather details printing osgi component.",
    description = "Sample OSGi component that uses thread and a OSGi service to print weather details in log.",
    immediate = true)
public class WeatherPrintingComponent {
  private static final Logger log = LoggerFactory.getLogger(WeatherPrintingComponent.class);

  /* Yahoo weather api end point */
  private static final String weatherApiEndpoint =
      "http://weather.yahooapis.com/forecastrss?p=80020&u=f";

  @Reference
  WeatherService weatherService;

  @Activate
  protected void activate(Map<String, String> config) {
    log.info("Weather printing component - activiated");
    // Set up a thread which wakes up every 5s and make a make a service call to fetch weather info
    // and print it in the log.
    Runnable task = () -> {
      try {
        while (!Thread.currentThread().isInterrupted()) {
          Thread.sleep(5000);
          try {
            log.info(weatherService.getWeatherFeed(weatherApiEndpoint));
          } catch (IOException e) {
            log.error("Unable to get weather details.", e);
          }
        }
      } catch (InterruptedException e) {
        log.error("Weather printing thread interrupted", e);
      }
    };

    Thread weatherThread = new Thread(task);
    weatherThread.setName("Compute Patterns - Weather printing");
    weatherThread.start();
  }
}
