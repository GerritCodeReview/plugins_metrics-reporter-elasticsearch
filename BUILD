load("//tools/bzl:plugin.bzl", "gerrit_plugin")

gerrit_plugin(
    name = "metrics-reporter-elasticsearch",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: metrics-reporter-elasticsearch",
    ],
    resources = glob(["src/main/resources/**/*"]),
    deps = [
        "@metrics_elasticsearch_reporters//jar",
    ],
)
