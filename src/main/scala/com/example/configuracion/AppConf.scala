package com.example.configuracion

import com.typesafe.config.ConfigFactory

import scala.util.Try

object AppConf {

  val config = ConfigFactory.load()

  lazy val port: Int = Try(System.getProperty("Port").toInt).getOrElse(5353)

  lazy val bdActiva: String = Try(System.getProperty("bd.activa").toString).getOrElse(
    Try(config.getString("bd.activa")).getOrElse("h2"))

  lazy val bdProfile: String = Try(System.getProperty(s"bd.${bdActiva}.profile").toString).getOrElse(
    Try(config.getString(s"bd.${bdActiva}.profile")).getOrElse(config.getString("bd.h2.profile")))

  lazy val bdUrl: String = Try(System.getProperty(s"bd.${bdActiva}.url").toString).getOrElse(
    Try(config.getString(s"bd.${bdActiva}.url")).getOrElse(config.getString("bd.h2.url")))

  lazy val suraURLmediatype: String = Try(System.getProperty("suraURLmediatype").toString).getOrElse("https://localhost.suranet.com/conceptos")

  lazy val lucenePath: String = Try(System.getProperty("lucene.path").toString).getOrElse(Try(config.getString("lucene.path")).getOrElse("lucene"))

  lazy val archivoEventos: String = Try(System.getProperty("eventos.path").toString).getOrElse(Try(config.getString("eventos.path")).getOrElse("eventos.txt"))

  lazy val archivoSql: String = "crearBd.sql"

  lazy val listaOrigin: List[String] = Try(System.getProperty("origins").toString).getOrElse(
    Try(config.getString("origins")).getOrElse("*")).split(",").toSet.toList

}
