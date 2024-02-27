import com.example.app.MyScalatraServlet
import javax.servlet.ServletContext
import org.scalatra._
import org.scalatra.servlet.RichServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    RichServletContext(context).mount(new MyScalatraServlet, "/hello")
  }
}
