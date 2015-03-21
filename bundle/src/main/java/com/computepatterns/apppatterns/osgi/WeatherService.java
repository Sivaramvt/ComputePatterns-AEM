package com.computepatterns.apppatterns.osgi;

import java.io.IOException;

/**
 * Service to provide weather details.
 */
public interface WeatherService {

  /**
   * Get weather feed using given end point.
   * 
   * @param apiEndPoint Url end point to hit and get the weather feed. Example - Yahoo weather end
   *        point.
   * @return Weather feed in xml format.
   * @throws IOException Exception in connecting to the url.
   */
  String getWeatherFeed(String apiEndPoint) throws IOException;
}
