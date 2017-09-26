import java.io.File

import com.twitter.finagle.Http
import com.twitter.finagle.http.service.{NotFoundService, RoutingService}
import com.twitter.finagle.ssl.server.SslServerConfiguration
import com.twitter.finagle.ssl.{ClientAuth, KeyCredentials, TrustCredentials}
import com.twitter.util.Await
import com.twitter.finagle.http.Method._
import com.twitter.finagle.http.path._
import jp.cormo.apagle.service.{HostMetaService, MainService, WebfingerService}

object RouteApp extends App {
  val printFilter = new PrintFilter()
  val rootService = RoutingService.byMethodAndPathObject {
    case Get -> Root => new MainService()
    case Get -> Root / ".well-known" / "webfinger" => new WebfingerService()
    case Get -> Root / ".well-known" / "host-meta" => new HostMetaService()
    case Get -> Root / "users" / _ => new MainService()
    case _ => NotFoundService
  }

  val server = Http
    .server
    .configured(Http.Netty4Impl)
    .withTransport
    .tls(SslServerConfiguration(
      trustCredentials = TrustCredentials.Insecure,
      clientAuth = ClientAuth.Off,
      keyCredentials = KeyCredentials.CertAndKey(
          new File("g:/develop/apagle/self.crt"), new File("g:/develop/apagle/self.key_")
        )
      )
    )
    .serve(":8443", printFilter.andThen(rootService))

  Await.ready(server)

}
