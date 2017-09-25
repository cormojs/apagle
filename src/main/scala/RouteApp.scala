import java.io.{BufferedInputStream, FileInputStream}
import java.security.{KeyStore, Security}
import javax.net.ssl.{KeyManagerFactory, SSLContext}

import com.twitter.finagle.Http
import com.twitter.finagle.ssl.{CipherSuites, ClientAuth, KeyCredentials, TrustCredentials}
import com.twitter.finagle.ssl.server.SslServerConfiguration
import com.twitter.util.Await
import java.io.File

object RouteApp extends App {

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
    .serve(":8443", new MainService())

  Await.ready(server)

}
