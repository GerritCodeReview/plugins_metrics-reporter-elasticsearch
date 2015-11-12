include_defs('//bucklets/gerrit_plugin.bucklet')
include_defs('//lib/maven.defs')

gerrit_plugin(
  name = 'metrics-reporter-elasticsearch',
  srcs = glob(['src/main/java/**/*.java']),
  resources = glob(['src/main/resources/**/*']),
  deps = [
    '//lib/dropwizard:dropwizard-core',
    ':metrics-graphite'
  ],
  manifest_entries = [
    'Gerrit-PluginName: metrics-reporter-elasticsearch',
  ],
)

maven_jar(
  name = 'metrics-graphite',
  id = 'org.elasticsearch:metrics-elasticsearch-reporter:2.0',
  sha1 = '399ff7b3378e94be017f475a114227acc41b7b31',
  license = 'Apache2.0',
)

# this is required for bucklets/tools/eclipse/project.py to work
java_library(
  name = 'classpath',
  deps = [':metrics-reporter-elasticsearch__plugin'],
)

