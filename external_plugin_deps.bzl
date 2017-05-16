load("//tools/bzl:maven_jar.bzl", "maven_jar")

def external_plugin_deps():
  maven_jar(
    name = 'metrics_elasticsearch_reporters',
    artifact = 'org.elasticsearch:metrics-elasticsearch-reporter:2.2.0',
    sha1 = '85d3e2f0123df56795e5d925ec2c7b4fd2556792',
    deps = [
      '@jackson_databind//jar',
      '@jackson_core//jar',
    ],
  )

  maven_jar(
    name = 'jackson_databind',
    artifact = 'com.fasterxml.jackson.core:jackson-databind:2.2.3',
    sha1 = '03ae380888029daefb91d3ecdca3a37d8cb92bc9',
    deps = [
      '@jackson_core//jar',
      '@jackson_annotations//jar',
    ],
  )

  maven_jar(
    name = 'jackson_core',
    artifact = 'com.fasterxml.jackson.core:jackson-core:2.2.3',
    sha1 = '1a0113da2cab5f4c216b4e5e7c1dbfaa67087e14',
  )

  maven_jar(
    name = 'jackson_annotations',
    artifact = 'com.fasterxml.jackson.core:jackson-annotations:2.2.3',
    sha1 = '0527fece4f23a457070a36c371a26d6c0208e1c3',
  )
