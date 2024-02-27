import jakarta.servlet.{ ServletContainerInitializer, ServletContext }

class ScalatraInitializer extends ServletContainerInitializer {
  override def onStartup(set: java.util.Set[Class[_]], ctx: ServletContext): Unit =
    new ScalatraBootstrap()
}
