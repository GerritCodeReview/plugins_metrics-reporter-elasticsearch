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

import com.google.gerrit.extensions.annotations.Listen;
import com.google.gerrit.extensions.annotations.PluginName;
import com.google.gerrit.extensions.events.LifecycleListener;
import com.google.gerrit.server.config.PluginConfigFactory;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import com.codahale.metrics.MetricRegistry;

import org.eclipse.jgit.lib.Config;
import org.elasticsearch.metrics.ElasticsearchReporter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Listen
@Singleton
public class GerritElasticsearchReporter implements LifecycleListener {
 private final ElasticsearchReporter reporter;

  @Inject
  public GerritElasticsearchReporter(
      PluginConfigFactory configFactory,
      @PluginName String pluginName,
      MetricRegistry registry) {
    Config config = configFactory.getGlobalPluginConfig(pluginName);
    String[] hosts = config.getStringList("elasticsearch", null, "host");
    if (hosts.length == 0) {
        hosts = new String[] { "localhost:9200" };
    }
    try {
      reporter = ElasticsearchReporter.forRegistry(registry)
          .hosts(hosts)
          .build();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
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
