package fscoward.reader

import org.apache.commons.io.FileUtils
import java.io.File

/**
 * Created by FScoward on 2014/07/23.
 */
case class Log(title: String, data: String)
object LogReader {
  /**
   * ログファイルの読み込み
   *
   * @param filename 読み込み対象のファイル名
   * @return ファイルの中身
   * */
  def readLog(filename: String, encode: String = "utf-8"): String = {
    FileUtils.readFileToString(new File(filename), encode)
  }

  /**
   * ディレクトリ内のログファイルのリストを取得する
   *
   * @param dir ディレクトリ
   * */
  def getLogFileList(dir: String): Iterator[File] = {
    import scala.collection.JavaConversions._
    FileUtils.iterateFiles(new File(dir), Array("log"), true)
  }

  def format(source: String) = {
    "<p>" + source + "</p>"
  }
}
