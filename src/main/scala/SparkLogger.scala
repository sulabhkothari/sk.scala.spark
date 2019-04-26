trait SparkLogger {
  def value = ConfForExecutors.confOfData
}

object OLogger extends SparkLogger

object ConfForExecutors {
  var confOfData = false
}