package com.computepatterns.apppatterns.osgi;

import java.util.Map;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.ConfigurationPolicy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(label = "Compute Patterns - OSGi component that is active only in specific run modes.",
    description = "OSGi component which works only in specific run mode (say, publish run mode).",
    policy = ConfigurationPolicy.REQUIRE, immediate = true)
/**
 * Sample OSGi component which works only in specific run mode. When we make component 'policy' to 'REQUIRE', 
 * OSGi container expects corresponding configuration object (osgi:Config node) to become satisfied. 
 * If we define the sling:OsgiConfig node under 'config.publish' folder, we could get this component 
 * active in 'publish' only run mode and 'unsatisfied' in all other run modes.
 */
public class RunmodeSpecificComponent{
  private static final Logger log = LoggerFactory.getLogger(RunmodeSpecificComponent.class);

  @Activate
  protected void activate(Map<String, String> config) {
    log.info("RunmodeAwareComponent ACTIVATED");
  }

}
