package jp.cormo.apagle.service

import java.io.{ByteArrayOutputStream, OutputStreamWriter}

import com.twitter.finagle.Service
import com.twitter.finagle.http.{Request, Response, Status}
import com.twitter.io.Buf
import com.twitter.util.Future

import scala.xml.XML
import scala.xml.dtd.DocType

class HostMetaService extends Service[Request, Response] {
  override def apply(request: Request): Future[Response] = {
    val templateString = "https://192.168.0.3:8443/.well-known/webfinger?resource={uri}"
    val responseXml =
      <XRD xmlns="http://docs.oasis-open.org/ns/xri/xrd-1.0">
        <Link rel="lrdd" type="application/xrd+xml" template={ templateString }/>
      </XRD>

    val stream = new ByteArrayOutputStream()
    val writer = new OutputStreamWriter(stream)
    XML.write(writer, responseXml, enc = "utf-8", xmlDecl = true, doctype = DocType("xml"))
    writer.flush()

    val response = Response(request.version, Status.Ok)
    response.content(Buf.ByteArray.apply(stream.toByteArray:_*))

    writer.close()

    Future.value(response)
  }
}
