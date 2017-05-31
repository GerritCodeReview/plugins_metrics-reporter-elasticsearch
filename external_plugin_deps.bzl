load("//tools/bzl:maven_jar.bzl", "maven_jar")

def external_plugin_deps():
  maven_jar(
    name = 'metrics_elasticsearch_reporters',
    artifact = 'org.elasticsearch:metrics-elasticsearch-reporter:2.2.0',
    sha1 = '85d3e2f0123df56795e5d925ec2c7b4fd2556792',
    deps = [
      '@jackson_databind//jar',
      '@jackson_module_afterburner//jar',
    ],
  )

  maven_jar(
    name = 'jackson_databind',
    artifact = 'com.fasterxml.jackson.core:jackson-databind:2.6.5',
    sha1 = 'd50be1723a09befd903887099ff2014ea9020333',
    deps = [
      '@jackson_core//jar',
      '@jackson_annotations//jar',
    ],
  )

  maven_jar(
    name = 'jackson_core',
    artifact = 'com.fasterxml.jackson.core:jackson-core:2.6.5',
    sha1 = '334369d7eff497f358b248c171dac0dd62c68f67',
  )

  maven_jar(
    name = 'jackson_annotations',
    artifact = 'com.fasterxml.jackson.core:jackson-annotations:2.6.0',
    sha1 = 'a0990e2e812ac6639b6ce955c91b13228500476e',
  )

  maven_jar(
    name = 'jackson_module_afterburner',
    artifact = 'com.fasterxml.jackson.module:jackson-module-afterburner:2.6.5',
    sha1 = 'b0dcd6af98fe599aa98c59bef27cbabbba7bef51',
    deps = [
      '@jackson_core//jar',
      '@jackson_annotations//jar',
    ],
  )
