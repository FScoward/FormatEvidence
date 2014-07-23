package fscoward

import fscoward.file.writer.HtmlWriter
import fscoward.reader.{Log, LogReader}
import com.typesafe.config.ConfigFactory
import fscoward.config.Config

/**
 * Created by FScoward on 2014/07/23.
 */
object Main {
  def main(args: Array[String]): Unit = {

    val config: Config = init

    val logList = LogReader.getLogFileList(config.logDir)
    val logs: List[Log] = logList.map(log => (Log(log.getName ,LogReader.readLog(log.getAbsolutePath)))).toList


    HtmlWriter.mkHtml("テストケース1", logs = Option(logs))
  }

  def init = {
    try {
      val config = ConfigFactory.load
      val logDir = config.getString("directory.log")
      val logEncode = config.getString("logEncode")
      Config(logDir, logEncode)
    } catch {
      case e: Exception => println("failed to initialize"); sys.exit()
    }
  }

}
