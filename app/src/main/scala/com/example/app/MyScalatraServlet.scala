package com.example.app

import org.scalatra._

class MyScalatraServlet extends ScalatraServlet {

  get("/") {
    "Hello"
  }

}
