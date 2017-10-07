package jp.cormo.apagle

import java.io.File

import com.twitter.finagle.Http
import com.twitter.finagle.http.Method._
import com.twitter.finagle.http.path._
import com.twitter.finagle.http.service.RoutingService
import com.twitter.finagle.ssl.server.SslServerConfiguration
import com.twitter.finagle.ssl.{ClientAuth, KeyCredentials, TrustCredentials}
import com.twitter.util.Await
import io.finch.jackson._
import jp.cormo.apagle.endpoint.ActorEndpoint
import jp.cormo.apagle.service.{HostMetaService, MainService, WebfingerService}

object RouteApp extends App {
  val printFilter = new PrintFilter
  val rootService = RoutingService.byMethodAndPathObject {
    case Get -> Root => new MainService
    case Get -> Root / ".well-known" / "webfinger" => new WebfingerService
    case Get -> Root / ".well-known" / "host-meta" => new HostMetaService
    case _ => ActorEndpoint().toService
  }

  val server = Http
    .server
    .configured(Http.Netty4Impl)
    .withTransport
    .tls(SslServerConfiguration(
      trustCredentials = TrustCredentials.Insecure,
      clientAuth = ClientAuth.Off,
      keyCredentials = KeyCredentials.CertAndKey(
          new File(Config.serverCertPath), new File(Config.serverKeyPath)
        )
      )
    )
    .serve(Config.hostname, printFilter.andThen(rootService))
  println(Config.hostname, Config.serverCertPath, Config.serverKeyPath)
  Await.ready(server)

}
