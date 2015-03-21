package com.computepatterns.apppatterns.osgi.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.lang.StringUtils;

import com.computepatterns.apppatterns.osgi.WeatherService;

/**
 * Weather service implementation. Connects to the provided end point using apache http client to
 * fetch the feed.
 *
 */
@Component(label = "Compute Patterns - Weather Service.",
    description = "Connects to the weather apis and fetches weather details.")
@Service
public class WeatherServiceImpl implements WeatherService {
  private static final Logger log = LoggerFactory.getLogger(WeatherServiceImpl.class);

  @Activate
  protected void activate(Map<String, String> config) {
    log.info("Weather Service - ACTIVATED");
  }

  @Override
  public String getWeatherFeed(String apiEndPoint) throws IOException {
    // Sanity check the arguments.
    if (StringUtils.isBlank(apiEndPoint)) {
      return StringUtils.EMPTY;
    }
    // Create a http client and hit the server.
    HttpClient httpClient = new HttpClient();
    GetMethod httpMethod = new GetMethod(apiEndPoint);
    // Return the response body if the request is successfully executed.
    if (httpClient.executeMethod(httpMethod) == HttpStatus.SC_OK) {
      log.trace("Successfully fetched data from the endpoint.");
      return httpMethod.getResponseBodyAsString();
    }
    log.trace("Connection not successful.");
    return StringUtils.EMPTY;
  }
}
