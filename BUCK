include_defs('//bucklets/gerrit_plugin.bucklet')
include_defs('//lib/maven.defs')

gerrit_plugin(
  name = 'metrics-reporter-elasticsearch',
  srcs = glob(['src/main/java/**/*.java']),
  resources = glob(['src/main/resources/**/*']),
  deps = [
    '//lib/dropwizard:dropwizard-core',
    ':metrics-elasticsearch-reporters',
    ':jackson-databind',
    ':jackson-core'
  ],
  manifest_entries = [
    'Gerrit-PluginName: metrics-reporter-elasticsearch',
  ],
)

maven_jar(
  name = 'metrics-elasticsearch-reporters',
  id = 'org.elasticsearch:metrics-elasticsearch-reporter:2.0',
  sha1 = '399ff7b3378e94be017f475a114227acc41b7b31',
  license = 'Apache2.0',
  deps = [ ':jackson-databind', ':jackson-core' ],
)
maven_jar(
  name = 'jackson-databind',
  id = 'com.fasterxml.jackson.core:jackson-databind:2.2.3',
  sha1 = '03ae380888029daefb91d3ecdca3a37d8cb92bc9',
  deps = [ ':jackson-core', ':jackson-annotations' ],
  license = 'Apache2.0',
)
maven_jar(
  name = 'jackson-core',
  id = 'com.fasterxml.jackson.core:jackson-core:2.2.3',
  sha1 = '1a0113da2cab5f4c216b4e5e7c1dbfaa67087e14',
  license = 'Apache2.0',
)
maven_jar(
  name = 'jackson-annotations',
  id = 'com.fasterxml.jackson.core:jackson-annotations:2.2.3',
  sha1 = '0527fece4f23a457070a36c371a26d6c0208e1c3',
  license = 'Apache2.0',
)
# this is required for bucklets/tools/eclipse/project.py to work
java_library(
  name = 'classpath',
  deps = [':metrics-reporter-elasticsearch__plugin'],
)

