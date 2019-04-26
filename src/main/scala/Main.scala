object Main {
  import org.apache.spark.SparkContext
  import org.apache.spark.SparkConf
  import org.apache.spark.sql.SparkSession


  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("main").setMaster("local")
    val sc = new SparkContext(conf)
    val spark = SparkSession.builder.config(sc.getConf).getOrCreate()
    import spark.implicits._

    val df = Seq((1, "abc"),(2, "bcd"),(3,"cde")).toDF("id","name")

    ConfForExecutors.confOfData = false

    df.filter(row => (new SparkLogger {}).value).show

    ConfForExecutors.confOfData = true

    df.map(_.get(1).toString.toUpperCase).filter(row => (new SparkLogger {}).value).show

    ConfForExecutors.confOfData = false

    df.map(_.get(1).toString.toUpperCase).filter(row => OLogger.value).show
  }


}
