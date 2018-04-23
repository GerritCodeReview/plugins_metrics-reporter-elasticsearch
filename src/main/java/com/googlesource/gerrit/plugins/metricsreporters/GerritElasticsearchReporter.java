// Copyright (C) 2015 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package com.googlesource.gerrit.plugins.metricsreporters;

import com.codahale.metrics.MetricRegistry;
import com.google.gerrit.common.Nullable;
import com.google.gerrit.extensions.annotations.Listen;
import com.google.gerrit.extensions.annotations.PluginName;
import com.google.gerrit.extensions.events.LifecycleListener;
import com.google.gerrit.server.config.CanonicalWebUrl;
import com.google.gerrit.server.config.PluginConfigFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import java.io.IOException;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.eclipse.jgit.lib.Config;
import org.elasticsearch.metrics.ElasticsearchReporter;

@Listen
@Singleton
public class GerritElasticsearchReporter implements LifecycleListener {
  private final ElasticsearchReporter reporter;

  @Inject
  public GerritElasticsearchReporter(
      PluginConfigFactory configFactory,
      @PluginName String pluginName,
      @Nullable @CanonicalWebUrl String gerritUrl,
      MetricRegistry registry) {
    Config config = configFactory.getGlobalPluginConfig(pluginName);
    String[] hosts = config.getStringList("elasticsearch", null, "host");
    if (hosts.length == 0) {
      hosts = new String[] {"localhost:9200"};
    }

    Map<String, Object> additionalFields = new HashMap<>(1);
    try {
      String hostName;
      if (gerritUrl != null) {
        URL u = new URL(gerritUrl);
        hostName = u.getHost() != null ? u.getHost() : getLocalHostName();
      } else {
        hostName = "Gerrit";
      }
      // Tools like Grafana has a gotcha related to how it treats
      // Hostnames, especially FQDN’s, and IP addresses (due to
      // their dots). When included in values for metric keys they will be
      // interpreted by Graphite as separate pieces.
      // To combat this we replace all dots in hostnames and IP addresses
      // with underscores.
      additionalFields.put("host", hostName.replace('.', '_'));
    } catch (MalformedURLException | UnknownHostException e) {
      throw new RuntimeException(e);
    }

    try {
      reporter =
          ElasticsearchReporter.forRegistry(registry)
              .hosts(hosts)
              .additionalFields(additionalFields)
              .build();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  private String getLocalHostName() throws UnknownHostException {
    return InetAddress.getLocalHost().getCanonicalHostName();
  }

  @Override
  public void start() {
    reporter.start(60, TimeUnit.SECONDS);
  }

  @Override
  public void stop() {
    reporter.stop();
  }
}
