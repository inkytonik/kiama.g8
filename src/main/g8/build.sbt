name := "$name$"

version := "$version$"

organization := "$organization$"

// Scala compiler settings

scalaVersion := "$scalaversion$"

scalacOptions ++= Seq ("-deprecation", "-feature", "-unchecked")

// Interactive settings

logLevel := Level.Info

shellPrompt in ThisBuild := {
    state =>
        Project.extract(state).currentRef.project + " " + version.value +
            " " + scalaVersion.value + "> "
}

// Fork the runs and connect sbt's input and output to the forked process so
// that we are immune to version clashes with the JLine library used by sbt

fork in run := true

connectInput in run := true

outputStrategy in run := Some (StdoutOutput)

// Dependencies

libraryDependencies ++=
    Seq (
        "org.bitbucket.inkytonik.kiama" %% "kiama" % "2.5.0",
        "org.bitbucket.inkytonik.kiama" %% "kiama-extras" % "2.5.0",
        "org.bitbucket.inkytonik.kiama" %% "kiama-extras" % "2.5.0" % "test" classifier ("tests"),
        "org.scalatest" %% "scalatest" % "3.2.9" % "test",
        "org.scalacheck" %% "scalacheck" % "1.15.4" % "test",
        "org.scalatestplus" %% "scalacheck-1-15" % "3.2.9.0" % "test"
    )

resolvers ++= Seq (
    Resolver.sonatypeRepo ("releases"),
    Resolver.sonatypeRepo ("snapshots")
)
