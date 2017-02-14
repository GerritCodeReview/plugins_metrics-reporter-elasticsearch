load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "metrics-reporter-elasticsearch",
    srcs = glob(["src/main/java/**/*.java"]),
    resources = glob(["src/main/resources/**/*"]),
    manifest_entries = [
        "Gerrit-PluginName: metrics-reporter-elasticsearch",
    ],
    deps = [
        "@dropwizard_core//jar",
        "@metrics_elasticsearch_reporters//jar",
        "@jackson_databind//jar",
        "@jackson_core//jar",
    ],
)
