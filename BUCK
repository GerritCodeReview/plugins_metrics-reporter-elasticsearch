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
  id = 'org.elasticsearch:metrics-elasticsearch-reporter:2.2.0',
  sha1 = '85d3e2f0123df56795e5d925ec2c7b4fd2556792',
  license = 'Apache2.0',
  deps = [ ':jackson-databind', ':jackson-module-afterburner' ],
)
maven_jar(
  name = 'jackson-databind',
  id = 'com.fasterxml.jackson.core:jackson-databind:2.6.5',
  sha1 = 'd50be1723a09befd903887099ff2014ea9020333',
  deps = [ ':jackson-core', ':jackson-annotations' ],
  license = 'Apache2.0',
)
maven_jar(
  name = 'jackson-module-afterburner',
  id = 'com.fasterxml.jackson.module:jackson-module-afterburner:2.6.5',
  sha1 = 'b0dcd6af98fe599aa98c59bef27cbabbba7bef51',
  deps = [ ':jackson-core', ':jackson-annotations' ],
  license = 'Apache2.0',
)
maven_jar(
  name = 'jackson-core',
  id = 'com.fasterxml.jackson.core:jackson-core:2.6.5',
  sha1 = '334369d7eff497f358b248c171dac0dd62c68f67',
  license = 'Apache2.0',
)
maven_jar(
  name = 'jackson-annotations',
  id = 'com.fasterxml.jackson.core:jackson-annotations:2.6.0',
  sha1 = 'a0990e2e812ac6639b6ce955c91b13228500476e',
  license = 'Apache2.0',
)

# this is required for bucklets/tools/eclipse/project.py to work
java_library(
  name = 'classpath',
  deps = [':metrics-reporter-elasticsearch__plugin'],
)

