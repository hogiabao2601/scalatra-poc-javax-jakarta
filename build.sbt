ThisBuild / scalaVersion := "2.12.17"
ThisBuild / organization := "bao.ho"

lazy val commonSettings = Seq(
  name    := "My Scalatra Web App",
  version := "0.1.0-SNAPSHOT",
  scalacOptions ++= Seq("-Ylog-classpath")
)

val env: Option[String] = sys.props.get("env")

val isJakarta = env match {
  case Some(environment) if environment == "jakarta" => true
  case _                                             => false
}

val scalatraV = if (isJakarta) "3.0.0-M2" else "2.8.2"

lazy val javaxProject = (project in file("javax-project"))
  .settings(commonSettings *)
  .enablePlugins(JettyPlugin)
  .settings(
    libraryDependencies ++= Seq(
      "org.scalatra"     %% "scalatra"           % scalatraV,
      "org.scalatra"     %% "scalatra-scalatest" % scalatraV % "test",
      "javax.servlet"     % "javax.servlet-api"  % "4.0.1",
      "org.eclipse.jetty" % "jetty-webapp"       % "9.2.15.v20160210"
    )
  )
  .dependsOn(app % "compile->compile;test->test")

lazy val jakartaProject = (project in file("jakarta-project"))
  .settings(commonSettings *)
  .enablePlugins(WebappPlugin)
  .settings(
    libraryDependencies ++= Seq(
      "org.scalatra"           %% "scalatra"            % scalatraV,
      "org.scalatra"           %% "scalatra-scalatest"  % scalatraV % "test",
      "jakarta.servlet"         % "jakarta.servlet-api" % "5.0.0",
      "org.apache.tomcat.embed" % "tomcat-embed-core"   % "10.0.23"
    )
  )
  .dependsOn(app % "compile->compile;test->test")

lazy val app = (project in file("app"))
  .settings(commonSettings *)
  .enablePlugins(WebappPlugin)
  .settings(
    libraryDependencies ++= Seq(
      "org.scalatra" %% "scalatra"           % scalatraV,
      "org.scalatra" %% "scalatra-scalatest" % scalatraV % "test"
    ) ++ (if (isJakarta)
            Seq(
              "jakarta.servlet"         % "jakarta.servlet-api" % "5.0.0",
              "org.apache.tomcat.embed" % "tomcat-embed-core"   % "10.0.23"
            )
          else
            Seq(
              "javax.servlet"           % "javax.servlet-api"   % "4.0.1",
              "org.eclipse.jetty"       % "jetty-webapp"        % "9.2.15.v20160210"
            ))
  )

lazy val root = (project in file("."))
  .settings(commonSettings *)
//  .aggregate(app, javaxProject, jakartaProject)
