resolvers += {
    val typesafeRepoUrl = new java.net.URL ("http://typesafe.artifactoryonline.com/typesafe/ivy-releases/")
    val pattern = Patterns (false,
        "[organisation]/[module]/[revision]/[type]s/[module](-[classifier]).[ext]")
    Resolver.url ("Typesafe Repository", typesafeRepoUrl) (pattern)
}

seq (giter8Settings : _*)
