package com.sist.model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.sist.controller.RequestMapping;

public class SeoulModel {
  @RequestMapping("seoul/weather.do")
  public String seoul_weather(HttpServletRequest request,
		  HttpServletResponse response)
  {
	  try
	  {
		  Document doc=Jsoup.connect("https://korean.visitseoul.net/weather").get();
		  Element section=doc.selectFirst("section#content");
		  String html="<section id=\"content\">";
		  html+=section.html();
		  html+="</section>";
		  html=html.replace("src=\"", "src=\"https://korean.visitseoul.net");
		  html=html.replace("제공 : 케이웨더(Kweather)", "");
		  request.setAttribute("html", html);
	  }catch(Exception ex) {}
	  request.setAttribute("main_jsp", "../seoul/weather.jsp");
	  return "../main/main.jsp";
  }
}
