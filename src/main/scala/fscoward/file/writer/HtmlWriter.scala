package fscoward.file.writer

/**
 * Created by FScoward on 2014/07/23.
 */
object HtmlWriter {

  /**
   * imgタグの作成
   *
   * @example <img src="xxx.jpg">
   *
   * @param imgPath 画像の絶対パス
   * */
  def mkImgTag(imgPath: String) = {
    "<img src=" + imgPath + ">"
  }

  def mkHtml(title: String, imgs: List[String]) = {
    s"""
      |<html>
      |  <head>
      |    <title>$title</title>
      |  </head>
      |  <body>
      |
      |  </body>
      |</html>
    """.stripMargin
  }
}
