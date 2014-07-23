package fscoward.file.writer

import org.apache.commons.io.FileUtils
import java.io.File
import fscoward.reader.Log

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

  /**
   * 個々のログ出力領域のHTML組み立て
   *
   * @param title ログファイル名
   * @param data ファイル内容
   * */
  private def mkLogSource(title: String, data: String) = {
    s"""
      |<h3>$title</h3>
      |<div class='log' id='#$title'>
      |  ${data.replaceAll("\\r\\n|\\r|\\n", "<br>")}
      |</div>
    """.stripMargin
  }

  /**
   * ログ領域のHTML組み立て
   *
   * @param logs ログのリスト
   * */
  private def mkLogSection(logs: List[Log]) = {
    logs.map(log => mkLogSource(log.title, log.data)).mkString
  }

  /**
   * @param title テストケース名
   * @param imgs 画像リスト
   * @param logs ログリスト
   * */
  def mkHtml(title: String, imgs: Option[List[String]] = None, logs: Option[List[Log]] = None) = {
    // todo
    val logSection = mkLogSection(logs.get) // よろしくない

    val source = s"""
      |<html>
      |  <head>
      |    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      |    <title>$title</title>
      |  </head>
      |  <body>
         $logSection
      |  </body>
      |</html>
    """.stripMargin

    // htmlファイル出力
    FileUtils.writeStringToFile(new File(title + ".html"), source, "UTF-8")
  }
}
