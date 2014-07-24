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
      |<div class="panel panel-default">
      |  <div class="panel-heading">
      |    <h4 class="panel-title">
      |      <a data-toggle="collapse" data-parent="#accordion" href="#$title">
      |        $title
      |      </a>
      |    </h4>
      |  </div>
      |  <div id="$title" class="panel-collapse collapse">
      |    <div class="panel-body">
      |      ${data.replaceAll("\\r\\n|\\r|\\n", "<br>")}
      |    </div>
      |  </div>
      |</div>
    """.stripMargin
  }

  /**
   * ログ領域のHTML組み立て
   *
   * @param logs ログのリスト
   * */
  private def mkLogSection(logs: List[Log]) = {
    logs.map(log => mkLogSource(log.title.replaceAll(".log", "_log"), log.data)).mkString
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
      |<!DOCTYPE html>
      |<html lang="ja">
      |  <head>
      |    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      |    <title>$title</title>
      |    <link rel="stylesheet" type="text/css" href="./src/main/resources/bootstrap-3.2.0-dist/css/bootstrap.css">
      |    <script type="text/javascript" src="./src/main/resources/bootstrap-3.2.0-dist/js/jquery-2.1.1.js"></script>
      |    <script type="text/javascript" src="./src/main/resources/bootstrap-3.2.0-dist/js/bootstrap.js"></script>
      |  </head>
      |  <body>
      |  <div class="well">$title</div>
      |  <div class="container">
      |    <div class="panel-group" id="accordion">
             $logSection
      |    </div>
      |  </div>
      |  </body>
      |</html>
    """.stripMargin

    // htmlファイル出力
    FileUtils.writeStringToFile(new File(title + ".html"), source, "UTF-8")
  }
}
